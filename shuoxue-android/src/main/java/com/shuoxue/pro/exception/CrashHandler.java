package com.shuoxue.pro.exception;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.liuxiong.library.log.XLog;
import com.shuoxue.pro.kit.AppKit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.Properties;


/**
 * 该类封装 系统对全局异常的处理
 * 
 * 以及对异常文件的上传等
 * 
 * UncaughtExceptionHandler只能处理unchecked异常 不能处理checked异常 如NullPointerException
 * 
 * */
public class CrashHandler implements UncaughtExceptionHandler {
	public static final String TAG = "CrashHandler";
	/**
	 * 是否开启日志输出开关
	 * */
	public static final boolean DEBUG = true;
	/**
	 * CrashHandler实例
	 * */
	private static CrashHandler INSTANCE;
	/**
	 * 版本名
	 * */
	private static final String VERSION_NAME = "versionName";
	/**
	 * 版本号
	 * */
	private static final String VERSION_CODE = "versionCode";
	/**
	 * 异常堆栈信息
	 * */
	private static final String STACK_TRACE = "STACK_TRACE";

	/**
	 * 系统默认的UncaughtException处理类
	 * */
	private UncaughtExceptionHandler mDefaultHandler;

	/**
	 * 程序的全局Context对象
	 * */
	private Context mContext;
	/**
	 * 使用Properties来保存设备的信息和错误堆栈信息
	 * */
	private Properties mDeviceCrashInfo = new Properties();

	/**
	 * 错误报告文件的扩展名
	 * */
	private static final String CRASH_REPORTER_EXTENSION = ".log";

	/**
	 * 保证只有一个CrashHandler实例
	 * */
	private CrashHandler() {
	}

	/**
	 * 获取CrashHandler实例 ,单例模式
	 * */
	public static CrashHandler getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CrashHandler();
		}
		return INSTANCE;
	}

	/**
	 * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
	 * 
	 * @param ctx
	 */
	public void init(Context ctx) {
		mContext = ctx;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		XLog.d("uncaughtException --- >> ");
		if (!handleException(ex) && mDefaultHandler != null) {
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			/**
			 * Sleep一会后结束程序
			 * */
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/**
			 * 直接退出程序
			 * */
//			 android.os.Process.killProcess(android.os.Process.myPid());
//			 System.exit(10);

//			// 重新启动程序
//			Intent intent = new Intent();
//			intent.setClass(mContext, LauncherActivity.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			mContext.startActivity(intent);
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}

	/**
	 * 异常处理方法
	 * 
	 * @param ex
	 * @return true:已处理该异常信息 否则返回false
	 */
	private boolean handleException(Throwable ex) {
		Log.e("ERROR", ex.toString());
		/**
		 * 异常类为空时 直接返回交给默认处理器处理
		 * */
		if (ex == null) {
			return false;
		}
		final String msg = getErrorInfo(ex);
		if (msg == null) {
			return false;
		} else {
			// 使用Toast来显示异常信息
			new Thread() {
				@Override
				public void run() {
					Looper.prepare();
//					Toast toast = Toast.makeText(mContext,
//							"非常抱歉程序发生异常，即将退出！", Toast.LENGTH_LONG);
					Toast toast = Toast.makeText(mContext,
							msg, Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
//					dialog(msg);
					Looper.loop();
				}
			}.start();
			sendCrashReportsToServer(AppKit.getVersion(), getErrorInfo(ex), "");
			sendCrashReportsToServer(mContext, ex);
			return true;
		}
	}

	/**
	 * 发送错误信息
	 * 
	 * @author 刘雄 2014-12-4 上午10:33:42
	 * @param
	 * @return void
	 */
	private void sendCrashReportsToServer(String version , String content ,String extra_infofinal) {
		//TODO 将错误报告发送至服务器
		XLog.e("sendCrashReportsToServer=" + extra_infofinal);
//		dialog(content);

//		debug(content);
//
//		if(isExist(getRootPath()))
//			createCacheFile(getRootPath() , content);
	}

	private void sendCrashReportsToServer(Context context , Throwable ex) {
//		MobclickAgent.reportError(context, ex);

	}



	/**
	 * 取出异常信息
	 * 
	 * @author 刘雄 2014-12-4 上午9:30:36
	 * @param arg1
	 * @return
	 * @return String
	 */
	private String getErrorInfo(Throwable arg1) {
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		arg1.printStackTrace(pw);
		pw.close();
		String error = writer.toString();
		String deviceInfo = collectCrashDeviceInfo(mContext);

		StringBuffer sb = new StringBuffer();
		sb.append(error);
		sb.append("\n\t");
		sb.append(deviceInfo);
		Log.e("ERROR INFO", sb.toString());
		return sb.toString();
	}

	/**
	 * 收集程序崩溃的设备信息
	 * 
	 * @param ctx
	 */
	public String collectCrashDeviceInfo(Context ctx) {
		try {
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (pi != null) {
				mDeviceCrashInfo.put(VERSION_NAME,
						pi.versionName == null ? "not set" : pi.versionName);
				mDeviceCrashInfo.put(VERSION_CODE, "" + pi.versionCode);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		/**
		 * 使用反射来收集设备信息.在Build类中包含各种设备信息, Build.BOARD -- 主板    
		 * 2. Build.BRAND-- android系统定制商     3. Build.CPU_ABI-- cpu指令集    
		 * 4. Build.DEVICE -- 设备参数     5. Build.DISPLAY -- 显示屏参数    
		 * 6. Build.FINGERPRINT -- 硬件名称     7. Build.HOST    
		 * 8. Build.ID -- 修订版本列表     9. Build.MANUFACTURER -- 硬件制造商    
		 * 10. Build.MODEL -- 版本     11. Build.PRODUCT -- 手机制造商    
		 * 12. Build.TAGS -- 描述build的标签     13. Build.TIME    
		 * 14. Build.TYPE -- builder类型     15. Build.USER
		 * */

		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				mDeviceCrashInfo.put(field.getName(), "" + field.get(null));
				if (DEBUG) {
					Log.d(TAG, field.getName() + " : " + field.get(null));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mDeviceCrashInfo.toString();
	}

	
	
	protected void dialog(String msg) {
		Builder builder = new Builder(mContext);
		builder.setMessage(msg);
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}



	private File createCacheFile(String path , String content){
		String fileName = path + "/" + System.currentTimeMillis() + ".crach";
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
//		writerToCSV(fileName, content);

		return file;
	}


//	private void writerToCSV(String fileName, String content) {
//		CsvWriter writer = new CsvWriter(fileName);
//		try {
//			writer.write(content);
//		}catch (IOException exception){
//			exception.printStackTrace();
//		}finally {
//			if(writer != null)
//				writer.close();
//		}
//	}


//	private String filePath = GlobalKit.getExternalStorageStatePath();
//	private String getRootPath(){
//		UserToken ut = LXApplication.getInstance().getUserToken();
//		if(ut == null){
//			GlobalKit.error("cacheBitmap UserToken is null ！！！ ");
//			return "";
//		}
//		String fileName = filePath + ut.getUserId() + "/crach";
//		return fileName;
//	}

	/**
	 * 判断指定文件夹路径是否存在
	 * @param path
	 * @return
	 */
	private boolean isExist(String path) {
		File file = new File(path);
		//判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) {
			XLog.d("缓存路径不存在，创建文件路径");
			return file.mkdirs();
		}
		XLog.d("缓存路径创建成功");
		return true;
	}




//	private void debug(String str){
//		UserAPI.debugApi(str, new Response.Listener<String>() {
//			@Override
//			public void onResponse(String response) {
//				GlobalKit.debug("发送奔溃日志成功");
//			}
//		}, new Response.ErrorListener() {
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				GlobalKit.debug("发送奔溃日志失败");
//			}
//		});
//	}




	// ---------------------------------------------------------------------------------//

	// /**
	// * 保存错误信息到文件中
	// *
	// * @param ex
	// * @return
	// */
	// private String saveCrashInfoToFile(Throwable ex) {
	// Writer info = new StringWriter();
	// PrintWriter printWriter = new PrintWriter(info);
	// ex.printStackTrace(printWriter);
	// Throwable cause = ex.getCause();
	// while (cause != null) {
	// cause.printStackTrace(printWriter);
	// cause = cause.getCause();
	// }
	// String result = info.toString();
	// printWriter.close();
	// mDeviceCrashInfo.put("EXEPTION", ex.getLocalizedMessage());
	// mDeviceCrashInfo.put(STACK_TRACE, result);
	// try {
	// Time t = new Time("GMT+8");
	// t.setToNow(); // 取得系统时间
	// int date = t.year * 10000 + (t.month + 1) * 100 + t.monthDay;
	// int time = t.hour * 10000 + t.minute * 100 + t.second;
	// String fileName = "genius-" + date + "-" + time
	// + CRASH_REPORTER_EXTENSION;
	// FileOutputStream trace = mContext.openFileOutput(fileName,
	// Context.MODE_PRIVATE);
	// mDeviceCrashInfo.store(trace, "");
	// trace.flush();
	// trace.close();
	// return fileName;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// /**
	// * 发送早期未发送报告 在程序启动时候, 可以调用该函数来发送以前没有发送的报告
	// */
	// public void sendPreviousReportsToServer() {
	// sendCrashReportsToServer(mContext);
	// }
	//
	// /**
	// * 把错误报告发送给服务器,包含新产生的和以前没发送的.
	// *
	// * @param ctx
	// */
	// private void sendCrashReportsToServer(Context ctx) {
	// String[] crFiles = getCrashReportFiles(ctx);
	// if (crFiles != null && crFiles.length > 0) {
	// TreeSet<String> sortedFiles = new TreeSet<String>();
	// sortedFiles.addAll(Arrays.asList(crFiles));
	// for (String fileName : sortedFiles) {
	// File cr = new File(ctx.getFilesDir(), fileName);
	// postReport(cr);
	// cr.delete();// 删除已发送的报告
	// }
	// }
	// }
	//
	// /**
	// * 获取错误报告文件名
	// *
	// * @param ctx
	// * @return
	// */
	// private String[] getCrashReportFiles(Context ctx) {
	// File filesDir = ctx.getFilesDir();
	// FilenameFilter filter = new FilenameFilter() {
	// public boolean accept(File dir, String name) {
	// return name.endsWith(CRASH_REPORTER_EXTENSION);
	// }
	// };
	// return filesDir.list(filter);
	// }
	//
	// private void postReport(File file) {
	// // TODO 发送错误报告到服务器
	//
	// }

}

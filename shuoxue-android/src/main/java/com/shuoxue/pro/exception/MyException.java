package com.shuoxue.pro.exception;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Toast;

import com.liuxiong.library.log.XLog;
import com.shuoxue.pro.R;
import com.shuoxue.pro.ui.LoginActivity;

import me.drakeet.materialdialog.MaterialDialog;


public abstract class MyException extends Exception{
	Context mContext;
	Handler uiHandler;
	
	
	public MyException(){
		 HandlerThread localHandlerThread = new HandlerThread("MyException");
	     localHandlerThread.start();
	     this.uiHandler = new Handler(localHandlerThread.getLooper());
	}
	
	/**
	 * Handler 负责更新UI界面 （显示提示框等）
	 */
	
	

	/**
	 * 显示提示对话框 （单个按钮）
	 * @author 刘雄   2014-8-19 下午4:03:18
	 * @param msg
	 * @return void
	 */
	public void showHintToast(Context context , String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	

	/**
	 * 显示提示对话框 （单个按钮）
	 * @author 刘雄   2014-8-19 下午4:03:18
	 * @param msg
	 * @return void
	 */
	public void showNoLoginAlertDialog(final Context context , String msg){
		final MaterialDialog mMaterialDialog = new MaterialDialog(context);
		mMaterialDialog.setTitle(context.getResources().getString(R.string.solutionDialogTitle));
		mMaterialDialog.setMessage(msg);
		mMaterialDialog.setPositiveButton(context.getResources().getString(R.string.common_sure), new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLoginActivity(context);
				mMaterialDialog.dismiss();
			}
		});
		mMaterialDialog.setNegativeButton(context.getResources().getString(R.string.common_cancel), new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mMaterialDialog.dismiss();
			}
		});
		mMaterialDialog.show();
	}
	
	
	private void startLoginActivity(Context context){
		XLog.d("跳转至登录界面！");
		Activity activity = (Activity) context;
		LoginActivity.launch(activity);
		activity.finish();
	}
	
	
	
	/**
	 * 接收异常
	 * @author 刘雄   2014-10-28 上午10:41:22
	 * @param e
	 * @return void
	 */
	protected abstract void handleException(Context mContext ,Object e);
	/**
	 * 处理异常
	 * @author 刘雄   2014-10-28 下午2:20:52
	 * @param mContext
	 * @param e
	 * @return void
	 */
	protected abstract void disposeException(Context mContext ,Object e);
	
	/**
	 * 发送处理后的异常信息
	 * @author 刘雄   2014-10-28 上午10:41:34
	 * @param
	 * @return void
	 */
	protected abstract void sendErrorMessage(int what , String message);
}

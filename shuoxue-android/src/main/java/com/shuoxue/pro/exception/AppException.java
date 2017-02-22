package com.shuoxue.pro.exception;

import android.app.Application;
import android.content.Context;

import com.liuxiong.library.log.XLog;
import com.shuoxue.pro.model.BaseResultInfo;

/**
 * 系统顶级异常 <br>
 * 所有异常信息均由此类进入 并 由此确定异常流向 100 <Exception> <br>
 * 
 * 1000 <filter中直接返回> <br>
 * 1001 ServiceBusyException <br>
 * 1004 LoginAuthException <br>
 * 1005 InvalidParamException <br>
 * 1006 NotFoundObjectException <br>
 * 1007 DuplicatedOperationException <br>
 * 1008 NotQualifiedOperationException <br>
 * 1009 InvalidAccountException <br>
 * 1010 AccountRegisteredException <br>
 * 1011 NotMatchedTypeException <br>
 * 1012 NotMoreObjectException <br>
 * 
 * @author Administrator
 * 
 */
public class AppException {
	// 超时
	public static final int TimeoutError = 2000;
	// 网络错误
	public static final int NetworkError = 2001;
	// 连接异常
	public static final int NoConnectionError = 2002;


	// 未登录异常
	public static final int NotLoginYetException = 401;

	// 服务武器异常
	public static final int ServiceException = 500;

	// 登录异常（用户名或密码错误）
	public static final int LoginAuthException = 1000;
	// 服务器忙  验证错误（注册短信验证码时验证错误、修改密码时密码验证错误）
	public static final int ServiceBusyException = 1001;
	// 重复操作异常
	public static final int DuplicatedOperationException = 1002;
	// 申请的用户名已被注册
	public static final int AccountRegisteredException = 1003;
	// 未发现指定资源
	public static final int NotFoundObjectException = 1004;

	// 请求参数错误
	public static final int InvalidParamException = 1005;
//	// 未发现请求资源
//	public static final int NotFoundObjectException = 1006;
	// 短信验证码错误
	public static final int InvalidSMSCodeException = 1015;
	// 未知异常
	public static final int UnKnownException = 1016;




	// 服务异常
	private final int TYPE_SERVE_EC = 1;
	// 系统异常
	private final int TYPE_SYSTEM_EC = 2;

	private int mEC_Type = TYPE_SYSTEM_EC;

	private static AppException appException;

	public AppException() {
	}

	/**
	 * 
	 */
	public static AppException getInstance() {
		if (appException != null) {
			return appException;
		}
		return appException = new AppException();
	}

	/**
	 * 接收异常
	 * 
	 * @author 刘雄 2014-9-25 上午11:56:28
	 * @param mContext 不接受ApplicationContext
	 * @return void
	 */
	public void handleException(Context mContext, Exception e) {
		if(mContext instanceof Application){
			new Exception("mContext 不能为Application,仅接受Activity Context ！").printStackTrace();
		}
		printLog(e);// 打印异常
		judgmentException(mContext, e);
	}


	public void handleException(Context mContext, BaseResultInfo e) {
		if(mContext instanceof Application){
			new Exception("mContext 不能为Application,仅接受Activity Context ！").printStackTrace();
		}
		printLog(e);// 打印异常
		judgmentException(mContext, e);
	}



	/**
	 * 异常判决 确定异常类型
	 */
	private void judgmentException(Context mContext, Object e) {
		if (e instanceof BaseResultInfo) {//服务器异常
			mEC_Type = TYPE_SERVE_EC;
		}else {
			mEC_Type = TYPE_SYSTEM_EC;
		}
		distributeException(mContext, mEC_Type, e);
	}

	/**
	 * 异常分发 更具异常类型 分发至不同的类去处理<br>
	 * 
	 * @author 刘雄 2014-9-25 上午11:58:18
	 * @param type
	 *            异常类型
	 * @param e
	 * @return void
	 */
	private void distributeException(Context mContext, int type, Object e) {
		XLog.d("distributeException;type=" + type);
		if (type == TYPE_SYSTEM_EC) {
			SystemException se = new SystemException(mContext);
			se.handleException(mContext, (Exception) e);
		}
		if (type == TYPE_SERVE_EC) {
			ServeException se = new ServeException(mContext);
			se.handleException(mContext, (BaseResultInfo)e);
		}
	}

	/**
	 * 异常日志输出
	 */
	private void printLog(Exception e) {
		if(e != null) {
			e.printStackTrace();
		}
	}

	private void printLog(BaseResultInfo e) {
		if(e != null)
			XLog.e(e.toString());
	}
}

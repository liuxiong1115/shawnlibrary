package com.shuoxue.pro.exception;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.liuxiong.library.log.XLog;

import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

/**
 * 系统异常
 * 系统服务异常
 * 所有异常信息均由此类进入  并 由此确定异常流向
 * @author Administrator
 *
 */
public class SystemException  extends MyException{
	Context mContext;
	public SystemException(Context mContext ) {
		this.mContext = mContext;
		 HandlerThread localHandlerThread = new HandlerThread("MyException");
	     localHandlerThread.start();
	     this.uiHandler = new Handler(localHandlerThread.getLooper());
	}
	
	
	Handler handler = new Handler(uiHandler.getLooper()) {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 400:
					showHintToast(mContext, msg.getData().getString("mesg"));
				break;

			default:
				break;
			}
		}
	};
	
	
	/**
	 * 接收异常
	 * 
	 * @author 刘雄 2014-9-25 上午11:56:28
	 * @param mContext
	 * @return void
	 */
	public void handleException(Context mContext ,Object e) {
		disposeException(mContext , e);
	}
	
	
	@Override
	protected void disposeException(Context mContext, Object error) {
		String errorMsg = "系统异常";
		if(error instanceof TimeoutException) {
			errorMsg = "网络超时";
		}
		if(error instanceof NetworkErrorException) {
			errorMsg = "网络错误";
		}
		if(error instanceof ConnectException) {
			errorMsg = "网络异常";
		}
//		if (error instanceof ServerError) {
//			XLog.e("错误的处理位置 -- 遗产处理位置错误，该异常属于 WeiboException 不应在 SystemException 异常处理类中处理！");
//			ServeException se = new ServeException(mContext);
//			se.handleException(mContext, error);
//		}
		XLog.e(errorMsg);
		sendErrorMessage(400 , errorMsg);
	}
	
	
	/**
	 * 异常消息发送 向Handler发送解析的异常信息 并提示用户
	 */
	protected void sendErrorMessage(int what , String message) {
		Bundle bundler = new Bundle();
		Message msg = new Message();
		msg.what = what;
		bundler.putString("mesg", message);
		msg.setData(bundler);
		if (handler != null)
			handler.sendMessage(msg);
	}
}

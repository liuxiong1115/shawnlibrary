package com.shuoxue.pro.exception;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.shuoxue.pro.model.BaseResultInfo;


/**
 * 应用级异常
 * 1.应用程序内部数据异常<br>
 * 2.运行时错误<br>
 * @author Administrator
 *
 */
public class ServeException extends MyException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1734123518312734505L;
	Context mContext;
	
	
	public ServeException(Context mContext) {
		this.mContext = mContext;
		 HandlerThread localHandlerThread = new HandlerThread("MyException");
	     localHandlerThread.start();
	     this.uiHandler = new Handler(localHandlerThread.getLooper());
	}
	
	
	Handler handler = new Handler(uiHandler.getLooper()) {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case AppException.UnKnownException:
			case AppException.ServiceException:
				showHintToast(mContext, msg.getData().getString("mesg"));
				break;
				
			case AppException.NotLoginYetException:
				showNoLoginAlertDialog(mContext , msg.getData().getString("mesg"));
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
		this.mContext = mContext;
		disposeException(mContext , e);
	}
	
	
	
	@Override
	protected void disposeException(Context mContext, Object error) {
		BaseResultInfo errorMsg = (BaseResultInfo)error;
		if (errorMsg != null) {
			int errorCode = errorMsg.getCode();
			String msg = "";
//			if(XDroidConf.LOG){
				msg += errorMsg.getMessage();
//			}
			sendErrorMessage(AppException.UnKnownException, msg);

//			if (errorCode == AppException.NotLoginYetException) {
//				String msg = "";
//				if(XDroidConf.LOG){
//					msg += errorMsg.getMessage();
//				}
//				sendErrorMessage(AppException.NotLoginYetException , msg);
//			} else if(errorCode == AppException.ServiceException){
//				sendErrorMessage(AppException.ServiceException, "获取数据失败\n请稍后重试");
//			}else if(errorCode == AppException.UnKnownException){
//				sendErrorMessage(AppException.UnKnownException, "服务器异常（" + errorCode + "）");
//			}
//		}else{
//			sendErrorMessage(AppException.UnKnownException, "服务器错误");
		}
	}
	
	
	
	/**
	 * 异常消息发送 向Handler发送解析的异常信息 并提示用户
	 */
	protected void sendErrorMessage(int what, String message) {
		Bundle bundler = new Bundle();
		Message msg = new Message();
		msg.what = what;
		bundler.putString("mesg", message);
		msg.setData(bundler);
		if (handler != null)
			handler.sendMessage(msg);
	}
}

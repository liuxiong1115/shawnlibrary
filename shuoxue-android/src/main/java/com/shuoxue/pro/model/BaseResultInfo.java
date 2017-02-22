package com.shuoxue.pro.model;


import java.io.Serializable;

/**
 * 所有接口数据均为该格式返回
 * 最终返回数据为JSON格式
 * @类功能说明：  
 * @类修改者：  
 * @修改日期：  
 * @修改说明：   
 * @作者：liuxiong 
 * @创建时间：2016-12-13 上午11:50:37  
 * @版本：V1.0
 */
public class BaseResultInfo implements Serializable{

	public static final int Result_Code_Fail = -1;//未知异常，失败
	public static final int Result_Code_Ok = 0;//正确
	public static final int Result_Code_PS_Error = 1;//密码错误
	public static final int Result_Code_Locked = 2;//账户锁定
	public static final int Result_Code_Server_Error = 3;//服务器错误
	public static final int Result_Code_Parameter_Error = 4;//参数错误


	private static final long serialVersionUID = 2831483542418314864L;
	
	private int code;//返回结果代码
	private String msg;//返回结果描述

	
	public BaseResultInfo() {
		super();
	}
	
	
	
	public BaseResultInfo(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public boolean isError(){
		return !(code == Result_Code_Ok);
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return msg;
	}
	public void setMessage(String message) {
		this.msg = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BaseReturnInfo [code=" + code + ", message=" + msg + "]";
	}
	
}

package com.shuoxue.pro.model;




public class ResultToken extends BaseResultInfo {

	private UserToken obj;

	public UserToken getObj() {
		return obj;
	}

	public void setObj(UserToken obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "ReturnToken [obj=" + obj + ", isError()=" + isError()
				+ ", getCode()=" + getCode() + ", getMessage()=" + getMessage()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}

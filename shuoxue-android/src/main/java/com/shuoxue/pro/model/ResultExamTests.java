package com.shuoxue.pro.model;


import java.util.List;

public class ResultExamTests extends BaseResultInfo {
	private List<Test> obj;

	public ResultExamTests() {
		super();
	}

	public ResultExamTests(List<Test> obj) {
		super(Result_Code_Ok , "成功");
		this.obj = obj;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(List<Test> obj) {
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

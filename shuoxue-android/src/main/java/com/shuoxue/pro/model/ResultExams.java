package com.shuoxue.pro.model;


import java.util.List;

public class ResultExams extends BaseResultInfo {
	private List<Exam> obj;

	public ResultExams() {
		super();
	}
	
	public ResultExams(List<Exam> obj) {
		super(Result_Code_Ok , "成功");
		this.obj = obj;
	}

	public List<Exam> getObj() {
		return obj;
	}

	public void setObj(List<Exam> obj) {
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

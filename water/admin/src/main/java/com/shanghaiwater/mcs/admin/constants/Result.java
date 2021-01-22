package com.shanghaiwater.mcs.admin.constants;

public class Result<T> {

	//code 状态值  ：0 为成功，其他数值代表失败
	private Integer code;
	
	//msg 错误信息
	private String msg;
	
	//content 返回体报文的出参，使用泛型兼容不同的类型
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}

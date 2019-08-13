package cn.cmh.myproject.entity;

public class ResponseResult<T> {
	private Integer state = 200;
	private String msg;
	private T data;
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public ResponseResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseResult(Integer state, String msg) {
		super();
		this.state = state;
		this.msg = msg;
	}
	
	
	
	
	
	
}

package cn.cmh.myproject.entity;

import java.io.Serializable;
import java.util.Date;

public class BasicEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8122913487897822915L;
	
	private String create_user;
	private Date create_time;
	private String modified_user;
	private Date modified_time;
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public Date getModified_time() {
		return modified_time;
	}
	public void setModified_time(Date modified_time) {
		this.modified_time = modified_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "BasicEntity [create_user=" + create_user + ", create_time=" + create_time + ", modified_user="
				+ modified_user + ", modified_time=" + modified_time + "]";
	}
	
	
}

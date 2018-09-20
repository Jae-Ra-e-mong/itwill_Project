package edu.java.miniproject;

import java.sql.Date;

public class MiniCalendar {

	
	//필드 
	private Date minidate;
	private String minitext;
	
	// 생성자
	public MiniCalendar(){}

	public MiniCalendar(Date minidate, String minitext) {
		this.minidate = minidate;
		this.minitext = minitext;
	}

	
	// getter / setter
	public Date getMinidate() {
		return minidate;
	}

	public void setMinidate(Date minidate) {
		this.minidate = minidate;
	}

	public String getMinitext() {
		return minitext;
	}

	public void setMinitext(String minitext) {
		this.minitext = minitext;
	}

	@Override
	public String toString() {
		String minicalender = "MiniCalendar [minidate=" + minidate + ", minitext=" + minitext + "]";
		return minicalender;
				
	}

	
}

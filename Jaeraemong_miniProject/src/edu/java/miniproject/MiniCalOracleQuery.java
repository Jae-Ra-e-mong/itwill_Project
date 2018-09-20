package edu.java.miniproject;

public interface MiniCalOracleQuery {
	String URL = "jdbc:oracle:thin:@192.168.11.9:1521:XE";
	String USER = "scott";
	String PASSWORD = "tiger";
	
	String TBL_CALENDAR = "cal_memo";
	String CAL_DATE = "cal_date";
	String CAL_TEXT = "cal_text";

	
	String SQL_SELECT = 
			"select * from " + TBL_CALENDAR + " where " + CAL_DATE + " = ?";
	
	
	
	String SQL_SAVE = 
			"insert into " + TBL_CALENDAR
			+ " values (?, ?)";
	
	String SQL_UPDATE = 
			"update "+ TBL_CALENDAR + " set "
			+ CAL_TEXT + " = ?" + " where " + CAL_DATE + " = ?";
	
//	String SQL_CLEAR = 
//			"update " + TBL_CALENDAR
//			+" set " + CAL_TEXT + " = ?, "
//			+ " where " + CAL_DATE + " = ?";
	
	String SQL_DELETE = 
			"delete from " + TBL_CALENDAR
			+ " where "+ CAL_DATE + " = ?";
}

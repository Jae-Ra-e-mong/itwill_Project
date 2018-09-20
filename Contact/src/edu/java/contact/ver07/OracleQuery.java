package edu.java.contact.ver07;

public interface OracleQuery {
	String URL = "jdbc:oracle:thin:@192.168.11.9:1521:XE";
	String USER = "scott";
	String PASSWORD = "tiger";

	String TBL_CONTACT = "ex_contact";
	String COL_CID = "cid";
	String COL_NAME = "name";
	String COL_PHONE = "phone";
	String COL_EMAIL = "email";
	String SQL_SELECT_ALL = "select * from " + TBL_CONTACT;
	String SQL_SELECT_BY_CID = "select * from " + TBL_CONTACT + " where " + COL_CID + " = ?";
	String SQL_UPDATE = "update " + TBL_CONTACT + " set " + COL_NAME + " = ?, " + COL_PHONE + " = ?, " + COL_EMAIL
			+ " = ? " + " where " + COL_CID + " = ?";
	String SQL_INSERT = "insert into " + TBL_CONTACT + " (name, phone, email) "  
			+ " values (?, ?, ?)";
	String SQL_DELETE = "delete from " + TBL_CONTACT + " where " + COL_CID + " = ?";

}

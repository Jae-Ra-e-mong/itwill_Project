package edu.java.contact.ver07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleDriver;
import static edu.java.contact.ver07.OracleQuery.*;

public class ContactDaoImple implements ContactDao {

	private static ContactDaoImple instance = null;

	private ContactDaoImple() {
		try {
			// Oracle JDBC 드라이버를 메모리에 로딩
			DriverManager.registerDriver(new OracleDriver());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ContactDaoImple getInstance() {
		if (instance == null) {
			instance = new ContactDaoImple();

		}
		return instance;
	}

	private void closeResources(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			closeResources(conn, stmt);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<Contact> select() {
		List<Contact> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int cid = rs.getInt(COL_CID);
				String name = rs.getString(COL_NAME);
				String phone = rs.getString(COL_PHONE);
				String email = rs.getString(COL_EMAIL);
				Contact contact = new Contact(cid, name, phone, email);

				list.add(contact);

			} // end while
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}// end select()

	@Override
	public Contact select(int cid) {
		Contact contact = null;
		Connection conn = null;
		;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_SELECT_BY_CID);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString(COL_NAME);
				String phone = rs.getString(COL_PHONE);
				String email = rs.getString(COL_EMAIL);
				contact = new Contact(cid, name, phone, email);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, rs);
		}

		return contact;
	}

	@Override
	public int insert(Contact contact) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getPhone());
			pstmt.setString(3, contact.getEmail());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt);
		}

		return result;
	}

	@Override
	public int update(Contact contact) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getPhone());
			pstmt.setString(3, contact.getEmail());
			pstmt.setInt(4, contact.getCid());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt);
		}
		return result;
	}

	@Override
	public int delete(int cid) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, cid);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources(conn, pstmt);
		}
		
		
		return result;
	}

} // end class ContactDaoImple

package edu.java.miniproject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleDriver;


import static edu.java.miniproject.MiniCalOracleQuery.*;

public class MiniCalDaoImple implements MiniCalDao {

	private static MiniCalDaoImple instance = null;

	private MiniCalDaoImple() {
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MiniCalDaoImple getinstance() {
		if (instance == null) {
			instance = new MiniCalDaoImple();
		}
		return instance;
	}

	@Override
	public MiniCalendar select(Date date) {
		MiniCalendar miniCal = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_SELECT);
			pstmt.setDate(1, date);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Date minidate = rs.getDate(CAL_DATE);
				String minitext = rs.getString(CAL_TEXT);
				
				miniCal = new MiniCalendar(minidate, minitext);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return miniCal;
	}

	@Override
	public int save(MiniCalendar minicalendar) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_SAVE);
			pstmt.setDate(1, minicalendar.getMinidate());
			pstmt.setString(2, minicalendar.getMinitext());

			// pstmt.setDate(index, sqlDate);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt);
		}

		return result;
	}

	@Override
	public int clear(MiniCalendar minicalendar) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_UPDATE);
			
			pstmt.setString(1, " ");
			pstmt.setDate(2, minicalendar.getMinidate());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}finally {
			closeResources(conn, pstmt);
		}
		
		return result;
	}

	@Override
	public int update(MiniCalendar minicalendar) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_UPDATE);
			
			pstmt.setString(1, minicalendar.getMinitext());
			pstmt.setDate(2, minicalendar.getMinidate());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}finally {
			closeResources(conn, pstmt);
		}
		
		return result;
	}

	@Override
	public int delete(Date minidate) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_DELETE);
			pstmt.setDate(1, minidate);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	private void closeResources(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // end closeResources()

	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			closeResources(conn, stmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end class MiniCalDaoImple

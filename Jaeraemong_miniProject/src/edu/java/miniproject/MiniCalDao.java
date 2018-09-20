package edu.java.miniproject;

import java.sql.Date;
import java.util.List;

public interface MiniCalDao {
	MiniCalendar select(Date minidate);
	
	int save(MiniCalendar minicalendar);
	int clear(MiniCalendar minicalendar);
	int update(MiniCalendar minicalendar);
	int delete(Date minidate);

	
}

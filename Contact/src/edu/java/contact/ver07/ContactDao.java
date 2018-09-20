package edu.java.contact.ver07;

import java.util.List;

public interface ContactDao {
	List<Contact> select();
	Contact select(int cid);
	int insert(Contact contact);
	int update(Contact contact);
	int delete(int cid);
} // end interface ContactDao

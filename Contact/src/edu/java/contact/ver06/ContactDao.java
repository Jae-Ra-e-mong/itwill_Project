package edu.java.contact.ver06;

import java.util.ArrayList;

import edu.java.contact.model.Contact;

public interface ContactDao {
	
	int insert(Contact contact);
	ArrayList<Contact> select();
	Contact select(int index);
	int update(int index, Contact contact);
	int delete(int index);

} // end interface ContactDao








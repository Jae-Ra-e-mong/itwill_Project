package edu.java.contact.ver04;

import java.util.ArrayList;

import edu.java.contact.model.Contact;

public class ContactDaoImple implements ContactDao {
	
	private static ContactDaoImple instance = null;
	
	private ContactDaoImple() {}
	
	public static ContactDaoImple getInstance() {
		if (instance == null) {
			instance = new ContactDaoImple();
		}
		return instance;
	}

	// 멤버 변수
	private ArrayList<Contact> contactList = new ArrayList<>();
	
	@Override
	public int insert(Contact contact) {
		boolean result = contactList.add(contact);
		if (result) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public ArrayList<Contact> select() {
		return contactList;
	}

	@Override
	public Contact select(int index) {
		if (index >= 0 && index < contactList.size()) {
			return contactList.get(index);
		} else {
			return null;
		}
	}

	@Override
	public int update(int index, Contact contact) {
		if (index >= 0 && index < contactList.size()) {
			contactList.set(index, contact);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int delete(int index) {
		if (index >= 0 && index < contactList.size()) {
			contactList.remove(index);
			return 1;
		} else {
			return 0;
		}
	}

} // end class ContactDaoImple












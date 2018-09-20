package edu.java.contact.ver05;

import java.util.ArrayList;

import edu.java.contact.fileutil.FileUtil;
import edu.java.contact.model.Contact;

public class ContactDaoImple implements ContactDao {
	
	private static ContactDaoImple instance = null;
	
	private ContactDaoImple() throws Exception {
		FileUtil.initDataDir();
		contactList = FileUtil.loadInitData();
	}
	
	public static ContactDaoImple getInstance() throws Exception {
		if (instance == null) {
			instance = new ContactDaoImple();
		}
		return instance;
	}

	// 멤버 변수
	private ArrayList<Contact> contactList;
	
	@Override
	public int insert(Contact contact) {
		boolean result = contactList.add(contact);
		if (result) {
			boolean result2 = FileUtil.writeDataToFile(contactList);
			if (result2) {
				return 1;
			} else {
				return 0;
			}
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
			// FIXME:
			FileUtil.writeDataToFile(contactList);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int delete(int index) {
		if (index >= 0 && index < contactList.size()) {
			contactList.remove(index);
			// FIXME:
			FileUtil.writeDataToFile(contactList);
			return 1;
		} else {
			return 0;
		}
	}

} // end class ContactDaoImple












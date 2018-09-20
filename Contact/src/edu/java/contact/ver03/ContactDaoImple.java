package edu.java.contact.ver03;

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

	private static final int MAX = 100;
	private Contact[] contactList = new Contact[MAX];
	private int count = 0;
	
	@Override
	public int insert(Contact contact) {
		if (count < MAX) { // 배열에 저장할 공간이 있는 경우
			contactList[count] = contact;
			count++;
			return 1;
		} else {
			return 0;
		}
	} // end insert()

	@Override
	public Contact[] select() {
		if (count > 0) {
			Contact[] list = new Contact[count];
			for (int i = 0; i < count; i++) {
				list[i] = contactList[i];
			}
			
			return list;
		} else {
			return null;
		}
		
	} // end select()
	
	@Override
	public Contact select(int index) {
		if (index >= 0 && index < count) {
			return contactList[index];
		} else {
			return null;
		}
	} // end select(index)
	
	@Override
	public int update(int index, Contact contact) {
		if (index >= 0 && index < count) {
			contactList[index].setName(contact.getName());
			contactList[index].setPhone(contact.getPhone());
			contactList[index].setEmail(contact.getEmail());
			
			return 1;
		} else {
			return 0;
		}
	} // end update()
	
} // end class ContactDaoImple




















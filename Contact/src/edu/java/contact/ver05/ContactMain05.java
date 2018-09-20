package edu.java.contact.ver05;

import java.util.ArrayList;
import java.util.Scanner;

import edu.java.contact.model.Contact;

import static edu.java.contact.menu.Menu.*;

public class ContactMain05 {
	
	private static Scanner scanner = new Scanner(System.in);
	private static ContactDao dao;

	public static void main(String[] args) {
		System.out.println("연락처 프로그램 ver 0.5");
		try {
			dao = ContactDaoImple.getInstance();
		} catch (Exception e) {
			System.out.println("데이터 폴더를 생성할 수 없어서 프로그램을 종료합니다...");
			return; // 프로그램 종료
		}

		boolean run = true;
		while (run) {
			showMainUI();
			int menu = inputNumber();
			switch (menu) {
			case QUIT:
				run = false;
				break;
			case INSERT:
				insertContact();
				break;
			case SELECT_ALL:
				selectAllContacts();
				break;
			case SELECT_BY_INDEX:
				selectContactByIndex();
				break;
			case UPDATE:
				updateContact();
				break;
			case DELETE:
				deleteContact();
				break;
			default:
				System.out.println("다시 선택하세요...");
			} // end switch
			
		} // end while
		
		System.out.println("프로그램 종료...");
	} // end main()

	private static void showMainUI() {
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println("[1]추가 [2]전체검색 [3]인덱스검색 [4]수정 [5]삭제 [0]종료");
		System.out.println("------------------------------------------------");
		System.out.println("선택>>");
	} // end showMainUI()
	
	private static void insertContact() {
		System.out.println();
		System.out.println("--- 새로운 연락처 추가 ---");
		System.out.println("이름 입력>>");
		String name = scanner.nextLine();
		System.out.println("전화번호 입력>>");
		String phone = scanner.nextLine();
		System.out.println("이메일 입력>>");
		String email = scanner.nextLine();
		
		Contact contact = new Contact(name, phone, email);
		int result = dao.insert(contact);
		if (result == 1) {
			System.out.println("새로운 연락처 등록 성공");
		} else {
			System.out.println("새로운 연락처 등록 실패");
		}
		
	} // end insertContact()
	
	private static void selectAllContacts() {
		System.out.println();
		System.out.println("--- 연락처 전체 검색 ---");
		ArrayList<Contact> list = dao.select();
		for (Contact c : list) {
			System.out.println(c);
		}
		
	} // end selectAllContacts()
	
	private static void selectContactByIndex() {
		System.out.println();
		System.out.println("--- 인덱스 검색 ---");
		System.out.println("검색할 인덱스>>");
		int index = inputNumber();
		Contact contact = dao.select(index);
		if (contact != null) {
			System.out.println(contact);
		} else {
			System.out.println("해당 인덱스에는 연락처 정보 없음");
		}
		
	} // end selectContactByIndex()
	
	private static void updateContact() {
		System.out.println();
		System.out.println("--- 연락처 수정 ---");
		System.out.println("수정할 인덱스>>");
		int index = inputNumber();
		System.out.println("수정할 이름>>");
		String name = scanner.nextLine();
		System.out.println("수정할 전화번호>>");
		String phone = scanner.nextLine();
		System.out.println("수정할 이메일>>");
		String email = scanner.nextLine();
		
		Contact contact = new Contact(name, phone, email);
		int result = dao.update(index, contact);
		if (result == 1) {
			System.out.println("연락처 수정 성공");
		} else {
			System.out.println("연락처 수정 실패");
		}
		
	} // end updateContact()
	
	private static void deleteContact() {
		System.out.println();
		System.out.println("--- 연락처 삭제 ---");
		System.out.println("삭제할 인덱스>>");
		int index = inputNumber();
		int result = dao.delete(index);
		if (result == 1) {
			System.out.println("연락처 삭제 성공");
		} else {
			System.out.println("연락처 삭제 실패");
		}
		
	} // end deleteContact()
	
	private static int inputNumber() {
//		int num;
		while (true) {
			try {
				int num = Integer.parseInt(scanner.nextLine());
				return num; //break;
			} catch (NumberFormatException e) {
				System.out.println("정수로 입력>>");
			}
		} // while
		
//		return num;
	} // end inputNumber()
	
} // end class ContactMain04























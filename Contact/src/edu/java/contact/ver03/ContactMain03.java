package edu.java.contact.ver03;

import java.util.Scanner;

import edu.java.contact.model.Contact;

import static edu.java.contact.menu.Menu.*;

public class ContactMain03 {
	
	// 멤버 변수
	private static Scanner scanner = new Scanner(System.in);
	private static ContactDaoImple dao = ContactDaoImple.getInstance();
	
	public static void main(String[] args) {
		System.out.println("연락처 프로그램 ver 0.3");

		boolean run = true;
		while (run) {
			showMainUI();
			int menu = scanner.nextInt();
			scanner.nextLine();
			
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
			default:
				System.out.println("없는 메뉴...");
			} // end switch
			
		} // end while
		
		System.out.println("프로그램 종료...");
	} // end main()

	private static void showMainUI() {
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println("[1]추가 [2]전체검색 [3]인덱스검색 [4]편집 [0]종료");
		System.out.println("-----------------------------------------");
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
		
		// Contact 인스턴스 생성
		Contact c = new Contact(name, phone, email);
		
		// Contact 인스턴스 DAO에게 매개변수 전달 -> DAO가 배열에 저장
		int result = dao.insert(c);
		if (result == 1) {
			System.out.println("새로운 연락처 추가 성공");
		} else {
			System.out.println("새로운 연락처 추가 실패");
		}
		
	} // end insertContact()
	
	private static void selectAllContacts() {
		System.out.println();
		System.out.println("--- 전체 검색 결과 ---");
		Contact[] list = dao.select();
		if (list != null) {
			for (Contact c : list) {
				System.out.println(c);
			}
		} else {
			System.out.println("저장된 연락처가 없습니다.");
		}
		
	} // end selectAllContacts()
	
	private static void selectContactByIndex() {
		System.out.println();
		System.out.println("--- 인덱스 검색 ---");
		int index = scanner.nextInt();
		scanner.nextLine();
		
		Contact c = dao.select(index);
		if (c != null) {
			System.out.println(c);
		} else {
			System.out.println("해당 인덱스는 연락처가 없습니다.");
		}
		
	} // end selectContactByIndex()

	private static void updateContact() {
		System.out.println();
		System.out.println("--- 연락처 수정 ---");
		System.out.println("수정할 인덱스>>");
		int index = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("변경할 이름>>");
		String name = scanner.nextLine();
		System.out.println("변경할 전화번호>>");
		String phone = scanner.nextLine();
		System.out.println("변경할 이메일>>");
		String email = scanner.nextLine();
		
		Contact c = new Contact(name, phone, email);
		int result = dao.update(index, c);
		if (result == 1) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
		
	} // end updateContact()
	
} // end class ContactMain03




























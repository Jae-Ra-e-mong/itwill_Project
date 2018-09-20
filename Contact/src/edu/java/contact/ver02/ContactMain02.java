package edu.java.contact.ver02;

import java.util.Scanner;

import edu.java.contact.model.Contact;

public class ContactMain02 {

	// 멤버 변수
	// 입력받기 위한 Scanner
	private static Scanner scanner = new Scanner(System.in);
	
	// 연락처를 저장할 배열
	private static Contact[] contacts = new Contact[1];
	// 배열에 저장된 원소(연락처)의 갯수를 저장할 변수
	private static int count = 0;
	
	public static void main(String[] args) {
		System.out.println("연락처 프로그램 ver 0.2");
		
		boolean run = true; // 무한루프 종료 조건
		while (run) {
			showMainUI();
			int menu = scanner.nextInt();
			scanner.nextLine(); // 숫자 다음에 입력되는 엔터를 제거
			
			switch (menu) {
			case 0: // 종료
				run = false;
				break;
			case 1: // 추가
				insertContact();
				break;
			case 2: // 전체검색
				selectAllContacts();
				break;
			case 3: // 인덱스검색
				selectContactByIndex();
				break;
			case 4: // 편집
				updateContact();
				break;
			default:
				System.out.println("없는 메뉴입니다...");
			} // end switch
			
		} // end while

		System.out.println("프로그램 종료...");
	} // end main()

	private static void showMainUI() {
		System.out.println("-----------------------------------------");
		System.out.println("[1]추가 [2]전체검색 [3]인덱스검색 [4]편집 [0]종료");
		System.out.println("-----------------------------------------");
		System.out.println("선택>>");
	} // end showMainUI()
	
	private static void insertContact() {
		if (count == contacts.length) {
			// 배열에 원소가 모두 채워졌을 때
			System.out.println("연락처를 추가할 메모리가 부족!");
			return; // 메소드 종료
		}
		
		System.out.println();
		System.out.println("--------------");
		System.out.println("새로운 연락처 추가");
		System.out.println("--------------");
				
		// Contact 클래스의 인스턴스를 생성
		Contact contact = inputContact();
		contact.displayInfo();
		
		// 생성된 인스턴스를 배열에 저장
		contacts[count] = contact;
		
		// 배열의 원소의 갯수를 증가
		count++;
		
	} // end insertContact()
	
	private static void selectAllContacts() {
		System.out.println();
		System.out.println("--- 전체 검색 결과 ---");
		for (int i = 0; i < count; i++) {
			System.out.println("[" + i + "] " + contacts[i].getName());
		}
	} // end selectAllContacts()
	
	private static void selectContactByIndex() {
		System.out.println();
		System.out.println("-------------");
		System.out.println("검색할 인덱스>>");
		System.out.println("-------------");
		int index = scanner.nextInt();
		scanner.nextLine();
		
		if (index < 0 || index >= count) {
			System.out.println("검색할 데이터 없음");
			return;
		}
		
		System.out.println("--- 인덱스 검색 결과 ---");
		contacts[index].displayInfo();
		
	} // end selectContactByIndex()
	
	private static void updateContact() {
		System.out.println();
		System.out.println("--- 연락처 편집 기능 ---");
		System.out.println("수정할 인덱스>>");
		int index = scanner.nextInt();
		scanner.nextLine();
		
		if (index < 0 || index >= count) {
			System.out.println("수정할 데이터 없음");
			return;
		}
		
		Contact contact = inputContact();
		
//		contacts[index].setName(contact.getName()); // 이름 변경
//		contacts[index].setPhone(contact.getPhone()); // 전화번호 변경
//		contacts[index].setEmail(contact.getEmail()); // 이메일 변경
		
		contacts[index] = contact;
		
	} // end updateContact()
	
	private static Contact inputContact() {
		System.out.println("이름 입력>>");
		String name = scanner.nextLine();
		
		System.out.println("전화번호 입력>>");
		String phone = scanner.nextLine();
		
		System.out.println("이메일 입력>>");
		String email = scanner.nextLine();
		
		Contact contact = new Contact(name, phone, email);
		
		return contact;
	} // end inputContact()
	
} // end class ContactMain02























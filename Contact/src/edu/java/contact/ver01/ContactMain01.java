package edu.java.contact.ver01;

import java.util.Scanner;

import edu.java.contact.model.Contact;

public class ContactMain01 {

	public static void main(String[] args) {
		System.out.println("연락처 프로그램 ver 0.1");

		// 1) 연락처 입력 기능 (새로운 연락처 추가)
		// Scanner로 이름/전화번호/이메일 입력 -> Contact 생성
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 입력>>");
		String name = sc.nextLine();
		System.out.println("전화번호 입력>>");
		String phone = sc.nextLine();
		System.out.println("이메일 입력>>");
		String email = sc.nextLine();
		
		// Contact 인스턴스 생성
		Contact contact = new Contact(name, phone, email);
		
		// 2) 검색 기능
		// Contact의 내용을 출력
		contact.displayInfo();
		
		// 3) 수정 기능
		// 수정할 이름/전화번호/이메일 입력 -> 생성된 Contact의 내용을 수정
		// -> 수정한 내용 확인
		System.out.println("수정할 이름>>");
		name = sc.nextLine();
		contact.setName(name);
		
		System.out.println("수정할 전화번호>>");
		phone = sc.nextLine();
		contact.setPhone(phone);
		
		System.out.println("수정할 이메일>>");
		email = sc.nextLine();
		contact.setEmail(email);
		
		contact.displayInfo(); // 수정된 결과 확인
	} // end main()

} // end class ContactMain01


















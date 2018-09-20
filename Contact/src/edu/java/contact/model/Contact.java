package edu.java.contact.model;

import java.io.Serializable;

// 연락처 프로그램에서 데이터 클래스로 사용할 클래스
// 데이터 클래스: 특별한 기능을 담당하는 클래스가 아니라, 
// 어떤 데이터들을 저장하기 위한 용도로만 사용하는 클래스

// 파일에 Contact 객체 타입을 read/write를 하기 위해서는 
// Serializable 인터페이스를 구현해야만 함
public class Contact implements Serializable {
	// 멤버 변수(필드, 프로퍼티)
	private String name; // 연락처에 저장할 이름
	private String phone; // 연락처에 저장할 전화번호
	private String email; // 연락처에 저장할 이메일

	// 기본 생성자
	public Contact() {}
	
	// 매개변수가 있는 생성자
	public Contact(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	// getter/setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	// 연락처 정보를 출력하는 메소드
	public void displayInfo() {
		System.out.println("이름: " + name);
		System.out.println("전화번호: " + phone);
		System.out.println("이메일: " + email);
	}

	@Override
	public String toString() {
		String s = "Contact{name: " + name
				+ ", phone: " + phone
				+ ", email: " + email + "}";
		
		return s;
	}
	
} // end class Contact





















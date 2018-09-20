package edu.java.contact.ver07;

// MVC 아키텍쳐에서 Model에 해당하는 클래스 : VO, DTO
// 데이터베이스의 테이블 구조와 동일하게 하는것이 일반적
public class Contact {
	private int cid;
	private String name;
	private String phone;
	private String email;
	
	// 생성자, getter/ setter , toString()
	public Contact () {}
	
	public Contact(int cid, String name, String phone, String email) {
		this.cid = cid;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

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

	@Override
	public String toString() {
		 
		
		String contact = "Contact [cid=" + cid + ", name=" + name + ", phone=" + phone + ", email=" + email + "]";
		return contact;
	}
	
	
	
	
}

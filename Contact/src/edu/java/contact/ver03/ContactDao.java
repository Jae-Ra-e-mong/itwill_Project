package edu.java.contact.ver03;

import edu.java.contact.model.Contact;

public interface ContactDao {
	
	/**
	 * 배열에 Contact 인스턴스 저장 - 새로운 연락처 추가 기능
	 * @param contact 배열에 저장할 데이터(이름, 전화번호, 이메일)
	 * @return 배열에 저장 성공했을 때 1, 실패하면 0을 리턴
	 */
	public abstract int insert(Contact contact);

	/**
	 * 전체 검색 기능 - 배열에서 저장된 연락처 갯수만큼 리턴
	 * @return Contact 배열을 리턴. 배열의 크기는 연락처 갯수와 같다.
	 * 저장된 연락처가 없으면 null을 리턴.
	 */
	Contact[] select();
	
	/**
	 * 인덱스 검색 - 검색할 인덱스를 매개변수로 전달받아서 해당 연락처 정보를 리턴
	 * @param index 검색하고자 하는 배열의 인덱스
	 * @return 해당 인덱스에 연락처가 있으면 Contact, 연락처가 없으면 null
	 */
	Contact select(int index);
	
	/**
	 * 편집(수정) 기능 - 인덱스와 변경할 연락처 데이터를 매개변수로 전달 받아서 수정
	 * @param index 수정할 인덱스
	 * @param contact 수정할 연락처 정보(이름, 전화번호, 이메일)
	 * @return 수정 성공이면 1, 실패면 0을 리턴
	 */
	int update(int index, Contact contact);
	
} // end interface ContactDao




















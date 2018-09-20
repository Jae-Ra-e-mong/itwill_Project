package edu.java.contact.fileutil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import edu.java.contact.model.Contact;

// 파일과 관련된 기능들(폴더 생성, 파일 read/write)을 담당하는 유틸리티 클래스
public class FileUtil {
	// 데이터 파일 이름, 데이터 파일이 저장되는 폴더 이름 - 상수로 정의
	public static final String DATA_DIR = "data"; // 폴더 이름
	public static final String DATA_FILE = "contact.dat"; // 파일 이름
	
	// 다른 클래스에서 인스턴스를 생성하지 못하도록 생성자를 private으로 선언
	private FileUtil() {}

	/**
	 * 데이터 파일(contact.dat)을 읽어서 ArrayList<Contact>를 
	 * 리턴하는 메소드
	 * @param None
	 * @return 파일에서 읽기 성공하면 ArrayList<Contact>를 리턴.
	 * 읽기 실패인 경우는 null을 리턴.
	 */
	public static ArrayList<Contact> readDataFromFile() {
		ArrayList<Contact> list = null;
		
		File dataFile = new File(DATA_DIR, DATA_FILE);
		InputStream in = null;
		BufferedInputStream bin = null;
		ObjectInputStream oin = null;
		try {
//			in = new FileInputStream(DATA_DIR + File.separator + DATA_FILE);
			in = new FileInputStream(dataFile);
			bin = new BufferedInputStream(in);
			oin = new ObjectInputStream(bin);
			list = (ArrayList<Contact>) oin.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oin.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	} // end readDataFromFile()
	
	/**
	 * ArrayList<Contact>를 매개변수로 전달받아서, 파일에 write(씀)
	 * @param ArrayList<Contact>
	 * @return 파일 쓰기 성공이면 true, 쓰기 실패면 false
	 */
	public static boolean writeDataToFile(ArrayList<Contact> list) {
		boolean result = false;
		
		String fileName = DATA_DIR + File.separator + DATA_FILE;
		try (OutputStream out =
				new FileOutputStream(fileName);
				BufferedOutputStream bout =
						new BufferedOutputStream(out);
				ObjectOutputStream oout = 
						new ObjectOutputStream(bout);) {
			oout.writeObject(list);
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	} // end writeDataToFile()
	
	/**
	 * 데이터 파일을 저장할 폴더가 있는 지 검사하고,
	 * 폴더가 없는 경우는 새로 생성.
	 * 만약 폴더 생성이 실패하는 경우는 프로그램을 계속 실행하면 안되므로,
	 * Exception을 생성해서 throw하도록 
	 * @return void
	 * @throws Exception
	 */
	public static void initDataDir() throws Exception {
		// File 클래스의 인스턴스 생성
		File dataDir = new File(DATA_DIR);
		System.out.println("데이터 폴더: " + dataDir.getAbsolutePath());
		if (dataDir.exists()) {
			System.out.println("데이터 폴더가 이미 존재합니다...");
		} else { // 데이터 폴더가 없는 경우
			if (dataDir.mkdir()) { // 데이터 폴더 생성
				System.out.println("데이터 폴더 생성 성공...");
			} else { // 폴더 생성 실패
				throw new Exception("data 폴더 생성 실패");
			}
		}
		
	} // end initDataDir()
	
	/**
	 * 데이터 파일(contact.dat)이 존재하는 지 검사,
	 * 데이터 파일이 있다면, 데이터 파일을 읽어서 ArrayList를 리턴
	 * 데이터 파일이 없다면, 비어 있는 ArrayList(원소 0개)를 리턴
	 * @return ArrayList<Contact>
	 */
	public static ArrayList<Contact> loadInitData() {
		ArrayList<Contact> list = null;
		
		// 파일 존재 여부를 검사하기 위해서
		File dataFile = new File(DATA_DIR, DATA_FILE);
		System.out.println("데이터 파일: " + dataFile.getAbsolutePath());
		
		if (dataFile.exists()) {
			list = readDataFromFile();
			System.out.println("기존 데이터 로딩...");
		} else {
			list = new ArrayList<>();
			System.out.println("새로운 데이터 생성...");
		}
		
		return list;
	} // end loadInitData()
	
} // end class FileUtil



















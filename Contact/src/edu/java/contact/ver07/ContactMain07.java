package edu.java.contact.ver07;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContactMain07 implements UpdateFrame.ContactUpdateCallback,InsertFrame.ContactInsertCallback {
	

	private static final String[] COLUMN_NAMES = {
		"이름", "전화번호", "이메일"
	};
	
	private JFrame frame;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTable table;
	private DefaultTableModel model;
	
	private ContactDao dao;
	private List<Contact> list;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactMain07 window = new ContactMain07();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ContactMain07() {
		// ContactDao를 사용할 준비
		dao = ContactDaoImple.getInstance();
		// UI 컴포넌트들(버튼, 테이블) 을 생성
		initialize();
		// 생성된 테이블에 데이터를 채워줌
		initializeTable();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("연락처 프로그램 Ver 0.7");
		frame.setBounds(100, 100, 692, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0,0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		btnInsert = new JButton("추가");
		btnInsert.addActionListener((e)->{
			showInsertFrame();
		});
		btnInsert.setFont(new Font("굴림", Font.BOLD, 20));
		panel.add(btnInsert);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener((e)->{
			showUpdateFrame();
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setFont(new Font("굴림", Font.BOLD, 20));
		panel.add(btnUpdate);
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener((e)->{
			deleteContact();
		});
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("굴림", Font.BOLD, 20));
		panel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeButtonStatus();
			}
		});
		scrollPane.setViewportView(table);
		
		
	} // end initialize()
	
	private void showUpdateFrame() {
		// JTable에서 선택된 행(row)의 연락처의 cid를 알아내야함.
		int index = table.getSelectedRow();
		int cid = list.get(index).getCid();
		// cid 정보를 UpdateFrame을 생성할 때 전달
		UpdateFrame.showUpdateFrame(cid, ContactMain07.this);
		
	} // end showUpdateFrame()

	private void changeButtonStatus() {
		if (table.getSelectedRow() >= 0) {
			btnUpdate.setEnabled(true);
			btnDelete.setEnabled(true);
		} else {
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
			
		}
	}// end changeButtonStatus()
	
	private void initializeTable() {
		model = new DefaultTableModel(null, COLUMN_NAMES);
		list = dao.select();
		for (Contact c : list) {
			String[] rowData = {
					 c.getName(), c.getPhone(), c.getEmail()
			};
			model.addRow(rowData);
			
		} // end for
		
		table.setModel(model);
	} // end initializeTable()
	
	private void showInsertFrame() {
		InsertFrame.showInsertFrame(ContactMain07.this);
	}

	// UpdateFrame에서 정의한 인터페이스 콜백
	@Override
	public void notifyContactUpdated() {
		initializeTable();
		
	}// end notifyContactUpdated()
	
	private void deleteContact() {
		int cid = list.get(table.getSelectedRow()).getCid();
		int result = dao.delete(cid);
		if ( result == 1) {
			JOptionPane.showMessageDialog(frame, "삭제되었습니다.");
		} else {
			JOptionPane.showMessageDialog(frame, "삭제 실패하였습니다");
		}
		initializeTable();
	}
} // end class ContactMain07

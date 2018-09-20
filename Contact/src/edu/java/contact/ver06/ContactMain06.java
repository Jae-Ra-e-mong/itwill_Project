package edu.java.contact.ver06;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.java.contact.model.Contact;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContactMain06 implements InsertFrame.DataChangeCallback,UpdateFrame.DataChangeCallback {
	private static final String[] COLIMN_NAMES = { "이름", "전화번호", "이메일" };
	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;

	private ContactDao dao;
	
	@Override
	public void notifyDataChanged() {
		loadContactDate();	
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactMain06 window = new ContactMain06();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public ContactMain06() throws Exception {
		dao = ContactDaoImple.getInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 662);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		btnInsert = new JButton("추가");
		btnInsert.addActionListener((e) -> {
			InsertFrame.showInsertFrame(ContactMain06.this);
		});
		panel.add(btnInsert);

		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener((e)->{
			int index = table.getSelectedRow();
			UpdateFrame.showUpdateFrame(ContactMain06.this,index);
		});

		btnUpdate.setEnabled(false);
		panel.add(btnUpdate);

		btnDelete = new JButton("삭제");
		btnDelete.setEnabled(false);
		panel.add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setRowHeight(40);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeButtonStatus();
			}
		});
		scrollPane.setViewportView(table);
		loadContactDate();

	} // end initialize()

	private void createTableModel() {

		model = new DefaultTableModel(null, COLIMN_NAMES) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		/*
		 * class MyTableModel extends DefaultTableModel {
		 * 
		 * public MyTableModel(Object[][] data, String[] colNames) { super(data,
		 * colNames);
		 * 
		 * }
		 * 
		 * @Override public boolean isCellEditable(int row, int column) { return false;
		 * }
		 * 
		 * } // end MyTableModel()
		 * 
		 * model = new MyTableModel(null, COLIMN_NAMES);
		 */
	} // end createTableModel()

	private void loadContactDate() {
		createTableModel(); // 테이블 모델 생성
		table.setModel(model); // 테이블 모델을 JTable에 설정

		// 연락처 데이터를 테이블 모델에 추가
		ArrayList<Contact> list = dao.select();
		for (Contact c : list) {
			String[] rowData = { c.getName(), c.getPhone(),c.getEmail() };
			model.addRow(rowData);
		}

	} // end loadContactDate()

	private void changeButtonStatus() {
		if (table.getSelectedRow() >= 0) {
			btnUpdate.setEnabled(true);
			btnDelete.setEnabled(true);
		} else {
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
		}
	} // end changeButtonStatus()

} // end class ContactMain06

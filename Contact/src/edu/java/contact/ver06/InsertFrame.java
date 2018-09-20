package edu.java.contact.ver06;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.contact.model.Contact;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertFrame extends JFrame {
	interface DataChangeCallback{
		void notifyDataChanged();
		
	}
	
	private DataChangeCallback callback;
	
	
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textEmail;

	private ContactDao dao;

	/**
	 * Launch the application.
	 */
	public static void showInsertFrame(DataChangeCallback callback) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertFrame frame = new InsertFrame(callback);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public InsertFrame(DataChangeCallback callback) throws Exception {
		this.callback = callback;
		dao = ContactDaoImple.getInstance();
		initialize();

	}

	public void initialize() {
		setTitle("연락처 입력");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("굴림", Font.BOLD, 25));
		lblName.setBounds(12, 10, 111, 53);
		contentPane.add(lblName);

		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("굴림", Font.BOLD, 25));
		lblPhone.setBounds(12, 73, 111, 53);
		contentPane.add(lblPhone);

		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("굴림", Font.BOLD, 25));
		lblEmail.setBounds(12, 136, 111, 53);
		contentPane.add(lblEmail);

		textName = new JTextField();
		textName.setBounds(135, 10, 287, 53);
		contentPane.add(textName);
		textName.setColumns(10);

		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(135, 73, 287, 53);
		contentPane.add(textPhone);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(135, 136, 287, 53);
		contentPane.add(textEmail);

		JButton btnInsert = new JButton("추가");
		btnInsert.addActionListener((e) -> {
			insertNewContact();

		});
		btnInsert.setFont(new Font("굴림", Font.BOLD, 25));
		btnInsert.setBounds(135, 199, 287, 52);
		contentPane.add(btnInsert);
	} // end initialize()

	private void insertNewContact() {
		String name = textName.getText();
		String phone = textPhone.getText();
		String email = textEmail.getText();
		if (!name.equals("") && !phone.equals("")) {
			Contact contact = new Contact(name, phone, email);
			int result = dao.insert(contact);
			if (result == 1) {
				JOptionPane.showMessageDialog(InsertFrame.this, "연락처 추가 성공");
				// Main 프레임에게 연락처 데이터가 변경됐다고 알려줌.
				callback.notifyDataChanged();
				dispose(); // InsertFrame 창 닫기
			} else {
				JOptionPane.showMessageDialog(InsertFrame.this, "연락처 추가 실패");
			}

		} else {
			JOptionPane.showMessageDialog(InsertFrame.this, "이름과 전화번호는 필수 입력 사항입니다.");
		}
	} // end insertNewContact()
} // end class InsertFrame

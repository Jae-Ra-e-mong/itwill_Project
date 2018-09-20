package edu.java.contact.ver07;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertFrame extends JFrame {

	interface ContactInsertCallback {
		void notifyContactUpdated();
	}
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textEmail;

	private ContactDao dao;
	private ContactInsertCallback callback;
	
	/**
	 * Launch the application.
	 */
	public static void showInsertFrame(ContactInsertCallback callback) {
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
	 */
	public InsertFrame(ContactInsertCallback callback) {
		this.callback = callback;
		dao = ContactDaoImple.getInstance();
		initialize();
	}
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("굴림", Font.BOLD, 25));
		lblName.setBounds(12, 10, 119, 47);
		contentPane.add(lblName);
		
		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("굴림", Font.BOLD, 25));
		lblPhone.setBounds(12, 68, 119, 47);
		contentPane.add(lblPhone);
		
		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("굴림", Font.BOLD, 25));
		lblEmail.setBounds(12, 125, 119, 47);
		contentPane.add(lblEmail);
		
		textName = new JTextField();
		textName.setBounds(162, 20, 322, 36);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(162, 68, 322, 36);
		contentPane.add(textPhone);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(162, 125, 322, 36);
		contentPane.add(textEmail);
		
		JButton btninsert = new JButton("입력완료");
		btninsert.addActionListener((e)->{
			String name = textName.getText();
			String phone = textPhone.getText();
			String email = textEmail.getText();
			
			Contact contact = new Contact(0, name, phone, email);
			
			dao.insert(contact);
			callback.notifyContactUpdated();
			dispose();
		});
		btninsert.setBounds(162, 192, 322, 74);
		contentPane.add(btninsert);
	}

}

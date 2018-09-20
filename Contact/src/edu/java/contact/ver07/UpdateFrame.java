package edu.java.contact.ver07;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyleConstants.ParagraphConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateFrame extends JFrame {
	interface ContactUpdateCallback{
		void notifyContactUpdated();
		
	}// end interface ContactUpdateCallback

	private ContactUpdateCallback callback;
	private int cid; // 업데이트할 연락처의 cid 정보
	private ContactDao dao;

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textEmail;

	/**
	 * Launch the application.
	 */
	public static void showUpdateFrame(int cid,
			ContactUpdateCallback callback) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateFrame frame = new UpdateFrame(cid, callback);
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

	public UpdateFrame(int cid, ContactUpdateCallback callback) {
		this.cid = cid;
		this.callback = callback;
		dao = ContactDaoImple.getInstance();
		initialize();
		initializeTextFileds();

	}

	public void initialize() {
		setTitle("연락처 수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 462, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("굴림", Font.BOLD, 20));
		lblName.setBounds(12, 10, 100, 42);
		contentPane.add(lblName);

		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("굴림", Font.BOLD, 20));
		lblPhone.setBounds(12, 62, 100, 42);
		contentPane.add(lblPhone);

		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("굴림", Font.BOLD, 20));
		lblEmail.setBounds(12, 114, 100, 42);
		contentPane.add(lblEmail);

		textName = new JTextField();
		textName.setBounds(124, 10, 306, 42);
		contentPane.add(textName);
		textName.setColumns(10);

		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(124, 62, 306, 42);
		contentPane.add(textPhone);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(124, 114, 306, 42);
		contentPane.add(textEmail);

		JButton btnUpdate = new JButton("수정완료");
		btnUpdate.addActionListener((e) -> {
			updateContact();
		});
		btnUpdate.setFont(new Font("굴림", Font.BOLD, 20));
		btnUpdate.setBounds(124, 166, 146, 54);
		contentPane.add(btnUpdate);
	} // end initialize()

	private void initializeTextFileds() {
		Contact contact = dao.select(cid);
		textName.setText(contact.getName());
		textPhone.setText(contact.getPhone());
		textEmail.setText(contact.getEmail());

	} // end initializeTextFileds()

	private void updateContact() {
		String name = textName.getText();
		String phone = textPhone.getText();
		String email = textEmail.getText();
		Contact contact = new Contact(cid, name, phone, email);

		int result = dao.update(contact);
		if (result == 1) {

			JOptionPane.showMessageDialog(UpdateFrame.this, "수정성공");
			callback.notifyContactUpdated();
			dispose();
			

		} else {
			JOptionPane.showMessageDialog(UpdateFrame.this, "수정실패");
		}
	}
} // end class UpdateFrame

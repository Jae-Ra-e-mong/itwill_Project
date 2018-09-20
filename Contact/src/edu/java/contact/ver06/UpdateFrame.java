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

public class UpdateFrame extends JFrame {
	interface DataChangeCallback {
		void notifyDataChanged();
	}

	private DataChangeCallback callback;
	private int index; // 수정이랑 삭제할 연락처의 인덱스
	private ContactDao dao;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textEmail;

	/**
	 * Launch the application.
	 */
	public static void showUpdateFrame(DataChangeCallback callback, int index) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateFrame frame = new UpdateFrame(callback, index);
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
	public UpdateFrame(DataChangeCallback callback, int index) throws Exception {
		this.callback = callback;
		this.index = index;
		dao = ContactDaoImple.getInstance();
		initialize();

		initTextFields();
	}

	public void initialize() {
		setTitle("연락처 수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("굴림", Font.BOLD, 20));
		lblName.setBounds(12, 10, 104, 53);
		contentPane.add(lblName);

		JLabel lblPhone = new JLabel("전화번호");
		lblPhone.setFont(new Font("굴림", Font.BOLD, 20));
		lblPhone.setBounds(12, 73, 104, 53);
		contentPane.add(lblPhone);

		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setFont(new Font("굴림", Font.BOLD, 20));
		lblEmail.setBounds(12, 136, 104, 53);
		contentPane.add(lblEmail);

		textName = new JTextField();
		textName.setFont(new Font("굴림", Font.PLAIN, 18));
		textName.setBounds(128, 10, 294, 53);
		contentPane.add(textName);
		textName.setColumns(10);

		textPhone = new JTextField();
		textPhone.setFont(new Font("굴림", Font.PLAIN, 18));
		textPhone.setColumns(10);
		textPhone.setBounds(128, 73, 294, 53);
		contentPane.add(textPhone);

		textEmail = new JTextField();
		textEmail.setFont(new Font("굴림", Font.PLAIN, 18));
		textEmail.setColumns(10);
		textEmail.setBounds(128, 136, 294, 53);
		contentPane.add(textEmail);

		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener((e) -> {
			updateContact();
		});
		btnUpdate.setBounds(128, 199, 135, 52);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener((e) -> {
			deleteContact();
		});
		btnDelete.setBounds(287, 199, 135, 52);
		contentPane.add(btnDelete);
	} // end initialize()

	private void initTextFields() {
		Contact c = dao.select(index);
		textName.setText(c.getName());
		textPhone.setText(c.getPhone());
		textEmail.setText(c.getEmail());
	}// end initTextFields()

	private void updateContact() {
		String name = textName.getText();
		String phone = textPhone.getText();
		String email = textEmail.getText();
		Contact c = new Contact(name, phone, email);
		int result = dao.update(index, c);
		if (result == 1) {
			JOptionPane.showMessageDialog(UpdateFrame.this, "연락처 수정 성공");
			// ContactMain 클래스에게 연락처 데이터가 변경됐다고 알려줌
			callback.notifyDataChanged();
			dispose(); // 창닫기
		} else {
			JOptionPane.showMessageDialog(UpdateFrame.this, "연락처 수정 실패");
		}

	}// end updateContact()

	private void deleteContact() {
		int confirm = JOptionPane.showConfirmDialog(UpdateFrame.this, "정말 삭제 하시겠습니까?", "삭제 확인",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			int result = dao.delete(index);
			if (result == 1) {
				JOptionPane.showMessageDialog(UpdateFrame.this, "삭제성공");
				callback.notifyDataChanged();
				dispose();
			} else {
				JOptionPane.showMessageDialog(UpdateFrame.this, "삭제실패");
			}
		}
	}// end deleteContact()

} // end class UpdateFrame

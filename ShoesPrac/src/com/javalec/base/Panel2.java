package com.javalec.base;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.javalec.dao.DaoLogin;

public class Panel2 extends JPanel {
	private JTextField tfID;
	private JTextField tfAddress;
	private JTextField tfEmail;
	private JTextField tfPhone;
	private JPasswordField pfPassword;
	private JTextField tfName;

	/**
	 * Create the panel.
	 */
	public Panel2() {
		setLayout(null);
		
		JLabel lblInterface = new JLabel("\uD68C\uC6D0\uAC00\uC785 \uD654\uBA74");
		lblInterface.setFont(new Font("����", Font.BOLD, 18));
		lblInterface.setBounds(159, 40, 130, 45);
		add(lblInterface);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("����", Font.BOLD, 15));
		lblID.setBounds(48, 117, 50, 15);
		add(lblID);
		
		tfID = new JTextField();
		tfID.setText(" ");
		tfID.setBounds(106, 113, 222, 21);
		add(tfID);
		tfID.setColumns(10);
		
		JLabel lblPassword = new JLabel("PW");
		lblPassword.setFont(new Font("����", Font.BOLD, 15));
		lblPassword.setBounds(48, 147, 50, 26);
		add(lblPassword);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(106, 148, 222, 26);
		add(pfPassword);
		
		JLabel lblAddress = new JLabel("\uC8FC\uC18C");
		lblAddress.setFont(new Font("����", Font.BOLD, 15));
		lblAddress.setBounds(47, 191, 50, 15);
		add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(109, 187, 222, 21);
		add(tfAddress);
		
		JLabel lblEmail = new JLabel("\uC774\uBA54\uC77C");
		lblEmail.setFont(new Font("����", Font.BOLD, 15));
		lblEmail.setBounds(48, 259, 50, 15);
		add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(114, 255, 222, 21);
		add(tfEmail);
		
		JLabel lblPhone = new JLabel("\uC804\uD654\uBC88\uD638");
		lblPhone.setFont(new Font("����", Font.BOLD, 15));
		lblPhone.setBounds(51, 292, 68, 17);
		add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(122, 288, 222, 21);
		add(tfPhone);
		
		JButton btnOverlapCheck = new JButton("\uC911\uBCF5\uD655\uC778");
		btnOverlapCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				overlapCheck();
			}
		});
		btnOverlapCheck.setBounds(347, 113, 91, 26);
		add(btnOverlapCheck);
		
		JButton btnInsert = new JButton("\uD68C\uC6D0\uAC00\uC785 \uC644\uB8CC");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeAccount();
				
				
			}
		});
		btnInsert.setBounds(218, 395, 116, 26);
		add(btnInsert);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblName.setBounds(48, 222, 50, 15);
		add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(110, 218, 222, 21);
		add(tfName);

	}
	
	
	private void overlapCheck() {
		DaoLogin dao = new DaoLogin(tfID.getText().trim());
		boolean result = dao.overlapCheck();
		if(!result) {
			tfID.setText("");
			tfID.requestFocus();
		}
	}
	
	private void makeAccount() {
		DaoLogin dao = new DaoLogin(tfID.getText().trim(), pfPassword.getText().trim() , 
				tfAddress.getText().trim() , tfName.getText().trim(),
				tfEmail.getText().trim(), tfPhone.getText().trim());
		boolean result = dao.makeAccount();
		if(result) {
			setVisible(false);
			Main.frame.getContentPane().add(new Panel1());
		}
	}
}

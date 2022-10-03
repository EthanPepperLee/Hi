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

public class Panel1 extends JPanel {
	private JTextField tfID;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPasswordField pfPassword;

	// 로그인 시에 id를 기억하기 위해서
	public static String id= ""; 
	
	/**
	 * Create the panel.
	 */
	public Panel1() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Log in");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 39));
		lblNewLabel.setBounds(165, 67, 120, 60);
		add(lblNewLabel);
		
		tfID = new JTextField();
		tfID.setBounds(195, 163, 120, 21);
		add(tfID);
		tfID.setColumns(10);
		
		lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(102, 166, 81, 15);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(102, 207, 81, 15);
		add(lblNewLabel_2);
		
		JButton btnLogIn = new JButton("log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAction();
			}
		});
		btnLogIn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnLogIn.setBounds(102, 276, 95, 23);
		add(btnLogIn);
		
		JButton btnSignUp = new JButton("sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Main.frame.getContentPane().add(new Panel2());
			}
		});
		btnSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSignUp.setBounds(220, 276, 95, 23);
		add(btnSignUp);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(195, 200, 120, 26);
		add(pfPassword);
	}
	
	// ------------------------------------------------------------------------------

	// 텍스트 필드 값들 없애기
		private void clearColumn() {
			tfID.setText("");
			pfPassword.setText("");

		}
	
	public void loginAction() {
		DaoLogin dao = new DaoLogin(tfID.getText().trim() , pfPassword.getText().trim());
		id= dao.loginAction();

		if(id.equals("")) {
			clearColumn();
		}else {
			setVisible(false);
			Main.frame.getContentPane().add(new Panel3());
		}
	}
}

package com.javalec.base;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.DaoCart;
import com.javalec.dao.DaoDetail;
import com.javalec.dao.DaoProduct;
import com.javalec.dto.DtoCart;
import com.javalec.dto.DtoDetail;
import com.javalec.dto.DtoProduct;

public class Panel5 extends JPanel {
	private JTable Inner_Table;
	private JTextField textField;
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	
	int sum = 0;

	/**
	 * Create the panel.
	 */
	public Panel5() {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				cartTableInit();
				sum = searchAction();
				textField.setText(Integer.toString(sum));
			}
			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});

		setLayout(null);

		JLabel lblCartList = new JLabel("장바구니 목록");
		lblCartList.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblCartList.setBounds(25, 19, 148, 37);
		add(lblCartList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 68, 415, 131);
		add(scrollPane);

		Inner_Table = new JTable();
		scrollPane.setViewportView(Inner_Table);
		Inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Inner_Table.setModel(Outer_Table);

		JButton btnNewButton = new JButton("취소");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAction();
			}
		});
		btnNewButton.setBounds(316, 211, 117, 29);
		add(btnNewButton);

		JLabel lblTotalPrice = new JLabel("총 가격 : ");
		lblTotalPrice.setBounds(25, 263, 61, 16);
		add(lblTotalPrice);

		textField = new JTextField();
		textField.setBounds(98, 258, 176, 26);
		add(textField);
		textField.setColumns(10);

		JLabel lblWon = new JLabel("원");
		lblWon.setBounds(287, 263, 61, 16);
		add(lblWon);

		JButton btnBuy = new JButton("구매하기");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertToPayAction();
				
				setVisible(false);
				Main.frame.getContentPane().add(new Panel3());
			}
		});
		btnBuy.setBounds(316, 399, 117, 29);
		add(btnBuy);
		
		JButton btnBuy_1 = new JButton("구매안하기");
		btnBuy_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Main.frame.getContentPane().add(new Panel3());
			}
		});
		btnBuy_1.setBounds(196, 399, 117, 29);
		add(btnBuy_1);

	}

	private void cartTableInit() {
		Outer_Table.addColumn("인덱스");
		Outer_Table.addColumn("모델명");
		Outer_Table.addColumn("사이즈");
		Outer_Table.addColumn("수량");
		Outer_Table.addColumn("브랜드");
		Outer_Table.addColumn("가격");

		Outer_Table.setColumnCount(6);

		// table에 있는 데이터 지우기
		int a = Outer_Table.getRowCount();
		for (int i = 0; i < a; i++) {
			Outer_Table.removeRow(0); // 열이 하나씩 당겨지기 때문에
		}

		Inner_Table.setAutoResizeMode(Inner_Table.AUTO_RESIZE_OFF);
		// 순서 column
		int vColIndex = 0;
		TableColumn col = Inner_Table.getColumnModel().getColumn(vColIndex);
		int width = 50;
		col.setPreferredWidth(width);
		
		vColIndex = 1;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 100;
		
		vColIndex = 2;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 100;

		vColIndex = 3;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 150;
		col.setPreferredWidth(width);
		vColIndex = 4;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);

		vColIndex = 5;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);
	}

	private int searchAction() {
		int sum = 0;
		DaoCart dao = new DaoCart();
		ArrayList<DtoCart> dtoCartList = dao.searchAction();

		for (DtoCart a : dtoCartList) {
			int num = a.getCd_product_no();
			int num2 = a.getCd_detail_no();
			DaoProduct daoPro = new DaoProduct(num);
			DtoProduct dtoPro = daoPro.tableClick();
			DaoDetail daoDet = new DaoDetail(num2);
			DtoDetail dtoDet = daoDet.tableClick();
			String[] qTxt = { Integer.toString(a.getCart_no()), dtoPro.getModel(), Integer.toString(dtoDet.getSize()), Integer.toString(a.getAmount()),
					dtoPro.getBrand(), Integer.toString(a.getPrice()) };
			sum += a.getAmount() * a.getPrice();
			Outer_Table.addRow(qTxt);
		}
		
		
		return sum;
	}
	
	private void deleteAction() {
		int selectRow = Inner_Table.getSelectedRow();
		String wkSequence = (String) Inner_Table.getValueAt(selectRow, 0);
		DaoCart dao = new DaoCart(Integer.parseInt(wkSequence));
		dao.deleteAction();
		cartTableInit();
		sum = searchAction();
		textField.setText(Integer.toString(sum));
	}
	
	private void insertToPayAction() {
		DaoCart dao = new DaoCart();
		dao.insertToPayAction();
		dao.updateAction();
	}
	
}

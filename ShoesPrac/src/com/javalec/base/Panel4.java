package com.javalec.base;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import com.javalec.dto.DtoDetail;
import com.javalec.dto.DtoProduct;

public class Panel4 extends JPanel {
	private JScrollPane scrollPane;
	private JButton btnCart;
	private JLabel lblPrice;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	
	private final DefaultTableModel Outer_Table = new DefaultTableModel(); // ******* 테이블준비하기(1/2) 바깥모양임
	private JTable Inner_Table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public static int detail_no;
	private JButton btnNext;
	private JButton btnPrev;

	
	/**
	 * Create the panel.
	 */
	public Panel4() {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				tableInit();
				searchAction();
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});

		setLayout(null);
		add(getLblPrice());
		add(getScrollPane());
		add(getLblNewLabel());
		add(getBtnCart());
		add(getLblNewLabel_1());

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(84, 242, 130, 26);
		add(textField);
		textField.setColumns(10);

		JLabel lblModel = new JLabel();
		lblModel.setText("모델  : ");
		lblModel.setBounds(33, 280, 39, 22);
		add(lblModel);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(84, 278, 130, 26);
		add(textField_1);

		JLabel lblBrand = new JLabel();
		lblBrand.setText("브랜드  : ");
		lblBrand.setBounds(23, 314, 49, 22);
		add(lblBrand);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(84, 313, 130, 26);
		add(textField_2);

		JLabel lblSize = new JLabel();
		lblSize.setText("사이즈 : ");
		lblSize.setBounds(23, 351, 45, 22);
		add(lblSize);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(84, 349, 130, 26);
		add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(262, 349, 130, 26);
		add(textField_4);
		add(getBtnNext());
		add(getBtnPrev());

	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(23, 48, 400, 184);
			scrollPane.setViewportView(getInner_Table());
		}
		return scrollPane;
	}

	private JTable getInner_Table() {
		if (Inner_Table == null) {
			Inner_Table = new JTable();
			Inner_Table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						tableClick();
					}

				}
			});
			Inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Inner_Table.setModel(Outer_Table); // 테이블 세팅하기(2/2) **********

		}
		return Inner_Table;
	}

	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel();
			lblPrice.setText("가격 : ");
			lblPrice.setBounds(33, 244, 39, 22);
		}
		return lblPrice;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("개");
			lblNewLabel.setBounds(402, 354, 61, 16);
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("< 장바구니 >");
			lblNewLabel_1.setBounds(185, 20, 83, 16);
		}
		return lblNewLabel_1;
	}

	private JButton getBtnCart() {
		if (btnCart == null) {
			btnCart = new JButton("장바구니에 담기");
			btnCart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertToCartAction();

				}
			});
			btnCart.setBounds(240, 382, 173, 29);
		}
		return btnCart;
	}

	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("결제창으로 넘어가기");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					Main.frame.getContentPane().add(new Panel5());
				}
			});
			btnNext.setBounds(240, 423, 173, 29);
		}
		return btnNext;
	}

	private JButton getBtnPrev() {
		if (btnPrev == null) {
			btnPrev = new JButton("상품 목록으로 돌아가기");
			btnPrev.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					Main.frame.getContentPane().add(new Panel3());
				}
			});
			btnPrev.setBounds(64, 423, 173, 29);
		}
		return btnPrev;
	}

	// -------------------------------- Function -----------------------------------

	private void tableInit() {
		Outer_Table.addColumn("detail_no");
		Outer_Table.addColumn("사이즈");
		Outer_Table.addColumn("재고");

		Outer_Table.setColumnCount(3);

		int i = Outer_Table.getRowCount();
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0);
		}

		Inner_Table.setAutoResizeMode(Inner_Table.AUTO_RESIZE_OFF);

		int vColIndex = 0;
		TableColumn col = Inner_Table.getColumnModel().getColumn(vColIndex);
		int width = 50;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
	}

	private void searchAction() {
		DaoDetail daoDetail = new DaoDetail();
		ArrayList<DtoDetail> dtoDetailList = daoDetail.searchAction();

		for (DtoDetail a : dtoDetailList) {
			String[] qTxt = { Integer.toString(a.getDetail_no()), Integer.toString(a.getSize()),
					Integer.toString(a.getStock()) };
			Outer_Table.addRow(qTxt);
		}

	}
	
	private void tableClick() {
		
			int selectRow = Inner_Table.getSelectedRow();
			String wkSequence = (String) Inner_Table.getValueAt(selectRow, 0);
			detail_no = Integer.parseInt(wkSequence);
			DaoDetail dao = new DaoDetail(detail_no);
			DtoDetail dto = dao.tableClick();
			int a = dto.getP_product_no();
			DaoProduct daoPro = new DaoProduct(a);
			DtoProduct dtoPro = daoPro.tableClick();
			
			textField_3.setText(Integer.toString(dto.getSize()));
			textField_1.setText(dtoPro.getModel());
			textField.setText(Integer.toString(dtoPro.getPrice()));
			textField_2.setText(dtoPro.getBrand());
	}
	
	private void insertToCartAction() {
		try {
			int num = Integer.parseInt(textField_4.getText().trim());
			DaoCart dao = new DaoCart(Integer.parseInt(textField.getText().trim()), num);
			dao.insertToCartAction();
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
} // End

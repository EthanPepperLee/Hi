package com.javalec.base;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.DaoProduct;
import com.javalec.dto.DtoProduct;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class Panel3 extends JPanel {
	private JScrollPane scrollPane;
	private JComboBox cbSelection;
	private JTextField tfSelection;
	private JButton btnQuery;
	private JButton btnDetail;
	private JButton btnShowAll;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	private final DefaultTableModel Outer_Table = new DefaultTableModel(); // ******* 테이블준비하기(1/2) 바깥모양임
	public static int clickNo;
	public static String clickBrand;
	public static String clickModel;
	public static int clickPrice;
	private JTable table;
	private JTable Inner_Table;

	/**
	 * Create the panel.
	 */
	public Panel3() {
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
		
		add(getScrollPane());
		add(getCbSelection());
		add(getTfSelection());
		add(getBtnQuery());
		add(getBtnDetail());
		add(getBtnShowAll());
		add(getLblNewLabel());
		add(getLblNewLabel_1());
		add(getLblNewLabel_2());
		
		JButton btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel1.id = "";
				setVisible(false);
				Main.frame.getContentPane().add(new Panel1());
			}
		});
		btnLogout.setBounds(18, 18, 117, 29);
		add(btnLogout);

	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(18, 87, 413, 275);
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
					if (e.getButton() == 1) { // 좌측마우스 클릭 -> 1
						tableClick(); // 테이블 클릭 시에 해당 테이블 행의 정보를 int clickNo에 저장
					}

				}
			});
			Inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Inner_Table.setModel(Outer_Table);
		}

		return Inner_Table;
	}

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setModel(new DefaultComboBoxModel(new String[] { "브랜드", "상품명" }));
			cbSelection.setBounds(18, 375, 95, 27);
		}
		return cbSelection;
	}

	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setColumns(10);
			tfSelection.setBounds(108, 374, 147, 26);
		}
		return tfSelection;
	}

	private JButton getBtnQuery() {
		if (btnQuery == null) {
			btnQuery = new JButton("검색");
			btnQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery();
				}
			});
			btnQuery.setBounds(251, 374, 74, 29);
		}
		return btnQuery;
	}

	private JButton getBtnDetail() {
		if (btnDetail == null) {
			btnDetail = new JButton("상세정보");
			btnDetail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					Main.frame.getContentPane().add(new Panel4());
				}
			});
			btnDetail.setBounds(274, 46, 87, 29);
		}
		return btnDetail;
	}

	private JButton getBtnShowAll() {
		if (btnShowAll == null) {
			btnShowAll = new JButton("전체보기");
			btnShowAll.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableInit();
					searchAction();
				}
			});
			btnShowAll.setBounds(337, 374, 94, 29);
		}
		return btnShowAll;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("테이블에서 상품을 클릭 후");
			lblNewLabel.setBounds(145, 51, 147, 16);
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("를 눌러주세요!");
			lblNewLabel_1.setBounds(357, 51, 74, 16);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("< 상품 목록 >");
			lblNewLabel_2.setBounds(180, 23, 87, 16);
		}
		return lblNewLabel_2;
	}

	// ------------------------------- Function -------------------------------

	private void tableInit() {
		Outer_Table.addColumn("순서"); // 1234
		Outer_Table.addColumn("브랜드");
		Outer_Table.addColumn("상품명");
		Outer_Table.addColumn("가격");

		Outer_Table.setColumnCount(4); // ***************

		int i = Outer_Table.getRowCount();
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0);
		}

		Inner_Table.setAutoResizeMode(Inner_Table.AUTO_RESIZE_OFF); // 리사이즈 못하게 정의

		int vColIndex = 0;
		TableColumn col = Inner_Table.getColumnModel().getColumn(vColIndex); // 0번부터
		int width = 50;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 140;
		col.setPreferredWidth(width);

		vColIndex = 3;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);

	}

	// DB to Table
	private void searchAction() {
		DaoProduct dao = new DaoProduct();
		dao.selectList();
		ArrayList<DtoProduct> dtoList = dao.selectList();

		int listCount = dtoList.size();

		for (int index = 0; index < listCount; index++) {
			String temp = Integer.toString(dtoList.get(index).getNo());
			String[] qTxt = { temp, dtoList.get(index).getBrand(), dtoList.get(index).getModel(),
					Integer.toString(dtoList.get(index).getPrice()) };
			Outer_Table.addRow(qTxt);

		}
	}

	// ---- <<< conditionQuery function / 검색항목상태 >>>
	// ----------------------------------------------------
	private void conditionQuery() {
		int i = cbSelection.getSelectedIndex();
		String conditionQueryColumn = "";
		switch (i) {
		case 0:
			conditionQueryColumn = "brand";
			break;
		case 1:
			conditionQueryColumn = "model";
			break;
		default:
			break;
		}
		tableInit();
		conditionQueryAction(conditionQueryColumn);
	}

	// ---- <<< conditionQueryAction function / >>>
	// ------------------------------------------------
	private void conditionQueryAction(String conditionQueryColumn) {

		DaoProduct dao = new DaoProduct(conditionQueryColumn, tfSelection.getText().trim());
		ArrayList<DtoProduct> dtoList = dao.conditionList();

		int listCount = dtoList.size();

		for (int index = 0; index < listCount; index++) {
			String temp = Integer.toString(dtoList.get(index).getNo());
			String[] qTxt = { temp, dtoList.get(index).getBrand(), dtoList.get(index).getModel(),
					Integer.toString(dtoList.get(index).getPrice()) };
			Outer_Table.addRow(qTxt);

		}
	}

	// 테이블 클릭 시에 해당 테이블 행의 정보를 int clickNo에 저장
	private void tableClick() {
		int i = Inner_Table.getSelectedRow();
		String wkSequence = (String) Inner_Table.getValueAt(i, 0); // String type으로 바꿔준다
		DaoProduct dao = new DaoProduct(Integer.parseInt(wkSequence));

		DtoProduct dto = dao.tableClick();

		clickNo = dto.getNo();
		clickBrand = dto.getBrand();
		clickModel = dto.getModel();
		clickPrice = dto.getPrice();

	}
} // End

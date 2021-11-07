package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.AddService_Order;
import controller.DeleteService_Order;
import controller.EditService_Order;
import model.DBOrder;
import model.DetailOrder;
import model.Order;

public class DetailPanelWithManipulateData_Order extends JPanel {

	
	private static String[] _columnNames;	
	private static DefaultTableModel _tableModel;
	private static JTable _detailContentTable;
	private ArrayList<JPanel> _paddingPanels;
	private ArrayList<JButton> _dataManipulateButtons;
	private String _titled;
	
	private void _setDataToDetailPanel(int index) {
		if (-1 == index) {
			clearAllData();
			return;
		}
		
		readData(DBOrder.currentOrders().get(index - 1));
	}
	
	private void makeDetailContentTable() {
		_detailContentTable = new JTable();
		
		//Make uneditable table
		_tableModel = new DefaultTableModel(_columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
									
			//all cells false
			return false;
			}
		};
		
		//enable table model
		_detailContentTable.setModel(_tableModel);
	}

	private void makePaddingPanelAndButtonsVar() {
		
		//make 3 padding panels
		_paddingPanels = new ArrayList<JPanel>();
		for (int i = 0; i < 3; ++i) {
			_paddingPanels.add(new JPanel());
		}
		
		
		//Make {Thêm, Xóa, Sửa} buttons
		_dataManipulateButtons = new ArrayList<JButton>();
		_dataManipulateButtons.add(new JButton("Thêm"));
		_dataManipulateButtons.add(new JButton("Xóa"));
		_dataManipulateButtons.add(new JButton("Sửa"));
		
		
	}
	
	private void setLayout() {
		//Make 2 padding panels
		for (int i = 0; i < 2; ++i) {
			_paddingPanels.add(new JPanel());
			this.add(_paddingPanels.get(i));	
			_paddingPanels.get(i).setBackground(this.getBackground());
		}	
		
		//Add detail table into bottom part of detail panel
		_paddingPanels.get(0).setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane(_detailContentTable);
		_paddingPanels.get(0).add(scroll);
		
		//Add 3 buttons into below part of detail panel
		_paddingPanels.get(1).setLayout(new FlowLayout());
		for (JButton button: _dataManipulateButtons) {
			_paddingPanels.get(1).add(button);
		}
		
		//Make 2 horizontal panel layout, one size 2/3, another 1/3
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 1;
		gbc.gridheight = 2;
		this.add(_paddingPanels.get(0), gbc);
		
		gbc.gridy = 2;
		gbc.gridheight = 1;
		this.add(_paddingPanels.get(1), gbc);
		
	}
	
	public DetailPanelWithManipulateData_Order() {}
	public DetailPanelWithManipulateData_Order(String titled, String[] attributeFieldNames) {
		_titled = new String(titled);
		
		//Add titled
		Border border = BorderFactory.createTitledBorder(_titled);
		this.setBorder(border);
		_columnNames = attributeFieldNames;
		makeDetailContentTable();
		makePaddingPanelAndButtonsVar();
		setLayout();
		
		
		_dataManipulateButtons.get(0).addActionListener(new AddService_Order());
		_dataManipulateButtons.get(1).addActionListener(new DeleteService_Order());
		_dataManipulateButtons.get(2).addActionListener(new EditService_Order());
		
	}
	public void setDataToDetailPanel(int index) { _setDataToDetailPanel(index); }
	public static void clearAllData() {_tableModel.setRowCount(0); }
	final public static void readData(Order data) {
		clearAllData();
		int index = 0;
		for (DetailOrder detail: data.detailOrders()) {
			++index;
			Object[] buffer = {index, detail.serie().name(), detail.serie().price(), detail.quantity()};
			_tableModel.addRow(buffer);
		}
	}
	

}

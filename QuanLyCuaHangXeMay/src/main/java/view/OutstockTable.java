package view;


import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import controller.ClickedRowService_MotorcycleCompany;
import controller.ClickedRowService_Order;
import model.DBOrder;
import model.MotorcycleCompany;
import model.MotorcycleSerie;
import model.Order;

public class OutstockTable extends TablePanel<Order> {
	private static OutstockTable _table;
	private static boolean isInit;
	protected static DefaultTableModel _tableModel;
	protected static String[] _columnNames;	
	protected static JPanel _detailInformationPanel;
	protected static JPanel _headerPanel;
	
	public OutstockTable() {
		isInit = false;
	}
	
	public static void clearAllData() {
		if (!isInit) {
			init();
		}
		_tableModel.setRowCount(0);
	}
	
	public static Order getSelectedOrder() {
		int row = _table.getSelectedRow();
		//Get the "STT" value
		int index = (Integer) _table.getValueAt(row, 0);
		return DBOrder.currentOrders().get(index - 1);
	}
	
	public static void init() {
		_table = new OutstockTable();
		
		//Setup for the table
		_columnNames = new String[]{"STT", "Tên dòng xe", "Hãng xe", "Số lượng tồn"};
				
		//Make uneditable table
		_tableModel = new DefaultTableModel(_columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
							
				//all cells false
				return false;
			}
		};
				
		//enable table model
		_table.setModel(_tableModel);
				
		//Setup for the detail panel
		//_detailInformationPanel = new MotorcycleCompanyDetailPanel();
		_detailInformationPanel = new JPanel();
						
		//Setup for the header panel
		_headerPanel = new JPanel();
		isInit = true;
	}
	
	public static void readData(ArrayList<Object[]> data) {	
		if (!isInit) {
			init();
		}
		
		clearAllData();
		int index = 0;
		for (Object[] object: data) {
			
			//Object[] serieBuffer = {index, serie.name(), serie.companyID()};
			
			MotorcycleSerie serieBuffer = (MotorcycleSerie) object[0];
			MotorcycleCompany companyBuffer = (MotorcycleCompany) object[1];
			int quantity = serieBuffer.getQuantityInStoraged();
			
			if (quantity < 10) {
			++ index;
			Object[] buffer = {index, serieBuffer.name(), companyBuffer.name(), quantity};
			_tableModel.addRow(buffer);
			}
		}
	}
	
	
	protected static Object[] _getSelectedRowInfo() {
		if (!isInit) {
			init();
		}
		
		Object[] result = new Object[_tableModel.getColumnCount()];
		
		int row = _table.getSelectedRow();
		for (int i = 0; i < result.length; ++i) {
			result[i] = _table.getValueAt(row, i);
		}
		
		return result;
	}
	
	public static Object[] getSelectedRowInfo() {
		if (!isInit) {
			init();
		}
		return _getSelectedRowInfo();
	}
	
	public static JPanel detailInformationPanel() {
		if (!isInit) {
			init();
		}
		return  _detailInformationPanel;
	}
	
	public static OutstockTable table() {
		if (!isInit) {
			init();
		}
		return _table;
	}
	
	public static JPanel headerPanel() {
		if (!isInit) {
			init();
		}
		return _headerPanel;
	}
	
	
}


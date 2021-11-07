package view;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import controller.ClickedRowService_MotorcycleCompany;
import controller.ClickedRowService_Order;
import model.DBOrder;
import model.MotorcycleCompany;
import model.Order;

public class OrderTable extends TablePanel<Order> {
	private static OrderTable _table;
	private static boolean isInit;
	protected static DefaultTableModel _tableModel;
	protected static String[] _columnNames;	
	protected static JPanel _detailInformationPanel;
	protected static JPanel _headerPanel;
	
	public OrderTable() {
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
		_table = new OrderTable();
		
		//Setup for the table
		_columnNames = new String[]{"STT", "Ngày đặt hàng", "Số tiền"};
				
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
		_detailInformationPanel = new DetailPanelWithManipulateData_Order(new String("Thông tin đơn hàng"),
				new String[]{"STT", "Tên xe", "Đơn giá", "Số lượng"});
				
		_table.addMouseListener(new ClickedRowService_Order((DetailPanelWithManipulateData_Order)_detailInformationPanel));
				
				
		//Setup for the header panel
		_headerPanel = new JPanel();
		isInit = true;
	}
	
	final public static void readData(ArrayList<Order> data) {
		if (!isInit) {
			init();
		}
		clearAllData();
		int index = 0;
		for (Order order: data) {
			++ index;
			Object[] buffer = {index, order.orderDay(), order.totalTurnover()};
			_tableModel.addRow(buffer);
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
	
	
	/*
	public static void updateDetailPanel(Object[] data) {
		if (!isInit) {
			init();
		}
		((DetailPanelWithManipulateData_Order)_detailInformationPanel).updateInformation(data);
	}*/
	
	public static Object[] getSelectedRowInfo() {
		if (!isInit) {
			init();
		}
		return _getSelectedRowInfo();
	}
	
	public static DetailPanelWithManipulateData_Order detailInformationPanel() {
		if (!isInit) {
			init();
		}
		return (DetailPanelWithManipulateData_Order) _detailInformationPanel;
	}
	
	public static OrderTable table() {
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

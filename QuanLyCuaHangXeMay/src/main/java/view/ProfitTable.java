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

public class ProfitTable extends TablePanel<Order> {
	private static ProfitTable _table;
	private static boolean isInit;
	protected static DefaultTableModel _tableModel;
	protected static String[] _columnNames;	
	protected static JPanel _detailInformationPanel;
	protected static JPanel _headerPanel;
	
	public ProfitTable() {
		isInit = false;
	}
	
	public static void clearAllData() {
		if (!isInit) {
			init();
		}
		_tableModel.setRowCount(0);
	}

	
	public static void init() {
		_table = new ProfitTable();
		
		//Setup for the table
		_columnNames = new String[]{};
				
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
		_detailInformationPanel = new DetailPanelForQueryWithMonthYear();
						
		//Setup for the header panel
		_headerPanel = new JPanel();
		isInit = true;
	}
	
	
	public static JPanel detailInformationPanel() {
		if (!isInit) {
			init();
		}
		return  _detailInformationPanel;
	}
	
	public static ProfitTable table() {
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



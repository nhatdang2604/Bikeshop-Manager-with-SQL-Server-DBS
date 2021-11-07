package view;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import controller.ClickedRowService_MotorcycleCompany;
import model.MotorcycleCompany;

@SuppressWarnings("serial")
public class MotorcycleCompanyTable extends TablePanel<MotorcycleCompany> {

	private static MotorcycleCompanyTable _table;
	private static boolean isInit;
	protected static DefaultTableModel _tableModel;
	protected static String[] _columnNames;	
	protected static JPanel _detailInformationPanel;
	protected static JPanel _headerPanel;
	
	public MotorcycleCompanyTable() {
		isInit = false;
	}
	
	public static void clearAllData() {
		if (!isInit) {
			init();
		}
		_tableModel.setRowCount(0);
	}
	
	public static void init() {
		_table = new MotorcycleCompanyTable();
		
		//Setup for the table
		_columnNames = new String[]{"STT", "Tên hãng", "Quốc gia", "Năm thành lập"};
				
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
		_detailInformationPanel = new DetailPanelWithManipulateData_MotorcycleCompany(new String("Thông tin hãng xe"),
				new String[]{"Tên hãng:", "Quốc gia:", "Năm thành lập:"});
				
		_table.addMouseListener(new ClickedRowService_MotorcycleCompany((DetailPanel)_detailInformationPanel));
				
				
		//Setup for the header panel
		_headerPanel = new JPanel();
		isInit = true;
	}
	
	final public static void readData(ArrayList<MotorcycleCompany> data) {
		if (!isInit) {
			init();
		}
		clearAllData();
		int index = 0;
		for (MotorcycleCompany company: data) {
			++ index;
			Object[] buffer = {index, company.name(), company.country(), company.year()};
			_tableModel.addRow(buffer);
		}
		
	}
	
	protected static Object[] _getSelectedRowInfo() {
		if (!isInit) {
			init();
		}
		
		//Don't get index column
		Object[] result = new Object[_tableModel.getColumnCount()];
		
		int row = _table.getSelectedRow();
		for (int i = 0; i < result.length; ++i) {
			result[i] = _table.getValueAt(row, i);
		}
		
		return result;
	}
	
	
	/*
	public MotorcycleCompanyTable() {
		
		//Setup for the table
		_columnNames = new String[]{"STT", "Tên hãng", "Quốc gia", "Năm thành lập"};
		
		//Make uneditable table
		_tableModel = new DefaultTableModel(_columnNames, 0) {
			
				@Override
				public boolean isCellEditable(int row, int column) {
					
					//all cells false
					return false;
				}
		};
		
		//enable table model
		this.setModel(_tableModel);
		
		//Setup for the detail panel
		//_detailInformationPanel = new MotorcycleCompanyDetailPanel();
		_detailInformationPanel = new DetailPanelWithManipulateData_MotorcycleCompany(new String("Thông tin hãng xe"),
				new String[]{"Tên hãng:", "Quốc gia:", "Năm thành lập:"}, this);
		
		this.addMouseListener(new ClickedRowService_MotorcycleCompany((DetailPanel)_detailInformationPanel));
		
		//((DetailPanel)_detailInformationPanel).updateInformation(new Object[] {
			//	"Honda", "Nhật", 2021});
		
		//Setup for the header panel
		_headerPanel = new JPanel();
		
	}*/
	
	public static void updateDetailPanel(Object[] data) {
		if (!isInit) {
			init();
		}
		((DetailPanelWithManipulateData_MotorcycleCompany)_detailInformationPanel).updateInformation(data);
	}
	
	public static Object[] getSelectedRowInfo() {
		if (!isInit) {
			init();
		}
		return _getSelectedRowInfo();
	}
	
	public static DetailPanelWithManipulateData_MotorcycleCompany detailInformationPanel() {
		if (!isInit) {
			init();
		}
		return (DetailPanelWithManipulateData_MotorcycleCompany) _detailInformationPanel;
	}
	
	public static MotorcycleCompanyTable table() {
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

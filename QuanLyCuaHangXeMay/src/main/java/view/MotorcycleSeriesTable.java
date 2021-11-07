package view;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import controller.ClickedRowService_MotorcycleCompany;
import controller.ClickedRowService_MotorcycleSerie;
import controller.DetailPanelWithManipulateData_MotorcycleSerie;
import model.DBMotorcycleCompany;
import model.MotorcycleCompany;
import model.MotorcycleSerie;

@SuppressWarnings("serial")
public class MotorcycleSeriesTable extends TablePanel<MotorcycleSerie>  {
	private static MotorcycleSeriesTable _table;
	private static boolean isInit;
	protected static DefaultTableModel _tableModel;
	protected static String[] _columnNames;	
	protected static JPanel _detailInformationPanel;
	protected static JPanel _headerPanel;
	
	public MotorcycleSeriesTable() {
		isInit = false;
	}
	
	public static void clearAllData() {
		if (!isInit) {
			init();
		}
		
		_tableModel.setRowCount(0);
	}
	
	public static void init() {
		_table = new MotorcycleSeriesTable();
		
		//Setup for the table
		_columnNames = new String[]{"STT", "Tên dòng xe", "Hãng xe"};
				
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
		_detailInformationPanel = new DetailPanelWithManipulateData_MotorcycleSerie(new String("Thông tin dòng xe"),
				new String[]{"Tên dòng xe:", "Hãng xe:", "Giá nhập:", "Giá bán:", "Số lượng tồn kho: "});
				
		
		_table.addMouseListener(new ClickedRowService_MotorcycleSerie((DetailPanel)_detailInformationPanel));
				
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
			++ index;
			//Object[] serieBuffer = {index, serie.name(), serie.companyID()};
			
			MotorcycleSerie serieBuffer = (MotorcycleSerie) object[0];
			MotorcycleCompany companyBuffer = (MotorcycleCompany) object[1];
			Object[] buffer = {index, serieBuffer.name(), companyBuffer.name()};
			
			_tableModel.addRow(buffer);
		}
	}
	

	static protected Object[] _getSelectedRowInfo() {
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
	public MotorcycleSeriesTable() {

		//Setup for the table
		_columnNames = new String[]{"STT", "Tên dòng xe", "Hãng xe"};
		
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
		
		//TESTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
		//String[] companies = DBMotorcycleCompany.getCurrentCompany().toArray(new String[0]);
		
		
		//Setup for the detail panel
		//_detailInformationPanel = new MotorcycleCompanyDetailPanel();
		_detailInformationPanel = new DetailPanelWithManipulateData_MotorcycleSerie(new String("Thông tin dòng xe"),
				new String[]{"Tên dòng xe:", "Hãng xe:", "Giá nhập:", "Giá bán:", "Số lượng tồn kho: "}, this);
		
		//((DetailPanel)_detailInformationPanel).updateInformation(new Object[] {
			//	"Honda", "Nhật", 2021});
		this.addMouseListener(new ClickedRowService_MotorcycleSerie((DetailPanel)_detailInformationPanel));
		
		//Setup for the header panel
		_headerPanel = new JPanel();
		
	}*/
	
	public static void updateDetailPanel(Object[] data) {
		if (!isInit) {
			init();
		}
		((DetailPanelWithManipulateData_MotorcycleSerie)_detailInformationPanel).updateInformation(data);
	}


	public static Object[] getSelectedRowInfo() {
		if (!isInit) {
			init();
		}
		return _getSelectedRowInfo();
	}
	
	public static MotorcycleSeriesTable table() {
		if (!isInit) {
			init();
		}
		return _table;
	}
	
	public static DetailPanelWithManipulateData_MotorcycleSerie detailInformationPanel() {
		return (DetailPanelWithManipulateData_MotorcycleSerie)_detailInformationPanel; 
	}
	public static JPanel headerPanel() {
		if (!isInit) {
			init();
		}
		return _headerPanel;
		
	}
}

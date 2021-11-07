package view;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public abstract class TablePanel<T> extends JTable {
	
	//protected static DefaultTableModel _tableModel;
	//protected static String[] _columnNames;	
	//protected static JPanel _detailInformationPanel;
	//protected static JPanel _headerPanel;
	
	
	//protected abstract void _setupHeaderPanel();
	protected static Object[] _getSelectedRowInfo() {
		//do nothing
		Object[] object = {1};
		return object;
	}
	
	
	//public TablePanel() {
		 //_tableModel = new DefaultTableModel();
	//}
	
	//public abstract void readData(ArrayList<T> data);
	public static void updateDetailPanel(Object[] data) {
		//do nothing
	}
	
	//public static void clearAllData() {
		//_tableModel.setRowCount(0);
	//}
	
	//public static JPanel detailInformationPanel() {return _detailInformationPanel; }
	//public static JPanel headerPanel() {return _headerPanel;}
}

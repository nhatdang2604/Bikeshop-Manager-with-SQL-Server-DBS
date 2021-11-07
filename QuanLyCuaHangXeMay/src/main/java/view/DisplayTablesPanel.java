package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import controller.ChangeTabService;
import model.DBMotorcycleCompany;
import model.DBMotorcycleSerie;
import model.DBOrder;
import model.MotorcycleCompany;

public class DisplayTablesPanel extends JPanel {

	
	protected JTabbedPane _tabbedPanel;
	protected PaginationPanel _paginationPanel;
	
	protected ArrayList<JPanel> _backgroundPanels;
	protected ArrayList<JTable> _tables;
	protected ArrayList<JPanel> _detailsInformationPanels;
	protected ArrayList<JPanel> _headerPanels;
	
	protected JTable _currentTable;
	protected JPanel _currentDetailInformationPanel;
	protected JPanel _currentHeaderPanel;
	
	
	protected void _setLayout() {
		double heightRatio = 0.95;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 1;
		gbc.weighty = heightRatio;
		this.add(_tabbedPanel, gbc);
		
		gbc.gridy = 1;
		gbc.weighty = 1 - heightRatio;
		this.add(_paginationPanel, gbc);
		
	}
	
	public DisplayTablesPanel(ArrayList<JPanel> backgroundPanels) {
		_backgroundPanels = backgroundPanels;
		_tabbedPanel = new JTabbedPane();
		_paginationPanel = new PaginationPanel();
		_tables = new ArrayList<JTable>();
		_detailsInformationPanels = new ArrayList<JPanel>();
		_headerPanels = new ArrayList<JPanel>();
		_paginationPanel.setBackground(new Color(230, 242, 255));
		
		_setLayout();
		
		
		
		/*
		//TESTTTTTTTTTTTTTTTTTTTTTTTTTTT
		MotorcycleCompanyTable test = new MotorcycleCompanyTable();
		ArrayList<MotorcycleCompany> data = new ArrayList<MotorcycleCompany>();
		for (int i = 0; i< 101; ++i) {
			data.add(new MotorcycleCompany(i, "Honda", "Nhật", 1990));
			//data.add(new MotorcycleCompany(1, "Honda", "Nhật", 1990));
		}
		test.readData(data);
		
		addTable(test, "test");
		addDetail(test.detailInformationPanel());
		addHeader(test.headerPanel());
		
		_currentTable = test;
		_currentDetailInformationPanel = test.detailInformationPanel();
		_currentHeaderPanel = test.headerPanel();*/
	}
	
	public void addTable(JTable table, String name) {
		_tables.add(table);
		JScrollPane scroll = new JScrollPane(table);
		_tabbedPanel.addTab(name, scroll);
		
		
	}
	
	public void addHeader(JPanel header) {
		_headerPanels.add(header);
	}
	
	
	public void addDetail(JPanel detailPanel) {
		_detailsInformationPanels.add(detailPanel);
	}
	
	public JTabbedPane tabbedPanel() {return _tabbedPanel;}
	public ArrayList<JTable> tables() {return _tables; };
	public ArrayList<JPanel> detailsInformationPanels() {return _detailsInformationPanels; }
	public ArrayList<JPanel> headerPanels() {return _headerPanels; }
	public JTable currentTable() {return _currentTable; }
	public JPanel currentDetailInformationPanel() {return _currentDetailInformationPanel; }
	public JPanel currentHeaderPanel() {return _currentHeaderPanel; }
	
	public void setCurrentTable(JTable table) {_currentTable = table; }
	public void setCurrentDetailInformationPanel(JPanel panel) {_currentDetailInformationPanel = panel; }
	public void setCurrentHeaderPanel(JPanel panel) {_currentHeaderPanel = panel; }
}


class Requirement1Panel extends DisplayTablesPanel {
	
	private void makeMotorcycleCompanyModule() {
		MotorcycleCompanyTable.readData(DBMotorcycleCompany.currentCompanies());
		this.addTable(MotorcycleCompanyTable.table(), "Hãng xe");
		this.addDetail(MotorcycleCompanyTable.detailInformationPanel());
		this.addHeader(MotorcycleCompanyTable.headerPanel());
		
	}
	
	private void makeMotorcycleSerieModule() {
		MotorcycleSeriesTable.readData(DBMotorcycleSerie.currentSeries());
		this.addTable(MotorcycleSeriesTable.table(), "Dòng xe");
		this.addDetail(MotorcycleSeriesTable.detailInformationPanel());
		this.addHeader(MotorcycleSeriesTable.headerPanel());
	}
	
	public Requirement1Panel(ArrayList<JPanel> backgroundPanels) {
		super(backgroundPanels);
		
		//Add 2 modules of requirement 1
		makeMotorcycleCompanyModule();
		makeMotorcycleSerieModule();
		
		//Change detail panel and header panel while change tab
		_tabbedPanel.addMouseListener(new ChangeTabService(this, _backgroundPanels));
		
		//Default tab is 0
		_currentTable = _tables.get(0);
		_currentDetailInformationPanel = _detailsInformationPanels.get(0);
		_currentHeaderPanel = _headerPanels.get(0);
		
	}
}

class Requirement2Panel extends DisplayTablesPanel {
	
	private void makeOrderModule() {
		DBMotorcycleSerie.updateSeriesBuffer();
		OrderTable.readData(DBOrder.currentOrders());
		this.addTable(OrderTable.table(), "Đơn hàng");
		this.addDetail(OrderTable.detailInformationPanel());
		this.addHeader(OrderTable.headerPanel());
	}
	
	public Requirement2Panel(ArrayList<JPanel> backgroundPanels) {
		super(backgroundPanels);
		
		//Add modules of requirement 2
		makeOrderModule();
		
		
		//Default tab is 0
		_currentTable = _tables.get(0);
		_currentDetailInformationPanel = _detailsInformationPanels.get(0);
		_currentHeaderPanel = _headerPanels.get(0);
		
	}

}

class Requirement3Panel extends DisplayTablesPanel {
	
	private void makeOutOfStockModule() {
		//DBMotorcycleSerie.updateSeriesBuffer();
		OutstockTable.readData(DBMotorcycleSerie.currentSeries());
		this.addTable(OutstockTable.table(), "Sắp hết");
		this.addDetail(OutstockTable.detailInformationPanel());
		this.addHeader(OutstockTable.headerPanel());
	}
	
	private void makeProfitModule() {
		this.addTable(ProfitTable.table(), "Doanh thu/Lợi nhuận");
		this.addDetail(ProfitTable.detailInformationPanel());
		this.addHeader(ProfitTable.headerPanel());
	}
	
	public Requirement3Panel(ArrayList<JPanel> backgroundPanels) {
		super(backgroundPanels);
		
		//Add modules of requirement 3
		makeOutOfStockModule();
		makeProfitModule();
		
		_tabbedPanel.addMouseListener(new ChangeTabService(this, _backgroundPanels));
		
		//Default tab is 0
		_currentTable = _tables.get(0);
		_currentDetailInformationPanel = _detailsInformationPanels.get(0);
		_currentHeaderPanel = _headerPanels.get(0);
		
	}

}

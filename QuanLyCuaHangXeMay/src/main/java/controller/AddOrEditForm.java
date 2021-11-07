package controller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import model.DBMotorcycleSerie;
import model.MotorcycleSerie;
import model.Order;
import view.MotorcycleCompanyTable;
import view.MotorcycleSeriesTable;

public abstract class AddOrEditForm extends JDialog implements ActionListener {
	protected boolean _isAdd;
	protected ArrayList<JButton> _buttons; //Yes/No button
	protected abstract void _addListenerForButtons();
	
}


class AddOrEditForm_MotorcycleCompany extends AddOrEditForm {
	private ArrayList<JLabel> _labels;	//labels for type field
	private ArrayList<JTextField> _typeFields;	//type field to add information
	
	@Override
	protected void _addListenerForButtons() {
		_buttons.get(0).addActionListener(new ProcessAddOrEditForm_MotorcycleCompany(this, _typeFields, _isAdd));
		_buttons.get(1).addActionListener(new CancelAddOrEditForm(this));
	}
	
	private void _makeForm() {
		
		this.setLayout(new GridLayout(_labels.size() + 1, 2));
		
		
		for (int i = 0; i<_labels.size(); ++i) {
			this.add(_labels.get(i));
			_labels.get(i).setLabelFor(_typeFields.get(i));
			this.add(_typeFields.get(i));
		}
		
		//Add a padding dummy panel;
		this.add(new JPanel());
				
		JPanel buttonPanel = new JPanel();
		for (JButton button: _buttons) {
			buttonPanel.add(button);
		}
		this.add(buttonPanel);

	}
	
	public AddOrEditForm_MotorcycleCompany(boolean isAdd) {
		_isAdd = isAdd;
		this.setTitle(_isAdd?"Thêm":"Sửa");
		//super(owner, modal, isAdd);
		
		//Create 2 buttons
		_buttons = new ArrayList<JButton>();
		_buttons.add(new JButton(_isAdd?"Thêm": "Sửa"));
		_buttons.add(new JButton("Hủy"));
		
		//Create 3 information titleds
		_labels = new ArrayList<JLabel>();
		_labels.add(new JLabel("Tên hãng xe:", JLabel.TRAILING));
		_labels.add(new JLabel("Quốc gia:", JLabel.TRAILING));
		_labels.add(new JLabel("Năm thành lập:", JLabel.TRAILING));
		
		//Create 3 information type areas
		_typeFields = new ArrayList<JTextField>();
		for (int i =0; i < _labels.size(); ++i) {
			_typeFields.add(new JTextField(30));
		}
		
		_makeForm();
		_addListenerForButtons();
		this.setBounds(400, 200, 300, 150);
		this.setResizable(false);
		this.setModal(true);
	}

	public void actionPerformed(ActionEvent e) {
		//Coding part of Thêm/Sửa button
		if (e.getSource() == _buttons.get(0)) {
			
		
		}
		
		if (e.getSource() == _buttons.get(1)) {
			
			//End frame
			this.dispose();
		}
	}
}


@SuppressWarnings("serial")
class AddOrEditForm_MotorcycleSerie extends AddOrEditForm {
	
	private ArrayList<JLabel> _labels;	//labels for type field
	private ArrayList<JTextField> _typeFields;	//type field to add information
	private static JComboBox<String> _companyField;
	
	static public void updateCompanies() {
		DetailPanelWithManipulateData_MotorcycleSerie.updateCompanies();
		_companyField = new JComboBox<String>(DetailPanelWithManipulateData_MotorcycleSerie.companies());
	}
	
	
	@Override
	protected void _addListenerForButtons() {
		_buttons.get(0).addActionListener(new ProcessAddOrEditForm_MotorcycleSerie(this, _typeFields, _isAdd, _companyField));
		_buttons.get(1).addActionListener(new CancelAddOrEditForm(this));
		
	}
	
	private void _makeForm() {
		
		this.setLayout(new GridLayout(_labels.size() + 1, 2));
		
		for (int i = 0; i < _labels.size() - 1; ++i) {
			this.add(_labels.get(i));
			_labels.get(i).setLabelFor(_typeFields.get(i));
			this.add(_typeFields.get(i));
		}
		this.add(_labels.get(_labels.size() - 1));
		this.add(_companyField);
		
		//Add a padding dummy panel;
		this.add(new JPanel());
		
		JPanel buttonPanel = new JPanel();
		for (JButton button: _buttons) {
			buttonPanel.add(button);
		}
		this.add(buttonPanel);
	}
	
	@SuppressWarnings("unchecked")
	public AddOrEditForm_MotorcycleSerie(boolean isAdd) {
		_isAdd = isAdd;
		this.setTitle(_isAdd?"Thêm":"Sửa");
		
		//Create 2 buttons
		_buttons = new ArrayList<JButton>();
		_buttons.add(new JButton(_isAdd?"Thêm": "Sửa"));
		_buttons.add(new JButton("Hủy"));
		
		//Create 3 information titleds
		_labels = new ArrayList<JLabel>();
		_labels.add(new JLabel("Tên dòng xe:", JLabel.TRAILING));
		_labels.add(new JLabel("Giá nhập:", JLabel.TRAILING));
		_labels.add(new JLabel("Giá bán:", JLabel.TRAILING));
		_labels.add(new JLabel("Số lượng tồn kho: ", JLabel.TRAILING));
		_labels.add(new JLabel("Hãng xe:", JLabel.TRAILING));
		
		
		//Create comboBox for select "Hãng xe"
		_companyField = new JComboBox<String>(
				DetailPanelWithManipulateData_MotorcycleSerie.companies());
		
		//Create 3 information type areas
		_typeFields = new ArrayList<JTextField>();
		for (int i = 0; i < _labels.size(); ++i) {
			_typeFields.add(new JTextField(30));
		}
	
		_makeForm();
		_addListenerForButtons();
		this.setBounds(400, 200, 300, 200);
		this.setResizable(false);
		this.setModal(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		//Coding part of Thêm/Sửa button
		if (e.getSource() == _buttons.get(0)) {
			
			//Get data
			String serie = _typeFields.get(0).getText();
			int importPrice = Integer.parseInt(_typeFields.get(1).getText());
			int price = Integer.parseInt(_typeFields.get(2).getText());
			String company = (String) _companyField.getSelectedItem();
			
			//process
			
			//End frame
			this.dispose();
			return;
		}
		
		if (e.getSource() == _buttons.get(1)) {
			
			//End frame
			this.dispose();
			return;
		}
	}
}

class AddOrEditForm_Order extends AddOrEditForm {
	private static JTable _table;
	private static DefaultTableModel _tableModel;
	private static boolean _isInit = false;
	
	private JLabel _dateLabel;
	private JTextField _dateField;
	
	public JLabel dateLabel()  {return _dateLabel; }
	public JTextField dateField() {return _dateField; }
	public static JTable table() {return _table; }
	public boolean isAdd() {return _isAdd; }
	
	
	private static void init() {
		
		String[] columnNames = {"STT", "Tên xe", "Đơn giá", "Số lượng tồn kho" ,"Số lượng mua"};
		_tableModel = new DefaultTableModel(columnNames, 0);
		_table =  new JTable(_tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
							
				//Only "Số lượng" column can be editted
				if (column < 4) 
					return false;
				else 
					return true;
				
			}
			
			 @Override
	         public Class<?> getColumnClass(int columnIndex) {
				 
				 //"Số lượng" column must be written by an integer
	             return (4 == columnIndex? Integer.class: super.getColumnClass(columnIndex));
	         }
			
		};
		_isInit = true;
	}
	
	private static void clearAllData() {
		_tableModel.setRowCount(0);
	}
	
	private void makeForm() {
		
		JPanel padding = new JPanel();
		padding.setLayout(new BorderLayout());
		padding.add(new JScrollPane(_table), BorderLayout.CENTER);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 0.5;
		this.add(_dateLabel, gbc);
		
		gbc.gridx = 1;
		this.add(_dateField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 6;
		
		//this.add(new JScrollPane(_table), gbc);
		//this.add(_table, gbc);
		this.add(padding, gbc);
		
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridy = 7;
		gbc.weightx = 0.5;
		this.add(_buttons.get(0), gbc);
		gbc.gridx = 1;
		this.add(_buttons.get(1), gbc);
	}
	
	final public static void updateCurrentSerie() {
		
		if (!_isInit) {
			init();
		}
		
		clearAllData();
		int index = 0;
		for (Object[] object: DBMotorcycleSerie.getCurrentSeries()) {
			MotorcycleSerie motor = (MotorcycleSerie)object[0];
			
			++ index;
			
			//quantity is for user's input
			Object[] buffer = {index, motor.name(), motor.price(), motor.getQuantityInStoraged() , null};
			_tableModel.addRow(buffer);
		}
		
	}
	
	@SuppressWarnings("serial")
	public AddOrEditForm_Order(boolean isAdd) {
		_isAdd = isAdd;
		this.setTitle(_isAdd?"Thêm":"Sửa");
		
		//Create 2 buttons
		_buttons = new ArrayList<JButton>();
		_buttons.add(new JButton(_isAdd?"Thêm": "Sửa"));
		_buttons.add(new JButton("Hủy"));
		
		//Make date filed
		_dateLabel = new JLabel("Ngày đặt hàng (dd/mm/yyyy):", JLabel.TRAILING);
		_dateField = new JTextField();
		
		if (!_isInit) {init(); }
		
		makeForm();
		//updateCurrentSerie();
		_addListenerForButtons();
		
		this.setBounds(400, 200, 800, 600);
		this.setModal(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void _addListenerForButtons() {
		_buttons.get(0).addActionListener(new ProcessAddOrEditForm_Order(this));
		_buttons.get(1).addActionListener(new CancelAddOrEditForm(this));
		
	}
	
}
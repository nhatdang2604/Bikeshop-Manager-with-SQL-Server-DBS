package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import model.DBMotorcycleCompany;
import model.DBMotorcycleSerie;
import model.DBOrder;
import model.Motorcycle;
import model.MotorcycleCompany;
import model.MotorcycleSerie;
import view.MotorcycleCompanyTable;
import view.MotorcycleSeriesTable;
import view.OrderTable;
import view.OutstockTable;

public class ProcessAddOrEditForm_MotorcycleSerie implements ActionListener {
	
	private JDialog _parent;
	private ArrayList<JTextField> _contentFields;
	private JComboBox<String> _companyField;
	private boolean _isAdd;
	
	public ProcessAddOrEditForm_MotorcycleSerie(JDialog parent, 
			ArrayList<JTextField> contentFields, boolean isAdd,
			JComboBox<String> companyField) {
		_isAdd = isAdd;
		_parent = parent;
		_contentFields = contentFields;
		_companyField = companyField;
	}
	public void actionPerformed(ActionEvent arg0) {
		int index = -1;
		
		if(!_isAdd) {
			index = ClickedRowService_MotorcycleSerie.currentIndex();
			if (-1 == index) {
				return;
			}
		}
		
		--index;
		String serie = _contentFields.get(0).getText();
		int importPrice = Integer.parseInt(_contentFields.get(1).getText());
		int price = Integer.parseInt(_contentFields.get(2).getText());
		int quantity = Integer.parseInt(_contentFields.get(3).getText());
		String company = (String) _companyField.getSelectedItem();
		
		
		//process while adding
		if (_isAdd) {
			DBMotorcycleSerie.addMotorcycleSeries(serie, company, importPrice, price, quantity);
			MotorcycleSeriesTable.readData(DBMotorcycleSerie.currentSeries());
			OutstockTable.readData(DBMotorcycleSerie.currentSeries());
		} else {
			//process while editting
			DBMotorcycleSerie.editMotorcycleSeries(index, serie, company, importPrice, price, quantity);
			DBMotorcycleSerie.updateSeriesBuffer();
			MotorcycleSeriesTable.readData(DBMotorcycleSerie.currentSeries());
			MotorcycleSeriesTable.detailInformationPanel().setDataToDetailPanel(index + 1);
			
			
			Object[] object = DBMotorcycleSerie.currentSeries().get(index);
			MotorcycleSerie ser = (MotorcycleSerie)object[0];
			//MotorcycleCompany comp = (MotorcycleCompany)object[1];
			
			for (Motorcycle cycle: ser.cycles()) {
				cycle.serie().setName(serie);
				cycle.serie().setPrice(price);
				cycle.serie().setImportPrice(importPrice);
			}
			
			DBOrder.updateOrderBuffer();
			OrderTable.readData(DBOrder.currentOrders());
			OutstockTable.readData(DBMotorcycleSerie.currentSeries());
		}
		
		
		//Repaint the table
		MotorcycleSeriesTable.table().repaint();
		
		//End frame
		_parent.dispose();
	}

}

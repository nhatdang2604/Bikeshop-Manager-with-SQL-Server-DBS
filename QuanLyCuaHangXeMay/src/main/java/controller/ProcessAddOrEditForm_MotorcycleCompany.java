package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import model.DBMotorcycleCompany;
import model.DBMotorcycleSerie;
import model.MotorcycleSerie;
import view.MotorcycleCompanyTable;
import view.MotorcycleSeriesTable;

public class ProcessAddOrEditForm_MotorcycleCompany implements ActionListener {
	
	//private MotorcycleCompanyTable _table;
	private JDialog _parent;
	private ArrayList<JTextField> _contentFields;
	private boolean _isAdd;

	
	public ProcessAddOrEditForm_MotorcycleCompany(JDialog parent, 
			ArrayList<JTextField> contentFields, boolean isAdd) {
		_parent = parent;
		_contentFields = contentFields;
		_isAdd = isAdd;
		
	}
	public void actionPerformed(ActionEvent arg0) {
		int index = -1;
	
		if (!_isAdd) {
			index = ClickedRowService_MotorcycleCompany.currentIndex();
			if (-1 == index) {
				return;
			}
		}
		
		--index;
		String name = _contentFields.get(0).getText();
		String country = _contentFields.get(1).getText();
		int year = Integer.parseInt(_contentFields.get(2).getText());
		
	
		//process while adding
		if (_isAdd) {
			DBMotorcycleCompany.addMotorcycleCompany(name, country, year);
			MotorcycleCompanyTable.readData(DBMotorcycleCompany.currentCompanies());
			AddOrEditForm_MotorcycleSerie.updateCompanies();
			
		} else {
			//process while editting
			DBMotorcycleCompany.editMotorcycleCompany(index, name, country, year);
			DBMotorcycleCompany.updateCompaniesBuffer();
			MotorcycleCompanyTable.readData(DBMotorcycleCompany.currentCompanies());
			MotorcycleCompanyTable.detailInformationPanel().setDataToDetailPanel(index + 1);
			for (MotorcycleSerie series: DBMotorcycleCompany.currentCompanies().get(index).series()) {
				series.company().setName(name);
				series.company().setCountry(country);
				series.company().setYear(year);
			}
			
			DBMotorcycleSerie.updateSeriesBuffer();
			MotorcycleSeriesTable.readData(DBMotorcycleSerie.currentSeries());
			AddOrEditForm_MotorcycleSerie.updateCompanies();
		}
		
		//Repaint the table
		MotorcycleCompanyTable.table().repaint();
		
		
		//End frame
		_parent.dispose();
	}

}

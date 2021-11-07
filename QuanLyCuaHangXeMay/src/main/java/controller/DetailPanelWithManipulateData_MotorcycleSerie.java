package controller;

import java.util.ArrayList;

import javax.swing.JLabel;

import model.DBMotorcycleCompany;
import model.DBMotorcycleSerie;
import model.Motorcycle;
import model.MotorcycleCompany;
import model.MotorcycleSerie;
import view.DetailPanelWithManipulateData;
import view.MotorcycleSeriesTable;

@SuppressWarnings("serial")
public class DetailPanelWithManipulateData_MotorcycleSerie extends DetailPanelWithManipulateData {
	private static String[] _companies;
	
	
	private static void _updateCompanies() {
		ArrayList<MotorcycleCompany> companies =  DBMotorcycleCompany.getCurrentCompany();
		_companies = new String[companies.size() + 1];
		int i = 0;
		for (MotorcycleCompany company : companies) {
			_companies[i] = company.name();
			++i;
		}
			
	}
	
	
	
	public static String[] companies() {
		return _companies;
	}
	
	public static void updateCompanies()  {
		_updateCompanies();
	}
	
	
	@Override
	public void setDataToDetailPanel(int index) {
		if (-1 == index) {
			for (JLabel field: _attributeData) {
				field.setText("");
			}
			return;
		}
		
		Object[] object = DBMotorcycleSerie.currentSeries().get(index - 1);
		MotorcycleSerie serie = (MotorcycleSerie) object[0];
		MotorcycleCompany company = (MotorcycleCompany) object[1];
		
		_attributeData.get(0).setText(serie.name());
		_attributeData.get(1).setText(company.name());
		_attributeData.get(2).setText(new Integer(serie.importPrice()).toString());
		_attributeData.get(3).setText(new Integer(serie.price()).toString());
		
		int count = 0;
		for (Motorcycle motor: serie.cycles()) {
			if (motor.isStoraged()) ++count;
		}
		_attributeData.get(4).setText(new Integer(count).toString());
	}
	
	public DetailPanelWithManipulateData_MotorcycleSerie(
			String titled, String[] attributeFieldNames) {
		
		super(titled, attributeFieldNames);
		//get current _companies
		_updateCompanies();
		
		
		_dataManipulateButtons.get(0).addActionListener(new AddService_MotorcycleSerie());
		_dataManipulateButtons.get(1).addActionListener(new DeleteService_MotorcycleSerie());
		_dataManipulateButtons.get(2).addActionListener(new EditService_MotorcycleSerie());
		
	}
	
}

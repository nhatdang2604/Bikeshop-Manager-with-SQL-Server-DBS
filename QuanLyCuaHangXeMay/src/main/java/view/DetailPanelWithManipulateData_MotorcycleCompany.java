package view;

import controller.AddService_MotorcycleCompany;
import controller.DeleteService_MotorcycleCompany;
import controller.EditService_MotorcycleCompany;
import model.DBMotorcycleCompany;
import model.MotorcycleCompany;

@SuppressWarnings("serial")
public class DetailPanelWithManipulateData_MotorcycleCompany extends DetailPanelWithManipulateData {
	
	
	@Override
	public void setDataToDetailPanel(int index) {
		if (-1 == index) {
			_attributeData.get(0).setText("");
			_attributeData.get(1).setText("");
			_attributeData.get(2).setText("");
			return;
		}
		
		MotorcycleCompany company = DBMotorcycleCompany.currentCompanies().get(index - 1);
		
		_attributeData.get(0).setText(company.name());
		_attributeData.get(1).setText(company.country());
		_attributeData.get(2).setText(new Integer(company.year()).toString());
	}
	
	
	public DetailPanelWithManipulateData_MotorcycleCompany(
			String titled, String[] attributeFieldNames) {
		super(titled, attributeFieldNames);
		
		_dataManipulateButtons.get(0).addActionListener(new AddService_MotorcycleCompany());
		_dataManipulateButtons.get(1).addActionListener(new DeleteService_MotorcycleCompany());
		_dataManipulateButtons.get(2).addActionListener(new EditService_MotorcycleCompany());
		
	}
}
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import view.MotorcycleCompanyTable;
import view.MotorcycleSeriesTable;

public class EditService_MotorcycleSerie implements ActionListener {
	
	public EditService_MotorcycleSerie() {
		//do nothing
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		//_companies = DB.getCurrentCompanies;
		JDialog editDialog = new AddOrEditForm_MotorcycleSerie(false);
		editDialog.setVisible(true);
    }
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import view.MotorcycleSeriesTable;

public class AddService_MotorcycleSerie implements ActionListener {
	
	public AddService_MotorcycleSerie() {
		//do nothing
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		//_companies = DB.getCurrentComapnies();
		JDialog addDialog = new AddOrEditForm_MotorcycleSerie(true);
		addDialog.setVisible(true);
    }
}

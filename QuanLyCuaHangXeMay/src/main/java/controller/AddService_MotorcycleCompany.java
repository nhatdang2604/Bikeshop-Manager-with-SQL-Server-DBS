package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import view.MotorcycleCompanyTable;

public class AddService_MotorcycleCompany implements ActionListener {
	
	public AddService_MotorcycleCompany() {
		//do nothing
	}
	
	public void actionPerformed(ActionEvent arg0) {
		JDialog addDialog = new AddOrEditForm_MotorcycleCompany(true);
		addDialog.setVisible(true);
    }
}
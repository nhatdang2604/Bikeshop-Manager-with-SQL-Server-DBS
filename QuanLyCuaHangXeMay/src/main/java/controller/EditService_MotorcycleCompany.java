package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import view.MotorcycleCompanyTable;

public class EditService_MotorcycleCompany implements ActionListener  {
	
	public EditService_MotorcycleCompany() {
		//do nothing
	}

	public void actionPerformed(ActionEvent arg0) {
		JDialog editDialog = new AddOrEditForm_MotorcycleCompany(false);
		editDialog.setVisible(true);
    }
}

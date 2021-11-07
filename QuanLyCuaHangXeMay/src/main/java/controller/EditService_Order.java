package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class EditService_Order implements ActionListener {
	public EditService_Order() {
		//do nothing
	}
	
	public void actionPerformed(ActionEvent arg0) {
		AddOrEditForm_Order.updateCurrentSerie();
		JDialog addDialog = new AddOrEditForm_Order(false);
		addDialog.setVisible(true);
    }
		
}

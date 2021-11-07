package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class AddService_Order implements ActionListener {
	public AddService_Order() {
		//do nothing
	}
	
	public void actionPerformed(ActionEvent arg0) {
		AddOrEditForm_Order.updateCurrentSerie();
		JDialog addDialog = new AddOrEditForm_Order(true);
		addDialog.setVisible(true);
    }
		
}

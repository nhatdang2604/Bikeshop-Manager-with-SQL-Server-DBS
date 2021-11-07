package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class CancelAddOrEditForm implements ActionListener {
	private JDialog _parent;
	public CancelAddOrEditForm(JDialog parent) {
		_parent = parent;
	}
	public void actionPerformed(ActionEvent e) {
		_parent.dispose();
	}

}

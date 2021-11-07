package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import view.DetailPanel;
import view.MotorcycleCompanyTable;

public class ClickedRowService_MotorcycleCompany implements MouseListener {
	static private int _currentIndex = -1;
	static public int currentIndex() {return _currentIndex; }
	
	private DetailPanel _detailPanel;
	
	public ClickedRowService_MotorcycleCompany(DetailPanel detailPanel){
		_detailPanel = detailPanel;
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Object[] row = ((MotorcycleCompanyTable)e.getSource()).getSelectedRowInfo();
		_currentIndex = (Integer) row[0];
		_detailPanel.setDataToDetailPanel(_currentIndex);
		_detailPanel.repaint();
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

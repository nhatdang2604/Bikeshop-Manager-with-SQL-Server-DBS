package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.DetailPanel;
import view.MotorcycleCompanyTable;
import view.MotorcycleSeriesTable;

public class ClickedRowService_MotorcycleSerie implements MouseListener{
	static private int _currentIndex = -1;
	static public int currentIndex() {return _currentIndex; }
	
	private DetailPanel _detailPanel;
	
	public ClickedRowService_MotorcycleSerie(DetailPanel detailPanel){
		_detailPanel = detailPanel;
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Object[] row = ((MotorcycleSeriesTable)e.getSource()).getSelectedRowInfo();
		_currentIndex = (Integer) row[0];
		_detailPanel.setDataToDetailPanel(_currentIndex);
		_detailPanel.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

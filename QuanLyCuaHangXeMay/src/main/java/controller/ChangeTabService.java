package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;

import view.DisplayTablesPanel;

public class ChangeTabService implements MouseListener  {
	
	private DisplayTablesPanel _panel;
	private ArrayList<JPanel> _parts;
	
	public ChangeTabService(DisplayTablesPanel panel, ArrayList<JPanel> parts) {
		_panel = panel;
		_parts = parts;

	}
	
	private void _changeDetailAndHeaderPanelWhileChagingTab() {
		int selectedIndex = _panel.tabbedPanel().getSelectedIndex();
        _panel.setCurrentTable(_panel.tables().get(selectedIndex));
        _panel.setCurrentDetailInformationPanel(_panel.detailsInformationPanels().get(selectedIndex));
        _panel.setCurrentHeaderPanel(_panel.headerPanels().get(selectedIndex));
        
       _parts.get(0).removeAll();
       _parts.get(1).removeAll();
       _parts.get(2).removeAll();
        
        _parts.get(0).add(_panel, BorderLayout.CENTER);
        _parts.get(1).add(_panel.currentHeaderPanel(), BorderLayout.CENTER);
        _parts.get(2).add(_panel.currentDetailInformationPanel(), BorderLayout.CENTER);
			
        _panel.currentDetailInformationPanel().setBackground(new Color(204, 204, 255));
        _panel.currentHeaderPanel().setBackground(UIManager.getColor("activeCaptionBorder"));
        _parts.get(1).repaint();
        _parts.get(2).repaint();
	}
	
	public void mouseClicked(MouseEvent e) {
         //do nothing
    }


	public void mouseEntered(MouseEvent arg0) {
		//do nothing
	}


	public void mouseExited(MouseEvent arg0) {
		//do nothing
	}


	public void mousePressed(MouseEvent arg0) {
		_changeDetailAndHeaderPanelWhileChagingTab();
	}


	public void mouseReleased(MouseEvent arg0) {
		//do nothing
	}
}

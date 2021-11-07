package view;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;

public class PaginationPanel extends JPanel{
	
	private Integer _currentPageNumber;
	private JTextArea _currentPageNumberBox;
	private JTable _currentTable;
	
	//_switchPageButtons[0]: previous page button
	//_switchPageButtons[1]: next page button
	private ArrayList<JButton> _switchPageButtons;
	
	/*
	Padding panel to make _switchPageButtons and _currtPageNumberBox in right position
	There are only 3 _paddingPanels*/
	private ArrayList<JPanel> _paddingPanels;
	
	/*
	Layout: 
	-------------------------------------------------------
	|								|_paddingPanels.get(1)|
	|								|----------------------
	|	   _paddingPanels.get(0)	|					  |
	|								|_paddingPanels.get(2)|
	|								|					  |
	-------------------------------------------------------
	*/
	private void _setLayout() {
		double widthRatio = 0.8;
		double heightRatio = 0.8;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill= GridBagConstraints.BOTH;
	
		gbc.weightx = widthRatio;
		gbc.weighty = 1;
		gbc.gridheight = 2;
		this.add(_paddingPanels.get(0), gbc);
		
		gbc.gridheight = 1;
		gbc.gridx = 1;
		gbc.weightx = 1 - widthRatio;
		gbc.weighty = 1 - heightRatio;
		this.add(_paddingPanels.get(1), gbc);
		_paddingPanels.get(1).setLayout(new FlowLayout());
		_paddingPanels.get(1).add(_switchPageButtons.get(0));
		_paddingPanels.get(1).add(_currentPageNumberBox);
		_paddingPanels.get(1).add(_switchPageButtons.get(1));
		
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.weighty = heightRatio;
		this.add(_paddingPanels.get(2), gbc);
		
	}
	
	public PaginationPanel() {
		_currentPageNumber = 1;
		_currentPageNumberBox = new JTextArea();
		_currentPageNumberBox.setEditable(false);
		_currentPageNumberBox.replaceSelection(_currentPageNumber.toString());
		
		_switchPageButtons = new ArrayList<JButton>();
		_switchPageButtons.add(new JButton("<<"));
		_switchPageButtons.add(new JButton(">>"));
		
		_paddingPanels = new ArrayList<JPanel>();
		for (int i = 0; i < 3; ++i) {
			_paddingPanels.add(new JPanel());
		}
		
		
		_setLayout();
	}
	
	
	/**** Setter ****/
	public void setCurrentPageNumber(int value) {
		_currentPageNumber = value;
		_currentPageNumberBox.replaceSelection(_currentPageNumber.toString());
	}
	public void setTable(JTable table) {
		_currentTable = table;
	}
	
	@Override
	public void setBackground(Color bg) {
		try {
			for (JPanel i: _paddingPanels) {
				i.setBackground(bg);
			}
		} catch (Exception e) {
			
			//Ignore exception, because we always change background color
			//of _paddingPanels after initialize _paddingPanels
			//	=> This bracket will never happen
		}
	}
	
}

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import controller.ChangeTabService;

public class DisplayFeaturePanel extends JPanel {
	
	//Private attribute
	
	/*
	_part[0] : The center panel, which contain: 
		+ Tabs for subservices
		+ Table for showing data
	_part[1] : The top panel, which give us button to manipulate data
	_part[2] : The most-right panel, which give us detail information of data in table
	*/
	protected ArrayList<JPanel> _parts;
	
	//Number of tabs which is displayed in _part[0]
	//private int _numberOfTabs;
	
	//Private method
	private void _setLayout() {
		double widthRatio = 0.7;
		double heightRatio = 0.1;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weighty = heightRatio;
		gbc.weightx = widthRatio;
		gbc.gridx = 0;
		this.add(_parts.get(1), gbc);
		

		gbc.weighty = 1 - heightRatio;
		gbc.weightx = widthRatio;
		gbc.gridy = 1;
		this.add(_parts.get(0), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.weighty = 1;
		gbc.weightx = 1 - widthRatio;
		
		
		this.add(_parts.get(2), gbc);
		
	}
	
	//Constructor
	DisplayFeaturePanel() {
		
		_parts = new ArrayList<JPanel>();
		
		for (int i = 0; i < 3; ++i) {
			_parts.add(new JPanel());
		}
		
		
		_setLayout();
		
		/*
		_parts.get(0).setLayout(new BorderLayout());
		_parts.get(1).setLayout(new BorderLayout());
		_parts.get(2).setLayout(new BorderLayout());
		
		DisplayTablesPanel test = new Requirement1Panel(_parts);
		
		_parts.get(0).setLayout(new BorderLayout());
		_parts.get(0).add(test, BorderLayout.CENTER);
		
		_parts.get(1).setLayout(new BorderLayout());
		_parts.get(1).add(test.currentHeaderPanel(), BorderLayout.CENTER);
		
		_parts.get(2).setLayout(new BorderLayout());
		_parts.get(2).add(test.currentDetailInformationPanel(), BorderLayout.CENTER);
		
		test.currentDetailInformationPanel().setBackground(new Color(204, 204, 255));
		test.currentHeaderPanel().setBackground(UIManager.getColor("activeCaptionBorder"));	*/
	}
	
	
}

class DisplayFeaturePanel_Requirement1 extends DisplayFeaturePanel {
	DisplayFeaturePanel_Requirement1() {
		super();
		
		_parts.get(0).setLayout(new BorderLayout());
		_parts.get(1).setLayout(new BorderLayout());
		_parts.get(2).setLayout(new BorderLayout());
		
		DisplayTablesPanel test = new Requirement1Panel(_parts);
		
		_parts.get(0).setLayout(new BorderLayout());
		_parts.get(0).add(test, BorderLayout.CENTER);
		
		_parts.get(1).setLayout(new BorderLayout());
		_parts.get(1).add(test.currentHeaderPanel(), BorderLayout.CENTER);
		
		_parts.get(2).setLayout(new BorderLayout());
		_parts.get(2).add(test.currentDetailInformationPanel(), BorderLayout.CENTER);
		
		test.currentDetailInformationPanel().setBackground(new Color(204, 204, 255));
		test.currentHeaderPanel().setBackground(UIManager.getColor("activeCaptionBorder"));	
	}
}

class DisplayFeaturePanel_Requirement2 extends DisplayFeaturePanel {
	DisplayFeaturePanel_Requirement2() {
		super();
		
		_parts.get(0).setLayout(new BorderLayout());
		_parts.get(1).setLayout(new BorderLayout());
		_parts.get(2).setLayout(new BorderLayout());
		
		DisplayTablesPanel test = new Requirement2Panel(_parts);
		
		_parts.get(0).setLayout(new BorderLayout());
		_parts.get(0).add(test, BorderLayout.CENTER);
		
		_parts.get(1).setLayout(new BorderLayout());
		_parts.get(1).add(test.currentHeaderPanel(), BorderLayout.CENTER);
		
		_parts.get(2).setLayout(new BorderLayout());
		_parts.get(2).add(test.currentDetailInformationPanel(), BorderLayout.CENTER);
		
		test.currentDetailInformationPanel().setBackground(new Color(204, 204, 255));
		test.currentHeaderPanel().setBackground(UIManager.getColor("activeCaptionBorder"));	
	}
}

class DisplayFeaturePanel_Requirement3 extends DisplayFeaturePanel {
	DisplayFeaturePanel_Requirement3() {
		super();
		
		_parts.get(0).setLayout(new BorderLayout());
		_parts.get(1).setLayout(new BorderLayout());
		_parts.get(2).setLayout(new BorderLayout());
		
		DisplayTablesPanel test = new Requirement3Panel(_parts);
		
		_parts.get(0).setLayout(new BorderLayout());
		_parts.get(0).add(test, BorderLayout.CENTER);
		
		_parts.get(1).setLayout(new BorderLayout());
		_parts.get(1).add(test.currentHeaderPanel(), BorderLayout.CENTER);
		
		_parts.get(2).setLayout(new BorderLayout());
		_parts.get(2).add(test.currentDetailInformationPanel(), BorderLayout.CENTER);
		
		test.currentDetailInformationPanel().setBackground(new Color(204, 204, 255));
		test.currentHeaderPanel().setBackground(UIManager.getColor("activeCaptionBorder"));	
	}
}

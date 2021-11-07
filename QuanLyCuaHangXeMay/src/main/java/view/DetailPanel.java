package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.AddService_MotorcycleCompany;
import controller.AddService_MotorcycleSerie;
import controller.DeleteService;
import controller.EditService_MotorcycleCompany;
import controller.EditService_MotorcycleSerie;
import model.DBMotorcycleCompany;
import model.MotorcycleCompany;

@SuppressWarnings("serial")
abstract public class DetailPanel extends JPanel {
	
	protected final String _titled;
	protected ArrayList<JPanel> _paddingPanels;
	
	protected ArrayList<JLabel> _attributeFields;
	protected ArrayList<JLabel> _attributeData;
	//protected ArrayList<JButton> _dataManipulateButtons;
	
	private void _setLayout() {
		//Make 3 horizontal equal panel layout
		this.setLayout(new GridLayout(3, 1));
				
		//Make 3 padding panels
		for (int i = 0; i < 3; ++i) {
			_paddingPanels.add(new JPanel());
			this.add(_paddingPanels.get(i));	
		}	
		
		_paddingPanels.get(1).setLayout(new GridLayout(_attributeFields.size(), 2));
		_paddingPanels.get(2).setLayout(new FlowLayout());
	}
	
	private void _addStuffsToPanel() {
		
		for (int i = 0; i < _attributeFields.size(); ++i) {
			
			//Add JLabel
			_paddingPanels.get(1).add(_attributeFields.get(i));
			_paddingPanels.get(1).add(_attributeData.get(i));
			
		}
		
		/*
		//Add Buttons
		for (JButton button: _dataManipulateButtons) {
			_paddingPanels.get(2).add(button);
		}*/
	}
	
	
	abstract public void setDataToDetailPanel(int index);
	
	public DetailPanel(String titled, String[] attributeFieldNames) {
		
		_titled = new String(titled);
		
		//Add titled
		Border border = BorderFactory.createTitledBorder(_titled);
		this.setBorder(border);
		
		//Create attribute information field
		_attributeFields = new ArrayList<JLabel>();
		_attributeData = new ArrayList<JLabel>();
		for (String name: attributeFieldNames) {
			_attributeFields.add(new JLabel(name));
			_attributeData.add(new JLabel());
		}
		
		//Create buttons
		/*
		_dataManipulateButtons = new ArrayList<JButton>();
		_dataManipulateButtons.add(new JButton("Thêm"));
		_dataManipulateButtons.add(new JButton("Xóa"));
		_dataManipulateButtons.add(new JButton("Sửa"));
		*/
		
		//Add padding panels and set layout
		_paddingPanels = new ArrayList<JPanel>();
		_setLayout();
		
		//Add jlabel and jbuttons to display data
		_addStuffsToPanel();
		
		//Change to default background
		//setBackground(new Color(204, 204, 255));
	}
	
	public void updateInformation(Object[] data) {
		
		for (int i = 0; i < _attributeFields.size(); ++i) {
			_attributeData.get(i).setText(data[i].toString());
		}
		this.repaint();
	}
	
	@Override
	public void setBackground(Color bg) {
		try {
			for (JPanel i: _paddingPanels) {
				i.setBackground(bg);
			}
		} catch (Exception ex) {
			//Ignore exception, because we always change background color
			//of _paddingPanels after initialize _paddingPanels
			//	=> This bracket will never happen
		}
	}
}






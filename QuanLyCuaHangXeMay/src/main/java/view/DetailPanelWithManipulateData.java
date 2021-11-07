package view;

import java.util.ArrayList;

import javax.swing.JButton;

@SuppressWarnings("serial")
public abstract class DetailPanelWithManipulateData extends DetailPanel {
	
	protected ArrayList<JButton> _dataManipulateButtons;
	
	public abstract void setDataToDetailPanel(int index);
	
	private void _makeButtons() {
		
		//Create buttons
		_dataManipulateButtons = new ArrayList<JButton>();
		_dataManipulateButtons.add(new JButton("Thêm"));
		_dataManipulateButtons.add(new JButton("Xóa"));
		_dataManipulateButtons.add(new JButton("Sửa"));
		
		
		//Add Buttons to detail panel
		for (JButton button: _dataManipulateButtons) {
			_paddingPanels.get(2).add(button);
		}
	}
	
	public DetailPanelWithManipulateData(String titled, String[] attributeFieldNames) {
		super(titled, attributeFieldNames);
		_makeButtons();
		
		//Add listener for "Xóa" button
		//_dataManipulateButtons.get(1).addActionListener(new DeleteService());
	}
}
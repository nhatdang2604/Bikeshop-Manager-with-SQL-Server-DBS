package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class Category extends JPanel {
	
	/****Private attribute ****/
	//Name of the Category
	private String _name;
	
	//The option button on the left side of main menu
	private JButton _button;
	
	//TOP, LEFT, DOWN, RIGHT borders while add into GridBagLayout
	private ArrayList<JPanel> _borders;	
	
	/**** Private method ****/
	void _setLayout() {
		double borderSize = 0.2;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weighty = borderSize;
		gbc.gridwidth = 3;
		this.add(_borders.get(0), gbc);
		
		gbc.gridwidth = 1;
		gbc.weightx = borderSize;
		gbc.weighty = 1 - 2 *borderSize;
		gbc.gridy = 1;
		this.add(_borders.get(1), gbc);
		
		gbc.gridx = 2;
		this.add(_borders.get(2), gbc);
		
		gbc.gridx = 1;
		this.add(_button, gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.weighty = borderSize;
		gbc.gridwidth = 3;
		this.add(_borders.get(3), gbc);
	
	}
	
	
	/**** Constuctor ****/
	public Category(String name) {
		
		_name = name;
		_borders = new ArrayList<JPanel>();
		for (int i = 0; i < 4; ++i) {
			_borders.add(new JPanel());
			_borders.get(i).setBackground(UIManager.getColor("activeCaptionBorder"));
		}
		_button = new JButton(name);
		
		_setLayout();
		
	}
	
	/**** Getter ****/
	public String name() {return _name; }
	public JButton button() {return _button;}
	
	
}


package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.DBOrder;
import model.Order;

public class DetailPanelForQueryWithMonthYear extends JPanel {
	private ArrayList<JPanel> _paddingPanels;
	private ArrayList<JButton> _buttons;
	private ArrayList<JLabel> _labels;
	private ArrayList<JComboBox> _contents;
	
	private void makeButton() {
		_buttons.get(0).addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Integer month = Integer.parseInt((String) _contents.get(0).getSelectedItem());
				Integer year = Integer.parseInt((String) _contents.get(1).getSelectedItem());
				Integer turnover = 0;
				Integer profit = 0;
				
				for (Order order: DBOrder.currentOrders()) {
					if (order.orderDay().getMonth() + 1 == month && order.orderDay().getYear() + 1900 == year) {
						turnover += order.totalTurnover();
						profit += order.totalProfit();
					}
				}
				
				_labels.get(3).setText(turnover.toString());
				_labels.get(5).setText(profit.toString());
				
			}
			
		});
	}
	
	private void init() {
		
		//make 3 padding panels
		_paddingPanels = new ArrayList<JPanel>();
		for (int i = 0; i < 3; ++i) {
			_paddingPanels.add(new JPanel());
		}
		
		
		//Make buttons
		_buttons = new ArrayList<JButton>();
		_buttons.add(new JButton("Truy vấn"));
		
		//Make labels
		_labels = new ArrayList<JLabel>();
		_labels.add(new JLabel("Tháng: "));
		_labels.add(new JLabel("Năm: "));
		_labels.add(new JLabel("Doanh thu: "));
		_labels.add(new JLabel());
		_labels.add(new JLabel("Lợi nhuận: "));
		_labels.add(new JLabel());
		
		String[] months = new String[12];
		for (int i = 0; i < 12; ++i) {
			months[i] = new Integer(i + 1).toString();
		}
		
		String[] years = new String[33];
		for (int i = 0; i < 33; ++i) {
			years[i] = new Integer(i + 1990).toString();
		}
		_contents = new ArrayList<JComboBox>();
		_contents.add(new JComboBox(months));
		_contents.add(new JComboBox(years));
		
	}
	
	private void setLayout() {
		this.setLayout(new GridLayout(3, 1));
		for (int i = 0; i < 3; ++i) {
			this.add(_paddingPanels.get(i));
			_paddingPanels.get(i).setBackground(new Color(204, 204, 255));
		}
		
		
		_paddingPanels.get(1).setLayout(new GridLayout(2, 1));
		for (int i = 0; i < 2; ++i) {
			_paddingPanels.get(1).add(_labels.get(i));
			_paddingPanels.get(1).add(_contents.get(i));
		}
		for (int i = 2; i < 6; ++i) {
			_paddingPanels.get(1).add(_labels.get(i));
		}
		
		_paddingPanels.get(2).add(_buttons.get(0));
		
	}
	
	DetailPanelForQueryWithMonthYear() {
		init();
		setLayout();
		makeButton();
	}
	
}

package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class MainJFrame extends JFrame {

	private JPanel window;
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	private ArrayList<JPanel> featurePanels;
	private ArrayList<Category> menuPanels;
	
	//Size of MainJFrame
	final private int mainFrameHeight = 700;
	final private int mainFrameWidth = 1250;
	
	private void addSwitchButtons() {
		menuPanels.get(0).button().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				rightPanel.removeAll();
				rightPanel.setLayout(new BorderLayout());
				rightPanel.add(featurePanels.get(0), BorderLayout.CENTER);
				//featurePanels.get(0).repaint();
				rightPanel.revalidate();
			}
		});
		
		menuPanels.get(1).button().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				rightPanel.removeAll();
				rightPanel.setLayout(new BorderLayout());
				rightPanel.add(featurePanels.get(1), BorderLayout.CENTER);
			//	featurePanels.get(1).repaint();
				rightPanel.revalidate();
			}
		});
		
		
		menuPanels.get(2).button().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				rightPanel.removeAll();
				rightPanel.setLayout(new BorderLayout());
				rightPanel.add(featurePanels.get(2), BorderLayout.CENTER);
			//	featurePanels.get(2).repaint();
				rightPanel.revalidate();
			}
		});
	}

	private void initLeftPanel() {
		
		//Init menuOptions
		menuPanels = new ArrayList<Category>();
		menuPanels.add(new Category("QUẢN LÝ DANH MỤC"));
		menuPanels.add(new Category("QUẢN LÝ ĐƠN HÀNG"));
		menuPanels.add(new Category("THỐNG KÊ BÁO CÁO"));
		
		/*
		Category[] menuOption = {
				new Category("QUẢN LÝ DANH MỤC"), 
				new Category("QUẢN LÝ ĐƠN HÀNG"),
				new Category("THỐNG KÊ BÁO CÁO")};*/
		
		//Setup the left side menu
		leftPanel = new JPanel();
		//leftPanel.setBackground(UIManager.getColor("SplitPaneDivider.draggingColor"));	
		leftPanel.setLayout(new GridLayout(menuPanels.size(), 1));
		
		//Add 3 button on the left side menu
		for (int i =0; i<menuPanels.size(); ++i) {
			leftPanel.add(menuPanels.get(i));
		}
		
		
		
	}
	
	private void initRightPanel() {
		featurePanels = new ArrayList<JPanel>();
		rightPanel = new JPanel();
		//rightPanel.setBackground(new Color(173, 216, 230));
		rightPanel.setLayout(new BorderLayout());
		
		featurePanels.add(new DisplayFeaturePanel_Requirement1());
		featurePanels.add(new DisplayFeaturePanel_Requirement2());
		featurePanels.add(new DisplayFeaturePanel_Requirement3());
		//featurePanels.add(new DisplayFeaturePanel_Requirement3())
		
		//default display
		//rightPanel.add(featurePanels.get(1), BorderLayout.CENTER);
		//rightPanel.removeAll();
		rightPanel.add(featurePanels.get(0), BorderLayout.CENTER);
		//rightPanel.repaint();
		
		
		addSwitchButtons();
	}
	
	
	void combineLeftRightPanel() {
		
		//Combine 2 part together
		window = new JPanel();
		window.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
				
		//Split only 2 horizontal part
		gbc.weighty = 1;
				
		//Setup for the left panel
		gbc.weightx = 0.05;
		gbc.gridx = 0;
		window.add(leftPanel, gbc);
				
		//Setup for the right panel
		gbc.weightx = 1 - gbc.weightx;
		gbc.gridx = 1;
		window.add(rightPanel, gbc);
	}
	
	//Init for the MainJFrame
	private void initMainJFrame() {
		
		//Init properties of main JFrame
		setTitle("Quản lí cửa hàng bán xe máy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(mainFrameWidth, mainFrameHeight);
		//setBounds(100, 100, mainFrameHeight, mainFrameWidth);
		//setResizable(false);
		
		//Setup left and right part of the main JFrame
		initLeftPanel();
		initRightPanel();
		
		//Combine 2 parts together
		combineLeftRightPanel();
		
			
		//Activate the JFrame
        setContentPane(window);
        setLocationRelativeTo(null);
	}
	
	
	
	public MainJFrame() {
		initMainJFrame();
	}
}


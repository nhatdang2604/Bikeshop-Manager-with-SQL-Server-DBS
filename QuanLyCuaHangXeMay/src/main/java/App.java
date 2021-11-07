import java.awt.EventQueue;

import model.HibernateUtils;
import view.MainJFrame;

public class App {
	public static void main(String[] args) {
		try {
			//Init database
			HibernateUtils.getSessionFactory();
			
			MainJFrame frame = new MainJFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame frame = new MainJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
	}


}

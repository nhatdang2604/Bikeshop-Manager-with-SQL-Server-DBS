package model;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;

import view.OrderTable;

public class DBOrder extends Database  {
	private static ArrayList<Order> _currentOrders;
	
	private static ArrayList<Order> _getCurrentOrder() {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		 
		String sql = new String("SELECT order\n"
	  			 + "FROM " + Order.class.getName() + " order\n");
		
		
		
		@SuppressWarnings("unchecked")
		ArrayList<Order> result = (ArrayList<Order>) session.createQuery(sql).list();
		 
		//Get all order detail of got orders;
		for (Order order: result) {
			
			sql = new String(
					"SELECT MÃĐƠNHÀNG, MÃLOẠI, SỐLƯỢNG " +
					"FROM CT_ĐƠNHÀNG " +
					"WHERE MÃĐƠNHÀNG = " + order.id());
					
			ArrayList<Object[]> detailOrders = (ArrayList<Object[]>) session.createSQLQuery(sql).list();
			
			for (Object[] dos: detailOrders) {
				Integer serieid = (Integer) dos[1];
				Integer quantity = (Integer) dos[2];
				
				for (Object[] object: DBMotorcycleSerie.currentSeries()) {
					MotorcycleSerie serie = (MotorcycleSerie)object[0];
					if (serieid == serie.id()) {
						order.detailOrders().add(new DetailOrder(order, serie, quantity));
						break;
					}
				}
			}
		
		}
		
		session.getTransaction().commit();
		return result;
	}
	
	private static void _addOrder(Date date, ArrayList<Integer> buffer) {

		 Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 
		 //Add new company
		 Order order = new Order(date);
		 
		 //Save company to dbs
		 Integer id = (Integer) session.save(order);
		 
		 session.getTransaction().commit();
		 
		 for (int i = 0; i < DBMotorcycleSerie.currentSeries().size(); ++i) {
			 if (null == buffer.get(i) || 0 == buffer.get(i) ) {
				 continue;
			 }
			 
			 session = HibernateUtils.getSessionFactory().getCurrentSession();
			 session.beginTransaction();
			 
			 int count = 0;
			 Object[] object = DBMotorcycleSerie.currentSeries().get(i);
			 MotorcycleSerie serie = (MotorcycleSerie)object[0];
			 for (Motorcycle motor: serie.cycles()) {
				 if (motor.isStoraged()) {
					 ++count;
					
					 motor.setStoraged(false);
					 session.update(motor);
					 if (count == buffer.get(i)) {break; }
				 }
			 }
			 
			 String sql = new String(
						"INSERT INTO CT_ĐƠNHÀNG(MÃĐƠNHÀNG, MÃLOẠI, SỐLƯỢNG) " +
						"VALUES ( " + id + ", " + serie.id() + ", " + buffer.get(i) + ")");
			 session.createSQLQuery(sql).executeUpdate();			
			
			 session.getTransaction().commit();
		 }
		 
		
		 //update buffer
		 updateOrderBuffer();
	}
	
	private static void _deleteOrder() {
		
		Order oldOrder = OrderTable.getSelectedOrder();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		for (DetailOrder dos: oldOrder.detailOrders()) {
			
			dos.serie().detailOrders().remove(dos);
			
			int n = 0;
			for (Motorcycle motor: dos.serie().cycles()) {
				if (!motor.isStoraged()) {
					motor.setStoraged(true);
					session.update(motor);
					++n;
					if (n == dos.quantity()) {
						break;
					}
				}
				
			}
			
		}
		
		String sql = new String(
				"DELETE CT_ĐƠNHÀNG" +
					" WHERE MÃĐƠNHÀNG = " + oldOrder.id());
					
		session.createSQLQuery(sql).executeUpdate();
		
		session.remove(oldOrder);
		
		session.getTransaction().commit();
		
		DBMotorcycleSerie.updateSeriesBuffer();
		updateOrderBuffer();
	}
	
	private static void _editOrder(Date date, ArrayList<Integer> buffer) {
		
		//release the old ordder first
		Order oldOrder = OrderTable.getSelectedOrder();
		oldOrder.setOrderDay(date);
		
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.update(oldOrder);
		
		for (DetailOrder dos: oldOrder.detailOrders()) {
			
			dos.serie().detailOrders().remove(dos);
			
			int n = 0;
			for (Motorcycle motor: dos.serie().cycles()) {
				if (!motor.isStoraged()) {
					motor.setStoraged(true);
					session.update(motor);
					++n;
					if (n == dos.quantity()) {
						break;
					}
				}
				
			}
			
			 String sql = new String(
					 "DELETE CT_ĐƠNHÀNG" +
						" WHERE MÃĐƠNHÀNG = " + oldOrder.id() +
						" AND MÃLOẠI = " + dos.serie().id());
			 session.createSQLQuery(sql).executeUpdate();
		}
		oldOrder.detailOrders().clear();
		session.getTransaction().commit();
		
		
		Integer id = (Integer) oldOrder.id(); 
		for (int i = 0; i < DBMotorcycleSerie.currentSeries().size(); ++i) {
			if (null == buffer.get(i) || 0 == buffer.get(i) ) {
				continue;
			}
			 
			session = HibernateUtils.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			 
			int count = 0;
			Object[] object = DBMotorcycleSerie.currentSeries().get(i);
			MotorcycleSerie serie = (MotorcycleSerie)object[0];
			for (Motorcycle motor: serie.cycles()) {
				if (motor.isStoraged()) {
					++count;
					
					motor.setStoraged(false);
					session.update(motor);
					if (count == buffer.get(i)) {break; }
				}
			}
			 
			String sql = new String(
						"INSERT INTO CT_ĐƠNHÀNG(MÃĐƠNHÀNG, MÃLOẠI, SỐLƯỢNG) " +
						"VALUES ( " + id + ", " + serie.id() + ", " + buffer.get(i) + ")");
			session.createSQLQuery(sql).executeUpdate();			
			
			session.getTransaction().commit();
		}
		
		
		//update buffer
		DBMotorcycleSerie.updateSeriesBuffer();
		updateOrderBuffer();
	}
	
	public static void editOrder(Date date, ArrayList<Integer> buffer) {
		_editOrder(date, buffer);
	}
	
	public static void addOrder(Date date, ArrayList<Integer> buffer) {
		_addOrder(date, buffer);
	}
	
	public static void deleteOrder() {_deleteOrder(); }
	
	
	public static ArrayList<Order> getCurrentOrder() {
		return _getCurrentOrder();
	}
	
	public static void updateOrderBuffer() {
		_currentOrders = getCurrentOrder();
	}
	
	public static ArrayList<Order> currentOrders() {
		if (null == _currentOrders) {
			updateOrderBuffer();
		}
		return _currentOrders;
	}
}

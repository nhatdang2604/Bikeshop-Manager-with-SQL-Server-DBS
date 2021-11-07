package model;
import org.hibernate.SQLQuery;
import java.util.ArrayList;

import org.hibernate.Session;

public class DBMotorcycleSerie extends Database {
	private static ArrayList<Object[]> _currentSeries;
	
	private static ArrayList<Object[]> _getCurrentSeries() {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		 
		String sql = new String(
	  			 "FROM " + MotorcycleSerie.class.getName() + " a LEFT JOIN a._company");
		
		
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> result = (ArrayList<Object[]>) session.createQuery(sql).list();
		 
		session.getTransaction().commit();
		return result;
	}
	
	private static void _addMotorcycles(int count,
			MotorcycleSerie serie, boolean isStorage) {
		
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		for (int i = 0; i<count; ++i) {
			session.save(new Motorcycle(serie, isStorage));
		}
		
		session.getTransaction().commit();
		 
		//update buffer
		updateSeriesBuffer();
	}
	
	private static Long _countNumberOfMotorcycle(int serieID) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//Process to calculate the quantity 
		String sql = "SELECT COUNT(*) FROM " +  Motorcycle.class.getName() + 
		" motorcycle WHERE motorcycle._serieID = " + serieID;
		
		@SuppressWarnings("unchecked")
		ArrayList<Long> result = (ArrayList<Long>) session.createQuery(sql).list();
		 
		session.getTransaction().commit();
		return result.get(0);
	}
	
	private static void _deleteMotorcycleSeries(int index) {
		//Delete all Motorcycle which have the same company first
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String sql = new String(
				"DELETE XE WHERE MÃLOẠI = " + ((MotorcycleSerie)(_currentSeries.get(index)[0])).id());
		
		session.createSQLQuery(sql).executeUpdate();
		
		sql = new String(
				"DELETE CT_ĐƠNHÀNG WHERE MÃLOẠI = " + ((MotorcycleSerie)(_currentSeries.get(index)[0])).id()
				);
		session.createSQLQuery(sql).executeUpdate();	
		
		session.getTransaction().commit();
		((MotorcycleSerie)(_currentSeries.get(index)[0])).cycles().clear();
		
		
		
		//Delete the serie
		session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		
		sql = new String(
				"DELETE " + MotorcycleSerie.class.getName() +
				" WHERE _id = " + ((MotorcycleSerie)(_currentSeries.get(index)[0])).id());
			
		session.createQuery(sql).executeUpdate();
		session.getTransaction().commit();
		
		//update buffer
		updateSeriesBuffer();
	}
	
	private static void _addMotorcycleSeries(
			String name, String companyName, int importPrice, 
			int price, int quantity) {
 
		 //find companies id
		 //int companyID = -1;
		 MotorcycleCompany company = null;
		 for (MotorcycleCompany companies: DBMotorcycleCompany.currentCompanies()) {
			 if (companyName.equals(companies.name())) {
				 company = companies;
				 break;
			 }
		 }
		 
		 //Add new company
		 MotorcycleSerie serie = new MotorcycleSerie(name, company, importPrice, price);
		 
		 
		 //Begin transaction
		 Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 
		 //Save company to dbs
		 session.save(serie);
		 
		 session.getTransaction().commit();
		 
		 //Create quantity Motorcycle which have _serieID = serieID
		 _addMotorcycles(quantity, serie, true);
		 
		 
		 
		 //update buffer
		 updateSeriesBuffer();
	}
	
	@SuppressWarnings("deprecation")
	private static void _editMotorcycleSeries(
			int index, String name, String companyName, int importPrice, 
			int price, int quantity) {
		 
		//find companies id
		 //int companyID = -1;
		 MotorcycleCompany company = null;
		 for (MotorcycleCompany companies: DBMotorcycleCompany.currentCompanies()) {
			 if (companyName.equals(companies.name())) {
				 company = companies;
				 break;
			 }
		 }
		 Object[] object = _currentSeries.get(index);
		 MotorcycleSerie serie = (MotorcycleSerie) object[0];
		 MotorcycleCompany comp = (MotorcycleCompany) object[1];
		 
		 if (comp.id() != company.id()) {
			 comp.series().remove(serie);
			 company.series().add(serie);
		 }
		 
		 
		
		//Decide to increase or decrease quantity of series cycle
		int count = 0;
		for (Motorcycle motor: serie.cycles()) {
			 if (motor.isStoraged()) ++count;
		}
			 
		String sql;
		ArrayList<Motorcycle> removalList = new ArrayList<Motorcycle>();
		//Decrease
		if (count > quantity) {
			int n = 0;
			for (Motorcycle motor: serie.cycles()) {
				 if (motor.isStoraged()) {
					Session session = HibernateUtils.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					 ++n;
					 sql = new String(
							 "DELETE " + Motorcycle.class.getName() +
								" WHERE _id = " + motor.id());
					 session.createQuery(sql).executeUpdate();
					 //serie.cycles().remove(motor);
					 removalList.add(motor);
					 session.getTransaction().commit();
					 
					 if (n == count - quantity) {
						 break;
					 }
				 }
				
			}
				 
		} else {
			
			//Increase
			if (count < quantity) {
				Session session = HibernateUtils.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				for (int i = 0; i < quantity - count; ++i) {
					Motorcycle motor = new Motorcycle(serie, true);
					Integer id = (Integer) session.save(motor);
					serie.cycles().add(new Motorcycle(id, motor.serie(), true));
					
				}
				session.getTransaction().commit();
			}
		}
		
		if (removalList.size() > 0) {
			for (Motorcycle motor: removalList) {
				serie.cycles().remove(motor);
			}
		}
 		
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//Add new series
		sql = new String(
				"UPDATE DÒNGXE SET " +
				"TÊNLOẠI = \'" + name + "\', " +
				"GIÁNHẬP = " + importPrice + ", " + 
				"GIÁBÁN = " + price + ", " + 
				"HÃNGXE = " + company.id() +
				" WHERE MÃLOẠI = " + serie.id());
		
		session.createSQLQuery(sql).executeUpdate();
		//session.createQuery(sql).executeUpdate();
		session.getTransaction().commit();
		
		//update buffer
		updateSeriesBuffer();
		//updateAll
	}
	
	public static Long countNumberOfMotorcycle(int serieID) {
		return _countNumberOfMotorcycle(serieID);
	}
	public static void addMotorcycleSeries(
			String name, String company, int importPrice, 
			int price, int quantity) {
		_addMotorcycleSeries(name, company, importPrice, price, quantity);
	}
	
	public static void editMotorcycleSeries(
			int index, String name, String companyName, int importPrice, 
			int price, int quantity) {
		_editMotorcycleSeries(index, name, companyName, importPrice, price, quantity);
	}
	public static void deleteMotorcycleSeries(int index) {
		_deleteMotorcycleSeries(index);
	}
	
	public static ArrayList<Object[]> getCurrentSeries() {
		return _getCurrentSeries();
	}
	
	public static void updateSeriesBuffer() {
		_currentSeries = getCurrentSeries();
	}
	
	public static ArrayList<Object[]> currentSeries() {
		if (null == _currentSeries) {
			updateSeriesBuffer();
		}
		return _currentSeries;
	}
}

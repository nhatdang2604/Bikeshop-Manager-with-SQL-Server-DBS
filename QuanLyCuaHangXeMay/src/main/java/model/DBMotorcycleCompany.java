package model;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.hibernate.Session;

public class DBMotorcycleCompany extends Database {
	private static ArrayList<MotorcycleCompany> _currentCompanies;
	
	private static ArrayList<MotorcycleCompany> _getCurrentCompany() {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		 
		String sql = new String("SELECT Company\n"
	  			 + "FROM " + MotorcycleCompany.class.getName() + " Company\n");
		
		@SuppressWarnings("unchecked")
		ArrayList<MotorcycleCompany> companies = (ArrayList<MotorcycleCompany>) session.createQuery(sql).list();
		 
	
		 
		session.getTransaction().commit();
		return companies;
	}
	
	private static void _addMotorcycleCompany(
			String name, String country, int year) {

		 Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 
		 //Add new company
		 MotorcycleCompany company = new MotorcycleCompany(name, country, year);
		 
		 //Save company to dbs
		 session.save(company);
		 
		 session.getTransaction().commit();
		 
		 //update buffer
		 updateCompaniesBuffer();
	}
	
	private static void _editMotorcycleCompany(
			int index, String name, String country, int year) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		 
		//Add new company
		String sql = new String(
				"UPDATE " + MotorcycleCompany.class.getName() +" set " +
				"_name = \'" + name + "\', " +
				"_country = \'" + country + "\', " +
				"_year = " + year +
				" where _id = " + _currentCompanies.get(index).id());
			
		
		session.createQuery(sql).executeUpdate();
		session.getTransaction().commit();
		
		//update buffer
		updateCompaniesBuffer();
		//updateAll
	}
	
	private static void _deleteMotorcycleCompany(int index) {
		
		//Delete all Motorcycle which have the same company first
		for (MotorcycleSerie series: _currentCompanies.get(index).series()) {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			String sql = new String(
					"DELETE XE WHERE MÃLOẠI = " + series.id());
			
			session.createSQLQuery(sql).executeUpdate();
			session.getTransaction().commit();
			series.cycles().clear();
		}
		
		//Dellete all DetailOrder which have the deleted serie the same company
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String sql = new String(
				"DELETE CT_ĐƠNHÀNG CTĐH WHERE EXISTS ( "
				+ "SELECT 1 " 
				+ "FROM DÒNGXE DX" 
				+ "WHERE DX.HÃNGXE = " + _currentCompanies.get(index).id()
				+ " AND DX.MÃLOẠI = CTĐH.MÃLOẠI)");
		
		session.createSQLQuery(sql).executeUpdate();
		session.getTransaction().commit();

		
		
		//Delete all MotorcycleSeries which have the deleted Company
		session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		sql = new String(
				"DELETE DÒNGXE WHERE HÃNGXE = " + _currentCompanies.get(index).id());
		
		session.createSQLQuery(sql).executeUpdate();
		session.getTransaction().commit();
		 _currentCompanies.get(index).series().clear();
		
		
		//Delete the company
		session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		
		sql = new String(
				"DELETE " + MotorcycleCompany.class.getName() +
				" WHERE _id = " + _currentCompanies.get(index).id());
			
		session.createQuery(sql).executeUpdate();
		session.getTransaction().commit();
		
		//update buffer
		updateCompaniesBuffer();
		//updateAll
	
	}
	
	public static void addMotorcycleCompany(
			String name, String country, int year) {
		_addMotorcycleCompany(name, country, year);
	}
	
	public static void editMotorcycleCompany(
			int index, String name, String country, int year) {
		_editMotorcycleCompany(index, name, country, year);
	}
	
	public static void deleteMotorcycleCompany(int index) {
		_deleteMotorcycleCompany(index);
	}
	
	public static ArrayList<MotorcycleCompany> getCurrentCompany() {
		return _getCurrentCompany();
	}
	
	public static void updateCompaniesBuffer() {
		_currentCompanies = getCurrentCompany();
	}
	
	public static ArrayList<MotorcycleCompany> currentCompanies() {
		if (null == _currentCompanies) {
			updateCompaniesBuffer();
		}
		return _currentCompanies;
	}
	
}
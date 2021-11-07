package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name = "HÃNGXE")
public class MotorcycleCompany {

	//Constructor
	public MotorcycleCompany(){}
	public MotorcycleCompany(int id, String name, String country, int year) {
		_id = id;
		_name = name;
		_country = country;
		_year = year;
		_series = new HashSet<MotorcycleSerie>();
	}
	public MotorcycleCompany(String name, String country, int year) {
		_name = name;
		_country = country;
		_year = year;
		_series = new HashSet<MotorcycleSerie>();
	}
	
	//Private attribute
	@Id
	@Column(name = "MÃHÃNG")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;
	
	@Column(name = "TÊNHÃNG")
	private String _name;
	
	@Column(name = "QUỐCGIA")
	private String _country;
	
	@Column(name = "NĂMTHÀNHLẬP")
	private int _year;
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "_company")
	private Set<MotorcycleSerie> _series;
	
	//Getter
	public int id() {return _id; }
	public String name() {return _name; }
	public String country() {return _country; }
	public int year() {return _year; }
	public Set<MotorcycleSerie> series() {return _series; }
	
	public void setName(String name) {_name = name; }
	public void setCountry(String country) {_country = country; }
	public void setYear(int year) {_year = year; }
	
}

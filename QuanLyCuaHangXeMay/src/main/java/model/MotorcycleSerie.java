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
import javax.persistence.Transient;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "DÒNGXE")
public class MotorcycleSerie {
	
	
	//Constructor
	public MotorcycleSerie(){}
	public MotorcycleSerie(int id, String name, MotorcycleCompany company,
			int companyID, int importPrice, int price) {
		_id = id;
		_name = name;
		_company =  company;
		_importPrice = importPrice;
		_price = price;
		_cycles = new HashSet<Motorcycle>();
		_detailOrders = new HashSet<DetailOrder>();
		company.series().add(this);
		
	}
	public MotorcycleSerie(String name, MotorcycleCompany company,
			int importPrice, int price) {
		_name = name;
		_company = company;
		_importPrice = importPrice;
		_price = price;
		_cycles = new HashSet<Motorcycle>();
		_detailOrders = new HashSet<DetailOrder>();
		company.series().add(this);
	}
	
	//Private attribute
	@Id
	@Column(name = "MÃLOẠI")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;
		
	@Column(name = "TÊNLOẠI")
	private String _name;
	
		
	@Column(name = "GIÁNHẬP")
	private int _importPrice;
	
	@Column(name = "GIÁBÁN")
	private int _price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HÃNGXE", nullable = false, 
	        foreignKey = @ForeignKey(name = "fk_serie_company"))
	private MotorcycleCompany _company;
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "_serie")
	private Set<Motorcycle> _cycles;
	
	@Transient
	private Set<DetailOrder> _detailOrders;
	
	private int _getQuantityInStoraged() {
		int quantity = 0;
		for (Motorcycle cycle: _cycles) {
			if (cycle.isStoraged()) {
				++quantity;
			}
		}
		return quantity;
	}

	
	//Getter
	public int id() {return _id; }
	public String name() {return _name; }
	public MotorcycleCompany company() {return _company; }
	public int importPrice() {return _importPrice; }
	public int price() {return _price; }
	public Set<Motorcycle> cycles() {return _cycles; }
	public Set<DetailOrder> detailOrders() {
		if (null == _detailOrders) {
			_detailOrders = new HashSet<DetailOrder>();
		}
		return _detailOrders;
	}
	
	//Setter
	public void setName(String name) {_name = name;}
	public void setImportPrice(int importPrice) {_importPrice = importPrice; }
	public void setPrice(int price) {_price = price;}
	
	//Method
	public int getQuantityInStoraged() {return _getQuantityInStoraged(); }
}

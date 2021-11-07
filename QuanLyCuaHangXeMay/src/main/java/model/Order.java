package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "ĐƠNHÀNG")
public class Order {
	
	@Id
	@Column(name = "MÃĐƠNHÀNG")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NGÀYĐẶTHÀNG")
	private Date _orderDay;
	
	@Transient
	private Set<DetailOrder> _detailOrders;
	
	public Order() {}
	public Order(int id, Date orderDay) {
		_id = id;
		_orderDay = orderDay;
		_detailOrders = new HashSet<DetailOrder>();
	}
	
	public Order(Date orderDay) {
		_orderDay = orderDay;
		_detailOrders = new HashSet<DetailOrder>();
	}
	
	private int _totalCost() {
		int result = 0;
		
		if (null == _detailOrders) {return 0; }
		for (DetailOrder detailOrder: _detailOrders) {
			result += detailOrder.totalCost();
		}
		return result;
	}
	
	private int _totalTurnover() {
		int result = 0;
		
		if (null == _detailOrders) {return 0; }
		for (DetailOrder detailOrder: _detailOrders) {		
			result += detailOrder.totalTurnover();
		}
		return result;
	}
	
	private int _totalProfit() {
		int result = 0;
		
		if (null == _detailOrders) {return 0; }
		for (DetailOrder detailOrder: _detailOrders) {
			
			result += detailOrder.totalProfit();
		}
		return result;
	}
	
	
	public int id() {return _id; }
	public Date orderDay() {return _orderDay; }
	public Set<DetailOrder> detailOrders() {
		if (null == _detailOrders) {
			_detailOrders = new HashSet<DetailOrder>();
		}
		return _detailOrders; }
	
	
	public void setOrderDay(Date orderDay) {_orderDay = orderDay; }
	
	public int totalCost() {return _totalCost();}
	public int totalTurnover() {return _totalTurnover();}
	public int totalProfit() {return _totalProfit();}
}

package model;


public class DetailOrder {
	
	
	private Order _order;
	private MotorcycleSerie _serie;
	private int _quantity;
	
	public DetailOrder() {}
	public DetailOrder(Order order, MotorcycleSerie serie, int quantity) {
		_order = order;
		_serie = serie;
		_quantity = quantity;
		order.detailOrders().add(this);
		serie.detailOrders().add(this);
	}
	
	public Order order() {return _order;}
	public MotorcycleSerie serie() {return _serie; }
	public int quantity() {return _quantity; }
	
	
	
	public int totalCost() {
		return _quantity * _serie.importPrice();
	}
	
	public int totalTurnover() {
		return _quantity * _serie.price();
	}
	
	public int totalProfit() {
		return _quantity * (_serie.price() - _serie.importPrice());
	}
}

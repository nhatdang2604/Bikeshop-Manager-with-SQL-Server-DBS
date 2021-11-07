package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "XE")
public class Motorcycle {
	
	//Constructor
	public Motorcycle(){}
	public Motorcycle(int id, MotorcycleSerie serie , boolean isStoraged) {
		_id = id;
		_serie = serie;
		_isStoraged = isStoraged;
		serie.cycles().add(this);
	}
	public Motorcycle(MotorcycleSerie serie , boolean isStoraged) {
		_serie = serie;
		_isStoraged = isStoraged;
		serie.cycles().add(this);
	}	
		
	//Private attribute
	@Id
	@Column(name = "MÃXE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;
	
	//@ManyToOne
	//@JoinColumn(name = "MÃLOẠI", referencedColumnName = "HÃNGXE")
	//@Column(name = "MÃLOẠI")
	//private int _serieID;
	
	@Column(name = "CÒNTRONGKHO")
	private boolean _isStoraged;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MÃLOẠI", nullable = false, 
	        foreignKey = @ForeignKey(name = "fk_motorcycle_serie"))
	private MotorcycleSerie _serie;
	
	public int id() {return _id;};
	//public int serieID() {return _serieID;}
	public boolean isStoraged() {return _isStoraged;}
	public MotorcycleSerie serie() {return _serie; }
	
	public void setStoraged(boolean value) {_isStoraged = value; }
}

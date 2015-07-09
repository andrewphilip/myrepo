package andy.hib.web.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="dailystockrecord")
public class DailyStockRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int rid;
	
	@Column(name="stockdate")
	private Date stockdate;
	
	@Column(name="price")
	private double price;
	
	@Column(name="stockopen")
	private double stockopen;
	
	@Column(name="stockclose")
	private double stockclose;
	
	@Column(name="volume")
	private double volume;
	
	@Column(name="stockhigh")
	private double stockhigh;
	
	@Column(name="stocklow")
	private double stocklow;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="stockid", nullable=false)
	private Stock stock;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}

	public Date getStockdate() {
		return stockdate;
	}
	public void setStockdate(Date stockdate) {
		this.stockdate = stockdate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getStockopen() {
		return stockopen;
	}
	public void setStockopen(double stockopen) {
		this.stockopen = stockopen;
	}
	public double getStockclose() {
		return stockclose;
	}
	public void setStockclose(double stockclose) {
		this.stockclose = stockclose;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getStockhigh() {
		return stockhigh;
	}
	public void setStockhigh(double stockhigh) {
		this.stockhigh = stockhigh;
	}
	public double getStocklow() {
		return stocklow;
	}
	public void setStocklow(double stocklow) {
		this.stocklow = stocklow;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	

}

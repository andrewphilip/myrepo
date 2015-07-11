package andy.hib.web.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tradesummary")
@AssociationOverrides({
							@AssociationOverride(name="jKey.stock", joinColumns=@JoinColumn(name="stockid")),
							@AssociationOverride(name="jKey.investor", joinColumns=@JoinColumn(name="userid"))
					  })
public class TradeSummary implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private StockInvestor jKey= new StockInvestor();
		private String isBuy;
		private double price;
		private int qty;

		
		@EmbeddedId
		public StockInvestor getjKey() {
			return jKey;
		}
		
		public void setjKey(StockInvestor jKey) {
			this.jKey = jKey;
		}
		
		@Column(name="isbuy", nullable=false, length=1)
		public String getIsBuy() {
			return isBuy;
		}
		public void setIsBuy(String isBuy) {
			this.isBuy = isBuy;
		}
		
		@Column(name="price")
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		
		@Column(name="qty")
		public int getQty() {
			return qty;
		}
		public void setQty(int qty) {
			this.qty = qty;
		}
		
		@Transient
		public Stock getStock(){
			return jKey.getStock();
		}
		
		public void setStock(Stock stock){
			jKey.setStock(stock);
		}
		
		@Transient
		public Investor getInvestor(){
			return jKey.getInvestor();
		}
		
		public void setInvestor(Investor investor){
			jKey.setInvestor(investor);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((jKey == null) ? 0 : jKey.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TradeSummary other = (TradeSummary) obj;
			if (jKey == null) {
				if (other.jKey != null)
					return false;
			} else if (!jKey.equals(other.jKey))
				return false;
			return true;
		}
		
		
}

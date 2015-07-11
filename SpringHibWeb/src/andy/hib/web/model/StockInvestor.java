package andy.hib.web.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
@Embeddable
public class StockInvestor implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Stock stock;
		private Investor investor;
		
		@ManyToOne
		public Stock getStock() {
			return stock;
		}
		public void setStock(Stock stock) {
			this.stock = stock;
		}
		
		@ManyToOne
		public Investor getInvestor() {
			return investor;
		}
		public void setInvestor(Investor investor) {
			this.investor = investor;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((investor == null) ? 0 : investor.hashCode());
			result = prime * result + ((stock == null) ? 0 : stock.hashCode());
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
			StockInvestor other = (StockInvestor) obj;
			if (investor == null) {
				if (other.investor != null)
					return false;
			} else if (!investor.equals(other.investor))
				return false;
			if (stock == null) {
				if (other.stock != null)
					return false;
			} else if (!stock.equals(other.stock))
				return false;
			return true;
		}
		
		
}

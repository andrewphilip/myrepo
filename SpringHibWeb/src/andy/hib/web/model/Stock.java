package andy.hib.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@javax.persistence.Entity
@Table(name="stocks")
public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="tradesummary",
			   joinColumns={
								@JoinColumn(name="stockid", nullable=false)
							},
				inverseJoinColumns={
										@JoinColumn(name="userid", nullable=false)
									}			
			    )
	private List<Investor>  investors=new ArrayList<Investor>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="jKey.stock", cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			  org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private Set<TradeSummary> tradeSummaries= new HashSet<TradeSummary>();
	
	@Id
	@GeneratedValue
	private int stockid;
	
	@Column
	private String market;
	
	@Column
	private String ticker;
	
	@Column
	private String displayname;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="stock")
	private Set<DailyStockRecord> dailyStockRecords = new HashSet<DailyStockRecord>();
	
	public int getStockid() {
		return stockid;
	}

	public void setStockid(int stockid) {
		this.stockid = stockid;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public List<Investor> getInvestors() {
		return investors;
	}

	public void setInvestors(List<Investor> investors) {
		this.investors = investors;
	}

	
	public Set<TradeSummary> getTradeSummaries() {
		return tradeSummaries;
	}

	public void setTradeSummaries(Set<TradeSummary> tradeSummaries) {
		this.tradeSummaries = tradeSummaries;
	}

	
	public Set<DailyStockRecord> getDailyStockRecords() {
		return dailyStockRecords;
	}

	public void setDailyStockRecords(Set<DailyStockRecord> dailyStockRecords) {
		this.dailyStockRecords = dailyStockRecords;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stockid;
		result = prime * result + ((ticker == null) ? 0 : ticker.hashCode());
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
		Stock other = (Stock) obj;
		if (stockid != other.stockid)
			return false;
		if (ticker == null) {
			if (other.ticker != null)
				return false;
		} else if (!ticker.equals(other.ticker))
			return false;
		return true;
	}
	
	
}

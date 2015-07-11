package andy.hib.web.service;

import java.util.List;

import andy.hib.web.model.DailyStockRecord;
import andy.hib.web.model.Investor;
import andy.hib.web.model.Stock;
import andy.hib.web.model.TradeSummary;

public interface StockService {
	
	public List<Stock> getAllStocks();
	
	public List<Investor> getInvestorsByStockId(int stockid);	
	
//	public Stock getStockById(Integer id);
	
	public void saveStock(int stockid, double price, int qty, String userid);	
	
	public List<DailyStockRecord> getDailyStockRecords(int stockid);	
	
}

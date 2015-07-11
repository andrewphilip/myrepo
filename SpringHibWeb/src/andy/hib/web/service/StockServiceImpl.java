package andy.hib.web.service;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andy.hib.web.dao.StockDao;
import andy.hib.web.model.DailyStockRecord;
import andy.hib.web.model.Investor;
import andy.hib.web.model.Stock;
import andy.hib.web.model.TradeSummary;
@Service("stockService")
public class StockServiceImpl implements StockService {

	@Autowired
	StockDao stockDao;
	
	public List<Stock> getAllStocks() {
		return stockDao.getAllStocks();
	}

	public List<Investor> getInvestorsByStockId(int stockid){
		return stockDao.getInvestorsByStockId(stockid);
	}	
	
//	public Stock getStockById(Integer id){
//		return stockDao.getStockById(id);
//	}
	
	public void saveStock(int stockid, double price, int qty, String userid){
		stockDao.saveStock(stockid, price, qty,userid);
	}
	
	public List<DailyStockRecord> getDailyStockRecords(int stockid){
		return stockDao.getDailyStockRecords(stockid);
	}
}

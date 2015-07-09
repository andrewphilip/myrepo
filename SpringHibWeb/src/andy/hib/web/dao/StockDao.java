package andy.hib.web.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import andy.hib.web.model.DailyStockRecord;
import andy.hib.web.model.Investor;
import andy.hib.web.model.Stock;
import andy.hib.web.model.TradeSummary;

@Repository
public class StockDao {
	
	@Autowired
	SessionFactory sessionFactory;

	protected Session getCurrentSession(){
		Session sess=null;
		sess=sessionFactory.getCurrentSession();
		if(sess == null){
			sess= sessionFactory.openSession();
		}
		
		return sess;
	}
	
	public List<Stock> getAllStocks(){
		Session sess=sessionFactory.openSession();
		List<Stock> list = sess.createQuery("from Stock").list();
		sess.close();
		return  list;
	}
	
	
	public List<Investor> getInvestorsByStockId(int stockid){
		Session sess=sessionFactory.openSession();
		sess.flush();
		String query="from Stock where stockid ="+stockid;
		List<Investor> investors=sess.createQuery(query).list();
		//sess.close();
		return investors;
	}
	
	/*
	public Stock getStockById(Integer id){
		Stock obj = null;
		Session sess=sessionFactory.openSession();
		obj=(Stock) sess.load(Stock.class, id);
		System.out.println("returning from getStockById() :"+obj.getTicker());
		sess.close();
		return obj;
	}
	
	public Investor getInvestorById(String userid){
		Investor obj=null;
		Session sess=sessionFactory.openSession();
		obj=(Investor) sess.load(Investor.class, userid);
		System.out.println("returning from getInvestorById() :"+obj.getUserid());
		sess.close();
		return obj;
	}
	*/
	
	public void saveStock(int stockid, double price, int qty, String userid ){
		Session sess=sessionFactory.openSession();
		System.out.println("transaction marked begin...");
		Stock s=(Stock) sess.load(Stock.class, stockid);
		Investor inv=(Investor) sess.load(Investor.class, userid);
		TradeSummary ts= new TradeSummary();
		ts.setStock(s);
		ts.setInvestor(inv);
		ts.setIsBuy("Y");
		ts.setPrice(price);
		ts.setQty(qty);
		s.getTradeSummaries().add(ts);
		sess.save(s);
		System.out.println("saved  Stock object...");
		sess.flush();
		sess.close();
	}
	
	public List<DailyStockRecord> getDailyStockRecords(int stockid){
		Session sess=sessionFactory.openSession();
		System.out.println("inside getDailyStockRecords...");
		sess.flush();
		String query="from DailyStockRecord where stockid ="+stockid;
		List<DailyStockRecord> list = sess.createQuery(query).list();
		return list;
	}
	
}

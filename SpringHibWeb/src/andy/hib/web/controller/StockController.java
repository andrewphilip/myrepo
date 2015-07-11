package andy.hib.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import andy.hib.web.model.DailyStockRecord;
import andy.hib.web.model.Investor;
import andy.hib.web.model.Stock;
import andy.hib.web.model.TradeSummary;
import andy.hib.web.service.StockService;

@Controller
@RequestMapping(value="/stock")
public class StockController {

	@Autowired
	StockService stockService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getAllStocks(ModelMap model){
		List<Stock> list=stockService.getAllStocks();
		model.addAttribute("stocks", list);
		return "list";
	}
	
	@RequestMapping(value="/investor/{id}", method=RequestMethod.GET)
	public String getInvestorsByStock(@PathVariable Integer id, ModelMap model){
		List<Investor> investors=stockService.getInvestorsByStockId(id);
		model.addAttribute("investors", investors);
		model.addAttribute("stockid", id);
		model.addAttribute("stockObj", investors.get(0));
		return "stockhome";
	}
	
	@RequestMapping(value="/buy/{id}/{userid}")
	public String buyStock4User(@PathVariable Integer id, @PathVariable String userid, ModelMap model){
		System.out.println("Userid:"+userid+" StockId:"+id);
		stockService.saveStock(id, 150.00, 200, userid);
		System.out.println("successfully saved.");
		return "redirect:/stock/list";
	}
	
	
	@RequestMapping(value="/daily/{id}", method=RequestMethod.GET)
	public String getDailyStockRecords(@PathVariable Integer id, ModelMap model){
		List<DailyStockRecord> list= stockService.getDailyStockRecords(id);
		model.addAttribute("dailystockrecords", list);
		System.out.println("Retrieved list of dailystockrecords...");
		return "dailystocklist";
	}
	
}

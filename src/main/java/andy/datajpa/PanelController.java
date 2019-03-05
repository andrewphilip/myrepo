package andy.datajpa;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import andy.datajpa.models.Panel;
import andy.datajpa.services.PanelService;

@RestController
public class PanelController {

	
	private static final Logger log = LoggerFactory.getLogger(PanelController.class);
	@Autowired
	private PanelService srvc;
	
	@PostMapping("/panels")
	public void addPanel(@RequestBody Panel panel) {
		log.info("Inside PanelController::addPanel");
		log.info(panel.toString());
		if(panel.getCreatedDate() == null) {
			Calendar cal=Calendar.getInstance();
			Date dt=new Date(cal.getTime().getTime());
			panel.setCreatedDate(dt);
		}
		srvc.addPanel(panel);
	}
	
	@GetMapping("/panels")
	public List<Panel> getAllPanels(){
		log.info("Inside PanelController::getAllPanels");
		return srvc.getAllPanels();
	}
	
	@GetMapping("/panels/{code}")
	public Panel getPanelByCode(@PathVariable String code){
		log.info("Inside PanelController::getPanelByCode");
		return srvc.getPanelByCode(code);
	}
	
}

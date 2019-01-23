package andy.datajpa.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andy.datajpa.dao.PanelDAO;
import andy.datajpa.models.Panel;
import andy.datajpa.repo.PanelRepository;

@Service
public class PanelService {

	
	private static final Logger log = LoggerFactory.getLogger(PanelService.class);

	@Autowired
	private PanelDAO dao;
	@Autowired
	private PanelRepository repo;
	
	public List<String> getAllPanelCodesByCreatedDate(){
		System.out.println("Inside PanelService::getAllPanelCodesByCreatedDate()....");
		return dao.getAllPanelCodesByCreatedDate();
	}
	
	public Panel addPanel(Panel panel) {
		log.info("Saving panel...");
		log.info(panel.toString());
		return repo.save(panel);
	}
	
	public List<Panel> getAllPanels(){
		log.info("Retrieval all panels...");
		List<Panel> panels=StreamSupport.stream( repo.findAll().spliterator(), false)
					.collect(Collectors.toList());
		
		return panels;
	}
	
	public Panel getPanelByCode(String code){
		log.info("Inside PanelService::getPanelByCode");
		return repo.findByPanelCode(code);
	}
	
}

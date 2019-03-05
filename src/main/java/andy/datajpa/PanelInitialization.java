package andy.datajpa;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import andy.datajpa.dao.PanelDAO;
import andy.datajpa.models.Panel;
import andy.datajpa.repo.PanelRepository;
import andy.datajpa.services.PanelService;

@Component
public class PanelInitialization implements ApplicationRunner{
	@Autowired
	private PanelService srvc;
	@Autowired
	private PanelRepository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		Calendar cal=Calendar.getInstance();
		Date dt=new Date(cal.getTime().getTime());
		repo.save(new Panel("PNL_001", dt));
		System.out.println("Saved");
		repo.findAll()
			.forEach(System.out::println);
		
		srvc.getAllPanelCodesByCreatedDate();
		
	}

}

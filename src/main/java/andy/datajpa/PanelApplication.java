package andy.datajpa;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import andy.datajpa.models.Panel;
import andy.datajpa.repo.PanelRepository;
import andy.datajpa.services.PanelService;

@SpringBootApplication
@EnableScheduling
public class PanelApplication {
	public static void main(String[] args) {
		SpringApplication.run(PanelApplication.class);
	}
	
	/*
	@Bean
	public CommandLineRunner demoRun(PanelRepository repo, PanelService  srvc) {
		return 	(args) -> {
			Calendar cal=Calendar.getInstance();
			Date dt=new Date(cal.getTime().getTime());
			repo.save(new Panel("PNL_001", dt));
			System.out.println("Saved");
			repo.findAll()
				.forEach(System.out::println);
			
			srvc.getAllPanelCodesByCreatedDate();
		};
	}
	*/
}

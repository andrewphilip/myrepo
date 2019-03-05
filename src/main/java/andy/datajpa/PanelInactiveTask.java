package andy.datajpa;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PanelInactiveTask {

	
	private static final Logger log = LoggerFactory.getLogger(PanelInactiveTask.class);

	private static final SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * The pattern is a list of six single space-separated fields: 
	 * representing second, minute, hour, day, month, weekday. Month and weekday names 
	 * can be given as the first three letters of the English names.
	 * "0 0 0 25 12 ?" = every Christmas Day at midnight
	 * "0 0 6,19 * * *" = 6:00 AM and 7:00 PM every day.
	 */
	 
	// '@Scheduled(cron="*/5 * * * * MON-FRI")'
	
	//@Scheduled(cron="0 0 4 * * *") // occurs every day at 4:00AM
	
	//@Scheduled(cron="*/10 * * * * *")  //occurs every 10 seconds...
	@Scheduled(cron="0 0/1 * * * *")  //occurs every minute...
	public void run2InactivatePanel() {
		log.info("Inactivating panel at {}", sdf.format(new Date()));
	}
}

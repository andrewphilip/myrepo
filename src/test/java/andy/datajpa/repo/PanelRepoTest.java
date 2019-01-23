package andy.datajpa.repo;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import andy.datajpa.models.Panel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PanelRepoTest {
	@Autowired
	private PanelRepository repo;
	
	@Test
	public void testPanelSave() {
		Calendar cal=Calendar.getInstance();
		Date dt=new Date(cal.getTime().getTime());
		
		Panel p =new Panel("PNL_004");
		p.setCreatedDate(dt);
		repo.save(p);
		assertNotNull(repo.findByPanelCode("PNL_004"));
		
	}
}

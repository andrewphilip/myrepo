package andy.datajpa.services;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import andy.datajpa.models.Panel;
import andy.datajpa.repo.PanelRepository;

@RunWith(MockitoJUnitRunner.class)
public class PanelServiceTest {

	@Mock
	PanelRepository repo;
	
	@InjectMocks
	PanelService srvc;
	
	@Test
	public void testPanel_tobeAdded() {
		Calendar cal=Calendar.getInstance();
		Date dt=new Date(cal.getTime().getTime());
		
		Panel p =new Panel("PNL_004");
		p.setCreatedDate(dt);
		
		when(repo.save(p)).thenReturn(p);
		
		assertEquals(p, srvc.addPanel(p));
		
	}
	
	
}

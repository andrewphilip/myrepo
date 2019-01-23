package andy.datajpa.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import andy.datajpa.PanelController;
import andy.datajpa.models.Panel;
import andy.datajpa.services.PanelService;

@RunWith(SpringRunner.class)
@WebMvcTest(PanelController.class)
public class PanelControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PanelService srvc;
	
	@Test
	public void givenPanel_tobeAdded() throws Exception{
		Calendar cal=Calendar.getInstance();
		Date dt=new Date(cal.getTime().getTime());
		
		Panel p =new Panel("PNL_003");
		p.setCreatedDate(dt);
		
		ObjectMapper mapper=new ObjectMapper();
		String panelJson=mapper.writeValueAsString(p);
		
		mvc.perform(post("/panels")
					.contentType(MediaType.APPLICATION_JSON)
					.content(panelJson)
				).andExpect(status().isOk());
	}

}

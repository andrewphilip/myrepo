package andy.webjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import andy.webjpa.model.Area;
import andy.webjpa.service.AreaService;

@Controller
@RequestMapping(value="/area")
@SessionAttributes("area")
public class AreaController {
		
		@Autowired
		private AreaService areaSrvc;
		
		@RequestMapping(value="/list", method=RequestMethod.GET)
		public String showList(Model model){
			List<Area> list=areaSrvc.findAll();
			model.addAttribute("areas", list);
			return "arealist";
		}
		
		
		@RequestMapping(value="/{areaseq}", method=RequestMethod.GET)
		public String getArea(@PathVariable String areaseq, Model model){
			Area area=areaSrvc.findByArea(areaseq);
			model.addAttribute("viewarea", area);
			return "viewarea";
		}
}

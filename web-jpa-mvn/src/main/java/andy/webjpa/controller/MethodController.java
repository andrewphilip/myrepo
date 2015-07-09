package andy.webjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import andy.webjpa.model.Method;
import andy.webjpa.service.MethodService;
import javax.annotation.*;

@Controller
@RequestMapping(value="/method")
@SessionAttributes("method")
public class MethodController {

	@Autowired
	public MethodService methodService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String showList(Model model){
		List<Method> methods=methodService.findAll();
		model.addAttribute("methods", methods);
		return "list";
	}
}

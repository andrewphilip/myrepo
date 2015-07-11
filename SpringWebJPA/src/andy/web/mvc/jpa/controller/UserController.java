package andy.web.mvc.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import andy.web.mvc.jpa.model.User;
import andy.web.mvc.jpa.service.UserService;

@Controller
@RequestMapping(value="/users")
@SessionAttributes("user")
public class UserController {

	@Autowired
	private UserService userSrvc;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String  getAllUsers(ModelMap model){
		List<User> users=userSrvc.findAll();
		model.addAttribute("users", users);
		return "list";
	}
	
}

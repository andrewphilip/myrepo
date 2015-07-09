package andy.web.mvc.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import andy.web.mvc.model.User;
import andy.web.mvc.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	protected final Log logger=LogFactory.getLog(getClass());
	
	@Autowired
	private UserService userSrvc;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getAllUsers(ModelMap model){
		logger.debug("Inside getAllUsers()::UserController");
		List<User> users=userSrvc.getUsers();
		model.addAttribute("users", users);
		return "users";
	}
	
	@RequestMapping(value="/{userid}", method=RequestMethod.GET)
	public String getUserById(@PathVariable String userid, ModelMap model){
		logger.debug("Inside getUserById()::UserController");
		User user=userSrvc.getUserById(userid);
		model.addAttribute("user", user);
		return "userDetail";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getUserByName(@RequestParam("name") String name, ModelMap model){
		logger.debug("Inside getUserByName()::UserController");
		User user=userSrvc.getUserByName(name);
		model.addAttribute("user", user);
		return "userDetail";
	}
	
	
}

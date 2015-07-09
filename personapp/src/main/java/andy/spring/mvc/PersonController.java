package andy.spring.mvc;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hsqldb.map.ReusableObjectCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @ RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonSrvc personSrvc;
	
	@RequestMapping(value="/person/list", method=RequestMethod.GET)	
	public String listPersons(ModelMap model){
		System.out.println("Inside listPersons()");
		try{
		List<Customer> list=personSrvc.getPersons();
		model.addAttribute("persons", list);
		}
		catch(Exception e){e.printStackTrace();}
		return "persons";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView add(ModelMap model, HttpServletRequest request) throws SQLException{
		String uname= request.getParameter("username");
		String fname= request.getParameter("firstname");
		String lname= request.getParameter("lastname");
		String age= request.getParameter("age");
		int zip	= Integer.parseInt(request.getParameter("zip"));
		String gender= request.getParameter("gender");
		
		Customer cust=new Customer();
		cust.setUsername(uname);
		cust.setFirstname(fname);
		cust.setLastname(lname);
		cust.setAge(age);
		cust.setGender(gender);
		cust.setZip(zip);
		
		personSrvc.createPerson(cust);
		return new ModelAndView("redirect:/person/list");
	}
	
	@RequestMapping(value="newPerson", method=RequestMethod.GET)
	public String getAddCustomerPage(ModelMap model){
		return "newPerson";
	}
	
	@RequestMapping(value="/delete/{username}", method=RequestMethod.GET)
	public ModelAndView deletePerson(@PathVariable String username,ModelMap model){
		try{
			personSrvc.deletePerson(username);
		}catch(Exception e){e.printStackTrace();}
		return new ModelAndView("redirect:/person/list");
	}

	@RequestMapping(value="/edit/{username}", method=RequestMethod.GET)
	public String editPerson(@PathVariable String username,ModelMap model){
		try{
			Customer cust=personSrvc.getPerson(username);
			model.addAttribute("customer", cust);
		}catch(Exception e){e.printStackTrace();}
		return "updPerson";
	}
	
	@RequestMapping(value="/edit/update", method=RequestMethod.POST)
	public ModelAndView updatePerson(HttpServletRequest request,ModelMap model){
		String uname= request.getParameter("username");
		String fname= request.getParameter("firstname");
		String lname= request.getParameter("lastname");
		String age= request.getParameter("age");
		int zip	= Integer.parseInt(request.getParameter("zip"));
		String gender= request.getParameter("gender");
		
		Customer cust=new Customer();
		cust.setUsername(uname);
		cust.setFirstname(fname);
		cust.setLastname(lname);
		cust.setAge(age);
		cust.setGender(gender);
		cust.setZip(zip);
		
		System.out.println("Username:"+cust.getUsername()+ "  is getting updated.");
		try{
			personSrvc.updatePerson(cust);
		}catch(Exception e){e.printStackTrace();}
		
		return new ModelAndView("redirect:/person/list");
	}
}

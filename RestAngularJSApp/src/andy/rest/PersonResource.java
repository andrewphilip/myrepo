package andy.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import andy.rest.service.PersonService;

@Path("/person")
public class PersonResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAllPersons(){
		System.out.println("Inside getAllPersons()");
		PersonService service=new PersonService();
		return service.getAllPersons();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createPerson(Person person){
		PersonService service=new PersonService();
		service.createPerson(person);
	}
}

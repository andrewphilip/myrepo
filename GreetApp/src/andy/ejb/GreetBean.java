package andy.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class GreetBean
 */
@Stateless
@LocalBean
public class GreetBean {

	public String greet(){
		return "Deus Tecum";
	}
}

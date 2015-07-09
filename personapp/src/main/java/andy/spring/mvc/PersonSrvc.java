package andy.spring.mvc;

import java.sql.SQLException;
import java.util.List;

public class PersonSrvc {

		CustomerDAO personDAO=null;

		public CustomerDAO getPersonDAO() {
			return personDAO;
		}

		public void setPersonDAO(CustomerDAO personDAO) {
			this.personDAO = personDAO;
		}
		
		public List<Customer> getPersons() throws SQLException{
			return personDAO.getPersons();
		}
		
		public void createPerson(Customer cust) throws SQLException{
			personDAO.createPerson(cust);
		}	
		
		public void deletePerson(String username) throws SQLException{
			personDAO.deletePerson(username);
		}		
		
		public Customer getPerson(String username) throws SQLException{
			Object obj= personDAO.getPerson(username);
			Customer cust=null;
			if(obj != null){
				cust=(Customer)obj;
			}
			
			return cust;
		}		
		
		public void updatePerson(Customer cust) throws SQLException{
			personDAO.updatePerson(cust);
		}
}

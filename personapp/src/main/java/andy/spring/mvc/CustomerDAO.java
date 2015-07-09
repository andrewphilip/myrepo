package andy.spring.mvc;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {

		public List<Customer> getPersons() throws SQLException;
		
		public void createPerson(Customer cust) throws SQLException;
		
		public void deletePerson(String username) throws SQLException;		
		
		public Object getPerson(String username) throws SQLException;		
		
		public void updatePerson(Customer cust) throws SQLException;		
}

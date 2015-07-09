package andy.spring.mvc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CustomerDAOImpl implements CustomerDAO {

	private JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public List<Customer> getPersons() throws SQLException {
		String query="select * from person";
		System.out.println("Query:"+query);
		System.out.println(jdbcTemplate.getDataSource().getConnection().getMetaData().getURL());
		List<Customer> customers=jdbcTemplate.query(query, 
					new RowMapper() {
						
						public Customer mapRow(ResultSet rs, int rownum) throws SQLException {
							Customer cust=new Customer();
							cust.setUsername(rs.getString("username"));
							cust.setFirstname(rs.getString("firstname"));
							cust.setLastname(rs.getString("lastname"));
							cust.setAge(rs.getString("age"));
							cust.setZip(rs.getInt("zipcode"));
							return cust;
						}
					}
				);
		return customers;
	}
	
	public void createPerson(Customer cust) throws SQLException{
		String query="insert into person (username,firstname,lastname,age,gender,zipcode) values(?,?,?,?,?,?)";
		String[] params={cust.getUsername(),cust.getFirstname(),cust.getLastname(),cust.getAge(),cust.getGender(),cust.getZip()+""};
		System.out.println("Query:"+query);
		jdbcTemplate.update(query,params);
		return;
	}	
	
	public void deletePerson(String username) throws SQLException{
		String query="delete from person where username= ?";
		System.out.println("Query:"+query+" param: "+username);
		jdbcTemplate.update(query, new String[]{username});
		return;
	}

	public Object getPerson(String username) throws SQLException{
		String query="select *  from person where username= ?";
		System.out.println("Query:"+query+" param: "+username);
		Object cust=jdbcTemplate.queryForObject(query, new String[]{username} ,
							new RowMapper() {
			
												public Customer mapRow(ResultSet rs, int rownum) throws SQLException {
													Customer cust=new Customer();
													cust.setUsername(rs.getString("username"));
													cust.setFirstname(rs.getString("firstname"));
													cust.setLastname(rs.getString("lastname"));
													cust.setAge(rs.getString("age"));
													cust.setZip(rs.getInt("zipcode"));
													return cust;
												}
											}
				
				);
		return cust;
	}

	public void updatePerson(Customer cust) throws SQLException{
		String query="update person set firstname=?,lastname=?,age=?,gender=? where username =?";
		String[] params={cust.getFirstname(),cust.getLastname(),cust.getAge(),cust.getGender(),cust.getUsername()};
		System.out.println("Query:"+query);
		jdbcTemplate.update(query,params);
		return;
	}	
	
}

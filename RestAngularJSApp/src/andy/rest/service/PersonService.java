package andy.rest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import andy.rest.Person;

public class PersonService {
	public List<Person> getAllPersons(){
		Connection conn=null;
		List<Person> result=new ArrayList<Person>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {

			 conn=DBServiceHelper.getInstance().getConnection();
			 pstmt=conn.prepareStatement("select * from person");
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()){
				 Person p=new Person();
				 p.setPersonId(rs.getString("PERSONID"));
				 p.setFirstName(rs.getString("FIRSTNAME"));
				 p.setLastName(rs.getString("LASTNAME"));
				 p.setPhone(rs.getString("PHONE"));
				 p.setEmail(rs.getString("EMAIL"));
				 result.add(p);
				 System.out.println("Person:"+p.getFirstName());
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){pstmt.close();}
				DBServiceHelper.getInstance().closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
		return result;
	}
	
	public void createPerson(Person p){
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			 conn=DBServiceHelper.getInstance().getConnection();
			 pstmt=conn.prepareStatement("insert into person (firstname,lastname,phone,email) values(?,?,?,?)");
			 pstmt.setString(1, p.getFirstName());
			 pstmt.setString(2, p.getLastName());
			 pstmt.setString(3, p.getPhone());
			 pstmt.setString(4, p.getEmail());
			 
			 int rows=pstmt.executeUpdate();
			 System.out.println("Rows inserted :"+rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if(pstmt != null){pstmt.close();}
				DBServiceHelper.getInstance().closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		
	}
}

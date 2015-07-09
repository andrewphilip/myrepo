package andy.web.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import andy.web.mvc.model.User;

public class UserDAO {
	protected final Log logger=LogFactory.getLog(getClass());
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<User> getUsers(){
		
		List<User> users=new ArrayList<User>();	
		List<Map<String,Object>> rows=jdbcTemplate.queryForList("select userid,statecode,name,email from unemadmin.userinfo");
		for(Map row:rows){
			User aUser=new User();
			aUser.setUserid((String)row.get("USERID"));
			aUser.setStatecode((String)row.get("STATECODE"));
			aUser.setName((String)row.get("NAME"));
			aUser.setEmail((String)row.get("EMAIL"));
			users.add(aUser);
		}
		System.out.println("Users list size:"+users.size());
		return users;
	}
	
	public User getUserByName(String name){
		String query="select userid,statecode,name,email from unemadmin.userinfo where name = :name";
		RowMapper<User> rowMapper=new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rnum) throws SQLException {
				User aUser=new User();
				aUser.setUserid(rs.getString("USERID"));
				aUser.setStatecode(rs.getString("STATECODE"));
				aUser.setName(rs.getString("NAME"));
				aUser.setEmail(rs.getString("EMAIL"));
				return aUser;
			}
		
		};
		SqlParameterSource namedParams=new MapSqlParameterSource("name", name);
		NamedParameterJdbcTemplate namedParamTmp=new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		return namedParamTmp.queryForObject(query, namedParams, rowMapper);
	}
	
	public User getUserById(String userId){
		logger.debug("Inside getUserById()::UserDAO");	
		User user= jdbcTemplate.queryForObject("select userid,statecode,name,email from unemadmin.userinfo where userid=?", 
				 						new Object[] {userId}, new RowMapper<User>() {
											@Override
											public User mapRow(ResultSet rs,
													int rnum)
													throws SQLException {
												User aUser=new User();
												aUser.setUserid(rs.getString("USERID"));
												aUser.setStatecode(rs.getString("STATECODE"));
												aUser.setName(rs.getString("NAME"));
												aUser.setEmail(rs.getString("EMAIL"));
												logger.debug("User :"+aUser.toString());
												return aUser;
											}
										});
		 return user;
	}
}

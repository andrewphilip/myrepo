package andy.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBServiceHelper {

	public static DBServiceHelper helper=null;
	public final String DBURL="jdbc:mysql://localhost:3306/andy";
	public final String USERNAME="andrew";
	public final String PASSWORD="xxxx";
	public static DBServiceHelper getInstance() {
		if(helper == null){
			helper=new DBServiceHelper();
		}
		return helper;
	}

	public Connection getConnection() throws SQLException{
		Connection conn=null;
		System.out.println("Before getting connection.");
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){e.printStackTrace();}
		conn= DriverManager.getConnection(DBURL, USERNAME , PASSWORD);
		return conn;

	}

	public void closeConnection(Connection conn) throws SQLException{
		if(conn != null){
			conn.close();
		}
	}

}

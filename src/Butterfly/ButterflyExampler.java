package Butterfly;
import java.sql.*;
import com.mysql.cj.jdbc.Driver;

public class ButterflyExampler {
	public static void main(String[]args) throws Exception{
		getConnection();
	}
	public static Connection getConnection() throws Exception{
		try {
			String driver="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost/butterfly";
			String username="root";
			String password="root";
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,username,password);
			System.out.println("connected");
			return conn;
		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}
}

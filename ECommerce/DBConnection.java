import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
	private static DBConnection db=null;
	private static Connection con=null;
	private String url="jdbc:postgresql://localhost:5432/e_commerce";
	private String username="postgres";
	private String password="selva";
	
	
	
	
	private DBConnection()
	{
		
	}
	
	
	
	public static DBConnection getInstance()
	{	
		if(db==null)
		{
			db=new DBConnection();
		}
		return db;
	}
	
	
	
	
	public void close() throws SQLException
	{	
		if(con==null)
		{
			con.close();
		}
	}
	
		
		
		
			
	public Connection getConnection()throws SQLException
	{				
		try
		{	
			if(con==null)
			{
				con=DriverManager.getConnection(url,username,password);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
}
	

import java.sql.ResultSet;
import java.sql.SQLException;
class Login
{
	ResultSets rs=new ResultSets();
	int checkLoginInfo(long mobile,String password,String name)
	{
		int id=-1;
		try
		{
			if(rs.checkMobile(mobile,name).next())
			{
				ResultSet result=rs.checkPassword(mobile,password,name);
				if(result.next())
				{
					id=result.getInt(1);
				}
				else
				{
					System.out.println("\n\t\tThe Password Is Incorrect...");
				}
			}
			else
			{
				System.out.println("\n\t\tNo Users Found With This Number...");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return id;
	}
	
	
	
	
}
				

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
class ViewMembers
{


	DBConnection db=null;
	Connection con=null;
	Statement st=null;
	ArrayList<Member> memberlist=null;
	
	
	ArrayList<Member> showMembers()
	{
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs1=st.executeQuery("Select * from member");
			memberlist=new ArrayList<>();
			insertToList(rs1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return memberlist;
	}
	
	
	
	
	
	void insertToList(ResultSet members)
	{
		try
		{
			memberlist.clear();
			while(members.next())
			{
				memberlist.add(new Member (members.getInt(1), members.getString(2), members.getLong(3), members.getString(4), members.getInt(5)));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}		 	
	
	
	
	
	
	 
			
		

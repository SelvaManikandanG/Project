import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



class MemberAdding
{


	DBConnection db=null;
	Connection con=null;
	Statement st=null;
	
	
	
	void addMemberDetails(String member_name,long mobile_no,String mail_id)
	{
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs1=st.executeQuery("select member_id from member where member_name='"+member_name+"' and mobile_no='"+mobile_no+"';");
			if(rs1.next())
			{
				int member_id=rs1.getInt(1);
				System.out.println(member_name+" is already a Member of this Library and the Member Id for "+member_name+" is "+member_id);
			}
			else
			{
				PreparedStatement ps1=con.prepareStatement("insert into member(member_name,mobile_no,mail_id)values(?,?,?)returning member_id;");
				ps1.setString(1,member_name);
				ps1.setLong(2,mobile_no);
				ps1.setString(3,mail_id);
				ResultSet rs2=ps1.executeQuery();
				if(rs2.next())
				{
					int member_id=rs2.getInt("member_id");
					System.out.println(member_name+" is added as a Member of this Library Successfully... and the Member Id for "+member_name+" is "+member_id);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
}
			
			
		
	

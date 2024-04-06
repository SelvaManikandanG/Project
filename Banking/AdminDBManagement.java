package com.zoho.Banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class AdminDBManagement extends ManagerDBManagement
{
	
	
	
	ResultSet takeBranchCount()
	{
		try
		{
			rs=st.executeQuery("Select count(*) from branch;");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	int insertBranch(String branch,String ifsc)
	{
		int id=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into branch(	branch_name,ifsc_code)values(?,?)returning id;");
			ps.setString(1,branch);
			ps.setString(2,ifsc);
			ResultSet result=ps.executeQuery();
			if(result.next())
			id=result.getInt("id");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return id;
	}
	

	
	
	
}

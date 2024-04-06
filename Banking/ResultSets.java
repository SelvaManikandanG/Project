package com.zoho.Banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ResultSets
{
	ResultSet rs=null;
	static Connection con=null;
	static Statement st=null;
	
	static
	{
		try
		{	
			st=((DBConnection.getInstance()).getConnection()).createStatement();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	ResultSet checkIfsc(String ifsc)
	{
		try
		{
			rs=st.executeQuery("Select id from branch where ifsc_code='"+ifsc+"';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	

	
		
			
	
}

package com.zoho.Banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class CashierDBManagement extends CustomerDBManagement 
{
	
	
	
	int insertTransaction(int customerId,double amount,int branchId,String column)
	{
		int id=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into transaction("+column+",amount,transaction_type,branch_id)values(?,?,?,?)returning id;");
			ps.setInt(1,customerId);
			ps.setDouble(2,amount);
			ps.setString(3,"Manual");
			ps.setInt(4,branchId);
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

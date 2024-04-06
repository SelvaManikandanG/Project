package com.zoho.Banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ClerkDBManagement extends CustomerDBManagement
{




	ResultSet takeStaff(int userid)
	{
		try
		{
			rs=st.executeQuery("Select u.id,u.user_name,r.role_name,u.mobile_no , u.gender, u.age,  u.dob ,u.mail_id ,  u.address ,b.branch_name,u.status from users u join role r on r.id=u.role_id join branch b on u.branch_id=b.id where u.id="+userid+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	ResultSet checkClerkStatus(int userid)
	{
		try
		{
			rs=st.executeQuery("Select * from users where id="+userid+" and status='active-canadd';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeCustomers(int branchid,String status)
	{
		try
		{
			rs=st.executeQuery("Select u.id,c.id,u.user_name ,c.aadhar_no,u.mobile_no , u.gender, u.age,  u.dob ,u.mail_id ,  u.address ,t.type_name,s.scheme_name,b.branch_name, u.status from users u join customer c on u.id=c.user_id join type t on t.id=c.type_id join scheme s on s.id=c.scheme_id join branch b on u.branch_id=b.id where c.status='"+status+"'and u.branch_id="+branchid+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	int updateCustomerStatus(int customerid,String status)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update customer set status='"+status+"' where id="+customerid+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
}

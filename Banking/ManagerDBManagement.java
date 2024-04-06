package com.zoho.Banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ManagerDBManagement extends ClerkDBManagement
{
	
	
	
	
	ResultSet takeApproved1Cus(int branchid)
	{
		try
		{
			rs=st.executeQuery("Select u.id,c.id,u.user_name ,c.aadhar_no,u.mobile_no , u.gender, u.age,  u.dob ,u.mail_id ,  u.address ,t.type_name,s.scheme_name,b.branch_name, u.status from users u join customer c on u.id=c.user_id join type t on t.id=c.type_id join scheme s on s.id=c.scheme_id join branch b on u.branch_id=b.id where c.status='approved1' and u.branch_id="+branchid+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeAllStaffs(int roleid1,int roleid2,int branchid)
	{
		try
		{
			rs=st.executeQuery("Select u.id,u.user_name,r.role_name,u.mobile_no , u.gender, u.age,  u.dob ,u.mail_id ,  u.address ,b.branch_name,u.status from users u join role r on r.id=u.role_id join branch b on u.branch_id=b.id where role_id="+roleid1+" or role_id="+roleid2+" and u.branch_id="+branchid+" and  u.status like 'active%';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeUserByStatus(int userid,String status)
	{
		try
		{
			rs=st.executeQuery("Select * from users where id="+userid+" and status='"+status+"';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeStaff(int roleid)
	{
		try
		{
			rs=st.executeQuery("Select u.id,u.user_name,r.role_name,u.mobile_no , u.gender, u.age,  u.dob ,u.mail_id ,  u.address ,b.branch_name,u.status from users u join role r on r.id=u.role_id join branch b on u.branch_id=b.id where u.role_id="+roleid+" and  u.status like 'active%';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	ResultSet takeStaff(int userid,int branchid)
	{
		try
		{
			rs=st.executeQuery("Select u.id,u.user_name,r.role_name,u.mobile_no , u.gender, u.age,  u.dob ,u.mail_id ,  u.address ,b.branch_name,u.status from users u join role r on r.id=u.role_id join branch b on u.branch_id=b.id where u.id="+userid+" and u.branch_id="+branchid+" and  u.status='active%';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	int updateUserStatus(int userid,String status)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update users set status='"+status+"' where id="+userid+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	
	int updateCustomerAccount(int customerid,String account)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update customer set account_no='"+account+"' where id="+customerid+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	int updateStaffBranch(int userid,int branchid )
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update users set branch_id="+branchid+" where id="+userid+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	

	
	
}

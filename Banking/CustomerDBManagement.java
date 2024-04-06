package com.zoho.Banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

class CustomerDBManagement
{
	ResultSet rs=null;
	PreparedStatement ps=null;
	static Connection con=null;
	static Statement st=null;
	
	static
	{
		try
		{	
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	
	
	ResultSet checkAadhar(long aadhar)
	{
		try
		{
			rs=st.executeQuery("Select users.id from users join customer on users.id=customer.user_id where customer.aadhar_no="+aadhar+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeRoleId(String role)
	{
		try
		{
			rs=st.executeQuery("Select id from role where upper(role_name)=upper('"+role+"');");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	ResultSet takeBranchId(String branch)
	{
		try
		{
			rs=st.executeQuery("Select id from branch where upper(branch_name)=upper('"+branch+"');");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	ResultSet takeSchemeId(String scheme)
	{
		try
		{
			rs=st.executeQuery("Select id from scheme where upper(scheme_name)=upper('"+scheme+"');");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	ResultSet takeTypeId(String type)
	{
		try
		{
			rs=st.executeQuery("Select id from type where upper(type_name)=upper('"+type+"');");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeType(int typeId)
	{
		try
		{
			rs=st.executeQuery("Select type_name from type where id="+typeId+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet checkStatus(int userid)
	{
		try
		{
			rs=st.executeQuery("Select * from users where id="+userid+" and upper(status)=upper('inactive');");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet checkSignedOrNot(int userid)
	{
		try
		{
			rs=st.executeQuery("Select * from users where id="+userid+" and password is not null;");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet checkUserMobile(int userid,long mobile)
	{
		try
		{
			rs=st.executeQuery("Select * from users where id="+userid+" and mobile_no="+mobile+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet checkUserLogin(int userid,String password)
	{
		try
		{
			rs=st.executeQuery("Select role_id,branch_id from users where id="+userid+" and password='"+password+"';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeRole(int roleid)
	{
		try
		{
			rs=st.executeQuery("Select role_name from role where id="+roleid+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeAccountNo(int userid)
	{
		try
		{
			rs=st.executeQuery("Select id,account_no from customer where user_id="+userid+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeCustomerInfo(String accountNo)
	{
		try
		{
			rs=st.executeQuery("Select id,type_id,balance from Customer where account_no='"+accountNo+"';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	ResultSet takeTransactions(int customerId)
	{
		try
		{
			rs=st.executeQuery("Select t.id,c.account_no,cu.account_no,t.amount,t.transaction_type from transaction t join customer c on t.debit_id=c.id join customer cu on t.credit_id=cu.id  where t.debit_id="+customerId+" or t.credit_id="+customerId+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	ResultSet takeBalance(int customerId)
	{
		try
		{
			rs=st.executeQuery("Select balance from customer where id="+customerId+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeCustomers(int userId)
	{
		try
		{
			rs=st.executeQuery("Select u.id,u.user_name ,c.aadhar_no,u.mobile_no , u.gender, u.age,  u.dob ,u.mail_id ,u.address ,t.type_name,s.scheme_name,b.branch_name, c.status,c.account_no from users u join customer c on u.id=c.user_id join type t on t.id=c.type_id join scheme s on s.id=c.scheme_id join branch b on u.branch_id=b.id where u.id="+userId+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	int insertUser(String name,String dob,byte age,String gender,long mobile,String address,String mail,int roleid,int branchid,String status)
	{
		int id=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into users(role_id,user_name,mobile_no,mail_id,gender,age,address,branch_id,dob,status)values(?,?,?,?,?,?,?,?,?,?) returning id;");
			ps.setInt(1,roleid);
			ps.setString(2,name);
			ps.setLong(3,mobile);
			ps.setString(4,mail);
			ps.setString(5,gender);
			ps.setByte(6,age);
			ps.setString(7,address);
			ps.setInt(8,branchid);
			ps.setDate(9,Date.valueOf(dob));
			ps.setString(10,status);
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
	
	
	
	
	int insertCustomer(int userid,long aadhar,int typeid,int schemeid)
	{
		int id=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into customer(user_id,aadhar_no,type_id,scheme_id,status)values(?,?,?,?,?)returning id;");
			ps.setInt(1,userid);
			ps.setLong(2,aadhar);
			ps.setInt(3,typeid);
			ps.setInt(4,schemeid);
			ps.setString(5,"applied");
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
	
	
	
	
	int updateUser(int userid,String password)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update users set password='"+password+"' where id="+userid+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	
	
	int insertTransaction(int debitId,int creditId,double amount,String txnType)
	{
		int id=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into transaction(debit_id,credit_id,amount,transaction_type)values(?,?,?,?)returning id;");
			ps.setInt(1,debitId);
			ps.setInt(2,creditId);
			ps.setDouble(3,amount);
			ps.setString(4,txnType);
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
	
	
	
	
	int updateAddBalance(int customerId,double amount )
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update customer set balance=balance+"+amount+" where id="+customerId+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	
	int updateSubBalance(int customerId,double amount )
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update customer set balance=balance-"+amount+" where id="+customerId+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	


}
	
	
	
	
	

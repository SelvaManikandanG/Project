import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class Insertion
{
	Connection con=null;
	PreparedStatement ps=null;
	
	
	
	
	int updateOrderStatus(int id,String status)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update orders set status='"+status+"' where id="+id+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	
	int updateOrderStatus(int userid,int orderid,String status)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update orders set status='"+status+"' where id="+orderid+" and user_id="+userid+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
		
		
		
	
	
	void updateDeliveredDate(int id)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update orders set delivered_date=now() where id="+id+";");
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	int insertUser(long mobile,String username,String password)
	{	
		int id=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into users(user_name,mobile_no,password)values(?,?,?) returning id;");
			ps.setString(1,username);
			ps.setLong(2,mobile);
			ps.setString(3,password);
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
	
	
	
	int insertAddress(int userid,String address)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into user_address(user_id,address)values(?,?);");
			ps.setInt(1,userid);
			ps.setString(2,address);
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	
	int insertOrder(int user_id,int product_id,int quantity,int deliverperson_id,String address,double amount)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into orders(user_id,product_id,quantity,deliver_person_id,deliver_address,amount,status)values(?,?,?,?,?,?,?);");
			ps.setInt(1,user_id);
			ps.setInt(2,product_id);
			ps.setInt(3,quantity);
			ps.setInt(4,deliverperson_id);
			ps.setString(5,address);
			ps.setDouble(6,amount);
			ps.setString(7,"Dispatched");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	int updateToSeller(String seller_name,double profitwithseller,double sellerwallet,double amount)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update seller set wallet= wallet+"+sellerwallet+", profit_with_seller=profit_with_seller+"+profitwithseller+",total=total+"+amount+" where name='"+seller_name+"';");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	
	int insertCart(int userid,int productid,int quantity)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into cart(product_id,quantity,user_id)values(?,?,?);");
			ps.setInt(1,productid);
			ps.setInt(2,quantity);
			ps.setInt(3,userid);
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
		
		
		
		
	
	int insertSeller(long mobile,String username,String password)
	{	
		int id=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into seller(name,mobile_no,password,approval)values(?,?,?,?) returning id;");
			ps.setString(1,username);
			ps.setLong(2,mobile);
			ps.setString(3,password);
			ps.setBoolean(4,false);
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
		
		
		
	
	
	int insertCategory(String categoryname)
	{	
		int id=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into category(category_name)values(?) returning id;");
			ps.setString(1,categoryname);
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
	
	
	
	
	int insertProduct(String productname)
	{	
		int id=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into product(product_name)values(?) returning id;");
			ps.setString(1,productname);
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
	
	
	
	int insertBrand(String brandname)
	{	
		int id=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into brand(brand_name)values(?) returning id;");
			ps.setString(1,brandname);
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
		
		
		
		
	
	int insertProductDetails(String model,int proid,int brandid,int catid,String spec,double amount,int sellerid)
	{
		int id=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("insert into product_details(model_name,product_id,brand_id,category_id,specification,amount,seller_id,status)values(?,?,?,?,?,?,?,?) returning id;");
			ps.setString(1,model);
			ps.setInt(2,proid);
			ps.setInt(3,brandid);
			ps.setInt(4,catid);
			ps.setString(5,spec);
			ps.setDouble(6,amount);
			ps.setInt(7,sellerid);
			ps.setBoolean(8,true);
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
	
	
	
	
	int updateProductStatus(int productid,int sellerid,String status)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update product_details set status='"+status+"' where product_id="+productid+" and seller_id="+sellerid+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	
	int updateProductStatus(int sellerid,String status)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update product_details set status='"+status+"' where seller_id="+sellerid+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	
	int updateAmount(int productid,int sellerid,double amount)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update product_details set amount="+amount+" where product_id="+productid+" and seller_id="+sellerid+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	int updateApproval(int sellerid)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update  seller set approval='t' where approval='f' and id="+sellerid+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	
	int updateStatus(int sellerid,String oldstatus,String newstatus)
	{
		int rows=0;
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			ps=con.prepareStatement("update  seller set status='"+newstatus+"' where status='"+oldstatus+"' and seller_id="+sellerid+";");
			rows=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	
	
	
	

}

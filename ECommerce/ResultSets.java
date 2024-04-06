import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ResultSets
{
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	
	ResultSet checkMobile(long mobile,String name)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("Select * from "+name+" where mobile_no="+mobile+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	ResultSet checkPassword(long mobile,String password,String name)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("Select * from "+name+" where mobile_no="+mobile+" and password='"+password+"';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	ResultSet takeAllProducts()
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("Select pd.id,pd.model_name,p.product_name,b.brand_name,c.category_name,pd.specification,pd.amount,s.name from product_details pd join product p on pd.product_id=p.id join brand b on pd.brand_id=b.id join category c on pd.category_id=c.id join seller s on pd.seller_id=s.id");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	ResultSet takeProduct(String name)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("Select pd.id,pd.model_name,p.product_name,b.brand_name,c.category_name,pd.specification,pd.amount,s.name from product_details pd join product p on pd.product_id=p.id join brand b on pd.brand_id=b.id join category c on pd.category_id=c.id join seller s on pd.seller_id=s.id where upper(pd.model_name) like upper('"+name+"%') or upper(p.product_name) like upper('"+name+"%') or upper(b.brand_name) like upper('"+name+"%') or upper(c.category_name) like upper('"+name+"%');");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	ResultSet takeProfile(int id)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select u.id,u.user_name,u.mobile_no,ua.address from users u join user_address ua on u.id=ua.user_id where u.id="+id+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	ResultSet takeCategories()
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from category ;");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	ResultSet takeOrders(int id)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select o.id,p.product_name,o.quantity,d.name,d.mobile_no,o.deliver_address,o.amount,o.order_date,o.status, o.delivered_date from orders o join users u on o.user_id=u.id join product p on o.product_id=p.id join delivery_person d on o.deliver_person_id=d.id where o.user_id="+id+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
			
			
			
	
	ResultSet takeCart(int id)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select cart.id,c.category_name,pd.model_name,p.product_name,pd.amount,cart.quantity from category c join product_details pd on c.id=pd.category_id join cart on cart.product_id=pd.id join product p on pd.product_id=p.id join users u on u.id=cart.user_id where u.id="+id+";");  
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet checkProductStatus(int productid)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from product_details where id="+productid+" and status='t';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	ResultSet takeAddress(int user_id)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from user_address where user_id="+user_id+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	
	ResultSet takeDeliverPersonAddress()
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select id,address from delivery_person;");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeAllOrders()
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select id,order_date from orders;");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
			
		
			
	
	ResultSet takeCategoryId(String categoryname)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select id from category where category_name='"+categoryname+"';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeProductId(String productname)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select id from product where product_name='"+productname+"';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeBrandId(String brandname)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select id from product where brand_name='"+brandname+"';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
			
			
			
	
	ResultSet takeProduct(int sellerid)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("Select pd.id,pd.model_name,p.product_name,b.brand_name,c.category_name,pd.specification,pd.amount,s.name,pd.rating from product_details pd join product p on pd.product_id=p.id join brand b on pd.brand_id=b.id join category c on pd.category_id=c.id join seller s on pd.seller_id=s.id where s.id="+sellerid+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeSellerRequest()
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("Select id,name,mobile_no,approval from seller where approval='f';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	
	ResultSet takeSellerIncome()
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("Select id,name,wallet,profit_with_seller,total from seller where approval='t';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}	
	
	
	
	
	ResultSet takeSellerStatus(String status)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("Select id,name,mobile_no,approval,status from seller where approval='t' and status='"+status+"';");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}	
	
	
	
	ResultSet takeDeliverOrders(int deliverid)
	{
		try
		{
			con=(DBConnection.getInstance()).getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select o.id,u.user_name,p.product_name,o.quantity,o.amount,o.order_date,o.status, o.delivered_date from orders o join users u on o.user_id=u.id join product p on o.product_id=p.id join delivery_person d on o.deliver_person_id=d.id where d.id="+deliverid+";");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
			
				
	
	
	

	
}	
		
		


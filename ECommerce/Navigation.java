import java.sql.ResultSet;
import java.sql.SQLException;
class Navigation
{
	ResultSets rs=new ResultSets();
	InputValidations inputvalid=new InputValidations();
	Insertion insert=new Insertion();
	boolean navigatePage(String option,int id)
	{ 
		switch(option)
		{
		
			case "p":
			case "P":
				
				viewProfile(id);
				break;
				
			case "c":
			case "C":
				
				viewCategories();
				break;
				
			case "o":
			case "O":
				
				viewOrders(id);
				break;
				
			case "k":
			case "K":
				
				viewCart(id);
				break;
						
				
			case "q":
			case "Q": 
			
				return true;
			
			default:
				 
				System.out.println("Invalid Option");
				
		}
		return false;
	}
	
	
	
	
	
	void viewProfile(int id)
	{
		try
		{
		ResultSet profile=rs.takeProfile(id);
		Users user=null;
		if(profile.next())
		{
			user=new Users(profile.getInt(1),profile.getString(2),profile.getLong(3),profile.getString(4));
			System.out.println(user);
			
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
		
					
		
		
		
	void viewCategories()
	{
		try
		{
			ResultSet categories=rs.takeCategories();
			Category category=null;
			while(categories.next())
			{
				category=new Category(categories.getInt(1),categories.getString(2));
				System.out.println(category);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
		
		
		
	void viewOrders(int userid)
	{
		try
		{
			ResultSet orderdetails=rs.takeOrders(userid);
			Orders order=null;
			while(orderdetails.next())
			{
				order=new Orders(orderdetails.getInt(1),orderdetails.getString(2), 						orderdetails.getInt(3),orderdetails.getString(4),orderdetails.getLong(5),orderdetails.getString(6),orderdetails.getDouble(7), orderdetails.getString(8),orderdetails.getString(9),orderdetails.getString(10));
				System.out.println(order);
			}
			int option=inputvalid.validateInteger("1=> Cancel The Product");
			if(!(option==1))
			{
				return;
			}
			else
			{
				int orderid=inputvalid.validateInteger("Enter The Order Id To Cancel The Order : ");
				if(orderid==-1)
				{
					return;
				}
				if(insert.updateOrderStatus(userid,orderid,"canceled")>0)
				{
					System.out.println("Order Canceled");
				}
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	void viewCart(int id)
	{
		try
		{
			ResultSet cartdetails=rs.takeCart(id);
			Cart cart=null;
			while(cartdetails.next())
			{
				cart=new Cart(cartdetails.getInt(1),cartdetails.getString(2),cartdetails.getString(3),cartdetails.getString(4),cartdetails.getDouble(5), cartdetails.getInt(6));
				System.out.println(cart);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	 

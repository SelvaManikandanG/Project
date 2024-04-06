import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.ResultSet;
import java.sql.SQLException;
class OrderProcess
{
	Scanner input=new Scanner(System.in);
	InputValidations inputvalid=new InputValidations();
	ResultSets rs=new ResultSets();
	Insertion insert=new Insertion();
	UserAddress useraddress=null;
	SignUp signup=new SignUp();
	ArrayList<UserAddress> addresslist=new ArrayList<>();
	
	
	
	
	void updateOrderStatus()
	{ 
		try
		{
			ResultSet orders=rs.takeAllOrders();
			String status="";
			while(orders.next())
			{
				int days=inputvalid.getDays(orders.getString(2),inputvalid.getCurrentDate());
				switch(days)
				{
				
					case 0:
						
						status="dispatched";
						break;
					
					case 1:
						
						status="shipped";
						break;
						
					case 2:
						
						status="Out For Delivery";
						break;
						
					default :
						
						status="Delivered";	
						break;
				}
				if(insert.updateOrderStatus(orders.getInt(1),status)>0)
				{
					if(status.equals("Delivered"))
					{
						insert.updateDeliveredDate(orders.getInt(1));
					}
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	

	void placeOrder(int user_id,int product_id,double amount,String seller_name)
	{
		try
		{
			if((rs.checkProductStatus(product_id)).next())
			{
				int quantity=inputvalid.validateInteger("Enter the quantity  : ");
				if(quantity==-1)
				{
					return;
				}
				String address;
				viewAddress(user_id);
				int click=inputvalid.validateInteger("Choose The Address For Delivery Or Press 0 To Enter A New Address");
				if(click==-1)
				{
					return;
				}
				if(click==0)
				{
					address=inputvalid.getAddress();
				}
				else
				{
					address=getAnAddress(click);
				}
				int deliverperson_id=chooseDeliverPerson(address);
				if(insert.insertOrder(user_id,product_id,quantity,deliverperson_id,address,amount)>0)
				{	
					double profitwithseller=amount*0.20;
					double sellerwallet=amount-profitwithseller;
					if(insert.updateToSeller(seller_name,profitwithseller,sellerwallet,amount)>0)
					{
						System.out.println("Order Placed Successfully...");
					}
				}
			}
			else
			{
				System.out.println("This Product Is Out Of Stock...");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
		
		
	
	
	
	void viewAddress(int user_id)
	{
		try
		{
			addresslist.clear();
			ResultSet address=rs.takeAddress(user_id);
			while(address.next())
			{
				useraddress=new UserAddress(address.getInt(1),address.getInt(2),address.getString(3));
				addresslist.add(useraddress);
				System.out.println("\n\t\t"+user_id+" => "+useraddress.getAddress());
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	String getAnAddress(int click)
	{
		String address="";
		Iterator itr=addresslist.iterator();
		while(itr.hasNext())
		{
			useraddress=(UserAddress)itr.next();
			if(click==useraddress.getId())
			{
				address=useraddress.getAddress();
			}
		}
		return address;
	}
	
	
	
	
	
	int chooseDeliverPerson(String address)
	{
		int count=0,maxcount=0,maxid=0;
		String[] addressarr=address.split(",");
		try
		{
			ResultSet deliverperson=rs.takeDeliverPersonAddress();
			while(deliverperson.next())
			{
				for(String str:addressarr)
				{
					if((deliverperson.getString(2)).contains(str))
					{
						count++;
					}
				}
				if(maxcount<count)
				{
					maxcount=count;
					maxid=deliverperson.getInt(1);
				}
				count=0;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}		
		return maxid;	
	}
}
	
	
	
	
	
	
	
	
		
	

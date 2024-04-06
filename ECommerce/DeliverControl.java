import java.util.Scanner;
import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
class DeliverControl
{
	Scanner input=new Scanner(System.in);
	ProductsView proview=new ProductsView();
	Login login=new Login();
	SignUp signup=new SignUp();
	InputValidations inputvalid=new InputValidations();
	ResultSets rs=new ResultSets();
	Insertion insert=new Insertion();
	Navigation navi=new Navigation();
	
	void deliveryLogin()
	{
		long mobile_no=inputvalid.validateLong("Mobile Number");
		System.out.println("\t\tPassword");
		char[] passwordArray = System.console().readPassword();
        	String password = new String(passwordArray);
        	int deliverid=login.checkLoginInfo(mobile_no,password,"delivery_person");
        	if(deliverid>0)
        	{
        		System.out.println("\n\t\tSuccessfully Logged in...");
        		viewOrders(deliverid);
        	}
        	else
        	{
        		System.out.println("\n\t\tThere is a problem in Logging In");
        		return;
        	}
        	
        }
        
        
        
        
        
        void viewOrders(int deliverid)
	{
		try
		{
			ResultSet orderdetails=rs.takeDeliverOrders(deliverid);
			Orders order=null;
			while(orderdetails.next())
			{
				order=new Orders(orderdetails.getInt(1),orderdetails.getString(2),orderdetails.getString(3),orderdetails.getInt(4), 						orderdetails.getDouble(5),orderdetails.getString(6),orderdetails.getString(7),orderdetails.getString(8));
				System.out.println(order);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}
	

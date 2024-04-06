import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


class Shoppy
{
	Scanner input=new Scanner(System.in);
	DBConnection db=DBConnection.getInstance();
	UserControl usercontrol=new UserControl();
	SellerControl sellercontrol=new SellerControl();
	InputValidations inputvalid=new InputValidations();
	SignUp signup=new SignUp();
	ResultSets rs=new ResultSets();
	AdminControl admincontrol=new AdminControl();
	DeliverControl delivercontrol=new DeliverControl();
	static Connection con=null;
	int option;
	boolean check=true;
	
	
	
	
	public static void main(String[] args)
	{
		OrderProcess orderpro=new OrderProcess();
		orderpro.updateOrderStatus();
		Shoppy shop=new Shoppy();
		shop.viewLoginPage();
	}
	
	
	
	
	void viewLoginPage()
	{
		do
		{
			System.out.println("\n\t\t|===================================|");
			System.out.println("\t\t\t     MENU OPTION");
			System.out.println("\t\t|===================================|");
			System.out.println("\n\n\t\t\t 1. Login\n\n\t\t\t 2. Sign Up\n\n\t\t\t 0. Exit");
			System.out.println("\n\t\t|===================================|");
			option=inputvalid.validateInteger("Enter The Menu Option : ");
			System.out.println("\n\t\t|===================================|");
			switch(option)
			{
				case 1:
					
					logIn();
					break;
				
				case 2: 
				
					signUp();
					break;
				
					
				case 0: 
			
					try
					{
						db.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
					System.exit(0);
					
				default :
					
					System.out.println("\n\t\tEnter The Valid Option...");
			}
		}while(check);
	}
		
		
	
	void logIn()
	{
	
		do
		{	
			System.out.println("\n\t\t|===================================|");
			System.out.println("\t\t\t    LOGIN MENU ");
			System.out.println("\t\t|===================================|");
			System.out.println("\n\n\t\t\t 1. User Login\n\n\t\t\t 2. Seller Login\n\n\t\t\t 3. Delivery Person Login\n\n\t\t\t 4.Admin Login\n\n\t\t\t 5. Back\n\n\t\t\t 6. Exit");
			System.out.println("\n\t\t|===================================|");
			option=inputvalid.validateInteger("Enter The Menu Option : ");
			System.out.println("\n\t\t|===================================|");
			switch(option)
			{
			
				case 1:
				
					usercontrol.userLogin();
					return;
					
				case 2:
					
					sellercontrol.sellerLogin();
					return;
					
				case 3:
					
					delivercontrol.deliveryLogin();
					return;	
					
				case 4:
					
					admincontrol.adminLogin();
					return;
						
				case 5:
				
					return;
					
				case 6: 
				
					try
					{
						db.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					System.exit(0);
				
				default :
				
					System.out.println("\n\t\tEnter The Valid Option...");
			}
		}while(check);
	}
	
	
	
	
	
	void signUp()
	{
		do
		{
			System.out.println("\n\t\t|===================================|");
			System.out.println("\t\t\t    SIGNUP MENU ");
			System.out.println("\t\t|===================================|");
			System.out.println("\n\n\t\t\t1. User Sign Up\n\n\t\t\t2. Seller Sign Up\n\n\t\t\t3. Delivery Person SignUp\n\n\t\t\t4. Back\n\n\t\t\t5. Exit");
			System.out.println("\n\t\t|===================================|");
			option=inputvalid.validateInteger("Enter The Menu Option : ");
			System.out.println("\n\t\t|===================================|");
			switch(option)
			{
			
				case 1:
				
					signup.signUp("users");
					return;
					
				case 2:
					
					sellercontrol.sellerSignUp("seller");
					return;
					
				case 3:
					
					signup.signUp("delivery_person");
					return;
					
				case 4:
				
					return;
					
				case 5: 
				
					try
					{
						db.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					System.exit(0);
				
				default :
				
					System.out.println("\n\t\tEnter The Valid Option...");
			}
		}while(check);
	}
	
	
	
	
	/*static void viewUserPage()
	{
		do
		{
			System.out.println("\n\n 1. Profile\n\n 2. Categories\n\n 3. Orders\n\n 4. Cart\n\n 5. Back\n\n 6. Exit");
			option=inputvalid.validateInteger("Enter The Menu Option : ");
			switch(option)
			{	
				
				case 1:
					
					viewProfile();
		
				case 2:
					
					viewCategories();
					
				case 3:
					
					viewOrders();
					
				case 4:
					
					viewCart();
					
				case 5:
				
					return;
					
				case 6: 
				
					try
					{
						db.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					System.exit(0);
				
				default :
				
					System.out.println("Enter The Valid Option...");
			}
		}while(check);
	}*/
	
}
		
				
			
			
			
			
			
			
			
			
			
			
				
		

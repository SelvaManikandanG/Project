import java.util.Scanner;
import java.io.Console;
class SellerControl
{
	Scanner input=new Scanner(System.in);
	ProductsView proview=new ProductsView();
	Login login=new Login();
	SignUp signup=new SignUp();
	InputValidations inputvalid=new InputValidations();
	ResultSets rs=new ResultSets();
	Navigation navi=new Navigation();
	ProductUpdate update=new ProductUpdate();
	
	
	
	void sellerSignUp(String table)
	{
		boolean check;
		long mobile_no=signup.getMobileNo(table);
		if(mobile_no==-1l)
		{
			return;
		}
		String password=signup.getPassword();
		if(password.length()==0)
		{
			return;
		}
		System.out.println("\n\t\t\tUser Name");
		String username=inputvalid.validateString("");
		if(username.length()==0)
		{
			return;
		}
		if(signup.updateSignUpInfo(mobile_no,username,password))
		{
			System.out.println("Signed Up Successfully...");
		}
		else
		{
			System.out.println("There Is A Problem In Signing Up");
		}
	}
	
	
	
	
	void sellerLogin()
	{
		System.out.println("\n\t\t\tMobile Number");
		long mobile_no=inputvalid.validateLong("");
		System.out.println("\n\t\t\tPassword");
		char[] passwordArray = System.console().readPassword();
        	String password = new String(passwordArray);
        	int sellerid=login.checkLoginInfo(mobile_no,password,"seller");
        	if(sellerid>0)
        	{
        		System.out.println("\n\t\tSuccessfully Logged in...");
        		proview.viewProduct(sellerid);
        		viewSellerMenu(sellerid);
        	}
        	else
        	{
        		System.out.println("\n\t\tThere is a problem in Logging In");
        		return;
        	}
        	
        }
	
	
	
	
	void viewSellerMenu(int sellerid)
	{	
		boolean check;
		do
		{
			check=true;
			System.out.println("\n\t\t|========================================|");
			System.out.println("\n\t\t\t      SELLER MENU ");
			System.out.println("\t\t|========================================|");
			System.out.println("\n\n\t\t\t 1. Add Product\n\n\t\t\t 2. Make Product Unavailable\n\n\t\t\t 3. Make Product Available\n\n\t\t\t 4. Update The Amount Of Product\n\n\t\t\t 5. Back");
			System.out.println("\n\t\t|===================================|");
			int option=inputvalid.validateInteger("");
			System.out.println("\n\t\t|===================================|");
			switch(option)
			{
				case 1:
				
					update.addProduct(sellerid);
					break;
					
				case 2:
					
					update.makeUnavailableProduct(sellerid);
					break;
					
				case 3:
					
					update.makeAvailableProduct(sellerid);
					break;
					
				case 4:
					
					update.updateProductAmount(sellerid);
					break;
					
				case 5:
					return;
					
				default:
				
					System.out.println("Invalid Input");
			}
		}
		while(check);
	}
	
	
	
	
	
	
}
		
	

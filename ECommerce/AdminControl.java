import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
class AdminControl
{
	Scanner input=new Scanner(System.in);
	InputValidations inputvalid=new InputValidations();
	ResultSets rs=new ResultSets();
	Insertion insert=new Insertion();
	ProductUpdate update=new ProductUpdate();
	Login login=new Login();
	
	Seller seller=null;
	

	void adminLogin()
	{
		long mobile_no=inputvalid.validateMobile("Mobile Number");
		if(mobile_no==-1)
		{
			return;
		}
		System.out.println("\n\t\t\tPassword");
		char[] passwordArray = System.console().readPassword();
        	String password = new String(passwordArray);
        	if(login.checkLoginInfo(mobile_no,password,"admin")>0)
        	{
        		System.out.println("\n\t\tLogged in successfully...");
        	}
        	else
        	{
        		System.out.println("\n\t\tThere is a problem in logging in");
        		return;
        	}
        	viewAdminMenu();
        }
        
        
        
        
        
        void viewAdminMenu()
        {
        	boolean check;
		do
		{
			check=true;
			System.out.println("\n\t\t|========================================|");
			System.out.println("\n\t\t\t      ADMIN MENU ");
			System.out.println("\t\t|========================================|");
			System.out.println("\n\n\t\t\t 1. Approval Request\n\n\t\t\t 2. Income From Seller\n\n\t\t\t 3. Deactivate Seller\n\n\t\t\t 4. Activate Seller\n\n\t\t\t 5. Back");
			System.out.println("\t\t|========================================|");
			int option=inputvalid.validateInteger("");
			System.out.println("\t\t|========================================|");
			switch(option)
			{
				case 1:
				
					viewRequests();
					break;
					
				case 2:
					
					viewIncome();
					break;
					
				case 3:
					
					deactivateSeller();
					break;
					
				case 4:
					
					activateSeller();
					break;
					
				case 5:
					return;
					
				default:
				
					System.out.println("Invalid Input");
			}
		}
		while(check);
	}
	
	
	
	
	void viewRequests()
	{
		try
		{
			ResultSet sellers=rs.takeSellerRequest();
			while(sellers.next())
			{
				String approval=sellers.getBoolean(4)==true?"Approved":"Waiting For Approval";
				seller=new Seller(sellers.getInt(1),sellers.getString(2),sellers.getLong(3),approval);
				System.out.println(seller);
			}
			boolean check;
			do
			{
				check=false;
				int option=inputvalid.validateInteger("\n\t\tEnter The Seller Id to Approve  Corresponding Seller : ");
				if(insert.updateApproval(option)==0)
				{
					System.out.println("\n\t\t\tInvalid ID");
					System.out.println("\n\t\tPress -1 to go back or press any key to re-enter...");
					String option1=input.next();
					if(option1.equals("-1"))
					{
						return;
					}
					check=true;
				}
				else
				{
					System.out.println("\n\t\tThe Seller Got Approved...");
				}
			}while(check);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	void viewIncome()
	{
		try
		{
			ResultSet sellers=rs.takeSellerIncome();
			while(sellers.next())
			{
				seller=new Seller(sellers.getInt(1),sellers.getString(2),sellers.getDouble(3),sellers.getDouble(4),sellers.getDouble(5));
				System.out.println(seller.income());
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	void deactivateSeller()
	{
		try
		{
			ResultSet sellers=rs.takeSellerStatus("t");
			while(sellers.next())
			{
				String approval=sellers.getBoolean(4)==true?"Approved":"Waiting For Approval";
				String status=sellers.getBoolean(5)==true?"Active":"Inactive";
				seller=new Seller(sellers.getInt(1),sellers.getString(2),sellers.getLong(3),approval,status);
				System.out.println(seller.status());
			}
			boolean check;
			do
			{
				check=false;
				int option=inputvalid.validateInteger("\n\t\tEnter The Seller Id to Deactivate  Corresponding Seller : ");
				if(insert.updateStatus(option,"t","f")==0)
				{
					System.out.println("\n\t\t\tInvalid ID");
					System.out.println("\n\t\tPress -1 to go back or press any key to re-enter...");
					String option1=input.next();
					if(option1.equals("-1"))
					{
						return;
					}
					check=true;
				}
				else
				{
					update.unavailableProduct(option);
					System.out.println("\n\t\tThe Seller Is Deactivated...");
				}
			}while(check);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	void activateSeller()
	{
		try
		{
			ResultSet sellers=rs.takeSellerStatus("f");
			while(sellers.next())
			{
				String approval=sellers.getBoolean(4)==true?"Approved":"Waiting For Approval";
				String status=sellers.getBoolean(5)==true?"Active":"Inactive";
				seller=new Seller(sellers.getInt(1),sellers.getString(2),sellers.getLong(3),approval,status);
				System.out.println(seller.status());
			}
			boolean check;
			do
			{
				check=false;
				int option=inputvalid.validateInteger("\n\t\tEnter The Seller Id to Activate  Corresponding Seller : ");
				if(insert.updateStatus(option,"f","t")==0)
				{
					System.out.println("\n\t\t\tInvalid ID");
					System.out.println("\n\t\tPress -1 to go back or press any key to re-enter...");
					String option1=input.next();
					if(option1.equals("-1"))
					{
						return;
					}
					check=true;
				}
				else
				{
					update.availableProduct(option);
					System.out.println("\n\t\tThe Seller Is Activated...");
				}
			}while(check);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
		
		
	
				
			
		
	
	
	
}



        	
		

package com.zoho.Banking;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
class Login
{
	Scanner input=new Scanner(System.in);	
	ResultSets rs=new ResultSets();
	InputValidations inputvalid=new InputValidations();
	CustomerDBManagement customerDB=new CustomerDBManagement();
	CustomerControl customer=new CustomerControl();
	ClerkControl clerk=new ClerkControl();
	CashierControl cashier=new CashierControl();
	ManagerControl manager=new ManagerControl();
	AdminControl admin=new AdminControl();




	void login()
	{
		int roleid=0,branchid=0;
		String role="";
		int userid=inputvalid.validateInteger("Enter The User/Customer ID : ");
		if(userid==-1)
		{
			return;
		}
		try
		{
			if(!((customerDB.checkSignedOrNot(userid)).next()))
			{
				System.out.println("User ID Is Not Yet Signed Up...");
				return;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		System.out.print("\nEnter The Password : ");
		String password=input.next();
		if(password.length()==0)
		{
			return;	
		}
		try
		{
			ResultSet result1=customerDB.checkUserLogin(userid,password);
			if(result1.next())
			{
				roleid=result1.getInt(1);
				branchid=result1.getInt(2);
				ResultSet result2=customerDB.takeRole(roleid);
				if(result2.next())
				{
					role=result2.getString(1);
				}
				System.out.println("\nSuccessfully Logged In...\n");
				viewMenu(userid,branchid,role);
				
			}
			else
			{
				System.out.println("\nInvalid Customer Id Or Password");	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	void viewMenu(int userid,int branchid,String role)
	{
		switch(role)
		{
			case "admin":
				
				admin.menu(userid);
				break;
				
			case "manager":
				
				manager.menu(userid,branchid);
				break;
				
			case "cashier":
				
				cashier.menu(userid,branchid);
				break;
				
			case "clerk":
				
				clerk.menu(userid,branchid);
				break;
				
			case "customer":
				
				customer.menu(userid,branchid);
				break;	
				
			default:
			
				System.out.println("There Is An Issue");
				break;
		}
	}					
	
	
	
	
}
				

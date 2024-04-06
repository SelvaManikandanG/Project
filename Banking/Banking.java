package com.zoho.Banking;
import java.sql.SQLException;
public class Banking 
{
	DBConnection db=DBConnection.getInstance();
	InputValidations inputvalid=new InputValidations();
	boolean check=true;
	CustomerControl customer=new CustomerControl();
	SignUp sign=new SignUp();
	Login login=new Login();


	public static void main(String[] args)
	{
		TableCreation table=new TableCreation();
		table.createTable();
		Banking bank=new Banking();
		bank.viewLoginPage();
	}
	
	void viewLoginPage()
	{
		while(check)
		{
			System.out.println("\n\t\t|===================================|");
			System.out.println("\t\t\t     MENU OPTION");
			System.out.println("\t\t|===================================|");
			System.out.println("\n\t\t\t1. Account Creation\n\n\t\t\t2. SignUp\n\n\t\t\t3. LogIn\n\n\t\t\t4. Exit");
			System.out.print("\n\t\t|===================================|");
			int option=inputvalid.validateInteger("\t\t   Enter The Option : ");
			System.out.println("\t\t|===================================|");
			switch(option)
			{
				case 1:
					
					customer.createAccount();
					break;
					
				case 2:
					
					sign.signUp();
					break;
					
				case 3:
					
					login.login();
					break;
					
				case 4: 
			
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
		}
	}
	
	
	
	
	
	
	
	
	
}

package com.zoho.Banking;
import java.sql.ResultSet;
import java.io.Console;
import java.util.Scanner;
import java.sql.SQLException;
class SignUp
{
	Scanner input=new Scanner(System.in);
	CustomerDBManagement customerDB=new CustomerDBManagement();
	InputValidations inputvalid=new InputValidations();
	
	
	
	void signUp()
	{
		int userid=inputvalid.validateInteger("Enter The User/Customer ID : ");
		if(userid==-1)
		{
			return;
		}
		try
		{
			if((customerDB.checkStatus(userid)).next())
			{
				System.out.println("\nAccount Application Is Not Yet Approved...");
				return;
			}
			if((customerDB.checkSignedOrNot(userid)).next())
			{
				System.out.println("\nUser/Customer ID Is Already Registered...");
				return;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		long mobile=getMobileNo(userid);
		if(mobile==-1l)
		{
			return;
		}
		String password=inputvalid.getNewPassword();
		if(password.length()==0)
		{
			return;
		}
		if(updateSignUpInfo(userid,password))
		{
			System.out.println("\nSuccessfully Signed Up...");
		}
		else
		{
			System.out.println("\nThere Is A Problem In Signing Up");	
		}
		
	}
	
	
	
	
	
	long getMobileNo(int userid)
	{
		long mobile=inputvalid.validateMobile("Mobile Number : ");
		if(mobile==-1)
		{
			return -1;
		}
		try
		{
			if(customerDB.checkUserMobile(userid,mobile).next())	
			{
				if(!(inputvalid.validateCaptcha()))
				{
					System.out.println("\nWrongly Entered");
					return -1;
				}
			}
			else
			{
				System.out.println("\nThere Is No Such User Id Registered With This Mobile Number...");
				return -1;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return mobile;
	}		
				
		
		
		
		
	boolean updateSignUpInfo(int userid,String password)
	{
		if(customerDB.updateUser(userid,password)>0)
		{
			return true;
		}
		return false;
	}
	
	
	
	
	
}
	
	
	

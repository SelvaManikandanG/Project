package com.zoho.Banking;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
class ClerkControl extends CustomerControl implements ClerkAccessible
{

	ClerkDBManagement clerkDB=new ClerkDBManagement();
	CustomerControl customer=new CustomerControl();
	
	
	
	public void menu(int userid,int branchid)
	{
		while(true)
		{
			int customerId=0;
			System.out.println("\t\t|===========================================|");
			System.out.println("\t\t\t    CLERK MENU OPTION");
			System.out.println("\t\t|===========================================|");
			System.out.println("\n\t\t\t1. View My Profile\n\n\t\t\t2. View Customers Profile\n\n\t\t\t3. Approve Application\n\n\t\t\t4.View Transactions Of Customer\n\n\t\t\t5.View Balance Of Customer\n\n\t\t\t6. Log Out\n\n\t\t\t7. Exit");
			System.out.print("\t\t|===========================================|");
			int option=inputvalid.validateInteger("\t\t\tEnter The Option : ");
			System.out.println("\t\t|===========================================|");
			switch(option)
			{
				
				case 1:
				
					viewProfile(userid);
					break;
					
				case 2:
					
					int userId=inputvalid.validateInteger("Enter The Customer ID : ");
					if(userId==-1)
					{
						return;
					}
					customer.viewProfile(userId);
					break;
				
				case 3:
				
					approve(userid,branchid);
					break;
					
				case 4:
					
					customerId=getCustomerId();
					if(customerId!=0)
					{
						viewTransactions(customerId);
					}
					break;
					
				case 5:
					
					customerId=getCustomerId();
					if(customerId!=0)
					{
						viewBalance(customerId);
					}
					break;
				 	
				case 6:
					
					return;
					
				case 7:
				
					try
					{
						(DBConnection.getInstance()).close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
					System.exit(0);
				
				default :
				
					System.out.println("\n\tInvalid Option");
			}
		}
	}
	
	
	
	
	void viewProfile(int userId)
	{
		try
		{
			if(!((customerDB.takeAccountNo(userId)).next()))
			{
				System.out.println("\n\t|---------------|");
				System.out.println("\t STAFF'S PROFILE");
				System.out.println("\t|---------------|");
				ResultSet result=clerkDB.takeStaff(userId);
				if(result.next())
				{
					user=new Users(result.getInt(1),result.getString(2),result.getString(3),result.getLong(4), result.getString(5), result.getByte(6),result.getString(7),result.getString(8),result.getString(9), result.getString(10),result.getString(11));
					System.out.println(user.staffToString());
				}
			}
			else
			{
				System.out.println("\n\tInvalid Customer ID");
			}
		}
		
		catch(SQLException e)
		{	
			e.printStackTrace();
		}
	}
	
	
	
	void approve(int userid,int branchid)
	{
		while(true)
		{
			int customerId=approveApplication(userid,branchid,"applied");
			if(customerId==0)
			{
				return;
			}
			else if(customerId==-1)
			{
				continue;
			}
			System.out.println("Press 1 To Approve The Application Or Press Any Key To Go Back...");
			String approveoption=input.next();
			if(approveoption.equals("1"))
			{
				clerkDB.updateCustomerStatus(customerId,"approved1");
				System.out.println("Successfully Approved The Application To The Manager Of The Customer ("+user.getUserId()+")");
			}
		}
	}
			
	
	
	
	public int approveApplication(int userid,int branchid,String getStatus)
	{
		int customerId=0;
		if(getStatus.equals("applied"))
		{
			try
			{
				if(!((clerkDB.checkClerkStatus(userid)).next()))
				{	
					System.out.println("\nYou Don't Have An Authority To Approve The Applications\n");
					return customerId;
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		userlist=insertCusToList(clerkDB.takeCustomers(branchid,getStatus));
		if(userlist.size()==0)
		{
			System.out.println("\nThere Is No Application Pending For Clerk In Your Branch\n");
			return customerId;
		}
		int selectoption=inputvalid.validateInteger("Enter The Customer ID To View The Application Or Press 0 To Go Back : ");
		if(selectoption==0)
		{
			return customerId;
		}
		Iterator itr=userlist.iterator();
		while(itr.hasNext())
		{
			user=(Users)itr.next();
			if(selectoption==user.getCustomerId())
			{	
				System.out.println(user);
				customerId=user.getCustomerId();
				return customerId;
			}
			System.out.println("Invalid Customer ID");
		}
		return -1;
	}
	
	
	
	
	ArrayList<Users> insertCusToList(ResultSet customer)
	{	
		try
		{
			userlist.clear();
			System.out.println("====================================================================================================");
		System.out.println(String.format("%-25s%-30s%-30s%-30s","Customer ID","User Name","Aadhar No.","Status"));
System.out.println("====================================================================================================");
			while(customer.next())
			{
				user=new Users(customer.getInt(1),customer.getInt(2),customer.getString(3), customer.getLong(4),customer.getLong(5),customer.getString(6),customer.getByte(7),customer.getString(8), customer.getString(9),customer.getString(10),customer.getString(11),customer.getString(12),customer.getString(13), customer.getString(14));
				userlist.add(user);
				System.out.println(user.cusOverView());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userlist;
			
	}
	
	
	
	int getCustomerId()
	{
		int customerId=0;
		String accountNo=inputvalid.validateAccountNo("Enter The Account Number : ");
		try
		{
			ResultSet result=customerDB.takeCustomerInfo(accountNo);
			if(result.next())
			{	
				customerId=result.getInt(1);
			}
			else
			{
				System.out.println("Invalid Account Number");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return customerId;
	}
		
	
	
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
					
					

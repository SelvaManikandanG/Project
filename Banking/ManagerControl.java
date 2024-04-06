package com.zoho.Banking;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
class ManagerControl extends ClerkControl implements ManagerAccessible
{
	
	ManagerDBManagement managerDB=new ManagerDBManagement();
	int option=0;
	
	
	public void menu(int userid,int branchid)
	{
		while(true)
		{
			System.out.println("\t\t|===============================================|");
			System.out.println("\t\t\t    MANAGER MENU OPTION");
			System.out.println("\t\t|===============================================|");
			System.out.println("\n\t\t\t1. View My Profile\n\n\t\t\t2. View Customer's Profile\n\n\t\t\t3. View Staff's Profile\n\n\t\t\t4. Approve Application\n\n\t\t\t5. Add Staff\n\n\t\t\t6. Update Or Transfer Or Remove Staff \n\n\t\t\t7. Log Out\n\n\t\t\t8. Exit");
			System.out.print("\t\t|===============================================|");
			option=inputvalid.validateInteger("\t\t\tEnter The Option : ");
			System.out.println("\t\t|===============================================|");
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
					
					int staffId=inputvalid.validateInteger("Enter The Staff ID : ");
					if(staffId==-1)
					{
						return;
					}
					viewProfile(staffId);
					break;
				
				case 4:
				
					approve(userid,branchid);
					break;
					
				case 5:
				
					hireStaff(branchid);
					break;
					
				case 6:
				
					updateTransferRemoveStaff(branchid);
					break;
					
				case 7:
				
					return;
					
				case 8:
				
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
				
					System.out.println("Invalid Option");
			}
		}
	}
	
	
	
	void approve(int userid,int branchid)
	{
		while(true)
		{
			int customerId=approveApplication(userid,branchid,"approved1");
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
				managerDB.updateCustomerStatus(customerId,"approved2");
				managerDB.updateUserStatus(user.getUserId(),"active");
				managerDB.updateCustomerAccount(customerId,String.format("%05d",branchid)+""+String.format("%04d",customerId));
				System.out.println("Successfully Approved The Application Of The Customer ("+user.getUserId()+")");
			}
		}
	}
	
	
	
	
	private void hireStaff(int branchid)
	{
		String role="";
		check=true;
		while(check)
		{
			role=inputvalid.validateString("Designation : ");
			if(!((role.toLowerCase()).equals("clerk"))&&!((role.toLowerCase()).equals("cashier")))
			{
				System.out.println("Invalid Designation");
				continue;
			}
			check=false;
		}
		addStaff(branchid,role,"active-cantadd");
	}
		
	
				

	
	
	public void addStaff(int branchid,String role,String status)
	{
		String name=inputvalid.validateString("Full Name : ");
		if(name.length()==0)
		{
			return;
		}
		String dob=inputvalid.validateDate("Date Of Birth ");
		if(dob.length()==0)
		{
			return;
		}
		String gender="";
		do
		{
			check=false;
			gender=inputvalid.validateString("Gender : ");
			if(gender.length()==0)
			{
				return;
			}
			if(!(((gender.toLowerCase())).equals("male"))&&!(((gender.toLowerCase())).equals("female"))&&!(((gender.toLowerCase())).equals("transgender")))
			{
				System.out.println("\nInvalid Input");
				check=true;
			}
		}while(check);
		long mobile=inputvalid.validateMobile("Mobile No. : ");
		if(mobile==-1)
		{
			return;
		}
		String address=inputvalid.getAddress();
		if(address.length()==0)
		{
			return;
		}
		String mail=inputvalid.validateMail("Mail ID : ");
		if(mail.length()==0)
		{
			return;
		}
		int userid=accCreate.addUser(role,branchid,name,dob,gender,mobile,address,mail,status);
		if(userid>0)
		{
			System.out.println("\n"+role.toUpperCase()+" Added Successfully And The User/Customer ID of "+role.toUpperCase()+" is "+userid);
		}
		else
		{
			System.out.println("\nThere Is A Problem In Adding The "+role);
		}
	}
	
	
	
	
	public void updateTransferRemoveStaff(int branchid)
	{
		while(true)
		{
			try
			{
				int clerkRoleId=0,cashierRoleId=0;
				ResultSet result1=managerDB.takeRoleId("clerk");
				if(result1.next())
				{	
					clerkRoleId=result1.getInt(1);
				}
				ResultSet result2=managerDB.takeRoleId("cashier");
				if(result2.next())
				{
					cashierRoleId=result2.getInt(1);
				}
				if(insertStaffToList(managerDB.takeAllStaffs(clerkRoleId,cashierRoleId,branchid)).size()==0)
				{
					System.out.println("There Is No Active Staffs In Your Branch");
					return;
				}
				int selectoption=inputvalid.validateInteger("Enter The Staff ID To View The Staff Details Or Press 0 To Go Back : ");
				if(selectoption==0)
				{
					return;
				}
					matchUserId(selectoption);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	
	private void matchUserId(int selectoption)
	{
		int userid=0;
		Iterator itr=userlist.iterator();
		while(itr.hasNext())
		{
			user=(Users)itr.next();
			if(selectoption==user.getUserId())
			{	
				userid=user.getUserId();
				System.out.println("\n\t|---------------|");
				System.out.println("\t STAFF'S PROFILE");
				System.out.println("\t|---------------|");
				System.out.println(user.staffToString());
				makeActionOnStaff(userid);
				return;
			}
		}
		System.out.println("Invalid Staff ID");
	}
	
	
	
	
	
	private void makeActionOnStaff(int userid)
	{
		try
		{
			check=true;
			while(check)
			{
				option=inputvalid.validateInteger("1=> Make Staff To Approve Application\t\t2=> Make Staff Not To Approve Application\t\t3=>Transfer Staff\t\t 4=> Dismiss Staff\n\nPress 0 To Go Back...");
				if(option==-1)
				{
					return;
				}
				switch(option)
				{
					case 1:

						if((managerDB.takeUserByStatus(userid,"active-canadd")).next())
						{	
							System.out.println("\nStaff Can Add Already");
							return;
						}
						managerDB.updateUserStatus(userid,"active-canadd");
						System.out.println("\nSuccessfully Made The Staff(Id : "+userid+") To Add Applications Of The Customer ");
						return;

					case 2:
					
						if((managerDB.takeUserByStatus(userid,"active-cantadd")).next())
						{	
							System.out.println("\nStaff Can't Add Already");
							return;
						}
						managerDB.updateUserStatus(userid,"active-cantadd");
						System.out.println("\nSuccessfully Made The Staff(Id : "+userid+")Not To Add Applications Of The Customer ");
						return;
						
					case 3:
						
						int branchid=accCreate.getBranchId();
						if(branchid==0)
						{
							return;
						}
						if((managerDB.takeStaff(userid,branchid)).next())
						{	
							System.out.println("\nStaff Is Already In That Branch");
							return;
						}
						if(managerDB.updateStaffBranch(userid,branchid)!=0)
						{
							System.out.println("\nSuccessfully Transferred The Staff (Id : "+userid+")");
						}
						return;
					
					case 4:
					
						managerDB.updateUserStatus(userid,"inactive");
						System.out.println("\nRemoved The Staff Of Id : "+userid);
						return;
						
					case 0:
					 	
					 	return;
						
					default :
						 
						 System.out.println("\nInvalid Option");
						 check=true;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
		
		
		
		
		
	ArrayList<Users> insertStaffToList(ResultSet staff)
	{	
		try
		{
			userlist.clear();
			System.out.println("====================================================================================================");
		System.out.println(String.format("%-15s%-35s%-30s%-30s","User ID","User Name","Role","Status"));
System.out.println("====================================================================================================");
			while(staff.next())
			{
				user=new Users(staff.getInt(1),staff.getString(2),staff.getString(3),staff.getLong(4), staff.getString(5), staff.getByte(6),staff.getString(7),staff.getString(8),staff.getString(9), staff.getString(10),staff.getString(11));
				userlist.add(user);
				System.out.println(user.staffOverView());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userlist;
			
	}


}

		
		
	
	
	
	
	

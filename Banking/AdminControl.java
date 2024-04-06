package com.zoho.Banking;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
class AdminControl extends ManagerControl implements AdminAccessible
{
	AdminDBManagement adminDB=new AdminDBManagement();
	int option=0;
	
	void menu(int userId)
	{
		while(true)
		{
			System.out.println("\t\t|=============================================|");
			System.out.println("\t\t\t    ADMIN MENU OPTION");
			System.out.println("\t\t|=============================================|");
			System.out.println("\n\t\t\t1. View My Profile\n\n\t\t\t2. View Customer's Profile\n\n\t\t\t3. View Staff's Profile\n\n\t\t\t4. Add Staff\n\n\t\t\t5. Transfer Or Dismiss Manager\n\n\t\t\t6. Update Or Transfer Or Remove Staff\n\n\t\t\t7. Add Branch\n\n\t\t\t8. Log Out\n\n\t\t\t9. Exit");
			System.out.print("\t\t|=============================================|");
			option=inputvalid.validateInteger("\t\t\tEnter The Option : ");
			System.out.println("\t\t|=============================================|");
			switch(option)
			{
			
				case 1:
				
					viewProfile(userId);
					break;
					
				case 2:
					
					int userid=inputvalid.validateInteger("Enter The Customer ID : ");
					if(userid==-1)
					{
						return;
					}
					customer.viewProfile(userid);
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
				
					hireStaff();
					break;
					
				case 5:
				
					transferOrDismissManager("manager");
					break;
					
				case 6:
				
					modifyStaff();
					break;
					
				case 7:
				
					addBranch();
					break;	
					
				case 8:

					return;
					
				case 9:
				
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
	
	
	
	
	private void hireStaff()
	{
		String role="";
		check=true;
		while(check)
		{
			role=inputvalid.validateString("Designation : ");
			if(role.length()==0)
			{
				return;
			}
			if(!((role.toLowerCase()).equals("clerk"))&&!((role.toLowerCase()).equals("cashier"))&&!((role.toLowerCase()).equals("manager")))
			{
				System.out.println("Invalid Designation");
				continue;
			}
			check=false;
		}
		int branchid=accCreate.getBranchId();
		if(branchid==0)
		{
			return;
		}
		if((role.toLowerCase()).equals("manager"))
		{
			addStaff(branchid,role,"active");
		}
		else
		{
			addStaff(branchid,role,"active-cantadd");
		}
	}
	
	
	
	
	
	public void transferOrDismissManager(String role)
	{
		try
		{
			ResultSet result1=adminDB.takeRoleId(role);
			if(result1.next())
			{
				if(insertStaffToList(adminDB.takeStaff(result1.getInt(1))).size()==0)
				{
					System.out.println("\nThere Is No Active Managers ");
					return;
				}
				int selectoption=inputvalid.validateInteger("\nEnter The Manager ID To View The Manager Details Or Press 0 To Go Back : ");
				if(selectoption==0)
				{
					return;
				}
				Iterator itr=userlist.iterator();
				int userid=0;
				while(itr.hasNext())
				{
					user=(Users)itr.next();
					if(selectoption==user.getUserId())
					{	
						userid=user.getUserId();
						System.out.println(user.staffToString());
						makeActionOnManager(userid);
						return;
					}
				}
				System.out.println("\nInvalid Staff ID");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	private void makeActionOnManager(int userid)
	{
		System.out.println("\n1=> Transfer Manager\t\t2=> Dismiss Manager\n\nPress 0  To Go Back...");
		try
		{
			check=true;
			while(check)
			{
				option=inputvalid.validateInteger("");
				if(option==-1)
				{
					return;
				}
				switch(option)
				{
					case 1:
					
						int branchid=accCreate.getBranchId();
						if(branchid==0)
						{
							return;
						}
						if((adminDB.takeStaff(userid,branchid)).next())
						{	
							System.out.println("\nManager Is Already In That Branch");
							return;
						}
						if(adminDB.updateStaffBranch(userid,branchid)!=0)
						{
							System.out.println("\nSuccessfully Transferred The Manager (Id : "+userid+")");
						}
						return;
				
					case 2:
				
						adminDB.updateUserStatus(userid,"inactive");
						System.out.println("\nRemoved The Manager Of Id : "+userid);
						return;
					
					case 0:
					
						return;
					
					default :
							 
						 System.out.println("\nInvalid Option");
				}
			}		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	private void modifyStaff()
	{
		int branchid=accCreate.getBranchId();
		if(branchid==0)
		{
			return;
		}
		updateTransferRemoveStaff(branchid);
	}
		
			
	
	
	
	
	public void addBranch()
	{
		do
		{
			check=false;
			String branch=inputvalid.validateString("Enter The New Branch Name : ");
			if(branch.length()==0)
			{
				check=true;
				continue;
			}
			try
			{
				ResultSet result1=adminDB.takeBranchId(branch);
				if(result1.next())
				{
					System.out.println("\nThis Branch Is Already Existed "+ result1.getInt(1));
					System.out.println("\nPress 1 to Re-Enter The New Branch Name Or Press Any Key To Go Back");
					if((input.next()).equals("1"))
					{
						check=true;
						continue;
					}
				}
				else
				{
					int count=0;
					ResultSet result2=adminDB.takeBranchCount();
					if(result2.next())
					{
						count=result2.getInt(1);
					}
					int branchid=adminDB.insertBranch(branch,(branch.toUpperCase()).substring(0,5)+(String.format("%04d",count+1)));
					if(branchid>0)
					{
						System.out.println("\nNew Branch Added Successfully... And The Branch Id For The Branch Is "+branchid);
					}
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}while(check);
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
					
					

package com.zoho.Banking;
import java.sql.ResultSet;
import java.sql.SQLException;
class AccountCreation
{
	CustomerDBManagement customerDB=new CustomerDBManagement();
	InputValidations inputvalid=new InputValidations();
	boolean check;
	
	
	
	int addUser(String role,int branchid,String name,String dob,String gender,long mobile,String address,String mail,String status)
	{
		int roleid=0,userid=0;
		byte age=inputvalid.getYears(dob,inputvalid.getCurrentDate());
		try
		{
			ResultSet result=customerDB.takeRoleId(role);
			if(result.next())
			{
				roleid=result.getInt(1);
			}
			
			userid=customerDB.insertUser(name,dob,age,gender,mobile,address,mail,roleid,branchid,status);
			if(userid<=0)
			{	
				System.out.println("\nThere is a problem in account creation");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return userid;
	}
	
	
	
	
	void addCustomer(int userid,long aadhar,int typeid,int schemeid)
	{
		if((customerDB.insertCustomer(userid,aadhar,typeid,schemeid))>0)
		{
			System.out.println("\n\nSuccessfully Applied For The Account Creation... And Your Customer ID Is "+userid+", Keep Noted!!!");
		}
		else
		{
			System.out.println("There Is A Problem In Account Creation");
		}
	}
	
	
	
	
	
	int getSchemeId(String dob)
	{
		int schemeid=0;
		byte age=inputvalid.getYears(dob,inputvalid.getCurrentDate());
		String scheme="";
		if(age<18)
		{
			scheme="minor";
		}
		else if(age>60)
		{
			scheme="seniorcitizen";
		}
		else
		{
			scheme="adult";
		}	
		try
		{
			ResultSet result=customerDB.takeSchemeId(scheme);
			if(result.next())
			{
				schemeid=result.getInt(1);
			}
			else
			{
				System.out.println("Invalid Scheme");
				check=true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return schemeid;
	}
	
	
	
	int getTypeId()
	{
		int typeid=0;
		String type="";
		do
		{
			check=false;
			int typeOption=inputvalid.validateInteger("1=> Saving Account\n\n2=> Current Account\n\n\nSelect The Account Type : ");
			switch(typeOption)
			{
				case 0:
				
					return typeid;
				
				case 1:
					
					type="saving";
					break;
					
				case 2:
					
					type="current";
					break;
				
				default:
					
					System.out.println("Invalid Option");
					check=true;
			}
		}while(check);			
		try
		{
			ResultSet result=customerDB.takeTypeId(type);
			if(result.next())
			{
				typeid=result.getInt(1);
			}
			else
			{
				System.out.println("Invalid Type");
				check=true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return typeid;
	}
	
	
	
	int getBranchId()
	{
		boolean check;
		int branchid=0;
		do
		{
			check=false;
			String branch=inputvalid.validateString("Branch Name : ");
			if(branch.length()==0)
			{
				return branchid;
			}
			try
			{
				ResultSet result=customerDB.takeBranchId(branch);
				if(result.next())
				{
					branchid=result.getInt(1);
				}
				else
				{
					System.out.println("Invalid Branch");
					check=true;
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}while(check);
		return branchid;
	}

	
}
		

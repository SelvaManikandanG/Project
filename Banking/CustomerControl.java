package com.zoho.Banking;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Scanner;
class CustomerControl implements CustomerAccessible
{
	Scanner input=new Scanner(System.in);
	AccountCreation accCreate=new AccountCreation();
	CustomerDBManagement customerDB=new CustomerDBManagement();
	InputValidations inputvalid=new InputValidations();
	TreeMap<Integer,String> accountNumber=new TreeMap<>();
	Users user=null;
	ArrayList<Users> userlist=new ArrayList<>();
	Transactions transaction=null;
	ArrayList<Transactions> transactionlist=new ArrayList<>();
	boolean check;
	
	
	
	
	public void createAccount()
	{
		System.out.println("\n\n--------------------------------Account Application Form--------------------------------");
		int typeid=accCreate.getTypeId();
		if(typeid==0)
		{
			return;
		}
		int branchid=accCreate.getBranchId();
		if(branchid==0)
		{
			return;
		}
		System.out.println("\n\n--------------------------------Applicant Details--------------------------------");
		String name=inputvalid.validateString("Full Name             : ");
		if(name.length()==0)
		{
			return;
		}
		String dob=inputvalid.validateDate("Date Of Birth ");
		if(dob.length()==0)
		{
			return;
		}
		long aadhar=inputvalid.validateAadhar("Aadhar No.            : ");
		if(aadhar==-1)
		{
			return;
		}
		try
		{
			ResultSet result=customerDB.checkAadhar(aadhar);
			if(result.next())
			{
				accCreate.addCustomer(result.getInt(1),aadhar,typeid,accCreate.getSchemeId(dob));
				return;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
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
		long mobile=inputvalid.validateMobile("Mobile No.          : ");
		if(mobile==-1)
		{
			return;
		}
		String address=inputvalid.getAddress();
		if(address.length()==0)
		{
			return;
		}
		String mail=inputvalid.validateMail("Mail ID               : ");
		if(mail.length()==0)
		{
			return;
		}
		int userid=accCreate.addUser("customer",branchid,name,dob,gender,mobile,address,mail,"inactive");
		if(userid>0)
		{
			accCreate.addCustomer(userid,aadhar,typeid,accCreate.getSchemeId(dob));
		}
		else
		{
			System.out.println("There Is A Problem In Account Creation");
		}
	}
	
	
	
	
	public void menu(int userId,int branchid)
	{
		while(true)
		{
			System.out.println("Account Numbers ");
			int customerId=0;
			String accountNo="";
			try
			{
				accountNumber.clear();
				ResultSet result=customerDB.takeAccountNo(userId);
				while(result.next())
				{
					customerId=result.getInt(1);
					accountNo=result.getString(2);
					System.out.println("-----------------------");
					System.out.print("|  "+customerId+"  :  ");
					System.out.println(accountNo+"   |");
					System.out.println("-----------------------\n\n");
					accountNumber.put(customerId,accountNo);
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			if(accountNumber.size()>1)
			{
				selectAccount(userId);
			}
			else
			{
				viewMenu(userId,customerId,accountNo);
				return;
			}
		}
	}
	
	
	
	
	
	private void selectAccount(int userId)
	{
		int customerId=0;
		String accountNo="";
		while(true)
		{
			int option=inputvalid.validateInteger("Enter The Account ID For The Further Actions : ");
			for (Map.Entry<Integer,String> entry : accountNumber.entrySet()) 
			{
				if(entry.getKey()==option)
				{
					customerId=(int)entry.getKey();
					accountNo=(String)entry.getValue();
					viewMenu(userId,customerId,accountNo);
					return;
	    			}
			}
			System.out.println("Invalid Account Number... Press 1 to Re-Enter Or Press Any Key To Go Back");
			if(!((input.next()).equals("1")))
			{
				return;
			}
		}
	}
	
	
	
	
	private void viewMenu(int userId,int customerId,String accountNo)
	{		
		while(true)
		{
			System.out.println("\t\t|========================================|");
			System.out.println("\t\t\t    CUSTOMER MENU OPTION");
			System.out.println("\t\t|========================================|");
			System.out.println("\n\t\t\t1. View My Profile\n\n\t\t\t2. Make Transaction\n\n\t\t\t3. View Transaction History\n\n\t\t\t4. Check Balance\n\n\t\t\t5. Log Out\n\n\t\t\t6. Exit");
			System.out.print("\t\t|========================================|");
			int option=inputvalid.validateInteger("\t\t\tEnter The Option : ");
			System.out.println("\t\t|========================================|");
			switch(option)
			{
				
				case 1:
				
					viewProfile(userId);
					break;
					
				case 2:
				
					makeTransaction(customerId,accountNo);
					break;
		
				case 3:
				
					viewTransactions(customerId);
					break;
					
				case 4:
				
					viewBalance(customerId);
					break;
					
				case 5:
				
					return;
					
				case 6:
				
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
	
	
	
	
	void viewProfile(int userId)
	{
		try
		{
			if((customerDB.takeAccountNo(userId)).next())
			{
				
				System.out.println("\n\t|-------------------|");
				System.out.println("\t  ACCOUNT DETAILS");
				System.out.println("\t|-------------------|");	System.out.println("===================================================================================================================");
				System.out.println(String.format("%-35s%-30s%-30s%-35s","Account No.","Type","Scheme","Branch"));
				System.out.println("===================================================================================================================");
				ResultSet result=customerDB.takeCustomers(userId);
				while(result.next())
				{
					user=new Users(result.getInt(1),result.getString(2), result.getLong(3),result.getLong(4),result.getString(5),result.getByte(6),result.getString(7), result.getString(8),result.getString(9),result.getString(10),result.getString(11),result.getString(12), result.getString(13),result.getString(14));
					System.out.println(user.accountView());
				}
				System.out.println("\n\t|-------------------|");
				System.out.println("\t  CUSTOMER'S PROFILE");
				System.out.println("\t|-------------------|");
				System.out.println(user.userView());
			}
			else
			{
				System.out.println("\nInvalid Customer ID");
			}
		}
		
		catch(SQLException e)
		{	
			e.printStackTrace();
		}
	}
			
	
	
	
	
	
	
	public void makeTransaction(int customerId1,String accountNo1)
	{
		boolean check;
		String txnType="";
		double limit=0,charge=0,withdrawLimit=0;;
		do
		{
			check=false;
			System.out.println("\n\t|--------------------|");
			System.out.println("\t  TRANSACTION MODE");
			System.out.println("\t|--------------------|\n");
			System.out.println("1. IMBS\n2. NEFT\n3.Back");
			int option=inputvalid.validateInteger("Enter The Option : ");
			switch(option)
			{
				case 1:
					limit=200000;
					charge=7;
					txnType="imbs";
					break;
				case 2:
					txnType="neft";
					break;
				case 3:
					return;
				default:
					System.out.println("\nInvalid Option");
					check=true;
					break;
			}
		}while(check);		
		try
		{
			String accountNo2=inputvalid.validateAccountNo("\nEnter A Beneficiary Account Number : ");
			if(accountNo1.equals(accountNo2))
			{
				System.out.println("\nThis Is Your Account Number!!!");
				return;
			}
			if(!((customerDB.takeCustomerInfo(accountNo2)).next()))
			{	
				System.out.println("\nInvalid Account Number");
				return;
			}
			String ifsc=inputvalid.validateStrAndNo("\nEnter A Beneficiary IFSC code : ");
			ResultSet result=customerDB.takeCustomerInfo(accountNo1);
			if(result.next())
			{	
				int customerId2=result.getInt(1);
				int typeId=result.getInt(2);
				double balance=result.getDouble(3);
				double amountLimit=getAmountLimit(typeId);
				double amount=getAmount(limit,balance,amountLimit,withdrawLimit);
				if(amount==-1)
				{
					return;
				}
				int txnId=customerDB.insertTransaction(customerId1,customerId2,amount,txnType);
				if(txnId>0)
				{
					if((customerDB.updateSubBalance(customerId1,amount)>0)&&customerDB.updateAddBalance(customerId2,amount)>0)
					{
						System.out.println("\nThe Transaction Is Made Successfully And The Transaction Id Is "+txnId);
					}
				}
				else
				{
					System.out.println("\nThere Is a Problem In The Transaction");
				}
			}
			else
			{
				System.out.println("\nInvalid Account Number");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	double getAmountLimit(int typeId)
	{
		double amountLimit=0;
		try
		{
			ResultSet result=customerDB.takeType(typeId);
			if(result.next())
			{
				if((result.getString(1)).equals("saving"))
				{
					amountLimit=3000;
				}
				else if((result.getString(1)).equals("current"))
				{
					amountLimit=10000;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return amountLimit;
	}
			
			
			
	
	
	private double getAmount(double limit,double balance,double amountLimit,double withdrawLimit)
	{
		double amount=0;
		do
		{
			check=false;
			amount=inputvalid.validateDouble("Enter The Amount To Make Transaction(Rs.) : ");
			if(balance<withdrawLimit)
			{
				System.out.println("Your Current Balance Is "+balance);	
				check=true;
				continue;
			}
			if((amount>balance-amountLimit)&&(amount<200000))
			{
				double txnLimit=0;
				withdrawLimit=balance-amountLimit;
				if((withdrawLimit>limit)&&(limit!=0))
				{
					txnLimit=limit;
				}
				else
				{	
					txnLimit=withdrawLimit;
				}
				System.out.println("\nYou Can Make Transaction Atmost "+txnLimit+" Rs.");
				System.out.println("\nPress 1 to Re-Enter or Press Any Key To Go Back");
				if((input.next()).equals("1"))
				{
					check=true;
				}
				else
				{
					return -1;
				}
			}
		}while(check);
		return amount;
	}
			
			
			
	
	
	public void  viewTransactions(int customerId)
	{	
		ResultSet transactions=customerDB.takeTransactions(customerId);
		try
		{
			transactionlist.clear();
			while(transactions.next())
			{
				transactionlist.add(new Transactions(transactions.getInt(1),transactions.getString(2),transactions.getString(3),transactions.getDouble(4), transactions.getString(5)));
				System.out.println(transaction);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	
	
	
	public void viewBalance(int customerId)
	{	
		ResultSet result=customerDB.takeBalance(customerId);
		transaction=new Transactions();
		try
		{
			if(result.next())
			{
				transaction.setBalance(result.getDouble(1));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		double balance=transaction.getBalance();
		if(balance!=0)	
		{
			System.out.println("The Current Balance Is "+balance);
		}
		else
		{
			System.out.println("Haven't Activated The Account or The Account Is Empty...");
		}
	}

	
}
			
		
		

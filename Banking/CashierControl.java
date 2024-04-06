package com.zoho.Banking;
import java.sql.SQLException;
import java.sql.ResultSet;
class CashierControl extends ClerkControl implements CashierAccessible
{

	CashierDBManagement cashierDB=new CashierDBManagement();
	
	public void menu(int userid,int branchid)
	{
		while(true)
		{
			System.out.println("\t\t|========================================|");
			System.out.println("\t\t\t    CASHIER MENU OPTION");
			System.out.println("\t\t|========================================|");
			System.out.println("\n\t\t\t1. View My Profile\n\n\t\t\t2. View Customers Profile\n\n\t\t\t3. Approve Applications\n\n\t\t\t4. Deposit Amount\n\n\t\t\t5. Withdraw Amount\n\n\t\t\t6. Log Out\n\n\t\t\t7. Exit");
			System.out.println("\t\t|========================================|");
			int option=inputvalid.validateInteger("\t\t\tEnter The Option : ");
			System.out.println("\t\t|========================================|");
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
				
					deposit(branchid);
					break;
					
				case 5:
				
					withdraw(branchid);
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
				
					System.out.println("Invalid Option");
			}
		}
	}
	
	
	
	public void deposit(int branchId)
	{
		try
		{
			//int ifsc_id=0;
			int txnId=0;
			String accountNo=inputvalid.validateAccountNo("Enter The Account Number : ");
			ResultSet result1=cashierDB.takeCustomerInfo(accountNo);
			if(result1.next())
			{	
				//String ifsc=inputvalid.validateString("Enter The Account IFSC code :");
				//ResultSet result=rs.checkIfsc(ifsc);
				//if(result.next())
				int customerId=result1.getInt(1);
				int typeId=result1.getInt(2);
				double balance=result1.getDouble(3);
				double amountLimit=getAmountLimit(typeId);
				double amount=getAmount(amountLimit,balance);				
				txnId=cashierDB.insertTransaction(customerId,amount,branchId,"credit_id");
				if(txnId>0)
				{
					if(cashierDB.updateAddBalance(customerId,amount)>0)
					{
						System.out.println("Successfully Amount Deposited In The Account And The Transaction Id Is "+txnId);
					}
				}
				else
				{
					System.out.println("There Is a Problem In Depositing The Amount");
				}
			}
			else
			{
				System.out.println("Invalid Account Number");
				return;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	
	
	
	private double getAmount(double amountLimit,double balance)
	{
		double amount=0;
		check=false;
		while(check)
		{
			if(balance==0)
			{
				amount=inputvalid.validateDouble("Enter The Amount To Deposit Alteast "+amountLimit+"(in Rs.) : ");
				if(amount<amountLimit)
				{
					System.out.println("You Should Deposit Atleast The Amount oF "+amountLimit);
					check=true;
				}
			}
			else
			{
				amount=inputvalid.validateDouble("Enter The Amount To Deposit (in Rs.) : ");
			}
		}
		return amount;
	}
	
		
				
	
	
	
	
	public void withdraw(int branchId)
	{
		try
		{
			double amount=0,withdrawLimit=0;
			int txnId=0;
			String accountNo=inputvalid.validateAccountNo("Enter The Account Number : ");
			ResultSet result=cashierDB.takeCustomerInfo(accountNo);
			if(result.next())
			{	
				int customerId=result.getInt(1);
				int typeId=result.getInt(2);
				double balance=result.getDouble(3);
				double amountLimit=getAmountLimit(typeId);
				check=false;
				while(check)
				{
					amount=inputvalid.validateDouble("Enter The Amount To Withdraw (Rs.) : ");
					if(amount>balance-amountLimit)
					{
						withdrawLimit=balance-amountLimit;
						System.out.println("You Can Withdraw Atmost "+withdrawLimit+" Rs.");
						check=true;
					}
				}
				txnId=cashierDB.insertTransaction(customerId,amount,branchId,"debit_id");
				if(txnId>0)
				{
					if(cashierDB.updateSubBalance(customerId,amount)>0)
					{
						System.out.println("Successfully Amount Withdrawn From The Account And The Transaction Id Is "+txnId);
					}
				}
				else
				{
					System.out.println("There Is a Problem In Withdrawing The Amount");
				}
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
	}
			
	
	
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
					
					

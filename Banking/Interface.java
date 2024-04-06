package com.zoho.Banking;


interface CustomerAccessible
{	
	void createAccount();
	void makeTransaction(int customerId,String accountNo);
	void viewTransactions(int customerId);
	void viewBalance(int customerId);
}


interface ClerkAccessible extends CustomerAccessible
{	
	int approveApplication(int userid,int branchid,String getStatus);
}


interface CashierAccessible extends ClerkAccessible 
{	
	void deposit(int branchId);
	void withdraw(int branchId);
}


interface ManagerAccessible extends ClerkAccessible
{	
	void addStaff(int branchid,String role,String status);
	void updateTransferRemoveStaff(int branchid);
}


interface AdminAccessible extends ManagerAccessible
{	
	void transferOrDismissManager(String role);
	void addBranch();
}



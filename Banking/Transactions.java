package com.zoho.Banking;	
public class Transactions {
    private int txnId;
    private String fromAccount;
    private String toAccount;
    private double amount;
    private String txnType;
    private double balance;

    public Transactions(int txnId, String fromAccount, String toAccount, double amount, String txnType) 
    {
        this.txnId = txnId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.txnType = txnType;
    }
    
    
    public Transactions()
    {
    
    }


    public int getTxnId() 
    {
        return txnId;
    }

    public void setTxnId(int txnId) 
    {
        this.txnId = txnId;
    }

    public String getFromAccount() 
    {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) 
    {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() 
    {
        return toAccount;
    }

    public void setToAccount(String toAccount) 
    {
        this.toAccount = toAccount;
    }

    public double getAmount() 
    {
        return amount;
    }

    public void setAmount(double amount) 
    {
        this.amount = amount;
    }

    public String getTxnType() 
    {
        return txnType;
    }

    public void setTxnType(String txnType)
    {
        this.txnType = txnType;
    }
    
     public double getBalance() 
    {
        return balance;
    }

    public void setBalance(double balance) 
    {
        this.balance = balance;
    }

    
    public String toString() 
    {
        return "Transaction ID: " + txnId + "\t" +
               "From Account: " + fromAccount + "\t" +
               "To Account: " + toAccount + "\t" +
               "Amount: " + amount + "\t" +
               "Transaction Type: " + txnType;
    }
}


package com.zoho.Banking;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
class TableCreation
{
     //public static Connection con=null;
     public static Statement st=null;
   //  public static PreparedStatement ps=null;
     public void createTable()
     {
     	try
     	{
		 if(st==null) 
		 {
			 st=((DBConnection.getInstance()).getConnection()).createStatement();
		 }
		 roleTable();
		 branchTable();
		 usersTable();
		 typeTable();
		 schemeTable();
		 customer_accountTable();
		 transactionTable();
		 staffTable();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
     }
     
     
     
      /* public void insertIntoTable()
       {
     	try
     	{
		 con=(DBConnection.getInstance()).getConnection();
		 insertIntoRole();
		 insertIntoBranchTable();
		 usersTable();
		 typeTable();
		 schemeTable();
		 customer_accountTable();
		 transactionTable();
		 staffTable();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
     }*/
     
     
     private void roleTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists role(id serial primary key,role_name varchar(20) not null)");                                
             }
        catch(SQLException e)
             {
                   System.out.println("Role Table Not Created");
             } 
     }
     
     
     
     private void branchTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists branch(id serial primary key,branch_name varchar(20) not null,IFSC_code varchar(11) not null )");                                
             }
         catch(SQLException e)
             {
                   System.out.println("Branch Table Not Created");
             } 
     }
     
     
     
     private void usersTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists users(id serial primary key,role_id int not null,user_name text not null,mobile_no bigint not null,mail_id text not null,password varchar(20) ,gender varchar(15) not null,age int not null,address text not null,branch_id int not null,constraint fkey1 foreign key(role_id) references role(id)on delete cascade,constraint fkey2 foreign key(branch_id) references branch(id) on delete cascade);");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("Users Table Not Created");
             } 
     }
     
     
     private void typeTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists type(id serial primary key,type_name varchar(20) not null)");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("Account Type Table Not Created");
             } 
     }
     
     
     
     private void schemeTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists scheme(id serial primary key,scheme_name varchar(20) not null)");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("Account Scheme Table Not Created");
             } 
     }
     
     
     
     private void customer_accountTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists customer(id serial primary key,user_id int not null,account_no bigint not null default 0,Aadhaar_no bigint not null,type_id int not null,scheme_id int not null,balance numeric(17,2) not null default 0,approval varchar(20) not null,constraint fkey3 foreign key(user_id) references users(id) on delete cascade,constraint fkey4 foreign key(type_id) references type(id)on delete cascade,constraint fkey5 foreign key(scheme_id) references scheme(id));");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("Customer Account Table Not Created");
             } 
     }



     private void transactionTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists transaction(id serial primary key,debit_id int,credit_id int,amount numeric(17,2) not null,transaction_type varchar(20),Transaction_date date not null default now(),constraint fkey6 foreign key(debit_id) references customer(id) on delete cascade,constraint fkey7 foreign key(credit_id) references customer(id) on delete cascade);");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("Transaction Table Not Created");
             } 
     }
     
     
     
     private void staffTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists staff(id serial primary key,role_id int not null,password varchar(20) not null,branch_id int not null,constraint fkey8 foreign key(role_id) references role(id) on delete cascade,constraint fkey9 foreign key(branch_id) references branch(id)on delete cascade);");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("Staff Table Not Created");
             } 
     }
     
     
     
}

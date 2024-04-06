import java.sql.ResultSet;
import java.io.Console;
import java.util.Scanner;
import java.sql.SQLException;
class SignUp
{
	Scanner input=new Scanner(System.in);
	ResultSets rs=new ResultSets();
	Insertion insert=new Insertion();
	InputValidations inputvalid=new InputValidations();
	
	
	
	void signUp(String table)
	{
		long mobile_no=getMobileNo(table);
		if(mobile_no==-1l)
		{
			return;
		}
		String password=getPassword();
		if(password.length()==0)
		{
			return;
		}
		String username=inputvalid.validateString(table+" Name");
		if(username.length()==0)
		{
			return;
		}
		String address=inputvalid.getAddress();
		if(address.length()==0)
		{
			return;
		}
		if(updateSignUpInfo(mobile_no,username,address,password))
		{
			System.out.println("\n\t\tSuccessfully Signed Up...");
		}
		else
		{
			System.out.println("\n\t\tThere Is A Problem In Signing Up");	
		}
		
	}
	
	
	
	
	
	long getMobileNo(String name)
	{
		boolean check;
		System.out.println("\n\t\t\tMobile Number");
		long mobile_no=inputvalid.validateMobile("");
		if(mobile_no==-1)
		{
			return -1;
		}
		try
		{
			if(rs.checkMobile(mobile_no,name).next())	
			{
				System.out.println("\n\t\tAlready Registered With This Mobile Number");
				return -1;
			}
			else if(!(inputvalid.validateCaptcha()))
			{
				System.out.println("Wrongly Entered");
				return -1;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return mobile_no;
	}		
				
		
	
	
	
	
	
	String getPassword()
	{
		String password; 
		boolean check;
		do
		{
			check=false;
			System.out.println("\n\t\t\tPassword");
			char[] passwordArray = System.console().readPassword();
			password = new String(passwordArray);
			if(inputvalid.checkPassword(password))
			{
				System.out.println("\n\t\t\tConfirm Password");
				char[] copasswordArray = System.console().readPassword();
				String copassword = new String(copasswordArray);
				if(!(password.equals(copassword)))
				{
					System.out.println("Password Mismatch : Password That You Entered and Confirm Password Should Be Same");
					System.out.println("Press Any Key To Re Enter Password Or Press -1 to Go Back");
					String backoption=input.next();
					if(backoption.equals("-1"))
					{
						return "";
					}
					check=true;
				}
			}
			else
			{
				System.out.println("\n\tThe Password Should Contain Atleast\n"+
						   "\t\t1 Uppercase\n"+
						   "\t\t1 Lowercase\n"+
						   "\t\t1 Special Character\n"+
						   "\t\t1 Number\n");
				System.out.println("\n\t\tPress Any Key To Re Enter Password Or Press -1 to Go Back");
				String backoption=input.next();
				if(backoption.equals("-1"))
				{
					return "";
				}
				check=true;
			}
		}while(check);
		return password;
	}
		
		
		
		
		
		
		
		
		
	boolean updateSignUpInfo(long mobile,String username,String address,String password)
	{
		int userid=insert.insertUser(mobile,username,password);
		if(userid>0)
		{
			if(insert.insertAddress(userid,address)>0)
			{
				return true;
			}
		}
		return false;
	}
	
	
	
	
	boolean updateSignUpInfo(long mobile,String username,String password)
	{
		int sellerid=insert.insertSeller(mobile,username,password);
		if(sellerid>0)
		{
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	

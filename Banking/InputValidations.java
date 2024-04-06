package com.zoho.Banking;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;	
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException; 
class InputValidations
{
	Scanner input=new Scanner(System.in);
	
	
	
	byte validateByte(String str)
	{
		byte num=-1;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n"+str);
				num=input.nextByte();
				input.nextLine();
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\n\t\tThe Given Input Is Not A Number\n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				if(s.equals("-1"))
				{
					flag=false;
				}
				else
				{
					flag=true;
				}
			}
		}while(flag);
		return num;
	}
	
	
	
	int validateInteger(String str)
	{
		int num=-1;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n"+str);
				num=input.nextInt();
				input.nextLine();
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\n\t\tThe Given Input Is Not A Number\n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				if(!(s.equals("-1")))
				{
					flag=true;
				}
			}
		}while(flag);
		return num;
	}
	
		
	
	
	
	long validateLong(String str)
	{
		long num=-1l;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n"+str);
				num=input.nextLong();
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\n\t\tThe Given Input Is Not A Number\n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				if(s.equals("-1"))
				{
					flag=false;
				}
				else
				{
					flag=true;
				}
			}
		}while(flag);
		return num;
	}
	
	
	
	long validateAadhar(String str)
	{
		long num=-1l;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n"+str);
				num=input.nextLong();
				if(!(num>200000000000l&&num<=999999999999l))
				{
					throw new InputMismatchException();
				}
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\n\t\tInvalid Aadhar Number\n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				if(s.equals("-1"))
				{
					flag=false;
				}
				else
				{
					flag=true;
				}
			}
		}while(flag);
		return num;
	}
	
	
	
	long validateMobile(String str)
	{
		long num=-1l;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n"+str);
				num=input.nextLong();
				if(!(num>6000000000l&&num<=9999999999l))
				{
					throw new InputMismatchException();
				}
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\n\t\tInvalid Mobile Number \n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				if(!(s.equals("-1")))
				{
					flag=true;
				}
			}
		}while(flag);
		return num;
	}
	
	
	
	
	
	double validateDouble(String str)
	{
		double num=-1;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n"+str);
				num=input.nextDouble();
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\n\t\tThe Given Input Is Not An Amount\n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				if(s.equals("-1"))
				{
					flag=false;
				}
				else
				{
					flag=true;
				}
			}
		}while(flag);
		return num;
	}
	
	
	
	
	
	
	String validateString(String str)
	{
		String returnstr="";
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n"+str);
				String inputstr=input.nextLine();
				if(inputstr.length()==0)
				{
					throw new NotALetterException("There Are Some Characters Other Than The Letters");
				}
				char[] letters=inputstr.toCharArray();
				for(int i=0;i<letters.length;i++)
				{
					if(!((letters[i]>='A'&&letters[i]<='Z')||(letters[i]>='a'&&letters[i]<='z')))
					{
						throw new NotALetterException("There Are Some Characters Other Than The Letters");
					}
				}
				return inputstr;
			}
			catch(NotALetterException e)
			{
				System.out.println("\n\t\tThe Given Input Is Invalid \n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				input.nextLine();
				if(!(s.equals("-1")))
				{
					flag=true;
				}
			}
			
		}while(flag);
		return returnstr;
	
	}
	
	
	
	String validateStrAndNo(String str)
	{
		String returnstr="";
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n"+str);
				String inputstr=input.nextLine();
				if(inputstr.length()==0)
				{
					throw new NotALetterException("There Are Some Characters Other Than The Letters");
				}
				char[] letters=inputstr.toCharArray();
				for(int i=0;i<letters.length;i++)
				{
					if(!((letters[i]>='A'&&letters[i]<='Z')||(letters[i]>='a'&&letters[i]<='z')||(letters[i]>='0'&&letters[i]<='9')))
					{
						throw new NotALetterException("There Are Some Characters Other Than The Letters");
					}
				}
				return inputstr;
			}
			catch(NotALetterException e)
			{
				System.out.println("\n\t\tThe Given Input Is Invalid \n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				input.nextLine();
				if(!(s.equals("-1")))
				{
					flag=true;
				}
			}
			
		}while(flag);
		return returnstr;
	
	}
	
	
	
	
	String validateMail(String str)
	{
		String returnstr="";
		boolean flag;
		do
		{
			flag=false;
			try
			{
				input.nextLine();
				System.out.print("\n"+str);
				String inputstr=input.nextLine();
				if(inputstr.length()==0)
				{
					throw new NotALetterException("There Are Some Characters Other Than The Letters");
				}
				char[] letters=inputstr.toCharArray();
				for(int i=0;i<letters.length;i++)
				{
					if(!((letters[i]>='A'&&letters[i]<='Z')||(letters[i]>='a'&&letters[i]<='z')||(letters[i]>='0'&&letters[i]<='9')||(letters[i]=='@')||(letters[i]=='.')))
					{
						throw new NotALetterException("There Are Some Characters Other Than The Letters");
					}
				}
				if(inputstr.contains("@gmail.com"))
				{
					return inputstr;
				}
				else
				{
					throw new NotALetterException("There Are Some Characters Other Than The Letters");
				}
			}
			catch(NotALetterException e)
			{
				System.out.println("\n\t\tInvalid Mail ID \n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				input.nextLine();
				if(s.equals("-1"))
				{
					flag=false;
				}
				else
				{
					flag=true;
				}
			}
			
		}while(flag);
		return returnstr;
	
	}
	
	
	
	
	String validateAccountNo(String str)
	{
		String returnstr="";
		boolean flag;
		do
		{
			flag=false;
			try
			{
				
				System.out.print("\n"+str);
				String inputstr=input.nextLine();
				char[] letters=inputstr.toCharArray();
				for(int i=0;i<letters.length;i++)
				{
					if(!(letters[i]>='0')&&!(letters[i]<='9'))
					{
						throw new NotALetterException("There Are Some Characters Other Than The Letters");
					}
				}
				return inputstr;
			}
			catch(NotALetterException e)
			{
				System.out.println("\n\t\tThe Given Input Is Not An Account Number\n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				input.nextLine();
				if(s.equals("-1"))
				{
					flag=false;
				}
				else
				{
					flag=true;
				}
			}
			
		}while(flag);
		return returnstr;
	
	}
	
	
		
	
	String validateALetter(String str)
	{
		String returnletter="";
		boolean flag;
		do
		{
			flag=false;
			try
			{
				
				System.out.print("\n"+str);
				String inletter=input.next();
				if(inletter.length()>1)
				{
					throw new NotALetterException("There Are Some Characters Other Than The Letters and numbers");
				}
				char letter=inletter.charAt(0);
				if(!((letter>='A'&&letter<='Z')||(letter>='a'&&letter<='z')||(letter>='1'&&letter<='9')||(letter=='>')))
				{
					throw new NotALetterException("There Are Some Characters Other Than The Letters and numbers");
				}
				return inletter;
			}
			catch(NotALetterException e)
			{
				System.out.println("\n\t\tThe Given Input Is Invalid\n");
				System.out.print("\tPress 0 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				input.nextLine();
				if(s.equals("0"))
				{
					flag=false;
				}
				else
				{
					flag=true;
				}
			}
			
		}while(flag);
		return returnletter;
	
	}
	
	
	
	
	
	
	String validateDate(String str) 
	{
		boolean flag;
		String returndate="";
		do
		{
			int year=0,month=0,day=0;
			boolean check;
			try
			{
				System.out.println("\n"+str);
				do
				{
					check=false;
					year=validateInteger("Year              : ");
					if(year==-1)
					{
						return "";
					}
					else if(year>2023||year<1)
					{
						System.out.println("Invalid Year");
						check=true;
					}
				}while(check);
				do
				{
					check=false;
					month=validateInteger("Month             : ");
					if(month==-1)
					{
						return "";
					}
					else if(month>12||month<1)
					{
						System.out.println("Invalid Month");
						check=true;
					}
				}while(check);
				do
				{	
					check=false;
					day=validateInteger("Date              : ");
					if(day==-1)
					{
						return "";
					}
					if(day>31||day<1)
					{
						System.out.println("Invalid Date");
						check=true;
					}
				}while(check);
				String date=year+"-"+String.format("%02d",month)+"-"+String.format("%02d",day);
				LocalDate.parse(date);
				return date;
			}
			catch (DateTimeParseException e) 
			{
				input.nextLine();
				System.out.println("\n\t\tThe Given Input Is Not A Date\n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				if(s.equals("-1"))
				{
					flag=false;
				}
				else
				{
					flag=true;
				}
			}
		}while(flag);
		return returndate;
     	}
     	
     	
     	
     	
     	
     	String getNewPassword()
	{
		String password; 
		boolean check;
		do
		{
			check=false;
			System.out.println("\nNew Password");
			char[] passwordArray = System.console().readPassword();
			password = new String(passwordArray);
			if(checkPassword(password))
			{
				System.out.println("\nConfirm Password");
				char[] copasswordArray = System.console().readPassword();
				String copassword = new String(copasswordArray);
				if(!(password.equals(copassword)))
				{
					System.out.println("\nPassword Mismatch : Password That You Entered and Confirm Password Should Be Same");
					System.out.println("\n\tPress Any Key To Re Enter Password Or Press -1 to Go Back");
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
						   "\t\t Uppercase\n"+
						   "\t\t1 Lowercase\n"+
						   "\t\t1 Special Character\n"+
						   "\t\t1 Number\n");
				System.out.println("\n\tPress Any Key To Re Enter Password Or Press -1 to Go Back");
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
     	
     	
     	
     	
     	
     	
     	public boolean checkPassword(String password) 
     	{
		String upper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower="abcdefghijklmnopqrstuvwxyz";
		String special="!@#$%^&*()-+";
		String number="0123456789";
		boolean up=false,low=false,spec=false,num=false,adj=true;
		if(password.length()>=8&&password.length()<=15)
		{
			for(int i=0;i<password.length();i++)
			{
				if(upper.contains(Character.toString(password.charAt(i))))
				{
					up=true;
				}
				if(lower.contains(Character.toString(password.charAt(i))))
				{
					low=true;
				}
				if(special.contains(Character.toString(password.charAt(i))))
				{
					spec=true;
				}
				if(number.contains(Character.toString(password.charAt(i))))
				{
					num=true;
				}
			}
			for(int j=1;j<password.length();j++)
			{
				if(password.charAt(j-1)==password.charAt(j))
				{
					adj=false;
					break;
				}
			}
			if(up==true&&low==true&&spec==true&&num==true&&adj==true)
			{
			   	return true;
			}
		}
		else
		{
			System.out.println("\n\tThe Password Must Conatain Atleast 8 and Atmost 15 Characters..."); 
		}
		return false;
	}
	
	
	
	
	
	String getAddress()
	{
		String address="";
		System.out.println("\nAddress ");
		boolean check=false;
		input.nextLine();
		System.out.print("\nHouse/Door No.             : ");
		String door=getDoor();
		if(door.length()==0)
		{
			return address;
		}
		String street=validateString("Street/Area Name : ");
		if(street.length()==0)
		{
			return address;
		}
		String city=validateString("City/Village Name  : ");
		if(city.length()==0)
		{
			return address;
		}
		String state=validateString("State             : ");
		if(city.length()==0)
		{
			return address;
		}
		int pincode=validatePincode("Pincode           : ");
		if(pincode==-1)
		{
			return address;
		}
		address=door+","+street+","+city+","+state+","+pincode;
		return address;
	}
	
	
	
	
	String getDoor()
	{
		String returnstr="";
		boolean flag;
		do
		{
			flag=false;
			try
			{
				String inputstr=input.nextLine();
				if(inputstr.length()==0)
				{
					throw new InputMismatchException();
				}
				char[] letters=inputstr.toCharArray();
				for(int i=0;i<letters.length;i++)
				{
					if(!((letters[i]>='0'&&letters[i]<='9')||letters[i]<='/'))
					{
						throw new InputMismatchException();
					}
				}
				return inputstr;
			}
			catch(InputMismatchException e)
			{
				System.out.println("\n\t\tThe Given Input Is Invalid \n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				input.nextLine();
				if(s.equals("-1"))
				{
					flag=false;
				}
				else
				{
					flag=true;
				}
			}
			
		}while(flag);
		return returnstr;
	
	}
	
	
	
	int validatePincode(String str)
	{
		int num=-1;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n"+str);
				num=input.nextInt();
				if(!(num>=600001&&num<=643253))
				{
					throw new InputMismatchException();
				}
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\nInvalid Pincode\n");
				System.out.print("\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
				if(s.equals("-1"))
				{
					flag=false;
				}
				else
				{
					flag=true;
				}
			}
		}while(flag);
		return num;
	}
	
	
	
	
	
	boolean validateCaptcha()
	{
		String captcha=createCaptcha();
		System.out.println("\n\t\t     |-------|");
		System.out.print("Enter This Captcha :   ");
		System.out.println(captcha);
		System.out.print("\t\t     |-------|");
		System.out.print("\n");
		String inputstr=input.next();
		if(inputstr.equals(captcha))
		{
			return true;
		}
		return false;
	}
		
	
	
	
	
	
	String createCaptcha()
	{
		String captcha="";
		int i=0;
		while(i<5)
		{
			byte random=(byte)(Math.random()*125);
			if(random>=48&&random<=57||random>=65&&random<=90||random>=97&&random<=122)
			{
				char character=(char)random;
				captcha+=character;
				i++;
			}
		}
		return captcha;
	}	
			
			
			
	
	
	String getCurrentDate()
	{
		LocalDateTime dt = LocalDateTime.now();
		DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = dt.format(ft);
		return date;
	}
	
	
	
	
	
	byte getYears(String day1,String day2)
	{
		byte years=0;
		LocalDate dateBefore = LocalDate.parse(day1);
		LocalDate dateAfter = LocalDate.parse(day2);
		years= (byte)ChronoUnit.YEARS.between(dateBefore, dateAfter);
		return years;
	}
	
}
     	
     	
     	
     	
     	
     

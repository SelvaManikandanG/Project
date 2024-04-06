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
	
	
	
	int validateInteger(String str)
	{
		int num=-1;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n\t\t  "+str);
				num=input.nextInt();
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\n\t\tThe Given Input Is Not A Number\n\n");
				System.out.print("\n\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
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
	
		
	
	
	
	long validateLong(String str)
	{
		long num=-1l;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n\t\t\t"+str);
				num=input.nextLong();
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\n\t\tThe Given Input Is Not A Number\n\n");
				System.out.print("\n\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
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
				System.out.println("\n\t\t\t"+str);
				System.out.print("\n\t\t\t");
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
				System.out.println("\n\t\tThe Given Input Is Not A Number\n\n");
				System.out.print("\n\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
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
	
	
	
	
	
	double validateDouble(String str)
	{
		double num=-1;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n\t\t"+str);
				num=input.nextDouble();
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\n\t\tThe Given Input Is Not An Amount\n\n");
				System.out.print("\n\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
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
				input.nextLine();
				System.out.println("\n\t\t\t"+str);
				System.out.print("\n\t\t\t");
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
				System.out.println("\n\t\tThe Given Input Is Invalid \n\n");
				System.out.print("\n\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
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
	
	
	
	
	
	String validateStrAndNo(String str)
	{
		String returnstr="";
		boolean flag;
		do
		{
			flag=false;
			try
			{
				
				System.out.print("\n\t\t"+str);
				String inputstr=input.nextLine();
				char[] letters=inputstr.toCharArray();
				for(int i=0;i<letters.length;i++)
				{
					if(!((letters[i]>='A'&&letters[i]<='Z')||(letters[i]>='a'&&letters[i]<='z')||(letters[i]==' ')||(letters[i]>=0&&letters[i]<=9)))
					{
						throw new NotALetterException("There Are Some Characters Other Than The Letters");
					}
				}
				return inputstr;
			}
			catch(NotALetterException e)
			{
				System.out.println("\n\t\tThe Given Input Is Invalid \n\n");
				System.out.print("\n\tPress -1 to go back... Or Press Any Key To Re-Enter..... ");
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
				
				System.out.print("\n\t\t"+str);
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
				System.out.println("\n\t\tThe Given Input Is Invalid \n\n");
				System.out.print("\n\tPress 0 to go back... Or Press Any Key To Re-Enter..... ");
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
		String date="";
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n\t\t"+str);
				date=input.next();
				LocalDate.parse(date);
				return date;
			}
			catch (DateTimeParseException e) 
			{
				input.nextLine();
				System.out.println("\n\t\tThe Given Input Is Not A Date\n\n");
				System.out.print("\n\tPress 0 to go back... Or Press Any Key To Re-Enter..... ");
				String s=input.next();
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
		return date;
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
	
	
	
	
	
	boolean validateCaptcha()
	{
		String captcha=createCaptcha();
		System.out.print("\n\t\tEnter This Captcha : ");
		System.out.println(captcha);
		System.out.print("\n\t\t\t");
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
			if(random>33)
			{
				char character=(char)random;
				captcha+=character;
				i++;
			}
		}
		return captcha;
	}
	
	
	
	
	String getAddress()
	{
		String address="";
		boolean check=false;
		do
		{
			input.nextLine();
			System.out.println("\n\t\t Address (Door No.,City,District,State,pincode)");
			System.out.print("\n\t\t"); 
			String inputaddress=input.nextLine();
			if(!(inputaddress.length()<10)&&inputaddress.contains(","))
			{
				return inputaddress;
			}
			else
			{
				System.out.println("\n\t\t\tInvalid Address");
				System.out.println("\n\t\tPress any key to re-enter the address or Press -1 to go back");
				String option =input.next();
				if(option.equals("-1"))
				{
					return "";
				}
				check=true;
			}
		}
		while(check);
		return address;
	}
			
			
			
			
	
	
	String getCurrentDate()
	{
		LocalDateTime dt = LocalDateTime.now();
		DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = dt.format(ft);
		return date;
	}
	
	
	
	
	
	int getDays(String day1,String day2)
	{
		int days=0;
		LocalDate dateBefore = LocalDate.parse(day1);
		LocalDate dateAfter = LocalDate.parse(day2);
		days= (int)ChronoUnit.DAYS.between(dateBefore, dateAfter);
		return days;
	}
	
}
     	
     	
     	
     	
     	
     

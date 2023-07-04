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
		int num=0;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n\t\t"+str);
				num=input.nextInt();
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\n\t\tThe Given Input Is Not A Number\n\n");
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
		return num;
	}
	
		
	
	
	
	long validateLong(String str)
	{
		long num=0l;
		boolean flag;
		do
		{
			flag=false;
			try
			{
				System.out.print("\n\t\t"+str);
				num=input.nextLong();
				return num;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.println("\n\t\tThe Given Input Is Not A Number\n\n");
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
				
				System.out.print("\n\t\t"+str);
				String inputstr=input.nextLine();
				char[] letters=inputstr.toCharArray();
				for(int i=0;i<letters.length;i++)
				{
					if(!((letters[i]>='A'&&letters[i]<='Z')||(letters[i]>='a'&&letters[i]<='z')||(letters[i]==' ')))
					{
						throw new NotALetterException("There Are Some Characters Other Than The Letters");
					}
				}
				return inputstr;
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
		return returnstr;
	
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
		
}

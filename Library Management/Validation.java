import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;  

class Validation
{


	DBConnection db=null;
	Connection con=null;
	Statement st=null;
	
	
	
	


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


	

	boolean isMemberIdValid(int member_id)
	{
		boolean isValid=false;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs1=st.executeQuery("Select * from member where member_id='"+member_id+"';");
			if(rs1.next())
			{
				isValid=true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return isValid;
	}
	
	
	
	
	
	boolean isBookIdValid(int book_id)
	{
		boolean isValid=false;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs1=st.executeQuery("Select * from book_details where id='"+book_id+"';");
			if(rs1.next())
			{
				isValid=true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return isValid;
	}
	
	
	
	
	
}		
			

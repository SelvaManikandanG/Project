import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class BookAdding
{


	DBConnection db=null;
	Connection con=null;
	Statement st=null;
	PreparedStatement ps=null;
	InputValidations inputvalid=new InputValidations();
		
		
		
		
	public void addBksDetails(String book_name,String author_name,String genre_type,int year_of_public,int availability)
	{
		int book_id=insertBkId(book_name,year_of_public);
		int author_id=insertAuthId(author_name);
		int genre_id=insertGenId(genre_type);
		insertIds(book_id,author_id,genre_id,availability);
	}
	
	
	
	
	
	
	public int insertBkId(String book_name,int year_of_public)
	{
		int book_id=0;
		boolean check=true;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			//String query1="Select book_id from book where upper(book_name) like upper('"+book_name+"%') and  year_of_publication="+year_of_public+";";
			ResultSet rs1=st.executeQuery("Select book_id from book where upper(book_name) like upper('"+book_name+"%') and  year_of_publication="+year_of_public+";");
			if(rs1.next())		
			{
				book_id=rs1.getInt(1);
				System.out.println(book_name+" Book Already Exists...");
				check=false;
			}
			if(check)
			{
				String query2="Select book_id from book where upper(book_name)=upper('"+book_name+"')";
				ResultSet rs2=st.executeQuery(query2);
				if(rs2.next())
				{
					book_id=insertBookEdition(book_name,year_of_public);
					System.out.println("Book Added Successfully...");
				}
				else
				{
					book_id=insertBook(book_name,year_of_public);
					System.out.println("Book Added Successfully...");
				}
			}
		}
		catch(Exception e)	
		{
			e.printStackTrace();
		}
		return book_id;
	}
	
	
	
	
	
	
	
	public int insertAuthId(String author_name)
	{		
		int author_id=0;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			String query3="Select author_id from author where upper(author_name)=upper('"+author_name+"');";
			ResultSet rs3=st.executeQuery(query3);
			if(rs3.next())
			{
				author_id=rs3.getInt(1);
			}
			else
			{
				author_id=insertAuthor(author_name);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return author_id;
	}
	
	
	
	
	
	public int insertGenId(String genre_type)
	{		
		int genre_id=0;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			String query3="Select genre_id from genre where upper(genre_type)=upper('"+genre_type+"');";
			ResultSet rs3=st.executeQuery(query3);
			if(rs3.next())
			{
				genre_id=rs3.getInt(1);
			}
			else
			{
				genre_id=insertGenre(genre_type);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return genre_id;
	}
	
	
	
	
	
	
	public void insertIds(int book_id,int author_id,int genre_id,int availability)
	{
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs1=st.executeQuery("Select id from book_details where book_id="+book_id+" and author_id="+author_id+" and genre_id="+genre_id);
			if(rs1.next()==false)
			{
				PreparedStatement ps1=con.prepareStatement("insert into book_details (book_id,author_id,genre_id,availability)values(?,?,?,?);");
				ps1.setInt(1,book_id);
				ps1.setInt(2,author_id);
				ps1.setInt(3,genre_id);
				ps1.setInt(4,availability);
				ps1.executeUpdate();
			}
			else
			{
				int id=rs1.getInt(1);
				increaseCount(id);
				return;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	void increaseCount(int id)
	{
		String str1="Do you want to increase the No. of Books?... Press 1 to increase the count";
		Scanner input=new Scanner(System.in);
		int opt=inputvalid.validateInteger(str1);
		if(opt==1)
		{
			String str2="How Many Book Counts Do You Want To Increase?";
			int no=inputvalid.validateInteger(str2);
			if(no==0)
			{
				return;
			}
			try
			{	
				db=DBConnection.getInstance();
				con=db.getConnection();
				PreparedStatement ps1=con.prepareStatement("update book_details set availability=availability+"+no+" where id="+id+";");
				ps1.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			return;
		}
	}
			
	
	
	
	
	
	public int insertBookEdition(String book_name,int year_of_public)
	{
		int book_id=0;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps2=con.prepareStatement("insert into book (book_name,year_of_publication) values (?,?)returning book_id;");
			ps2.setString(1,book_name+"(Edition" +year_of_public+")");
			ps2.setInt(2,year_of_public);
			ResultSet rs4=ps2.executeQuery();
			if(rs4.next())
			book_id=rs4.getInt("book_id");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return book_id;
	}
	
	
	
	
	
	
	public int insertBook(String book_name,int year_of_public)
	{
		int book_id=0;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps3=con.prepareStatement("insert into book (book_name,year_of_publication) values (?,?)returning book_id;");
			ps3.setString(1,book_name);
			ps3.setInt(2,year_of_public);
			ResultSet rs5=ps3.executeQuery();
			if(rs5.next())
			book_id=rs5.getInt("book_id");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return book_id;
	}
	
	
	
	
	
	public int insertAuthor(String author_name)
	{	
		int author_id=0;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps4=con.prepareStatement("insert into author (author_name) values (?)returning author_id;");
			ps4.setString(1,author_name);
			ResultSet rs6=ps4.executeQuery();
			if(rs6.next())
			author_id=rs6.getInt("author_id");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return author_id;
	}
	
	
	
	
	
	public int insertGenre(String genre_type)
	{	
		int genre_id=0;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps5=con.prepareStatement("insert into genre (genre_type) values (?)returning genre_id;");
			ps5.setString(1,genre_type);
			ResultSet rs7=ps5.executeQuery();
			if(rs7.next())
			genre_id=rs7.getInt("genre_id");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return genre_id;
	}
}
	
	
	
	

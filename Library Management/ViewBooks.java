import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ViewBooks
{


	Book bk=null;
	Author auth=null;
	Genre gen=null;
	BookDetails bkdetails=null;
	ArrayList<BookDetails> bkdetailslist=new ArrayList<>();
	ArrayList<Book> booklist=new ArrayList<>();
	ArrayList<Author> authorlist=new ArrayList<>();
	ArrayList<Genre> genrelist=new ArrayList<>();
	DBConnection db=null;
	Connection con=null;
	Statement st=null;
	PreparedStatement ps=null;
	
	
	
	
	
	
	
	public ArrayList getDetailsBk(String bkname)
	{
		try
		{
			String query1="Select book_details.id,book.book_name,author.author_name,genre.genre_type,book.year_of_publication,book_details.availability,book_details.reserved from book join book_details on book.book_id=book_details.book_id join author on book_details.author_id=author.author_id join genre on book_details.genre_id=genre.genre_id where upper(book.book_name) like upper('"+bkname+"%');";
			db=DBConnection.getInstance();
			con=db.getConnection();
			st =con.createStatement();
			ResultSet rs1=st.executeQuery(query1);
			bkdetailslist=insertDetToList(rs1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bkdetailslist;
		
	}
	
	
	
	
	
	
	
	public ArrayList getDetailsAuth(String authname)
	{
		try
		{
			String query1="Select book_details.id,book.book_name,author.author_name,genre.genre_type,book.year_of_publication,book_details.availability,book_details.reserved from book join book_details on book.book_id=book_details.book_id join author on book_details.author_id=author.author_id join genre on book_details.genre_id=genre.genre_id where upper(author.author_name) like upper('"+authname+"');";
			db=DBConnection.getInstance();
			con=db.getConnection();
			st =con.createStatement();
			ResultSet rs2=st.executeQuery(query1);
			bkdetailslist=insertDetToList(rs2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bkdetailslist;
	}
	
	
	
	
	
	
	public ArrayList getDetailsGen(String gentype)
	{
		try
		{
			String query1="Select book_details.id,book.book_name,author.author_name,genre.genre_type,book.year_of_publication,book_details.availability,book_details.reserved from book join book_details on book.book_id=book_details.book_id join author on book_details.author_id=author.author_id join genre on book_details.genre_id=genre.genre_id where upper(genre.genre_type) like upper('"+gentype+"');";
			db=DBConnection.getInstance();
			con=db.getConnection();
			st =con.createStatement();
			ResultSet rs3=st.executeQuery(query1);
			bkdetailslist=insertDetToList(rs3);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bkdetailslist;
	}
	
	
	
	
	
	public ArrayList insertDetToList(ResultSet info)
	{	
		try
		{
			bkdetailslist.clear();
			while(info.next())
			{
				bkdetailslist.add(new BookDetails(info.getInt(1),info.getString(2),info.getString(3),info.getString(4),info.getInt(5),info.getInt(6),info.getInt(7)));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bkdetailslist;
			
	}
	
	
	
	
	
	
	public ArrayList showAllBooks()
	{
		try
		{
			String query1="Select * from book ;";
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs1=st.executeQuery(query1);
			booklist=insertBooksToList(rs1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
	
		}
		return booklist;
	}
	
	
	
	
	public ArrayList insertBooksToList(ResultSet books)
	{	
		try
		{	
			booklist.clear();
			while(books.next())
			{
				booklist.add(new Book(books.getInt(1),books.getString(2),books.getInt(3)));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return booklist;
	}
	
	
	
	
	
	
	public ArrayList showAllAuthors()
	{
		try
		{
			String query1="Select * from author;";
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs2=st.executeQuery(query1);
			authorlist=insertAuthorsToList(rs2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return authorlist;
	}
	
	
	
	
	
	
	public ArrayList insertAuthorsToList(ResultSet authors)
	{	
		try
		{
			authorlist.clear();
			while(authors.next())
			{
				authorlist.add(new Author(authors.getInt(1),authors.getString(2)));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return authorlist;		
	}	
	
	
	
	
	
	
	public ArrayList showAllGenres()
	{
		try
		{
			String query1="Select * from genre;";
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs3=st.executeQuery(query1);
			authorlist=insertGenresToList(rs3);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return genrelist;
	}
	
	
	
	
	
	
	public ArrayList insertGenresToList(ResultSet genres)
	{	
		try
		{
			genrelist.clear();
			while(genres.next())
			{
				genrelist.add(new Genre(genres.getInt(1),genres.getString(2)));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return genrelist;		
	}		 	 

	
		
	
	
	
	
}

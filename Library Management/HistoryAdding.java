import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
class HistoryAdding
{


	DBConnection db=null;
	Connection con=null;
	Statement st=null;
	BorrowHistory brwhis=null;
	ReservingBook resbk=new ReservingBook();
	Validation valid=new Validation();
	ArrayList<BorrowHistory> historylist=null;




	/*int searchBkId(String book_name,String author_name,String genre_type,int year)
	{
		int id=-1;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs1=st.executeQuery("Select * from book_details where availability>reserved");
			if(rs1.next())
			{
				rs1.close();
				ResultSet rs2=st.executeQuery("Select book_details.id from book_details join book on book.book_id=book_details.book_id join author on 			     					book_details.author_id=author.author_id join genre on book_details.genre_id=genre.genre_id where upper(book.book_name) like upper('"+book_name+"%') and 					upper(author.author_name) like upper('"+author_name+"') and upper(genre.genre_type) like upper('"+genre_type+"') and upper(book.year_of_publication) like 				upper('"+year+"') ;");
				if(rs2.next())
				{
					id=rs2.getInt(1);
					rs2.close();
				}
				else
				{
					System.out.println("The Book Is Not Existed...");
					return id;
				}
			}
			else
			{
				System.out.println("The Book Is Temporarily Not Available...");
				return id;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}*/
	
	
	
	
	
	
	void addHistory(int member_id,int book_id)
	{
		try
		{	
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			if(valid.isMemberIdValid(member_id))
			{
				if(valid.isBookIdValid(book_id))
				{
					ResultSet rs3=st.executeQuery("Select * from book_details where availability>reserved and book_id="+book_id+";");
					if(rs3.next())
					{
						rs3.close();
						insertHistory(member_id,book_id);
					}
					else
					{
						System.out.println("\n\tThe Books Are Reserved... You Can Reserve A Book");
						return;
					}
				}
				else
				{
					System.out.println("\n\tThe Book Id is Invalid");
					return;
				}
			}
			else
			{
				System.out.println("\n\tThe Member Id is Invalid");
				return;	
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	void insertHistory(int member_id,int book_id)
	{
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs4=st.executeQuery("Select * from member where books_borrow_limit>=1 and member_id="+member_id+";");
			if(rs4.next())
			{
				rs4.close();
				ResultSet rs5=st.executeQuery("Select * from book_details where availability>0 and book_id="+book_id+";");
				if(rs5.next())
				{
					rs5.close();
					PreparedStatement ps1=con.prepareStatement("insert into borrow_history(member_id,id,borrow_date)values("+member_id+","+book_id+",now());");
					ps1.executeUpdate();
					reduceBkCount(book_id);
					reduceMemCount(member_id);	
					System.out.println("\n\tThe Borrow History Is Updated Successfully...");
					System.out.println("===========================================================================================================================================================================");
		System.out.println(String.format("%-20s%-27s%-40s%-20s%-25s%-25s%-25s","History ID","Member ID","Member Name","Book ID","Borrow Date","Due Date","Return Date"));
		System.out.println("===========================================================================================================================================================================");
					ResultSet rs6=st.executeQuery("SELECT bh.history_id, bh.member_id, m.member_name, bh.id, bh.borrow_date, bh.due_date, bh.return_date FROM borrow_history bh JOIN member m ON bh.member_id = m.member_id where bh.member_id="+member_id+" and bh.id="+book_id+";");
					historylist=new ArrayList<>();
					insertToList(rs6);
					System.out.println(historylist);
					System.out.println("===========================================================================================================================================================================");
				}
				else
				{
					System.out.println("\n\tThe Book Is Temporarily Not Available...");
					return;
				}
			}
			else
			{
				System.out.println("\n\tThe Member Id "+member_id+" Has Already Borrowed 5 Books...");
				return;
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	void reduceBkCount(int id)
	{
		try
		{	
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps1=con.prepareStatement("update book_details set availability=availability-1 where id="+id+";");
			ps1.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	void increaseBkCount(int id)
	{
		try
		{	
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps1=con.prepareStatement("update book_details set availability=availability+1 where id="+id+";");
			ps1.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	void reduceMemCount(int id)
	{
		try
		{	
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps2=con.prepareStatement("update member set books_borrow_limit=books_borrow_limit-1 where member_id="+id+";");
			ps2.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	void increaseMemCount(int id)
	{
		try
		{	
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps3=con.prepareStatement("update member set books_borrow_limit=books_borrow_limit+1 where member_id="+id+";");
			ps3.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	void setReturnDate(int memberid,int bookid,String date)
	{
		int rows=0;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			if(valid.isMemberIdValid(memberid))
			{
				if(valid.isBookIdValid(bookid))
				{
					ResultSet rs1=st.executeQuery("Select * from borrow_history where member_id="+memberid+" and id="+bookid+";");
					if(rs1.next())
					{
						PreparedStatement ps3=con.prepareStatement("update borrow_history set return_date='"+date+"' where member_id="+memberid+" and id="+bookid+" and return_date is null;");	
						rows=ps3.executeUpdate();
						if(rows>0)
						{
							increaseBkCount(bookid);
							increaseMemCount(memberid);
							resbk.setReserveDate(bookid,date);
							System.out.println("The Book Returning Is Updated Successfully...");
							System.out.println("===========================================================================================================================================================================");
		System.out.println(String.format("%-20s%-27s%-40s%-20s%-25s%-25s%-25s","History ID","Member ID","Member Name","Book ID","Borrow Date","Due Date","Return Date"));
		System.out.println("===========================================================================================================================================================================");
							System.out.println(displayMemHistory(memberid));
							System.out.println("===========================================================================================================================================================================");	
						}
						else
						{
							System.out.println("\n\tThe Member With The Id "+memberid+" Has Already Returned The Book Of ID "+bookid);	
						}
					}	
					else	
					{
						System.out.println("\n\tThere Is No Borrowed History With This Member ID and Book ID");
					}
				}
				else
				{
					System.out.println("\n\tThe Book Id is Invalid");
					return;
				}
			}
			else
			{
				System.out.println("\n\tThe Member Id is Invalid");
				return;	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	ArrayList<BorrowHistory> displayHistory()
	{
		historylist=new ArrayList<>();
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT bh.history_id, bh.member_id, m.member_name, bh.id, bh.borrow_date, bh.due_date, bh.return_date FROM borrow_history bh JOIN member m ON bh.member_id = m.member_id;");
			insertToList(rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return historylist;
	}
	
	
	
	
	
	ArrayList<BorrowHistory> displayMemHistory(int id)		
	{
		historylist=new ArrayList<>();
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT bh.history_id, bh.member_id, m.member_name, bh.id, bh.borrow_date, bh.due_date, bh.return_date FROM borrow_history bh JOIN member m ON bh.member_id = m.member_id where m.member_id="+id+";");
			insertToList(rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return historylist;
	}	
			
			
			
			
			
	void insertToList(ResultSet history) 
	{
		try
		{
			historylist.clear();
			while(history.next())
			{
				historylist.add(new BorrowHistory (history.getInt(1), history.getInt(2), history.getString(3), history.getInt(4), history.getString(5), history.getString(6), history.getString(7)));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
}		 	
	
	
	
	
	
	 
			
		
				
				
		
		
		
		
			
			
			
			
		

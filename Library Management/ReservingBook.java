import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
class ReservingBook
{


	DBConnection db=null;
	Connection con=null;
	Statement st=null;
	Reservations res=null;
	Validation valid=new Validation();
	ArrayList<Reservations> reservelist=null;
	
	
	
	
	void setReserveDate(int book_id,String date)
	{
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps=con.prepareStatement("update reservations set date='"+date+"' where id="+book_id+";");
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	void addReservation(int member_id,int book_id)
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
					ResultSet rs1=st.executeQuery("Select * from book_details where availability>reserved  and id="+book_id+";");
					if(!rs1.next())
					{
						rs1.close();
						if(!isReserved(member_id,book_id))
						{
							PreparedStatement ps=con.prepareStatement("insert into reservations (member_id,id) values("+member_id+","+book_id+");");
							ps.executeUpdate();
							System.out.println("\n\tThe Book Is Reserved Successfully...");
						}
						else
						{
							System.out.println("\n\tThis Member Is Already Reserved The Same Book");
						
						}
					}
					else
					{
						System.out.println("\n\tThis Book Is Currently Available... Please Search The Book Properly.");
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
	
	
	
	
	
	boolean isReserved(int member_id,int book_id)
	{
		boolean reserved=false;
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs1=st.executeQuery("Select * from Reservations where member_id="+member_id+" and id="+book_id+" and borrowed is null;");
			if(rs1.next())
			{	
				reserved=true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return reserved;
	}

	
	
	
	
	
	void reduceResBkCount(int id)
	{
		try
		{	
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps1=con.prepareStatement("update book_details set reserved=reserved-1 where id="+id+";");
			ps1.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	void increaseResBkCount(int id)
	{
		try
		{	
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps1=con.prepareStatement("update book_details set reserved=reserved+1 where id="+id+";");
			ps1.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	void updateReserveStatusTrue(int member_id,int book_id)
	{
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps1=con.prepareStatement("update reservations set borrowed='Yes' where member_id="+member_id+" and id="+book_id+" and borrowed is null;");
			ps1.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	void updateReserveStatusFalse(int member_id,int book_id)
	{
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			PreparedStatement ps1=con.prepareStatement("update reservations set borrowed='No' where member_id="+member_id+" and id="+book_id+" and borrowed is null;");
			ps1.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	ArrayList<Reservations> displayReservations()
	{
		reservelist=new ArrayList<>();
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT r.reservation_id, r.member_id, m.member_name, r.id, r.date, r.borrowed FROM reservations r JOIN member m ON r.member_id = m.member_id;");
			insertToList(rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return reservelist;
	}	
	
	
	
	
	
	
	ArrayList<Reservations> displayMemberReservations(int id)
	{
		reservelist=new ArrayList<>();
		try
		{
			db=DBConnection.getInstance();
			con=db.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT r.reservation_id, r.member_id, m.member_name, r.id, r.date, r.borrowed FROM reservations r JOIN member m ON r.member_id = m.member_id where m.member_id="+id+";");
			insertToList(rs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return reservelist;
	}	
	
	
	
	
	
	
	Reservations insertToList(ResultSet reservations) 
	{
		try
		{
			reservelist.clear();
			while(reservations.next())
			{
				res=new Reservations (reservations.getInt(1), reservations.getInt(2), reservations.getString(3), reservations.getInt(4), reservations.getString(5), reservations.getBoolean(6));
				reservelist.add(res);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	
	void checkReservedLimit()
	{
		displayReservations();
		Iterator itr=reservelist.iterator();
		while(itr.hasNext())
		{
			res=(Reservations) itr.next();
			if(res.getDate()!=null)
			{
				String current_date=valid.getCurrentDate();	
				int days=valid.getDays(current_date,res.getDate());
				if(days>1)
				{
					updateReserveStatusFalse(res.getMemberId(),res.getBookId());
				}
			}
		}			
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
				
				

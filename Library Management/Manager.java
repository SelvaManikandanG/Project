import java.util.Scanner;
import java.util.ArrayList;
public class Manager
{

	
	Scanner input=new Scanner(System.in);
	BookAdding bkadd=new BookAdding();
	ViewBooks viewbk=new ViewBooks();
	MemberAdding memadd=new MemberAdding();
	HistoryAdding hisadd=new HistoryAdding();
	ReservingBook resbk=new ReservingBook();
	ViewMembers viewmem=new ViewMembers();
	Validation valid=new Validation();
	InputValidations inputvalid=new InputValidations();
	BorrowHistory brwhis=null;
	Member member=null;
	


	
	
	
	void addBooks()
	{
		boolean flag1,flag2=false;
		int year_of_public,availability;
		String str1="Enter The Book Name : ";
		String book_name=inputvalid.validateString(str1);
		if(book_name.length()==0)
		{
			return;
		}
		String str2="Enter The Author Name : ";
		String author_name=inputvalid.validateString(str2);
		if(author_name.length()==0)
		{
			return;
		}
		String str3="Enter The Genre Type: ";
		String genre_type=inputvalid.validateString(str3);
		if(genre_type.length()==0)
		{
			return;
		}
		do
		{
			flag1=false;
			String str="Enter The No. of Available Books : ";
			availability=inputvalid.validateInteger(str);
			if(availability<1)
			{
				System.out.println("\n\tThe Number Of Books Must Be Atleast 1");
				flag1=true;
				System.out.println("\n\tPress Any Key To Re-Enter The Number Of Available Books Or Press 0 To Go Back!...");
				String backoption=input.next();
				if(backoption.equals("0"))
				{
					return;
				}
			}
		}while(flag2);
		do
		{
			flag2=false;
			String str="Enter The Year Of Publication : ";
			year_of_public=inputvalid.validateInteger(str);
			if(year_of_public<1||year_of_public>2023)
			{
				System.out.println("\n\tThe Given Year Is Invalid...");
				flag2=true;
				System.out.println("\n\tPress Any Number Key to Re-Enter the Year Of Publication Or Press 0 to Go Back!...");
				String backoption=input.next();
				if(backoption.equals("0"))
				{
					return;
				}
			}
		}while(flag2);
		bkadd.addBksDetails(book_name,author_name,genre_type,year_of_public,availability);
	}	
	
	

	
	
	
	void searchByBooks()
	{
		String str="Enter The Book Name To View The Books :";
		String book_name=inputvalid.validateString(str);
		if(book_name.length()==0)
		{
			return;
		}
		ArrayList<BookDetails> bookdetails=viewbk.getDetailsBk(book_name);
		if(bookdetails.size()==0)
		{
			System.out.println("The Book "+book_name+" Is Not Existed");
		}
		else
		{
			System.out.println("==========================================================================================================================================");
			System.out.println(String.format("%-7s%-30s%-30s%-30s%-10s%-20s%-10s","ID","Book Name","Author Name","Genre Type","Year","Availability","Reserved"));
			System.out.println("==========================================================================================================================================");
			System.out.print(bookdetails);
		}
		
	}
	
	
	
	
	
	void searchByAuthors()
	{
		String str="Enter The Author Name To View The Books :";
		String author_name=inputvalid.validateString(str);
		if(author_name.length()==0)
		{
			return;
		}
		ArrayList<BookDetails> bookdetails=viewbk.getDetailsAuth(author_name);
		if(bookdetails.size()==0)
		{
			System.out.println("The Author "+author_name+" Is Not Existed");
		}
		else
		{
			System.out.println("=============================================================================================================================");
			System.out.println(String.format("%-7s%-30s%-30s%-30s%-10s%-20s%-10s","ID","Book Name","Author Name","Genre Type","Year","Availability","Reserved"));
			System.out.println("=============================================================================================================================");
			System.out.print(bookdetails);
		}
	}
	
	
	
	
	
	
	void searchByGenre()
	{
		String str="Enter The Genre Type To View The Books :";
		String genre_type=inputvalid.validateString(str);
		if(genre_type.length()==0)
		{
			return;
		}
		ArrayList<BookDetails> bookdetails=viewbk.getDetailsGen(genre_type);
		if(bookdetails.size()==0)
		{
			System.out.println("The Author "+genre_type+" Is Not Existed");
		}
		else
		{
			System.out.println("==========================================================================================================================================");
			System.out.println(String.format("%-7s%-30s%-30s%-30s%-20s%-20s%-10s","ID","Book Name","Author Name","Genre Type","Year","Availability","Reserved"));
			System.out.println("==========================================================================================================================================");
			System.out.print(bookdetails);
		}
	}
	
	
	
	
	
	void viewAllBooks()
	{
		ArrayList<Book> booklist=viewbk.showAllBooks();
		System.out.println("=====================================================================================");
		System.out.println(String.format("%-20s%-40s%-25s","Book ID","Book Name","Year"));
		System.out.println("=====================================================================================");
		System.out.print(booklist);
		
	}
	
	
	
	
	
	void viewAllAuthors()
	{
		ArrayList<Author> authorlist=viewbk.showAllAuthors();
		System.out.println("=============================================================");
		System.out.println(String.format("%-20s%-40s","Author ID","Author Name"));
		System.out.println("=============================================================");
		System.out.print(authorlist);
		
		
	}
	
	
	
	
		
	void viewAllGenres()
	{
		ArrayList<Genre> genrelist=viewbk.showAllGenres();
		System.out.println("=============================================================");
		System.out.println(String.format("%-20s%-40s","Genre ID","Genre Name"));
		System.out.println("=============================================================");
		System.out.print(genrelist);
	}
	
	
	
	
	
	void addMember()
	{
		boolean flag1,flag2;
		long mobile_no;
		String mail_id;
		String str1="Enter The Member Name :";
		String member_name=inputvalid.validateString(str1);
		if(member_name.length()==0)
		{
			return;
		}
		do
		{	
			flag1=false;
			String str2="Enter The Member's Mobile Number :";
			mobile_no=inputvalid.validateLong(str2);
			if(!(mobile_no>6000000000l&&mobile_no<=9999999999l))
			{
				System.out.println("\n\tThe Given Mobile Number Is Invalid...");
				flag1=true;
				System.out.println("\n\tPress Any Key To Re_Enter The Mobile Number Or Press 0 To Go Back!...");
				String option=input.nextLine();
				if(option.equals("0"))
				{
					return;
				}
			}
		}while(flag1); 	
		do
		{	
			flag2=false;
			System.out.println("Enter The Mail ID :");
			input.nextLine();
			mail_id=input.nextLine();
			if(!(mail_id.contains("@gmail.com")||mail_id.contains("@yahoo.com")||mail_id.contains("@outlook.com")&&mail_id.contains("@zoho.com")))
			{
				System.out.println("\n\tThe Given Mail Id Is Invalid...");
				flag2=true;
				System.out.println("\n\tPress Any Number Key To Re_Enter The Mail ID Or Press 0 To Go Back!...");
				int option=input.nextInt();
				if(option==0)
				{
					return;
				}
			}
		}while(flag2); 
		memadd.addMemberDetails(member_name,mobile_no,mail_id);
	}
	
	
	
	
	
	void viewMembers()
	{
		ArrayList<Member> memberlist=viewmem.showMembers();
		System.out.println("===================================================================================================================================================");
		System.out.println(String.format("%-15s%-35s%-30s%-40s%-25s","Member ID","Member Name","Mobile No.","Mail ID","Book Limit"));
		System.out.println("===================================================================================================================================================");
		System.out.print(memberlist);
	}
		
		
	
	
	
	
	
	
	void borrowBook()
	{
		//int year_of_public;
		//boolean flag2;
		String str1="Enter The Member Id: ";
		int member_id=inputvalid.validateInteger(str1);
		if(member_id==0)
		{
			return;
		}
		String str2="Enter The Book Id: ";
		int book_id=inputvalid.validateInteger(str2);
		if(book_id==0)
		{
			return;
		}
		hisadd.addHistory(member_id,book_id);
		/*System.out.println("Enter The Book Name: ");
		input.nextLine();
		String book_name=input.nextLine();
		System.out.println("Enter The Author Name: ");
		String author_name=input.nextLine();
		System.out.println("Enter The Genre Name: ");
		String genre_name=input.nextLine();
		do
		{
			flag2=false;
			System.out.println("Enter The Year Of Publication : ");
			year_of_public=input.nextInt();
			if(year_of_public<1||year_of_public>2023)
			{
				System.out.println("The Given Year Is Invalid...");
				flag2=true;
				System.out.println("Press Any Number Key to Re-Enter the Year Of Publication Or Press 0 to Go Back!...");
				int backoption=input.nextInt();
				if(backoption==0)
				{
					return;
				}
			}
		}while(flag2);
		int book_id=hisadd.searchBkId(book_name,author_name,genre_name,year_of_public);
		if(book_id!=-1)
		{
			hisadd.addHistory(member_id,book_id);
		}*/
		
	}
		
		
		
		
		
	void returnBook()
	{
		String str1="Enter The Member Id: ";
		int member_id=inputvalid.validateInteger(str1);
		if(member_id==0)
		{
			return;
		}
		String str2="Enter The ID Of A Book That A Member Is Borrowing: ";
		int book_id=inputvalid.validateInteger(str2);
		if(book_id==0)
		{
			return;
		}
		String str3="Enter A Book Returning Date(as yyyy-MM-dd): ";
		String date=inputvalid.validateDate(str3);
		if(date.length()==0)
		{
			return;
		}
		hisadd.setReturnDate(member_id,book_id,date);
		
		
	}
	
	
	
	
	
	void borrowReservedBook()
	{
		String str1="Enter The Member Id: ";
		int member_id=inputvalid.validateInteger(str1);
		if(member_id==0)
		{
			return;
		}
		String str2="Enter The ID Of A Book That A Member Was Reserved: ";
		int book_id=inputvalid.validateInteger(str2);
		if(book_id==0)
		{
			return;
		}
		if(valid.isMemberIdValid(member_id))
		{
			if(valid.isBookIdValid(book_id))
			{
				if(resbk.isReserved(member_id,book_id))
				{
					hisadd.insertHistory(member_id,book_id);
					resbk.reduceResBkCount(book_id);
					resbk.updateReserveStatusTrue(member_id,book_id);
					
				}
				else
				{
					System.out.println("\n\tThere Is No Reservation For This Book By This Member ID"); 
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
		
		
		
		
		
		
		
	
	void showBorrowHistory()
	{
		ArrayList<BorrowHistory> historylist=hisadd.displayHistory();
		System.out.println("===========================================================================================================================================================================");
		System.out.println(String.format("%-20s%-27s%-40s%-20s%-25s%-25s%-25s","History ID","Member ID","Member Name","Book ID","Borrow Date","Due Date","Return Date"));
		System.out.println("===========================================================================================================================================================================");
		System.out.print(historylist);
	}
	
	
	
	
	
	void showMemberHistory()
	{
		String str="Enter The Member Id: ";
		int member_id=inputvalid.validateInteger(str);
		if(member_id==0)
		{
			return;
		}
		if(valid.isMemberIdValid(member_id))
		{
			ArrayList<BorrowHistory> historylist=hisadd.displayMemHistory(member_id);
			System.out.println("===========================================================================================================================================================================");
		System.out.println(String.format("%-20s%-27s%-40s%-20s%-25s%-25s%-25s","History ID","Member ID","Member Name","Book ID","Borrow Date","Due Date","Return Date"));
		System.out.println("===========================================================================================================================================================================");
			System.out.print(historylist);
		}
		else
		{
			System.out.println("\n\tThe Member Id is Invalid");
			return;	
		}	
	}
	
	
	
	
	void reserveBook()
	{
		String str1="Enter The Member Id: ";
		int member_id=inputvalid.validateInteger(str1);
		if(member_id==0)
		{
			return;
		}
		String str2="Enter The Book Id: ";
		int book_id=inputvalid.validateInteger(str2);
		if(book_id==0)
		{
			return;
		}
		resbk.addReservation(member_id,book_id);
		resbk.increaseResBkCount(book_id);
	}
	
	
	
	
	
	
	void showReservations()
	{
		ArrayList<Reservations> reservelist=resbk.displayReservations();
		System.out.println("=====================================================================================================================================================");
		System.out.println(String.format("%-20s%-25s%-35s%-20s%-30s%-20s","Reservation ID","Member ID","Member Name","Book ID","Book Available Date","Has Borrowed"));
		System.out.println("=====================================================================================================================================================");
		System.out.print(reservelist);
	}
	
	
	
	
	void showMemberReservations()
	{
		String str1="Enter The Member Id: ";
		int member_id=inputvalid.validateInteger(str1);
		if(member_id==0)
		{
			return;
		}
		if(valid.isMemberIdValid(member_id))
		{
			ArrayList<Reservations> reservelist=resbk.displayMemberReservations(member_id);
			System.out.println("=====================================================================================================================================================");
			System.out.println(String.format("%-20s%-25s%-35s%-20s%-30s%-20s","Reservation ID","Member ID","Member Name","Book ID","Book Available Date","Has Borrowed"));
			System.out.println("=====================================================================================================================================================");
			System.out.print(reservelist);
		}
		else
		{
			System.out.println("The Member Id is Invalid");
			return;	
		}	
	}
																						
			
		
}
	
		
		
		
	
	
			
			
			

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class LibraryManagement
{
	Scanner input=new Scanner(System.in);
	Manager man=new Manager();
	BookAdding bkadd=new BookAdding();
	ViewBooks viewbk=new ViewBooks();
	InputValidations inputvalid=new InputValidations();	
	DBConnection db=DBConnection.getInstance();
	



	public static void main(String[] args)
	{
		LibraryManagement libman=new LibraryManagement();
		ReservingBook resbk=new ReservingBook();
		resbk.checkReservedLimit();
		libman.viewMenu();
	}
	
	
	
	
	
	
	void viewMenu()
	{
		
		boolean check=true;
		do
		{
			System.out.println("\n\t|====================================================|");
			System.out.println("\t\t\t     MENU OPTION");
			System.out.println("\t|====================================================|");
			System.out.println("\n\n\t|\t 1. Add Books                                |"+
					    "\n\n\t|\t 2. View Books, Authors and Genres           |"+
					    "\n\n\t|\t 3.Add Member                                |"+
					    "\n\n\t|\t 4. View Members                             |"+
					    "\n\n\t|\t 5.Update Borrow History                     |"+
					    "\n\n\t|\t 6.Reserve a Book                            |"+
					    "\n\n\t|\t 0. Exit                                     |");
			System.out.println("\t|====================================================|");
			String str="Enter The Menu Option In Number : ";
			int opt1=inputvalid.validateInteger(str);
			if(opt1==0)
			{
				return;
			}
			System.out.println("\t|====================================================|\n\n");
			switch(opt1)
			
			{
				case 1:
			
					man.addBooks();
					break;
				
				case 2:
				
					viewBooks();
					break;
				
				case 3:
				
					man.addMember();
					break;
					
				case 4:
				
					man.viewMembers();
					break;
					
				case 5: 
				
					updateHistory();
					break;
					
				case 6:
					
					reserveBook();
					break;
				
				case 0:
				
					check=false;
					try
					{
						db.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					System.exit(0);
				
					
				default :
				
					System.out.println("Enter The Valid Option");	
			}
		}while(check);
	}
	
	
	
	
	
	
	
	
	
	
	
	void viewBooks()
	{
		boolean check=true;
		do
		{
		
		        System.out.println("\n\t|====================================================|");
			System.out.println("\t\t\t  VIEW BOOKS MENU");
			System.out.println("\t|====================================================|");
			System.out.println("\n\n\t|\t 1. Search By Books                          |"+
					    "\n\n\t|\t 2. Search By Authors                        |"+
					    "\n\n\t|\t 3.Search By Genre                           |"+
					    "\n\n\t|\t 4. View All Books                           |"+
					    "\n\n\t|\t 5.View All Authors                          |"+
					    "\n\n\t|\t 6.View All Genres                           |"+
					    "\n\n\t|\t 7. Back                                     |"+
					    "\n\n\t|\t 8. Exit                                     |");
			System.out.println("\t|====================================================|");
			String str="Enter The Menu Option In Number : ";
			int opt2=inputvalid.validateInteger(str);
			if(opt2==0)
			{
				return;
			}
			System.out.println("\t|====================================================|\n\n");
			switch(opt2)
			{
				case 1:
			
					man.searchByBooks();
					break;
				
				case 2:
				
					man.searchByAuthors();
					break;
					
				case 3:
				
					man.searchByGenre();
					break;
					
				case 4:
				
					man.viewAllBooks();
					break;
					
				case 5:
				
					man.viewAllAuthors();
					break;
					
				case 6:
				
					man.viewAllGenres();
					break;
				
				case 7:
				
					viewMenu();
					break;
					
				case 8:
				
					check=false;
					try
					{
						db.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					System.exit(0);
						
				default :
				
					System.out.println("Enter The Valid Option");
			}
		}while(check);
	}
	
	
	
	
	void updateHistory()
	{
		
		boolean check=true;
		do
		{
			System.out.println("\n\t|====================================================|");
			System.out.println("\t\t\tUPDATE HISTORY MENU");
			System.out.println("\t|====================================================|");
			System.out.println("\n\n\t|\t 1. Book Borrow                              |"+
					    "\n\n\t|\t 2. Reserved Book Borrow                     |"+
					    "\n\n\t|\t 3. Book Return                              |"+
					    "\n\n\t|\t 4. View Borrow History                      |"+
					    "\n\n\t|\t 5. View Member Borrow History               |"+
					    "\n\n\t|\t 6. Back                                     |"+
					    "\n\n\t|\t 7. Exit                                     |");
			System.out.println("\t|====================================================|");
			String str="Enter The Menu Option In Number : ";
			int opt3=inputvalid.validateInteger(str);
			if(opt3==0)
			{
				return;
			}
			System.out.println("\t|====================================================|\n\n");
			switch(opt3)
			{
				case 1:
			
					man.borrowBook();
					break;
					
				case 2:
			
					man.borrowReservedBook();
					break;
				
				case 3:
				
					man.returnBook();
					break;
					
				case 4:
			
					man.showBorrowHistory();
					break;
					
				case 5:
			
					man.showMemberHistory();
					break;
					
				case 6:
				
					viewMenu();
					break;
					
				case 7:
				
					check=false;
					try
					{
						db.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					System.exit(0);
					
				default :
				
					System.out.println("Enter The Valid Option");
			}
		}while(check);
	}
	
	
	
	
	void reserveBook()
	{
		
		boolean check=true;
		do
		{
		
			System.out.println("\n\t|====================================================|");
			System.out.println("\t\t\t RESERVATION MENU");
			System.out.println("\t|====================================================|");
			System.out.println("\n\n\t|\t 1. Reserve A Book                           |"+
					    "\n\n\t|\t 2. All Reservations                         |"+
					    "\n\n\t|\t 3. Member Reservations                      |"+
					    "\n\n\t|\t 4. Back                                     |"+
					    "\n\n\t|\t 5. Exit                                     |");
			System.out.println("\t|====================================================|");
			String str="Enter The Menu Option In Number : ";
			int opt4=inputvalid.validateInteger(str);
			if(opt4==0)
			{
				return;
			}
			System.out.println("\t|====================================================|\n\n");
			switch(opt4)
			{
				case 1:
			
					man.reserveBook();
					break;
					
				case 2:
			
					man.showReservations();
					break;
					
				case 3:
			
					man.showMemberReservations();
					break;
					
				case 4:
				
					viewMenu();
					break;
					
				case 5:
				
					check=false;
					try
					{
						db.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					System.exit(0);
					
				default :
				
					System.out.println("Enter The Valid Option");
			}
		}while(check);
	}
	
	
	

}
				
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			
	
		
	

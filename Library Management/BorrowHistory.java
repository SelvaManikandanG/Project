class BorrowHistory
{
	private int id,book_id,member_id;
	private String member_name,borrow_date,due_date,return_date;
	BorrowHistory(int id,int member_id,String member_name,int book_id,String borrow_date,String due_date,String return_date)
	{
		this.id=id;
		this.member_id=member_id;
		this.member_name=member_name;
		this.book_id=book_id;
		this.borrow_date=borrow_date;
		this.due_date=due_date;
		this.return_date=return_date;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public int getBookId()
	{
		return book_id;
	}
	public void setBookId(int id)
	{
		book_id=id;
	}
	public int getMemberId()
	{
		return member_id;
	}
	public void setMemberId(int id)
	{
		member_id=id;
	}	  
	public String getMemberName()
	{
		return member_name;
	}
	public void setMemberName(String name)
	{
		member_name=name;
	}
	public String getBorrowDate()
	{
		return borrow_date;
	}
	public void setBorrowDate(String date)
	{
		borrow_date=date;
	}
	public String getDueDate()
	{
		return due_date;
	}
	public void setDueDate(String date)
	{
		due_date=date;
	}
	public String getReturnDate()
	{
		return return_date;
	}
	public void setReturnDate(String date)
	{
		return_date=date;
	}
	public String toString()
	{
		return String.format("\n%-20s%-27s%-40s%-20s%-25s%-25s%-25s",id,member_id,member_name,book_id,borrow_date,due_date,return_date);
	}
		
}








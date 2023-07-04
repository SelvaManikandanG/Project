class Reservations
{
	private int reserve_id,member_id,book_id,id;
	private String reserve_date,member_name;
	private boolean isborrowed;
	
	Reservations(int reserve_id,int member_id,String member_name,int book_id,String reserve_date,boolean isborrowed)
	{
		this.reserve_id=reserve_id;
		this.member_id=member_id;
		this.member_name=member_name;	
		this.book_id=book_id;
		this.reserve_date=reserve_date;
		this.isborrowed=isborrowed;
	}
	
	public int getReserveId()
	{
		return reserve_id;
	}
	public void setReserveId(int id)
	{
		this.reserve_id=id;
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
	public int getBookId()
	{
		return book_id;
	}
	public void setBookId(int id)
	{
		book_id=id;
	}
	public String getDate()
	{
		return reserve_date;
	}
	public void setDate(String date)
	{
		reserve_date=date;
	}
	public boolean isBorrowed()
	{
		return isborrowed;
	}
	public void setIsBorrowed(boolean isborrowed)
	{
		this.isborrowed=isborrowed;
	}
	public String toString()
	{
		return String.format("\n%-20s%-25s%-35s%-20s%-30s%-20s",reserve_id,member_id,member_name,book_id,reserve_date,isborrowed);
	}
}
	

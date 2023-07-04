class Book
{
	private int book_id;
	private String book_name;
	private int year;
	Book(int book_id,String book_name,int year_of_publication)
	{
		this.book_id=book_id;
		this.book_name=book_name;
		year=year_of_publication;
	}
	public int getBookId()
	{
		return book_id;
	}
	public void setBookId(int id)
	{
		book_id=id;
	}
	public String getBookName()
	{
		return book_name;
	}
	public void setBookName(String name)
	{
		book_name=name;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year=year;
	}
	public String toString()
	{
		return String.format("\n%-20s%-40s%-25s",book_id,book_name,year);
	}
}	
		

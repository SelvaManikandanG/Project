class BookDetails
{
	private int id,availability;
	private String book_name,author_name,genre_type;
	private int year;
	private int reserved;
	BookDetails(int id,String book_name,String author_name,String genre_type,int year_of_publication,int availability,int reserved)
	{
		this.id=id;
		this.book_name=book_name;
		this.author_name=author_name;
		this.genre_type=genre_type;
		year=year_of_publication;
		this.availability=availability;
		this.reserved=reserved;
		
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public String getBookName()
	{
		return book_name;
	}
	public void setBookName(String name)
	{
		book_name=name;
	}
	public String getAuthorName()
	{
		return author_name;
	}
	public void setAuthorName(String name)
	{
		author_name=name;
	}
	public String getGenretype()
	{
		return genre_type;
	}
	public void setGenretype(String type)
	{
		genre_type=type;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year=year;
	}
	public int getAvailability()
	{
		return availability;
	}
	public void setAvailability(int noofbooks)
	{
		availability=noofbooks;
	}
	public int getReserved()
	{
		return reserved;
	}
	public void setReserved(int reserved)
	{
		this.reserved=reserved;
	}
	public String toString()
	{
		return String.format("\n%-7s%-30s%-30s%-30s%-20s%-20s%-10s",id,book_name,author_name,genre_type,year,availability,reserved);
	} 
}	
		

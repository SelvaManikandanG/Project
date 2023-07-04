class Genre
{
	private int genre_id;
	private String genre_type;
	Genre(int genre_id,String genre_type)
	{
		this.genre_id=genre_id;
		this.genre_type=genre_type;
	}
	public int getGenreId()
	{
		return genre_id;
	}
	public void setGenreId(int id)
	{
		genre_id=id;
	}
	public String getGenretype()
	{
		return genre_type;
	}
	public void setGenretype(String type)
	{
		genre_type=type;
	}
	public String toString()
	{
		return String.format("\n%-20s%-40s",genre_id,genre_type);
	}
}	
		

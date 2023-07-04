class Author
{
	private int author_id;
	private String author_name;
	Author(int author_id,String author_name)
	{
		this.author_id=author_id;
		this.author_name=author_name;
	}
	public int getAuthorId()
	{
		return author_id;
	}
	public void setAuthorId(int id)
	{
		author_id=id;
	}
	public String getAuthorName()
	{
		return author_name;
	}
	public void setAuthorName(String name)
	{
		author_name=name;
	}
	public String toString()
	{
		return String.format("\n%-20s%-40s",author_id,author_name);
	}
}	
		

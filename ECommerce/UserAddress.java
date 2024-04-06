class UserAddress
{
	private int id,user_id;
	private String address;
	UserAddress(int id,int user_id,String address)
	{
		this.user_id=user_id;
		this.address=address;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public int getUserId()
	{
		return user_id;
	}
	public void setUserId(int id)
	{
		user_id=id;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	
}
	

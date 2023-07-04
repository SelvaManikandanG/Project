class Member
{
	private int member_id,booklimit;
	private long mobile_no;
	private String member_name,mail_id;
	Member(int member_id,String member_name,long mobile_no,String mail_id,int limit)
	{
		this.member_id=member_id;
		this.member_name=member_name;
		this.mobile_no=mobile_no;
		this.mail_id=mail_id;
		booklimit=limit;
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
	public long getMobile()
	{
		return mobile_no;
	}
	public void setMobile(long mobile)
	{
		mobile_no=mobile;
	}
	public String getMail()
	{
		return mail_id;
	}
	public void setMail(String mail)
	{
		mail_id=mail;
	}
	public int getBooklimit()
	{
		return booklimit;
	}
	public void setBookLimit(int limit)
	{
		booklimit=limit;
	}
	public String toString()
	{
		return String.format("\n%-15s%-35s%-30s%-40s%-25s",member_id,member_name,mobile_no,mail_id,booklimit);
	}
}
	
	
	

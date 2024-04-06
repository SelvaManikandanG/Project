class CartProcess
{
	InputValidations inputvalid=new InputValidations();
	ResultSets rs=new ResultSets();
	Insertion insert=new Insertion();
	void addToCart(int userid,int productid)
	{
		int quantity=inputvalid.validateInteger("\n\t\tEnter The Quantity : ");
		if(insert.insertCart(userid,productid,quantity)>0)
		{
			System.out.println("\n\t\tAdded To The Cart Successfully...");
		}
		
	}
}

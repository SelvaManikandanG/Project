import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
class ProductsView
{
	ResultSets rs=new ResultSets();
	//ProductDetails prodetails=new ProductDetails();
	TreeMap<Integer,ProductDetails> map=new TreeMap<>();
	ArrayList<ProductDetails> productlist=new ArrayList<>();
	Navigation nav=new Navigation();
	OrderProcess orderpro=new OrderProcess();
	CartProcess cartpro=new CartProcess();
	InputValidations inputvalid=new InputValidations();
	Scanner input=new Scanner(System.in);
	ProductDetails products=null;
		
		
		
	
	
	
	
	void viewAllProducts(int id,int limit)
	{	
		productlist.clear();
		for (Map.Entry<Integer,ProductDetails> entry : map.entrySet()) 
		{
			if(entry.getKey()<=limit&&entry.getKey()>=limit-4)
			{
				products=(ProductDetails)entry.getValue();
    				System.out.println(products.overView());
    				productlist.add(products);
    			}
    			if(entry.getKey()==map.size())
    			{
    				System.out.println("End Of The Product List");
    			}
		}
	}
	
	
	
	
	void selectProduct(int userid,int option)
	{
		for(ProductDetails products: productlist)
		{
			int product_id=products.getId();
			if(product_id==option)
			{
				System.out.println(products);
				System.out.println("\t\t1=> Place Order\t\t2=>Add To Cart");
				System.out.println("\t\t-1=> Back\t\t-2=>Exit");
				int select=inputvalid.validateInteger("");
				switch(select)
				{
					case 1:
						orderpro.placeOrder(userid,product_id,products.getAmount(),products.getSellerName());
						break;
					case 2:
						cartpro.addToCart(userid,product_id);
						break;
					case -1:
						return;
					case -2:
						System.exit(0);
					default :
						System.out.println("Invalid Input");
				}
			}
		}
	}
				
			
	
	
	
	
	void insertIntoMap(ResultSet products)
	{
		try
		{
			map.clear();
			int i=1;
			while(products.next())
			{
				map.put(i++,(new ProductDetails(products.getInt(1),products.getString(2),products.getString(3),products.getString(4),products.getString(5),products.getString(6),
				products.getDouble(7),products.getString(8))));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	int searchProducts(String name)
	{
		int presence=0;
		productlist.clear();
		insertIntoMap(rs.takeProduct(name));	
		Set set=map.entrySet();
		Iterator itr=set.iterator();
		while(itr.hasNext())
		{
			Map.Entry entry=(Map.Entry)itr.next();
			products=(ProductDetails)entry.getValue();
			System.out.println(products.overView());
			productlist.add(products);	
			presence++;	
		}
		return presence;
	}
	
	
	
	
	
	void viewProduct(int sellerid)
	{
		insertIntoMap(rs.takeProduct(sellerid));
		Set set=map.entrySet();
		Iterator itr=set.iterator();
		while(itr.hasNext())
		{
			Map.Entry entry=(Map.Entry)itr.next();
			products=(ProductDetails)entry.getValue();
			System.out.println(products);
		}
	}
	

	
	
	/*boolean viewProduct(String option,int endLimit,int id)
	{
		for (Map.Entry<Integer,String> entry : map.entrySet()) 
		{
			if(entry.getKey()>=endLimit-4&&entry.getKey()<=endLimit)
			{
				products=(ProductDetails)entry.getValue();
				if(Integer.parseInt(option)==products.getId())
				{
					System.out.println(products);
					return true;
				}
			}
		}
		if(option.equals("A")||option.equals("a")||option.equals("B")||option.equals("b")||option.equals("C")||option.equals("c")||option.equals("D")||option.equals("d")||option.equals("z")||option.equals("Z")||option.equals("d")||option.equals("D"))
		{
			nav.navigatePage(option,id);
			return true;
		}
		else
		{
			System.out.println("Invalid Option... Please enter valid ID or Option...");
			return false;
		}
	}*/








	/*void viewAllProducts(int id)
	{	
		try
		{
			insertIntoMap(rs.takeAllProducts());	
			for (Map.Entry<Integer,String> entry : map.entrySet()) 
			{
				if(entry.getKey()>=startLimit&&entry.getKey()<=endLimit)
				{
					products=(ProductDetails)entry.getValue();
            				System.out.println(products.overView);
            				startLimit++;
            			}
            			if(startLimit-1==endLimit)
            			{
            				boolean check;
            				do
            				{
		    				System.out.println("0 to Go Next Page... Other key For Corresponding Functions");
		    				String nextopt=input.next();
		    				if(nextopt.equals("0"))
		    				{
		    					startLimit=endLimit+1;
		    					endLimit+=5;
		    					continue;
		    				}
		    				else 
		    				{
		    					check=viewProduct(nextopt,endLimit,id);
		    				}
		    			}while(!check)
            				
            			}
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}*/
	
	
	
}

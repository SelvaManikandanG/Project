import java.sql.ResultSet;
import java.sql.SQLException;
class ProductUpdate
{

	InputValidations inputvalid=new InputValidations();	
	ResultSets rs=new ResultSets();
	Insertion insert=new Insertion();
	
	
	
	void addProduct(int sellerid)
	{
		String category=inputvalid.validateString("Enter The Category Name : ");
		if(category.length()==0)
		{
			return;
		}
		String product=inputvalid.validateString("Enter The Product Name : ");
		if(product.length()==0)
		{
			return;
		}
		String brand=inputvalid.validateString("Enter The Brand Name : ");
		if(brand.length()==0)
		{
			return;
		}
		String model=inputvalid.validateStrAndNo("Enter The Model Name : ");
		if(model.length()==0)
		{
			return;
		}
		String spec=inputvalid.validateStrAndNo("Enter The Specifications : ");
		if(spec.length()==0)
		{
			return;
		}
		double amount=inputvalid.validateDouble("Enter The Price : "); 
		if(amount==-1)
		{
			return;
		}
		int catid=getCategoryId(category);
		int proid=getProductId(product);
		int brandid=getBrandId(brand);
		if(insert.insertProductDetails(model,proid,brandid,catid,spec,amount,sellerid)>0)
		{
			System.out.println("Product Added Successfully...");
		}
		else
		{
			System.out.println("There is a problem in adding a product");
		}
	}
	
	
	
	
	int getCategoryId(String categoryname)
	{
		int categoryid=0;
		try
		{
			ResultSet category=rs.takeCategoryId(categoryname);
			if(category.next())
			{
				categoryid=category.getInt(1);
			}
			else
			{
				categoryid=insert.insertCategory(categoryname);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return categoryid;
	}
	
	
	
	
	
	int getProductId(String productname)
	{
		int productid=0;
		try
		{
			ResultSet product=rs.takeProductId(productname);
			if(product.next())
			{
				productid=product.getInt(1);
			}
			else
			{
				productid=insert.insertProduct(productname);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return productid;
	}	
	
	
	
	
	
	int getBrandId(String brandname)
	{
		int brandid=0;
		try
		{
			ResultSet brand=rs.takeBrandId(brandname);
			if(brand.next())
			{
				brandid=brand.getInt(1);
			}
			else
			{
				brandid=insert.insertBrand(brandname);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return brandid;
	}	
	
	
	
	
	
	void makeUnavailableProduct(int sellerid)
	{
		int productid=inputvalid.validateInteger("Enter The Product Id : ");
		if(insert.updateProductStatus(productid,sellerid,"f")>0)
		{
			System.out.println("The Product Is Now Unavailable...");
		}
	}
	
	
	
	void makeAvailableProduct(int sellerid)
	{
		int productid=inputvalid.validateInteger("Enter The Product Id : ");
		if(insert.updateProductStatus(productid,sellerid,"t")>0)
		{
			System.out.println("The Product Is Now Available...");
		}
	}
	
	
	
	
	void unavailableProduct(int sellerid)
	{
		insert.updateProductStatus(sellerid,"f");
	}
	
	
	
	void availableProduct(int sellerid)
	{
		insert.updateProductStatus(sellerid,"t");
	}
	
	
	
	
	void updateProductAmount(int sellerid)
	{
		int productid=inputvalid.validateInteger("Enter The Product Id That You Want To Update The Price : ");
		double amount=inputvalid.validateDouble("Enter The New Price Of The Product : ");
		if(insert.updateAmount(productid,sellerid,amount)>0)
		{
			System.out.println("Product Price Updated Successfully...");
		}
		else
		{
			System.out.println("There Is A Problem In Updating The Price of The Product");
		}
	}
	
	
	
}
		
		
		
		
		
		
		
			
		

public class ProductDetails {
    private int id;
    private String modelName;
    private String productName;
    private String brandName;
    private String categoryName;
    private String specification;
    private double amount;
    private String sellerName;

    public ProductDetails(int id, String modelName, String productName, String brandName, String categoryName, String specification, double amount, String sellerName) 
    {
        this.id = id;
        this.modelName = modelName;
        this.productName = productName;
        this.brandName = brandName;
        this.categoryName = categoryName;
        this.specification = specification;
        this.amount = amount;
        this.sellerName = sellerName;
    }


    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getModelName() 
    {
        return modelName;
    }

    public void setModelName(String modelName) 
    {
        this.modelName = modelName;
    }

    public String getProductName() 
    {
        return productName;
    }

    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getBrandName() 
    {
        return brandName;
    }

    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getSpecification() 
    {
        return specification;
    }

    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public double getAmount() 
    {
        return amount;
    }

    public void setAmount(double amount) 
    {
        this.amount = amount;
    }

    public String getSellerName() 
    {
        return sellerName;
    }

    public void setSellerName(String sellerName) 
    {
        this.sellerName = sellerName;
    }
    
    public String overView()
    {
    	return  "\n-------------"+
        	 "\n|   Id =" + id +"   |"+
        	 "\n-------------"+
        	 "\nCategory Name   =" + categoryName +
                "\nModel Name      =" + modelName +
                "\nProduct Name    =" + productName +
                "\nAmount          =" + amount +"\n\n";
    }
    
    public String toString() 
    {
        return  "\n-------------"+
        	 "\n|   Id =" + id +"   |"+
        	 "\n-------------"+
                "\nModel Name      =" + modelName + 
                "\nProduct Name    =" + productName +
                "\nBrand Name      =" + brandName +
                "\nSpecification   =" + specification +
                "\nAmount          =" + amount +
                "\nSeller Name     =" + sellerName+"\n\n";
     }
     
     
     
}


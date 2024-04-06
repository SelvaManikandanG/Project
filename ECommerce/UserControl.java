import java.util.Scanner;
import java.io.Console;
class UserControl
{
	Scanner input=new Scanner(System.in);
	ProductsView proview=new ProductsView();
	Login login=new Login();
	SignUp signup=new SignUp();
	InputValidations inputvalid=new InputValidations();
	ResultSets rs=new ResultSets();
	Navigation navi=new Navigation();
	
	

	
	
	
	void userLogin()
	{
		System.out.println("\n\t\t\tMobile Number");
		long mobile_no=inputvalid.validateMobile("");
		if(mobile_no==-1)
		{
			return;
		}
		System.out.println("\n\t\t\tPassword");
		char[] passwordArray = System.console().readPassword();
        	String password = new String(passwordArray);
        	int userid=login.checkLoginInfo(mobile_no,password,"users");
        	String option;
       	if(userid!=-1)
        	{
        		int limit=5;
        		
        		boolean check;
        		do
        		{
        			check=true;
        			proview.insertIntoMap(rs.takeAllProducts());	
        			proview.viewAllProducts(userid,limit);
        			limit+=5;
        			System.out.println("\n\n\t\t----------------------------");
        			System.out.println("\t\t| S => Search For Products |");
        			System.out.println("\t\t----------------------------\n\n");
        			System.out.println("\n\t ---------------     -----------------      -------------      -----------     --------------"); 
        			System.out.println("\t| P=> Profile |     | C=>Categories |      | O=>Orders |      | K=>Cart |     | Q=>Log Out |"); 
				System.out.println("\t ---------------     -----------------      -------------      -----------     --------------\n\n"); 
    				System.out.println("\n> to Go Next Page... Other key For Corresponding Functions");
    				option=inputvalid.validateALetter("");
    				try
    				{
    					int numopt=Integer.parseInt(option);
    					if(numopt>=1&&numopt<=9)
    					{
    						proview.selectProduct(userid,numopt);
    					}
    					else
    					{
    						throw new NumberFormatException();
    					}
    					
    				}
    				catch(NumberFormatException e)
    				{
	    				if(option.equals(">"))
	    				{
	    					proview.viewAllProducts(userid,limit);  					
	    				}
	    				else if(option.equals("S")||option.equals("s"))
					{
						String product_name=inputvalid.validateString("Enter Product Name : ");
						if(product_name.length()==0)
						{
							return;
						}
						if(proview.searchProducts(product_name)>0)
						{
							int productid=inputvalid.validateInteger("Enter The Product Id : ");
							proview.selectProduct(userid,productid);
						}
						else
						{
							System.out.println("There Is No Such Products");
						}
					}	
					else if(option.equals("p")||option.equals("P")||option.equals("o")||option.equals("O")||option.equals("c")||option.equals("C")||option.equals("k")||option.equals("K")||option.equals("q")||option.equals("Q"))
					{
						if(navi.navigatePage(option,userid))
						{
							return;
						}
					}
				}
			}while(check);
					
        	}
        }
        
        
       /* if(startLimit-1==endLimit)
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
			
		}*/
            			
            			
 }

import java.time.LocalDate;

public class Orders
{
    private int id;
    private String user_name;
    private String product_name;
    private int quantity;
    private String deliver_person_name;
    private long deliver_person_no;
    private String deliver_address;
    private double amount;
    private String order_date;
    private String status;
    private String delivered_date;

    public Orders(int id, String product_name, int quantity, String deliver_person_name,long deliver_person_no,
                 String deliver_address, double amount,String order_date, String status, String delivered_date) 
    {
        this.id = id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.deliver_person_name = deliver_person_name;
        this.deliver_person_no = deliver_person_no;
        this.deliver_address = deliver_address;
        this.amount = amount;
        this.order_date = order_date;
        this.status = status;
        this.delivered_date = delivered_date;
    }
    
    
    public Orders(int id,String user_name, String product_name, int quantity, double amount,String order_date, String status, String delivered_date) 
    {
        this.id = id;
        this.user_name = user_name;
        this.product_name = product_name;
        this.quantity = quantity;
        this.amount = amount;
        this.order_date = order_date;
        this.status = status;
        this.delivered_date = delivered_date;
    }




    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }
    
    public String getUser_name() 
    {
        return user_name;
    }

    public void setUser_name(String user_name) 
    {
        this.user_name = user_name;
    }

    public String getProduct_name() 
    {
        return product_name;
    }

    public void setProduct_name(String product_name) 
    {
        this.product_name = product_name;
    }

    public int getQuantity() 
    {
        return quantity;
    }

    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }

    public String getDeliver_person_name() 
    {
        return deliver_person_name;
    }

    public void setDeliver_person_name(String deliver_person_name) 
    {
        this.deliver_person_name = deliver_person_name;
    }
    
    public long getDeliver_person_no() 
    {
        return deliver_person_no;
    }

    public void setDeliver_person_no(long deliver_person_no) 
    {
        this.deliver_person_no = deliver_person_no;
    }

    public String getDeliver_address() 
    {
        return deliver_address;
    }

    public void setDeliver_address(String deliver_address) 
    {
        this.deliver_address = deliver_address;
    }

    public double getAmount() 
    {
        return amount;
    }

    public void setAmount(double amount) 
    {
        this.amount = amount;
    }

    public String getOrder_date() 
    {
        return order_date;
    }

    public void setOrder_date(String order_date) 
    {
        this.order_date = order_date;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getDelivered_date() 
    {
        return delivered_date;
    }

    public void setDelivered_date(String delivered_date) 
    {
        this.delivered_date = delivered_date;
    }
    
    public String deliver() 
    {
        return  "Order Id=" + id +
        	", User_name='" + user_name + '\'' +
                ", product_name='" + product_name + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", order_date=" + order_date +
                ", status='" + status + '\'' +
                ", delivered_date=" + delivered_date;
    }

    public String toString() 
    {
        return  "Order Id=" + id +
                ", product_name='" + product_name + '\'' +
                ", quantity=" + quantity +
                ", deliver_person_name='" + deliver_person_name + '\'' +
                ", deliver_person_no='" + deliver_person_no + '\'' +
                ", deliver_address='" + deliver_address + '\'' +
                ", amount=" + amount +
                ", order_date=" + order_date +
                ", status='" + status + '\'' +
                ", delivered_date=" + delivered_date;
    }
}

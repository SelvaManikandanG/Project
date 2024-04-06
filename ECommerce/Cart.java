public class Cart {
    private int id;
    private String category_name;
    private String model_name;
    private String product_name;
    private double amount;
    private int quantity;

    public Cart(int id, String category_name, String model_name, String product_name,
                double amount, int quantity) {
        this.id = id;
        this.category_name = category_name;
        this.model_name = model_name;
        this.product_name = product_name;
        this.amount = amount;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return  "\n---------------"+
        	 "\n| Cart Id =" + id +" |"+
        	 "\n---------------"+
                "\ncategory_name   = " + category_name + 
                "\nmodel_name      = " + model_name +
                "\nproduct_name    = " + product_name + 
                "\namount          = " + amount +
                "\nquantity        = " + quantity +"\n\n";
    }
}


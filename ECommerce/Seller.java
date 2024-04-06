public class Seller {
    private int id;
    private String name;
    private long mobileNo;
    private double wallet;
    private double profitWithSeller;
    private double total;
    private String approval;
    private String status;

  /* public Seller(int id, String name, String mobileNo, double wallet, double profitWithSeller,
                  double total, String password, String approval,String status) {
        this.id = id;
        this.name = name;
        this.mobileNo = mobileNo;
        this.wallet = wallet;
        this.profitWithSeller = profitWithSeller;
        this.total = total;
        this.approval = approval;
        this.status=status;
    }*/
    
    
    public Seller(int id, String name, long mobileNo,String approval) {
        this.id = id;
        this.name = name;
        this.mobileNo = mobileNo;
        this.approval = approval;
    }
    
    
    public Seller(int id, String name, long mobileNo,String approval,String status) {
        this.id = id;
        this.name = name;
        this.mobileNo = mobileNo;
        this.approval = approval;
        this.status=status;
    }
    
    
     public Seller(int id, String name, double wallet, double profitWithSeller,
                  double total) {
        this.id = id;
        this.name = name;
        this.wallet = wallet;
        this.profitWithSeller = profitWithSeller;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public double getProfitWithSeller() {
        return profitWithSeller;
    }

    public void setProfitWithSeller(double profitWithSeller) {
        this.profitWithSeller = profitWithSeller;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
    public String income() {
        return "Seller Id=" + id +
                ", name='" + name + '\'' +
                ", Seller's Wallet='" + wallet + '\'' +
                ", Profit From The Seller=" + profitWithSeller +
                ", Total Purchase Amount=" + total+"\n\n";
    }
    
    
     public String status() {
        return "Seller Id=" + id +
                ", Name='" + name + '\'' +
                ", MobileNo='" + mobileNo + '\'' +
                ", Status=" + status +"\n\n";
    }
    
    
    public String toString() {
        return "Seller Id=" + id +
                ", name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", approval=" + approval +"\n\n";
    }
}



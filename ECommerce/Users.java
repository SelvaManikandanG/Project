public class Users {
    private int id;
    private String user_name,address;
    private long mobile_no;

    public Users(int id, String user_name, long mobile_no,String address) {
        this.id = id;
        this.user_name = user_name;
        this.mobile_no = mobile_no;
        this.address=address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(long mobile_no) {
        this.mobile_no = mobile_no;
    }
    
    
    
    public String getAddress() {
        return user_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    

    public String toString() {
        return  "\n-------------"+
        	 "\n| User Id =" + id +" |"+
        	 "\n-------------"+
                "\nUser Name    =" + user_name +
                "\nMobile No.   =" + mobile_no +
                "\nAddress      =" + address+"\n\n";
    }
    
    
    
}


package com.zoho.Banking;	
public class Users {
    private int user_id;
    private int customer_id;
    private String user_name;
    private String role;
    private long aadhar;
    private long mobile;
    private String gender;
    private byte age;
    private String dob;
    private String mail;
    private String address;
    private String accountNo;
    private String type;
    private String scheme;
    private String branch;
    private String status;	

    public Users(int user_id,int customer_id, String user_name, long aadhar, long mobile, String gender, byte age, String dob, String mail, String address, String type, String scheme, String branch, String status) {
        this.user_id = user_id;
        this.customer_id = customer_id;
        this.user_name = user_name;
        this.aadhar = aadhar;
        this.mobile = mobile;
        this.gender = gender;
        this.age = age;
        this.dob = dob;
        this.mail = mail;
        this.address = address;
        this.type = type;
        this.scheme = scheme;
        this.branch = branch;
        this.status = status;

    }
    
    
    public Users(int user_id, String user_name, long aadhar, long mobile, String gender, byte age, String dob, String mail, String address, String type, String scheme, String branch, String status,String accountNo) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.aadhar = aadhar;
        this.mobile = mobile;
        this.gender = gender;
        this.age = age;
        this.dob = dob;
        this.mail = mail;
        this.address = address;
        this.type = type;
        this.scheme = scheme;
        this.branch = branch;
        this.status = status;
        this.accountNo=accountNo;
    }
    
    
    public Users(int user_id, String user_name,String role, long mobile, String gender, byte age, String dob, String mail, String address,String branch, String status) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.role=role;
        this.mobile = mobile;
        this.gender = gender;
        this.age = age;
        this.dob = dob;
        this.mail = mail;
        this.address = address;
        this.branch = branch;
        this.status = status;
    }
    

    

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }


    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public long getAadhar() {
        return aadhar;
    }

    public void setAadhar(long aadhar) {
        this.aadhar = aadhar;
    }

    public long getmobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getmail() {
        return mail;
    }

    public void setmail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    public String cusOverView()
    {
    	switch(status)
    	{
    		case "applied":
    		
    			status="Waiting For Approval";  
    			
    		case "approved1":
    		
    			status="Waiting For Manager Approval";  
    		
    		case "approved2":
    		
    			status="Approved";  
    	}
    	return String.format("%-25s%-30s%-30s%-30s",customer_id,user_name,aadhar,status);
   
    }
    
    
    
    public String accountView() 
    {
    	if(accountNo.equals("0"))
    	{
    		accountNo="Not Yet Created";
    	}
        return String.format("%-35s%-30s%-30s%-35s",accountNo,type,scheme,branch);
    }
    
    
    
    public String userView() 
    {
    	switch(status)
    	{
    		case "applied":
    		
    			status="Waiting For Approval";  
    			
    		case "approved1":
    		
    			status="Waiting For Manager Approval";  
    		
    		case "approved2":
    		
    			status="Approved";  
    	}
        return "\nCustomer ID        : " + user_id + "\n" +
               "User Name          : " + user_name + "\n" +
               "Aadhar No          : " + aadhar + "\n" +
               "Mobile No          : " + mobile + "\n" +
               "Gender             : " + gender + "\n" +
               "Age                : " + age + "\n" +
               "Date of Birth      : " + dob + "\n" +
               "Email ID           : " + mail + "\n" +
               "Address            : " + address + "\n" +
               "Status             : " + status+"\n\n";
    }
    

  
    public String toString() 
    {
    	switch(status)
    	{
    		case "applied":
    		
    			status="Waiting For Approval";  
    			
    		case "approved1":
    		
    			status="Waiting For Manager Approval";  
    		
    		case "approved2":
    		
    			status="Approved";  
    	}
        return "\nCustomer ID        : " + customer_id + "\n" +
               "User Name          : " + user_name + "\n" +
               "Aadhar No          : " + aadhar + "\n" +
               "Mobile No          : " + mobile + "\n" +
               "Gender             : " + gender + "\n" +
               "Age                : " + age + "\n" +
               "Date of Birth      : " + dob + "\n" +
               "Email ID           : " + mail + "\n" +
               "Address            : " + address + "\n" +
               "Type               : " + type + "\n" +
               "Scheme             : " + scheme + "\n" +
               "Branch             : " + branch + "\n" +
               "Status             : " + status+"\n\n";
    }
    
    
    public String staffOverView()
    {
    	return String.format("%-15s%-35s%-30s%-30s",user_id,user_name,role,status);
    }
    
    
     public String staffToString() 
     {
        return "\nUser ID            : " + user_id + "\n" +
               "User Name          : " + user_name + "\n" +
               "Role               : " + role + "\n" +
               "Mobile No          : " + mobile + "\n" +
               "Gender             : " + gender + "\n" +
               "Age                : " + age + "\n" +
               "Date of Birth      : " + dob + "\n" +
               "Email ID           : " + mail + "\n" +
               "Address            : " + address + "\n" +
               "Branch             : " + branch + "\n" +
               "Status             : " + status+"\n\n";
    }
}


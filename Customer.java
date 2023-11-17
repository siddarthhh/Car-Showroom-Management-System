import java.util.*;
import java.sql.*;
public class Customer extends Employee {
    private int c_id;
    private String c_name;
    private String mobileno;
    private String emailid;
    private String username;
    private String password;

    public Customer(int c_id, String c_name, String mobileno, String emailid, String username, String password) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.mobileno = mobileno;
        this.emailid = emailid;
        this.username = username;
        this.password = password;
    }
    public Customer(){};
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public int getC_id() {
        return c_id;
    }

    public String getC_name() {
        return c_name;
    }

    @Override
    public String getMobileno() {
        return mobileno;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public boolean validation(String username, String password, Connection con) throws Exception{
        System.out.println(username+" "+ password);
        if(username !="" && password != ""){
            try{
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select  c_username ,c_pass from customer where c_username='"+username+"'");
                while (rs.next()) {
                    String user_name = rs.getString(1);
                    String pass_word = rs.getString(2);
                    rs.close();

                    if(user_name.equals(username)&&password.equals(pass_word)){
                        return true;
                    }else
                        return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    public Customer(int c_id, String c_name, String mobileno, String emailid) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.mobileno = mobileno;
        this.emailid = emailid;

    }
    void insertUserData(Connection con, Scanner sc) throws Exception {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO customer(c_id,c_name,mobileno,emailid,c_username,c_pass) VALUES(?,?,?,?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, this.c_id);
        stmt.setString(2, this.c_name);
        stmt.setString(3, this.mobileno);
        stmt.setString(4, this.emailid);
        stmt.setString(5, getUsername());
        stmt.setString(6, getPassword());
        stmt.executeUpdate();
    }

    public void viewCar(Connection con, Scanner sc) throws Exception {
        PreparedStatement stmt =null;
        String sql = "Select * from car";
        stmt=con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getInt(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getInt(5));
        }
    }

    public void bookCar(Connection con , int CarId , Scanner sc)throws Exception{

        if(getCarQuantity(CarId,con)==-1){
            System.out.println("Sorry the Car is out of stock");
            return;
        }
        PreparedStatement stmt = con.prepareStatement("Insert into sales (c_id,car_id,emp_id)VALUES (?,?,?) ");

        stmt.setInt(1,this.c_id);
        stmt.setInt(2,CarId);
        stmt.setInt(3,1);
        stmt.executeUpdate();
        int currQuantity=getCarQuantity(CarId,con);
        Employee.updateCarQuantity(CarId, currQuantity-1, con);
    }


    public void viewBookings(Connection con) throws Exception{
        PreparedStatement stmt =con.prepareStatement( "Select * from sales where c_id ='"+getC_id()+"'");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            PreparedStatement st = con.prepareStatement("Select * from car where car_id = '"+rs.getInt(3)+"'");
            ResultSet r1 = st.executeQuery();
            System.out.println("Sales Id:"+rs.getInt(1));
            System.out.println("Customer Id:"+rs.getInt(2));
            System.out.println("CarId:"+rs.getString(3));
            while(r1.next()) {
                System.out.println("CarId:"+r1.getString(2));
                System.out.println("Car model:"+r1.getInt(3));
                System.out.println("Car type:"+r1.getString(4));
                System.out.println("Car Price:"+r1.getInt(5));
            }
        }
    }
}
import java.sql.*;
import java.util.*;

public class Employee {
    private int emp_id ;
    private String emp_name;
    private String mobileno;
    private String e_username;
    private String e_pass ;
    private String email;
    private String username;
    private String password;

    public Employee e;

    public Employee(int emp_id, String emp_name, String mobileno, String e_username, String e_pass, String email) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.mobileno = mobileno;
        this.e_username = e_username;
        this.e_pass = e_pass;
        this.email = email;
    }

    public Employee() {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getEmp_id() {
        return emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getE_username() {
        return e_username;
    }

    public String getE_pass() {
        return e_pass;
    }


    public String getEmail() {
        return email;
    }

    public boolean empValidation (String username, String password, Connection con){
        System.out.println(username+" "+ password);
        if(username !="" && password != ""){
            try{
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select  e_username ,e_pass from employee where e_username='"+username+"'");
                while (rs.next()) {
                    String user_name = rs.getString(1);
                    String pass_word = rs.getString(2);
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
    public void addCar(Scanner sc, Connection con) throws Exception{
        System.out.println("Enter car id:");
        int car_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter car name");
        String car_name = sc.nextLine();
        System.out.println("Enter car model");
        int car_model = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter car type:");
        String car_type=sc.nextLine();
        System.out.println("Enter car Price");
        int car_price=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the number of stock available");
        int quantity=sc.nextInt();
        car c1 = new car(car_id,car_name,car_model,car_type,car_price,quantity);
        c1.insertCar(con);
    }
    public void viewUser (Scanner sc , Connection con) throws Exception{
        PreparedStatement stmt =null;
        String sql = "Select * from customer";
        stmt=con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getString(5));
            System.out.println(rs.getString(6));
        }

    }

    public void deleteCar(int id,Connection con)throws Exception{
        PreparedStatement stmt = con.prepareStatement("Delete from car where car_id = ?");
        stmt.setInt(1,id);
        stmt.executeUpdate();
        con.close();
    }
    public static int getCarQuantity(int carID,Connection con)throws Exception{
        int quantity=0;
        PreparedStatement stmt = con.prepareStatement("Select quantity from car where car_id = ?");
        stmt.setInt(1,carID);
        ResultSet result = stmt.executeQuery();
        if(result.next()){
            return result.getInt("quantity");
        }
        else return-1;
    }
    public static  void updateCarQuantity(int carId , int newQuantity ,Connection con)throws Exception{
        PreparedStatement statement = con.prepareStatement("Update car Set quantity = ? Where car_id = ?");
        statement.setInt(1, newQuantity);
        statement.setInt(2, carId);
        statement.executeUpdate();
    }

}

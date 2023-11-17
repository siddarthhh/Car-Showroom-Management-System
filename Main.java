import java.util.Scanner;
import java.sql.*;
public class Main extends conn_db{
    public static void main(String[] args) throws Exception{
        conn_db dbconnect =new conn_db();
        Connection con=dbconnect.getConnection();
        Scanner sc = new Scanner(System.in);
        char c_check='y';
        //Customer c1 = new Customer();
        while(c_check=='y'){
            System.out.println("Press \n 1.Customer Signup \n 2.Customer Login \n 3.Employee Login");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    System.out.println("Enter customer id:");
                    int c_id=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter customer name:");
                    String c_name = sc.nextLine();
                    System.out.println("Enter customer mobileno");
                    String mno=sc.nextLine();
                    System.out.println("Enter email-id");
                    String email=sc.nextLine();
                    Customer s = new Customer(c_id,c_name,mno,email);
                    System.out.println("Enter username");
                    String u_name= password.check(sc,con);
                    s.setUsername(u_name);
                    boolean success= password.passwordCheck(s,sc,con);
                    //s.setUsername(password.username);
                    if(success) {
                        s.insertUserData(con,sc);
                        System.out.println("Account created successfully!");
                        break;
                    }
                    continue;

                case 2:
                    while(true){
                        Customer c = new Customer();
                        System.out.println("Enter username");
                        c.setUsername(sc.nextLine());
                        System.out.println("Enter password");
                        c.setPassword(sc.nextLine());
                        if(c.validation(c.getUsername(),c.getPassword(),con)){
                            password.username=c.getUsername();
                            Statement s1 =con.createStatement();
                            ResultSet r1 = s1.executeQuery("select  c_id from customer where c_username='"+ password.username+"'");
                            if(r1.next()) {
                                int x = r1.getInt(1);
                                c.setC_id(x);
                            }
                            System.out.println("Logged In Sucessfully!!!!");
                            while(true){
                                System.out.println("Press 10 to view car\nPress 20 to book car\nPress 30 to view your bookings");
                                int mchoice = sc.nextInt();
                                switch (mchoice) {
                                    case 10:
                                        c.viewCar(con,sc);
                                        break;
                                    case 20:
                                        System.out.println("enter the carId which you want to book");
                                        c.bookCar(con,sc.nextInt(),sc);
                                        break;
                                    case 30:
                                        c.viewBookings(con);
                                        break;
                                }
                            }
                        }
                        else{
                            System.out.println("Invalid username or password\n\nPlease Try again!!!");
                        }
                    }
                case 3:
                    while(true) {
                        Employee e = new Employee();
                        System.out.println("Enter Employee username:");
                        e.setUsername(sc.nextLine());
                        System.out.println("Enter password");
                        e.setPassword(sc.nextLine());
                        if (e.empValidation(e.getUsername(), e.getPassword(), con)) {
                            password.username = e.getUsername();
                            System.out.println("Logged In Sucessfully!!");
                            while(true){
                            System.out.println("Press 10 to add car\nPress 20 to view User Details");
                            int mchoice = sc.nextInt();
                            switch (mchoice) {
                                case 10:
                                    e.addCar(sc, con);
                                    break;
                                case 20:
                                    e.viewUser(sc,con);
                                    break;
                            }
                        }
                        }else {
                            System.out.println("Invalid username or password\n\nPlease Try again!!!");
                        }
                    }
                        default:
                            System.out.println("Enter a valid choice");
                            continue;
            }
        }
        System.out.println();

    }
}


import java.util.*;
import java.sql.*;
public class password {
    static String username;
    conn_db dbconnect =new conn_db();
    Connection con=dbconnect.getConnection();
    static boolean passwordCheck(Customer s,Scanner sc,Connection con)throws Exception{
        System.out.println("Enter password");
        String password=sc.nextLine();
        System.out.println("Enter confirm password");
        String confirm_password=sc.nextLine();
        if(password.equals(confirm_password)) {
            s.setPassword(password);
            return true;
        }
        System.out.println("Password doesn't match!!!!");
        return passwordCheck(s,sc,con);
    }
    static public String check(Scanner sc,Connection con) throws Exception{
        String username=sc.nextLine();
        Statement stmt=con.createStatement();
        String query = "Select c_username from customer where c_username='"+username+"'";
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next())
        {
            System.out.println("User name already exists!\n Try entering another user_name");
            username=check(sc,con);
        }
        return username;
    }
}

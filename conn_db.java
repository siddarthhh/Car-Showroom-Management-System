import java.sql.*;
public class conn_db {
    private static Connection con = null;

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/car_application", "root", "");
        } catch (Exception e) {
            System.out.print(e);

        }
        return con;
    }
}
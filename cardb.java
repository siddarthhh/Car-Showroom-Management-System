//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.util.Scanner;
//
//public class cardb extends car{
//    public void insertCar(Connection con)throws Exception{
//        PreparedStatement stmt =null;
//        String sql ="Insert into car (car_id,car_name,car_model,car_type,car_price,quantity) Values(?,?,?,?,?,?)";
//        stmt=con.prepareStatement(sql);
//        stmt.setInt(1,car_id);
//        stmt.setString(2,car_name);
//        stmt.setInt(3,car_model);
//        stmt.setString(4,car_type);
//        stmt.setInt(5,car_price);
//        stmt.setInt(6,quantity);
//        stmt.executeUpdate();
//
//
//    }
//
//}

import java.sql.Connection;
import java.sql.PreparedStatement;

public class car {

    private int car_id;
    private String car_name;
    private int car_model;
    private String car_type;
    private int car_price;
    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCar_id() {
        return car_id;
    }

    public String getCar_name() {
        return car_name;
    }

    public int getCar_model() {
        return car_model;
    }

    public String getCar_type() {
        return car_type;
    }

    public int getCar_price() {
        return car_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public car(){};
    public car(int car_id, String car_name, int car_model, String car_type, int car_price, int quantity) {
        this.car_id = car_id;
        this.car_name = car_name;
        this.car_model = car_model;
        this.car_type = car_type;
        this.car_price = car_price;
        this.quantity=quantity;
    }

    public void insertCar(Connection con)throws Exception{
        PreparedStatement stmt =null;
        String sql ="Insert into car (car_id,car_name,car_model,car_type,car_price,quantity) Values(?,?,?,?,?,?)";
        stmt=con.prepareStatement(sql);
        stmt.setInt(1,car_id);
        stmt.setString(2,car_name);
        stmt.setInt(3,car_model);
        stmt.setString(4,car_type);
        stmt.setInt(5,car_price);
        stmt.setInt(6,quantity);
        stmt.executeUpdate();
    }
    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public void setCar_model(int car_model) {
        this.car_model = car_model;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public void setCar_price(int car_price) {
        this.car_price = car_price;
    }
}

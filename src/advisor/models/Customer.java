package advisor.models;

import advisor.storage.DBHandler;
import static advisor.models.Deal.newDeal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final int id;

    private String name;
    private String address;
    private String phone;
    private String img;

    public Customer(int id, String name, String address, String phone, String img) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.img = img;
    }

    public Customer(String name, String address, String phone, String img) {
        this(generateId(), name, address, phone, img);
    }

    public void save() {
        try {
            System.out.println("INSERT INTO customer (id, name, address, phone, img) VALUES (" + id + ", \"" + name + "\", \"" + address + "\", \"" + phone + "\", \"" + img + "\"); ");
            DBHandler.connection.createStatement().execute("INSERT INTO customer (id, name, address, phone, img) VALUES (" + id + ", \"" + name + "\", \"" + address + "\", \"" + phone + "\", \"" + img + "\"); ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            System.out.println("UPDATE customer SET  name = \"" + name + "\", img = \"" + img + "\", phone = \"" + phone + "\", address = \"" + address + "\" WHERE id = " + id + " ;");
            DBHandler.connection.createStatement().executeUpdate("UPDATE customer SET  name = \"" + name + "\", img = \"" + img + "\", phone = \"" + phone + "\", address = \"" + address + "\" WHERE id = " + id + " ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            DBHandler.connection.createStatement().execute("Delete From customer Where id = " + this.id + ";");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Customer find(int id) {
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from customer WHERE id = " + id + " ;");
            return new Customer(result.getInt("id"), result.getString("name"), result.getString("address"), result.getString("phone"), result.getString("img"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Customer> findAll() {
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from customer;");
            List<Customer> customers = new ArrayList();
            while (result.next()) {
                customers.add(new Customer(result.getInt("id"), result.getString("name") , result.getString("address"), result.getString("phone"), result.getString("img")));
            }
            return customers;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Deal> findDeals() throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from deal WHERE SectionID = " + id + " ;");
        List<Deal> deals = new ArrayList();
        while (result.next()) {
            deals.add(newDeal(id, result.getString("date"), result.getString("delay"), Customer.find(result.getInt("customerId")), Company.find(result.getInt("companyId")), Admin.find(result.getInt("AdminID")), result.getString("method"), result.getDouble("amount"), result.getString("note"), result.getInt("type")));
        }
        return deals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public static int generateId() {
        try {
            ResultSet rs = DBHandler.connection.createStatement().executeQuery("select max(id) from customer");
            rs.next();
            int maxId = rs.getInt(1);
            return ++maxId;
        } catch (SQLException e) {
            return -1;
        }
    }

    public int getId() {
        return this.id;
    }

}

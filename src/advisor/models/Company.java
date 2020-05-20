package advisor.models;

import advisor.storage.DBHandler;
import static advisor.models.Admin.newAdmin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Company {

    private final int id;
    private String name;
    private String image;

    public Company(String name, String image) {
        this.name = name;
        id = generateId();
        this.image = image;
    }

    public Company(int id, String name, String image) {
        this.name = name;
        this.id = id;
        this.image = image;
    }

    public void save() {
        try {
            DBHandler.connection.createStatement().execute("INSERT INTO company (id, name, img) VALUES (" + id + ", \"" + name + "\", \"" + image + "\"); ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            System.out.println("UPDATE company SET name = \"" + name + "\", img = \"" + image + "\" WHERE id = " + id + "; ");
            DBHandler.connection.prepareStatement("UPDATE company SET name = \"" + name + "\", img = \"" + image + "\" WHERE id = " + id + "; ").executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete() {
        try {
            DBHandler.connection.createStatement().executeUpdate("Delete From company Where id = " + this.id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Company find(int id) {
        if (id == 0) {
            return new Company(0, "office",  null);
        }
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from company WHERE id = " + id + " ;");
            return new Company(id, result.getString("name"), result.getString("img"));
        } catch (SQLException e) {
            return new Company(-1, "deleted", null);
        }
    }

    public static Company find(String name) {
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from company WHERE name = " + name + " ;");
            return new Company(result.getInt("id"), name, result.getString("img"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Company> findAll() {
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from company;");
            List<Company> companys = new ArrayList();
            while (result.next()) {
                companys.add(new Company(result.getInt("id"), result.getString("name"), result.getString("img")));
            }
            return companys;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Admin> findAdmins() throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from admin WHERE companyId = " + id + " ;");
        List<Admin> admins = new ArrayList();
        while (result.next()) {
            admins.add(newAdmin(result.getInt("id"), result.getString("name"), result.getString("email"), result.getString("password"), result.getString("img"), result.getString("phone"), Company.find(result.getInt("companyId")), result.getInt("role")));
        }
        return admins;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static int generateId() {
        try {
            ResultSet rs = DBHandler.connection.createStatement().executeQuery("select max(id) from company");
            rs.next();
            int maxId = rs.getInt(1);
            return ++maxId;
        } catch (SQLException e) {
            return -1;
        }
    }

}

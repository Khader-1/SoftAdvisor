package advisor.models;

import advisor.DBHandler;
import static advisor.models.Admin.newAdmin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Company {

    private final int id;
    private String name;

    public Company(String name) {
        this.name = name;
        id = generateId();
    }

    public Company(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public void save() throws SQLException {
        DBHandler.connection.createStatement().execute("INSERT INTO company (id, name) VALUES (" + id + ", \"" + name + "\"); ");
    }

    public void update() throws SQLException {
        DBHandler.connection.createStatement().execute("UPDATE company SET name = \"" + name + "\" WHERE id = " + id + "; ");
    }

    public void delete() throws SQLException {
        DBHandler.connection.createStatement().execute("Delete From company Where id = " + this.id + ";");
    }

    public static Company find(int id) throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from company WHERE id = " + id + " ;");
        return new Company(id, result.getString("name"));
    }

    public static Company find(String name) throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from company WHERE name = " + name + " ;");
        return new Company(result.getInt("id"), name);
    }

    public static List<Company> findAll() throws SQLException {
        ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from company;");
        List<Company> sections = new ArrayList();
        while (result.next()) {
            sections.add(new Company(result.getInt("id"), result.getString("name")));
        }
        return sections;
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

    private static int generateId() {
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

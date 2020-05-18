package advisor.models;

import advisor.Advisor;
import advisor.DBHandler;
import static advisor.models.Deal.newDeal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Admin {

    public static boolean auth(String email, String password) {
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from admin WHERE email = \"" + email + "\" AND password = \"" + password + "\" ;");
            Advisor.auth(find(result.getInt("id")));
            return true;
        } catch (SQLException ex) {
        }
        return false;
    }

    public final static int READONLY = 1;
    public final static int WRITE = 2;
    public final static int MANAGER = 3;
    public final static int ADVISOR = 4;
    public final static int FULL_PRIVELLEGS = 5;

    private final int id;
    private String name;
    private String email;
    private String password;
    private String img;
    private String phone;
    private Company company;

    public Admin(int id, String name, String email, String password, String img, String phone, Company company) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.img = img;
        this.phone = phone;
        this.company = company;
    }

    public Admin(String name, String email, String password, String img, String phone, Company company) {
        this(generateId(), name, email, password, img, phone, company);
    }

    public Admin() {
        this.id = generateId();
    }

    public void save() {
        try {
            DBHandler.connection.createStatement().execute("INSERT INTO admin (id, name, email, password, img, phone, companyId, role) VALUES (" + id + ", \"" + name + "\", \"" + email + "\", \"" + password + "\", \"" + img + "\", \"" + phone + "\", " + company.getId() + ", " + getRole() + "); ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update() {
        try {
            DBHandler.connection.createStatement().execute("UPDATE admin SET  name = \"" + name + "\", email = \"" + email + "\", password = \"" + password + "\", img = \"" + img + "\", phone = \"" + phone + "\", companyId = " + company.getId() + " WHERE id = " + id + " ;");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Admin find(int id) {
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from admin WHERE id = " + id + " ;");
            return newAdmin(result.getInt("id"), result.getString("name"), result.getString("email"), result.getString("password"), result.getString("img"), result.getString("phone"), Company.find(result.getInt("companyId")), result.getInt("role"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Admin> findAll() {
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from admin;");
            List<Admin> admins = new ArrayList();
            while (result.next()) {
                admins.add(newAdmin(result.getInt("id"), result.getString("name"), result.getString("email"), result.getString("password"), result.getString("img"), result.getString("phone"), Company.find(result.getInt("companyId")), result.getInt("role")));
            }
            return admins;
        } catch (SQLException e) {
            return null;
        }
    }

    public void delete() {
        try {
            DBHandler.connection.createStatement().execute("Delete From admin Where id = " + this.id + ";");
        } catch (SQLException e) {
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImg() {
        return img;
    }

    public String getPhone() {
        return phone;
    }

    protected Company getCompany() {
        return company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    protected void setCompany(Company company) {
        this.company = company;
    }

    public static int generateId() {
        try {
            ResultSet rs = DBHandler.connection.createStatement().executeQuery("select max(id) from admin");
            rs.next();
            int maxId = rs.getInt(1);
            return ++maxId;
        } catch (SQLException e) {
        }
        return -1;
    }

    public List<Deal> getDeals() {
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from deal WHERE adminId = " + id + " AND ORDER by id DESC;");
            List<Deal> deals = new ArrayList();
            while (result.next()) {
                deals.add(newDeal(id, result.getString("date"), result.getString("delay"), Customer.find(result.getInt("customerId")), Company.find(result.getInt("companyId")), Admin.find(result.getInt("AdminID")), result.getString("method"), result.getDouble("amount"), result.getString("note"), result.getInt("type")));
            }
            return deals;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    abstract int getRole();

    public static Admin newAdmin(int id, String name, String email, String password, String img, String phone, Company company, int role) {
        switch (role) {
            case 3:
                return new Manager(id, name, email, password, img, phone, company);
            case 4:
            case 5:
                return new advisor.models.Advisor(id, name, email, password, img, phone, role);
            default:
                return new Employee(name, email, password, img, phone, company, role);
        }
    }
}

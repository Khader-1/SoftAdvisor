package advisor.models;

import advisor.storage.DBHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Deal {

    public final static int PAYMENT = 1;
    public final static int PURCHASE = 2;

    private final int id;
    private String date;
    private String delay;
    private Customer customer;
    private Company company;
    private Admin admin;
    private String method;
    private double amount;
    private String note;

    public Deal(int id, String date, String delay, Customer customer, Company company, Admin admin, String method, double amount, String note) {
        this.id = id;
        this.date = date;
        this.delay = delay;
        this.customer = customer;
        this.admin = admin;
        this.company = company;
        this.method = method;
        this.amount = amount;
        this.note = note;
    }

    public Deal(String date, String delay, Customer customer, Company company, Admin admin, String method, double amount, String note) {
        this(generateId(), date, delay, customer, company, admin, method, amount, note);
    }

    public void save() {
        try {
            DBHandler.connection.createStatement().execute("INSERT INTO deal (id, date, delay, customerId, adminId, companyId, method, amount, note, type) VALUES (" + id + ", \"" + date + "\", " + "\"" + delay + "\", " + customer.getId() + ", " + admin.getId() + ", " + company.getId() + ", \"" + method + "\", " + amount + ", \"" + note + ", " + getType() + "); ");
        } catch (SQLException e) {
        }
    }

    private static int generateId() {
        try {
            ResultSet rs = DBHandler.connection.createStatement().executeQuery("select max(id) from bill");
            rs.next();
            int maxId = rs.getInt(1);
            return ++maxId;
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
    }

    public static Deal find(int id) {
        try {
            ResultSet result = DBHandler.connection.createStatement().executeQuery("SELECT * from section WHERE id = " + id + " ;");
            return newDeal(id, result.getString("date"), result.getString("delay"), Customer.find(result.getInt("customerId")), Company.find(result.getInt("companyId")), Admin.find(result.getInt("AdminID")), result.getString("method"), result.getDouble("amount"), result.getString("note"), result.getInt("type"));
        } catch (Exception e) {
            return null;
        }
    }

    abstract int getType();

    public static Deal newDeal(int id, String date, String delay, Customer customer, Company company, Admin admin, String method, double amount, String note, int type) {
        switch (type) {
            case PURCHASE:
                return new Purchase(id, date, delay, customer, company, admin, method, amount, note);
            case PAYMENT:
                return new Payment(id, date, delay, customer, company, admin, method, amount, note);
            default:
                return null;
        }
    }
}

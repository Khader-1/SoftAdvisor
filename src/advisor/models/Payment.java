package advisor.models;

public class Payment extends Deal{

    public Payment(int id, String date, String delay, Customer customer, Company company, Admin admin, String method, double amount, String note) {
        super(id, date, null, customer, company, admin, method, amount, note);
    }
    public Payment(String date, String delay, Customer customer, Company company, Admin admin, String method, double amount, String note) {
        super(date, null, customer, company, admin, method, amount, note);
    }

    @Override
    int getType() {
        return Deal.PAYMENT;
    }
    
}

package advisor.models;

public class Purchase extends Deal {

    public Purchase(int id, String date, String delay, Customer customer, Company company, Admin admin, String method, double amount, String note) {
        super(id, date, delay, customer, company, admin, method, amount, note);
    }

    public Purchase(String date, String delay, Customer customer, Company company, Admin admin, String method, double amount, String note) {
        super(date, delay, customer, company, admin, method, amount, note);
    }

    @Override
    int getType() {
        return Deal.PURCHASE;
    }

}

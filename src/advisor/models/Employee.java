package advisor.models;

public class Employee extends Admin {

    private int role;

    public Employee(int id, String name, String email, String password, String img, String phone, Company company, int role) {
        super(id, name, email, password, img, phone, company);
        this.role = role;
    }

    public Employee(String name, String email, String password, String img, String phone, Company company, int role) {
        super(name, email, password, img, phone, company);
        this.role = role;
    }

    public Employee(int role) {
        this.role = role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public int getRole() {
        return this.role;
    }

}

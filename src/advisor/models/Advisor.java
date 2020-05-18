package advisor.models;

public class Advisor extends Admin{
    public int role;

    public Advisor(int id, String name, String email, String password, String img, String phone, int role) {
        super(id, name, email, password, img, phone, new Company(0, "Adv"));
        this.role = role;
    }

    public Advisor(String name, String email, String password, String img, String phone, int role) {
        super(name, email, password, img, phone, new Company(0, "Adv"));
        this.role = role;
    }

    public Advisor(int role) {
        this.role = role;
    }
    
    @Override
    int getRole() {
        return this.role;
    }
}

package advisor.models;

public class Manager extends Admin {

    public Manager() {
    }

    public Manager(int id, String name, String email, String password, String img, String phone, Company company) {
        super(id, name, email, password, img, phone, company);
    }

    public Manager(String name, String email, String password, String img, String phone, Company company) {
        super(name, email, password, img, phone, company);
    }

    @Override
    int getRole() {
        return Admin.MANAGER;
    }

}

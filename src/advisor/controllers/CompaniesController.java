package advisor.controllers;

import advisor.views.layout.LayoutController;
import advisor.Advisor;
import advisor.models.Company;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import advisor.views.companies.AddCompany;
import advisor.views.companies.CompanyDataController;
import advisor.views.companies.ComponentController;
import advisor.views.companies.List;
import advisor.views.layout.Warning;
import java.io.IOException;
import static advisor.Advisor.*;

public class CompaniesController {

    public static void addCompany() {
        LayoutController.showAlert(Advisor.component("companies/AddCompany"), 420, 350);
    }

    public static void editCompany(Company company) {
        try {
            FXMLLoader loader = getResourse(component("companies/AddCompany"));
            Node root = loader.load();
            AddCompany controller = (AddCompany) loader.getController();
            controller.bind(company);
            LayoutController.showAlert(root, 420, 350);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteCompany(Company company) {
        try {
            FXMLLoader loader = getResourse(component("layout/Warning"));
            Node item = loader.load();
            Warning warning = (Warning) loader.getController();
            warning.bind((event) -> {
                delete(company);
            });
            LayoutController.showAlert(item, 400, 320);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void save(Company company) {
        company.save();
        LayoutController.hideAlert();
    }

    public static void update(Company company) {
        company.update();
        LayoutController.hideAlert();
    }
    
    public static void delete(Company company) {
        company.delete();
        LayoutController.hideAlert();
    }

    private static Node generateComponent(Company company) {
        try {
            FXMLLoader loader = getResourse(component("companies/AccountComponent"));
            Node toReturn = loader.load();
            ComponentController controller = (ComponentController) loader.getController();
            controller.bind(company);
            return toReturn;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public static void viewAll() {
        try {
            FXMLLoader loader = getResourse(component("companies/List"));
            Node root = loader.load();
            List controller = (List) loader.getController();
            LayoutController.route(root);
            Company.findAll().forEach((company) -> {
                controller.addComponent(generateComponent(company));
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void companyData(Company company) {
        try {
            FXMLLoader loader = getResourse(component("companies/CompanyData"));
            Node item = loader.load();
            CompanyDataController controller = (CompanyDataController) loader.getController();
            controller.bind(company);
            LayoutController.route(item);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void addEmployee(Company company) {
        System.out.println("Add Employee");
    }
    
    public static void addManager(Company company) {
        System.out.println("Add Manager");
    }

}

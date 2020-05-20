package advisor.controllers;

import advisor.views.layout.LayoutController;
import advisor.Advisor;
import advisor.models.Admin;
import javafx.fxml.FXMLLoader;
import static advisor.Advisor.*;
import advisor.views.admins.AddAdmin;
import advisor.views.admins.AdminItem;
import advisor.views.admins.List;
import advisor.views.layout.Warning;
import java.io.IOException;
import javafx.scene.Node;

public class AdminsController {

    public static void addAdmin(boolean isEmployee) {
        try {
            FXMLLoader loader = getResourse(component("admins/AddAdmin"));
            Node root = loader.load();
            AddAdmin controller = (AddAdmin) loader.getController();
            controller.bind(isEmployee);
            LayoutController.showAlert(root, 500, 500);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void view(Admin admin) {
        LayoutController.route(Advisor.view(Advisor.component("accounts/Account")));
    }

    public static void addEmployee() {
        addAdmin(true);
    }

    public static void addAdvisor() {
        addAdmin(false);
    }

    public static void save(Admin admin) {
        admin.save();
        LayoutController.hideAlert();
    }

    private static Node generateComponent(Admin admin) {
        try {
            FXMLLoader loader = getResourse(component("admins/AdminItem"));
            Node toReturn = loader.load();
            AdminItem controller = (AdminItem) loader.getController();
            controller.bind(admin);
            return toReturn;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void viewAll() {
        try {
            FXMLLoader loader = getResourse(component("admins/List"));
            Node root = loader.load();
            List controller = (List) loader.getController();
            LayoutController.route(root);
            Admin.findAll().forEach((admin) -> {
                System.out.println(admin.getId() + "\t" + admin.getName());
                controller.addComponent(generateComponent(admin));
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void deleteAdmin(Admin admin) {
        System.out.println(admin.getId() + "\t" + admin.getName());
        try {
            FXMLLoader loader = getResourse(component("layout/Warning"));
            Node item = loader.load();
            Warning warning = (Warning) loader.getController();
            warning.bind((event) -> {
                delete(admin);
            });
            LayoutController.showAlert(item, 400, 320);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void viewAdmin(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void UpdateAdmin(Admin admin) {
        try {
            FXMLLoader loader = getResourse(component("admins/AddAdmin"));
            Node root = loader.load();
            AddAdmin controller = (AddAdmin) loader.getController();
            controller.bind(admin);
            LayoutController.showAlert(root, 500, 500);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void delete(Admin admin) {
        System.out.println(admin.getId() + "\t" + admin.getName());
        admin.delete();
        LayoutController.hideAlert();
    }

    public static void update(Admin admin) {
        admin.update();
        LayoutController.hideAlert();
    }
}

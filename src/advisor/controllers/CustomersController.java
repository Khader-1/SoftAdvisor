package advisor.controllers;

import advisor.views.layout.LayoutController;
import advisor.Advisor;
import static advisor.Advisor.component;
import static advisor.Advisor.getResourse;
import advisor.models.Customer;
import advisor.views.customers.AddCustomer;
import advisor.views.customers.CustomerDataController;
import advisor.views.customers.List;
import advisor.views.customers.CustomerItem;
import advisor.views.layout.Warning;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class CustomersController {

    public static void addCustomer() {
        LayoutController.showAlert(Advisor.component("customers/AddCustomer"), 440, 380);
    }

    public static void updateCustomer(Customer customer) {
        try {
            FXMLLoader loader = getResourse(component("customers/AddCustomer"));
            Node root = loader.load();
            AddCustomer controller = (AddCustomer) loader.getController();
            controller.bind(customer);
            LayoutController.showAlert(root, 440, 380);
        } catch (IOException ex) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void save(Customer customer) {
        customer.save();
        LayoutController.hideAlert();
    }

    private static Node generateComponent(Customer customer) {
        try {
            FXMLLoader loader = getResourse(component("customers/CustomerItem"));
            Node toReturn = loader.load();
            CustomerItem controller = (CustomerItem) loader.getController();
            controller.bind(customer);
            return toReturn;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void viewAll() {
        try {
            FXMLLoader loader = getResourse(component("customers/List"));
            Node root = loader.load();
            List controller = (List) loader.getController();
            LayoutController.route(root);
            Customer.findAll().forEach((customer) -> {
                controller.addComponent(generateComponent(customer));
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void addPayment(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void deleteCustomer(Customer customer) {
        try {
            FXMLLoader loader = getResourse(component("layout/Warning"));
            Node item = loader.load();
            Warning warning = (Warning) loader.getController();
            warning.bind((event) -> {
                delete(customer);
            });
            LayoutController.showAlert(item, 400, 320);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void delete(Customer customer) {
        customer.delete();
        LayoutController.hideAlert();
    }

    public static void CustomerData(Customer customer) {
        try {
            FXMLLoader loader = getResourse(component("customers/CustomerData"));
            Node root = loader.load();
            CustomerDataController controller = (CustomerDataController) loader.getController();
            controller.bind(customer);
            LayoutController.route(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void addPurchase(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void update(Customer customer) {
        customer.update();
        LayoutController.hideAlert();
    }

}

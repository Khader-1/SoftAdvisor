package advisor.views.customers;

import advisor.controllers.CustomersController;
import advisor.models.Customer;
import advisor.viewsManager.ImageViewer;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class CustomerItem implements Initializable {
    
    private Customer customer;
    
    @FXML
    Circle img;
    @FXML
    AnchorPane out;
    @FXML
    JFXButton details;
    @FXML
    Label id;
    @FXML
    JFXButton addDeal;
    @FXML
    JFXButton options;
    @FXML
    JFXButton update;
    @FXML
    JFXButton delete;
    @FXML
    JFXButton view;
    @FXML
    JFXButton payment;
    @FXML
    JFXButton purchase;
    @FXML
    AnchorPane menu;
    @FXML
    AnchorPane secMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        options.setOnMouseClicked((event) -> {
            collapseMenu(secMenu);
            toggleMenu(menu);
        });
        img.setOnMouseClicked((event) -> {
            hideMenus();
        });
        delete.setOnMouseClicked((event) -> {
            hideMenus();
        });
        out.setOnMouseClicked((event) -> {
            hideMenus();
        });
        update.setOnMouseClicked((event) -> {
            hideMenus();
        });
        view.setOnMouseClicked((event) -> {
            hideMenus();
        });
        addDeal.setOnMouseClicked((event) -> {
            collapseMenu(menu);
            toggleMenu(secMenu);
        });
        payment.setOnMouseClicked((event) -> {
            CustomersController.addPayment(customer);
        });
        purchase.setOnMouseClicked((event) -> {
            CustomersController.addPurchase(customer);
        });
        update.setOnMouseClicked((event) -> {
            CustomersController.updateCustomer(customer);
        });
        delete.setOnMouseClicked((event) -> {
            CustomersController.deleteCustomer(customer);
        });
        view.setOnMouseClicked((event) -> {
            CustomersController.CustomerData(customer);
        });
    }

    public void bind(Customer customer) {
        this.customer = customer;
        ImageViewer.view(img, customer.getImg());
        details.setText(customer.getName());
        id.setText(customer.getId() + "");
    }

    private void dropMenu(AnchorPane menu) {
        if (!isDropped(menu)) {
            menu.setPrefWidth(150);
            menu.setVisible(true);
        }
    }

    private void collapseMenu(AnchorPane menu) {
        if (isDropped(menu)) {
            menu.setPrefWidth(0);
            menu.setVisible(false);
        }
    }

    private boolean isDropped(AnchorPane menu) {
        return menu.isVisible();
    }

    private void toggleMenu(AnchorPane menu) {
        if (isDropped(menu)) {
            collapseMenu(menu);
        } else {
            dropMenu(menu);
        }
    }

    private void hideMenus() {
        if (isDropped(menu)) {
            collapseMenu(menu);
        }
    }

}

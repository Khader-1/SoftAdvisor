package advisor.views.admins;

import advisor.controllers.CustomersController;
import advisor.models.Customer;
import advisor.viewsManager.ImageViewer;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class AdminDataController implements Initializable {

    @FXML
    Label name;
    @FXML
    Circle img;
    @FXML
    JFXButton update;
    @FXML
    JFXButton delete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void bind(Customer customer) {
        this.name.setText(customer.getName());
        ImageViewer.view(img, customer.getImg());
        update.setOnMouseClicked((event) -> {
            CustomersController.updateCustomer(customer);
        });
        delete.setOnMouseClicked((event) -> {
            CustomersController.deleteCustomer(customer);
        });
    }

}

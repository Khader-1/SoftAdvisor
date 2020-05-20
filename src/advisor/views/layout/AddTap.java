package advisor.views.layout;

import advisor.controllers.AdminsController;
import advisor.controllers.CompaniesController;
import advisor.controllers.CustomersController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AddTap implements Initializable {

    @FXML
    JFXButton company;
    @FXML
    JFXButton customer;
    @FXML
    JFXButton employee;
    @FXML
    JFXButton advisor;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        company.setOnMouseClicked((event) -> {
            CompaniesController.addCompany();
        });
        customer.setOnMouseClicked((event) -> {
            CustomersController.addCustomer();
        });
        employee.setOnMouseClicked((event) -> {
            AdminsController.addEmployee();
//            AdminsController.addAdmin();
        });
        advisor.setOnMouseClicked((event) -> {
            AdminsController.addAdvisor();
//            AdminsController.addAdmin();
        });
    }

}

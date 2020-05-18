package advisor.views.admins;

import advisor.models.Company;
import advisor.views.layout.LayoutController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AddAdmin implements Initializable {

    @FXML
    JFXTextField name;
    @FXML
    JFXTextField email;
    @FXML
    JFXTextField address;
    @FXML
    JFXTextField phone;
    @FXML
    JFXPasswordField password;
    @FXML
    JFXPasswordField cpassword;
    @FXML
    JFXComboBox<Company> company;
    @FXML
    JFXComboBox<String> role;
    @FXML
    JFXButton save;
    @FXML
    JFXButton cancle;
    @FXML
    JFXButton close;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        save.setOnMouseClicked((event) -> {
        });
        cancle.setOnMouseClicked((event) -> {
            LayoutController.hideAlert();
        });
        close.setOnMouseClicked((event) -> {
            LayoutController.hideAlert();
        });
    }

}

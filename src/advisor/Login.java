package advisor;

import advisor.models.Admin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class Login implements Initializable {

    @FXML
    JFXButton submit;
    @FXML
    JFXButton cancle;
    @FXML
    JFXTextField email;
    @FXML
    JFXPasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit.setOnMouseClicked((event) -> {
            if(Admin.auth(this.email.getText(), this.password.getText())) {
                Advisor.route(Advisor.component("layout/Layout"));
            }else {
                System.out.println("Wrong");
            }
        });
        cancle.setOnMouseClicked((event) -> {
            System.exit(0);
        });
    }

}

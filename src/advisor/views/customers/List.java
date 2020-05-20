package advisor.views.customers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class List implements Initializable{

    @FXML
    JFXButton add;
    @FXML
    JFXButton search;
    @FXML
    VBox container;
    @FXML
    Label title;
   @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    public void addComponent(Node node) {
        this.container.getChildren().add(node);
        VBox.setMargin(node, new Insets(0, 0, 20, 0));
    }
    
}

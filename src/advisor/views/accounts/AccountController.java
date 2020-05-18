package advisor.views.account;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class AccountController implements Initializable{

    @FXML
    Label id;
    @FXML
    Label name;
    @FXML
    Label date;
    @FXML
    Label room;
    @FXML
    Label title;
    @FXML
    Label section;
    @FXML
    Label phone;
    @FXML
    Label email;
    @FXML
    Label address;
    @FXML
    Circle image;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        if (this.id.getText() == null) {
//            bind(Advisor.getAdmin());
//        }
//        if (this.id.getText().isEmpty()) {
//            bind(Advisor.getAdmin());
//        }
    }
    
//    public void bind(Admin admin) {
//        id.setText(admin.getId()+ "");
//        name.setText(admin.getName());
//        address.setText(admin.getAddress());
//        date.setText(admin.getBirthday());
//        section.setText(admin.getSection().getName());
//        room.setText(admin.getRoom());
//        email.setText(admin.getEmail());
//        phone.setText(admin.getPhone());
//        ImageViewer.view(image, admin.getImg());
//    }
//    
}

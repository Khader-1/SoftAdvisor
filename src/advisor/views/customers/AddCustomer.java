package advisor.views.customers;

import advisor.controllers.CustomersController;
import advisor.models.Customer;
import advisor.storage.FilesHandler;
import advisor.views.layout.LayoutController;
import advisor.viewsManager.ImageViewer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class AddCustomer implements Initializable {

    private String image;
    private boolean isEdit;
    private Customer customer;
    @FXML
    Label title;
    @FXML
    Rectangle img;
    @FXML
    JFXTextField name;
    @FXML
    JFXTextField address;
    @FXML
    JFXTextField phone;
    @FXML
    JFXButton save;
    @FXML
    JFXButton cancle;
    @FXML
    JFXButton close;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler<? super MouseEvent> handler = (event) -> {
            String temp = "cus" + Customer.generateId();
            String name = FilesHandler.getImage(temp);
            if (!temp.equals(name)) {
                System.out.println(FilesHandler.get(name).toURI().toString());
                img.setFill(new ImagePattern(new Image(FilesHandler.get(name).toURI().toString())));
                this.image = name;
            }
        };
        img.setOnMouseClicked(handler);
        save.setOnMouseClicked((event) -> {
            if (!isEdit) {
                CustomersController.save(new Customer(name.getText(), address.getText(), phone.getText(), image));
            } else {
                customer.setName(name.getText());
                customer.setAddress(address.getText());
                customer.setPhone(phone.getText());
                customer.setImg(image);
                CustomersController.update(customer);
            }
        });
        cancle.setOnMouseClicked((event) -> {
            LayoutController.hideAlert();
        });
        close.setOnMouseClicked((event) -> {
            LayoutController.hideAlert();
        });
    }

    public void bind(Customer customer) {
        title.setText("Update Customer");
        isEdit = true;
        this.customer = customer;
        this.name.setText(customer.getName());
        this.phone.setText(customer.getPhone());
        this.address.setText(customer.getAddress());
        this.image = customer.getImg();
        ImageViewer.view(img, image);
    }

}

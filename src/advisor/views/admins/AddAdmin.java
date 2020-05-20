package advisor.views.admins;

import advisor.controllers.AdminsController;
import advisor.models.Admin;
import advisor.models.Advisor;
import advisor.models.Company;
import advisor.models.Customer;
import advisor.models.Employee;
import advisor.models.Manager;
import advisor.storage.FilesHandler;
import advisor.views.layout.LayoutController;
import advisor.viewsManager.ImageViewer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.util.StringConverter;

public class AddAdmin implements Initializable {

    private boolean isEmployee;
    private boolean isEdit;
    private Admin admin;
    private String image;
    @FXML
    JFXTextField name;
    @FXML
    JFXTextField email;
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
    @FXML
    Rectangle img;
    @FXML
    Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler<? super MouseEvent> handler = (event) -> {
            String temp = "cus" + Customer.generateId();
            String name = FilesHandler.getImage(temp);
            if (!temp.equals(name)) {
                img.setFill(new ImagePattern(new Image(FilesHandler.get(name).toURI().toString())));
                this.image = name;
            }
        };
        img.setOnMouseClicked(handler);
        save.setOnMouseClicked((event) -> {
            int privellegs;
            if (isEmployee) {
                switch (role.getValue()) {
                    case "Manager":
                        if (isEdit) {
                            admin.setEmail(email.getText());
                            admin.setPhone(phone.getText());
                            admin.setName(name.getText());
                            admin.setImg(image);
                            admin.setCompany(company.getValue());
                            AdminsController.update(admin);
                            Manager admin = new Manager(this.admin.getId(), this.admin.getName(), this.admin.getEmail(), this.admin.getPassword(), image, this.admin.getPhone(), this.admin.getCompany());
                            AdminsController.update(admin);
                            return;
                        }
                        Manager m = new Manager(name.getText(), email.getText(), password.getText(), image, phone.getText(), company.getValue());
                        AdminsController.save(new Manager(name.getText(), email.getText(), password.getText(), image, phone.getText(), company.getValue()));
                        return;
                    case "Read Write":
                        privellegs = Admin.READ_WRITE;
                        break;
                    default:
                        privellegs = Admin.READ_ONLY;
                }
                if (isEdit) {
                    admin.setEmail(email.getText());
                    admin.setPhone(phone.getText());
                    admin.setName(name.getText());
                    admin.setImg(image);
                    admin.setCompany(company.getValue());
                    Employee admin = new Employee(this.admin.getId(), this.admin.getName(), this.admin.getEmail(), this.admin.getPassword(), image, this.admin.getPhone(), this.admin.getCompany(), privellegs);
                    AdminsController.update(admin);
                    return;
                }
                AdminsController.save(new Employee(name.getText(), email.getText(), password.getText(), image, phone.getText(), company.getValue(), privellegs));
            } else {
                switch (role.getValue()) {
                    case "Full Privellegs":
                        privellegs = Admin.FULL_PRIVELLEGS;
                        break;
                    default:
                        privellegs = Admin.ADVISOR;
                }
                if (isEdit) {
                    admin.setEmail(email.getText());
                    admin.setPhone(phone.getText());
                    admin.setName(name.getText());
                    admin.setImg(image);
                    Advisor admin = new Advisor(this.admin.getId(), this.admin.getName(), this.admin.getEmail(), this.admin.getPassword(), image, this.admin.getPhone(), privellegs);
                    AdminsController.update(admin);
                    return;
                }
                AdminsController.save(new Advisor(name.getText(), email.getText(), password.getText(), image, phone.getText(), privellegs));
            }
        });
        cancle.setOnMouseClicked((event) -> {
            LayoutController.hideAlert();
        });
        close.setOnMouseClicked((event) -> {
            LayoutController.hideAlert();
        });
    }

    public void bind(boolean isEmployee) {
        this.isEmployee = isEmployee;
        if (isEmployee) {
            company.setConverter(new StringConverter<Company>() {
                @Override
                public String toString(Company object) {
                    return object.getName();
                }

                @Override
                public Company fromString(String string) {
                    return Company.find(string);
                }
            });
            company.getItems().addAll(Company.findAll());
            role.getItems().addAll("Read Only", "Read Write", "Manager");
        } else {
            company.setVisible(false);
            role.getItems().addAll("Advisor", "Full Privellegs");
        }
    }

    public void bind(Admin admin) {
        this.admin = admin;
        isEdit = true;
        title.setText("Edit Admin");
        isEmployee = !(admin instanceof Advisor);
        bind(isEmployee);
        this.name.setText(admin.getName());
        this.email.setText(admin.getEmail());
        this.phone.setText(admin.getPhone());
        image = admin.getImg();
        ImageViewer.view(img, admin.getImg());
        if (isEmployee) {
            this.company.setValue(admin.getCompany());
        } else {
            company.setVisible(false);
        }
        password.setVisible(false);
        cpassword.setVisible(false);
    }
}

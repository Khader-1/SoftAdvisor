package advisor.views.companies;

import advisor.controllers.CompaniesController;
import advisor.models.Admin;
import advisor.models.Company;
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

public class AddCompany implements Initializable {

    private boolean isUpdate;
    private String image;
    private Company company;
    @FXML
    Rectangle img;
    @FXML
    JFXTextField name;
    @FXML
    JFXButton save;
    @FXML
    JFXButton cancle;
    @FXML
    JFXButton close;
    @FXML
    Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler<? super MouseEvent> handler = (event) -> {
            String temp = "com" + Company.generateId();
            String name = FilesHandler.getImage(temp);
            if (!temp.equals(name)) {
                System.out.println(FilesHandler.get(name).toURI().toString());
                img.setFill(new ImagePattern(new Image(FilesHandler.get(name).toURI().toString())));
                this.image = name;
            }
        };
        img.setOnMouseClicked(handler);
        save.setOnMouseClicked((event) -> {
            if (isUpdate) {
                company.setName(name.getText());
                company.setImage(image);
                CompaniesController.update(company);
            } else {
                CompaniesController.save(new advisor.models.Company(this.name.getText(), image));
            }
        });
        cancle.setOnMouseClicked((event) -> {
            LayoutController.hideAlert();
        });
        close.setOnMouseClicked((event) -> {
            LayoutController.hideAlert();
        });
    }

    public void bind(Company company) {
        this.company = company;
        title.setText("Updaete company");
        isUpdate = true;
        name.setText(company.getName());
        ImageViewer.view(img, company.getImage());
        image = company.getImage();
    }

}

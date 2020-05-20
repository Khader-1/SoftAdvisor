package advisor.views.companies;

import advisor.controllers.CompaniesController;
import advisor.models.Company;
import advisor.viewsManager.ImageViewer;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class CompanyDataController implements Initializable {

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

    public void bind(Company company) {
        this.name.setText(company.getName());
        ImageViewer.view(img, company.getImage());
        update.setOnMouseClicked((event) -> {
            CompaniesController.editCompany(company);
        });
        delete.setOnMouseClicked((event) -> {
            CompaniesController.deleteCompany(company);
        });
    }

}

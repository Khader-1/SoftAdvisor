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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ComponentController implements Initializable {

    private Company company;

    @FXML
    ImageView img;
    @FXML
    JFXButton details;
    @FXML
    JFXButton options;
    @FXML
    Label id;
    @FXML
    JFXButton delete;
    @FXML
    JFXButton update;
    @FXML
    JFXButton view;
    @FXML
    AnchorPane menu;
    @FXML
    JFXButton addEmployee;
    @FXML
    AnchorPane out;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        options.setOnMouseClicked((event) -> {
            toggleMenu(menu);
        });
        addEmployee.setOnMouseClicked((event) -> {
            hideMenus();
            CompaniesController.addManager(company);
        });
        img.setOnMouseClicked((event) -> {
            hideMenus();
        });
        delete.setOnMouseClicked((event) -> {
            CompaniesController.deleteCompany(company);
            hideMenus();
        });
        out.setOnMouseClicked((event) -> {
            hideMenus();
        });
        details.setOnMouseClicked((event) -> {
            hideMenus();
            CompaniesController.companyData(company);
        });
        update.setOnMouseClicked((event) -> {
            hideMenus();
            CompaniesController.editCompany(company);
        });
        view.setOnMouseClicked((event) -> {
            hideMenus();
            CompaniesController.companyData(company);
        });
        addEmployee.setOnMouseClicked((event) -> {
            hideMenus();
            CompaniesController.addEmployee(company);
        });
    }

    public void bind(Company company) {
        this.company = company;
        ImageViewer.view(img, company.getImage());
        details.setText(company.getName());
        id.setText(company.getId() + "");
    }

    private void dropMenu(AnchorPane menu) {
        if (!isDropped(menu)) {
            menu.setPrefWidth(150);
            menu.setVisible(true);
        }
    }

    private void collapseMenu(AnchorPane menu) {
        if (isDropped(menu)) {
            menu.setPrefWidth(0);
            menu.setVisible(false);
        }
    }

    private boolean isDropped(AnchorPane menu) {
        return menu.isVisible();
    }

    private void toggleMenu(AnchorPane menu) {
        if (isDropped(menu)) {
            collapseMenu(menu);
        } else {
            dropMenu(menu);
        }
    }

    private void hideMenus() {
        if (isDropped(menu)) {
            collapseMenu(menu);
        }
    }

}

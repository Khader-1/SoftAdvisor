package advisor.views.admins;

import advisor.controllers.AdminsController;
import advisor.models.Admin;
import advisor.models.Advisor;
import advisor.models.Employee;
import advisor.models.Manager;
import advisor.viewsManager.ImageViewer;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class AdminItem implements Initializable {

    public final String READ_ONLY = "LIBRARY";
    public final String WRITE = "PENCIL";
    public final String MANAGER = "ACCOUNT_STAR";
    public final String ADVISOR = "SHIELD";
    public final String FULL = "VERIFIED";
    private Admin admin;

    @FXML
    Circle img;
    @FXML
    AnchorPane out;
    @FXML
    JFXButton details;
    @FXML
    Label id;
    @FXML
    JFXButton addDeal;
    @FXML
    JFXButton options;
    @FXML
    JFXButton update;
    @FXML
    JFXButton delete;
    @FXML
    JFXButton view;
    @FXML
    AnchorPane menu;
    @FXML
    MaterialDesignIconView icon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        img.setOnMouseClicked((event) -> {
            AdminsController.viewAdmin(admin);
            hideMenus();
        });
        out.setOnMouseClicked((event) -> {
            hideMenus();
        });
        update.setOnMouseClicked((event) -> {
            AdminsController.UpdateAdmin(admin);
            hideMenus();
        });
        delete.setOnMouseClicked((event) -> {
            AdminsController.deleteAdmin(admin);
            hideMenus();
        });
        view.setOnMouseClicked((event) -> {
            AdminsController.viewAdmin(admin);
            hideMenus();
        });
        options.setOnMouseClicked((event) -> {
            toggleMenu(menu);
        });
    }

    public void bind(Admin admin) {
        System.out.println(admin.getId() + "\t" + admin.getName());
        this.admin = admin;
        ImageViewer.view(img, admin.getImg());
        details.setText(admin.getName());
        if (admin instanceof Advisor) {
            if (((Advisor) admin).getRole() == Admin.FULL_PRIVELLEGS) {
                this.id.setText("Full privellegs");
                icon.setGlyphName(FULL);
            } else {
                this.id.setText("Advisor");
                icon.setGlyphName(ADVISOR);
            }
        } else if (admin instanceof Manager) {
            this.id.setText("Manager");
            icon.setGlyphName(MANAGER);
        } else {
            try {
                System.out.println(admin);
                if (((Employee) admin).getRole() == Admin.READ_WRITE) {
                    this.id.setText("Content provider");
                    icon.setGlyphName(WRITE);
                } else {
                    this.id.setText("Read only");
                    icon.setGlyphName(READ_ONLY);
                }
            } catch (Exception e) {
                e.printStackTrace();
                id.setText("error");
                icon.setGlyphName(READ_ONLY);
            }
        }
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

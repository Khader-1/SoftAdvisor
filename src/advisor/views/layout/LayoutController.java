package advisor.views.layout;

import advisor.Advisor;
import advisor.Router;
import advisor.controllers.AdminsController;
import advisor.viewsManager.CustomTransition;
import advisor.viewsManager.ImageViewer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class LayoutController implements Initializable {

    private static LayoutController refernce;
    private final double duaration = 1;
    private final boolean startOpened = true;
    private final int maxWidth = 200;

    @FXML
    AnchorPane main;
    @FXML
    AnchorPane outer;
    @FXML
    ScrollPane menu;
    @FXML
    JFXHamburger ham;
    @FXML
    VBox container;
    @FXML
    AnchorPane alert;
    @FXML
    JFXButton close;
    @FXML
    Rectangle image;
    @FXML
    AnchorPane front;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = Advisor.getAdmin().getImg();
        ImageViewer.view(image, path);
        if (startOpened) {
            menu.setPrefWidth(maxWidth);
            menu.setMaxWidth(maxWidth);
            menu.setMinWidth(maxWidth);
            menu.setTranslateX(0);
        } else {
            menu.setPrefWidth(0);
            menu.setMinWidth(0);
            menu.setMaxWidth(0);
            menu.setTranslateX(- 1 * maxWidth);
        }
        HamburgerNextArrowBasicTransition t = new HamburgerNextArrowBasicTransition(ham);
        t.setRate(startOpened ? -1 : 1);
        t.setDelay(Duration.ONE);
        ham.setOnMouseClicked((event) -> {
            t.setRate(t.getRate() * -1);
            t.play();
            CustomTransition transition;
            if (t.getRate() > 0) {
                transition = CustomTransition.getTransition((frac) -> {
                    menu.setTranslateX((0 - frac) * maxWidth);
                    menu.setPrefWidth((1 - frac) * maxWidth);
                    menu.setMinWidth((1 - frac) * maxWidth);
                    menu.setMaxWidth((1 - frac) * maxWidth);

                });
            } else {
                transition = CustomTransition.getTransition((frac) -> {
                    menu.setTranslateX(((frac) - 1) * maxWidth);
                    menu.setPrefWidth((frac) * maxWidth);
                    menu.setMinWidth((frac) * maxWidth);
                    menu.setMaxWidth((frac) * maxWidth);

                });
            }
            transition.setCycleDuration(duaration * 1000);
            transition.play();

        });
        refernce = this;
        route(Advisor.view(Advisor.component("accounts/Account")));
        initNav();
        initTap();
    }

    private void ref(JFXButton btn, String path) {
        btn.setOnMouseClicked((event) -> {
            Parent parent = Advisor.view(path);
            route(parent);
            select(btn);
        });
    }

    private void ref(JFXButton btn, Node item) {
        btn.setOnMouseClicked((event) -> {
            route(item);
            select(btn);
        });
    }

    private void ref(JFXButton btn, String path, EventHandler<? super MouseEvent>... handlers) {
        ref(btn, path);
        appendHandler(btn, handlers[0]);
    }

    private void ref(JFXButton btn, Node item, EventHandler<? super MouseEvent>... handlers) {
        btn.setOnMouseClicked((event) -> {
            route(item);
        });
        select(btn);
        appendHandler(btn, handlers[0]);
    }

    private void select(JFXButton btn) {
        btn.setStyle("-fx-background-color: #424874;-fx-border-color: #f4eeff;-fx-border-radius:  5;-fx-background-radius:  5;");
        btn.setTextFill(Color.rgb(244, 238, 255, 1.0));
        GlyphIcon icon = nav.get(btn);
        icon.setFill(Color.rgb(244, 238, 255, 1.0));
    }

    private void selectTap(JFXButton btn) {
        taps.keySet().stream().map((button) -> {
            button.setStyle("-fx-background-radius:  10;");
            return button;
        }).map((button) -> taps.get(button)).forEachOrdered((icon) -> {
            icon.setFill(Color.rgb(220, 214, 247, 1.0));
        });
        btn.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: #424874; -fx-background-radius:  10;");
        GlyphIcon icon = taps.get(btn);
        icon.setFill(Color.rgb(66, 72, 116, 1.0));
    }

    private void refAlert(JFXButton btn, String path, double width, double hieght) {
        btn.setOnMouseClicked((event) -> {
            Parent parent = Advisor.view(path);
            refAlert(btn, parent, width, hieght);
        });

    }

    private void refAlert(JFXButton btn, Node node, double width, double hieght) {
        btn.setOnMouseClicked((event) -> {
            showAlert(node, width, hieght);
            nav.forEach((button, icon) -> {
                button.setStyle("-fx-background-color: #f4eeff;-fx-border-color:  #424874;-fx-border-radius:  5;-fx-background-radius:  5;");
                button.setTextFill(Color.rgb(66, 72, 116, 1.0));
                icon.setFill(Color.rgb(66, 72, 116, 1.0));
            });
            select(btn);
        });
    }

    private void refAlert(JFXButton btn, Node node, double width, double hieght, EventHandler<? super MouseEvent> handler) {
        refAlert(btn, node, width, hieght);
        appendHandler(btn, handler);
    }

    private void refAlert(JFXButton btn, String node, double width, double hieght, EventHandler<? super MouseEvent> handler) {
        Node item = Advisor.view(node);
        refAlert(btn, item, width, hieght);
    }

    private void ref(JFXButton btn, EventHandler<? super MouseEvent> handler) {
        btn.setOnMouseClicked((event) -> {
            nav.forEach((button, icon) -> {
                button.setStyle("-fx-background-color: #f4eeff;-fx-border-color:  #424874;-fx-border-radius:  5;-fx-background-radius:  5;");
                button.setTextFill(Color.rgb(66, 72, 116, 1.0));
                icon.setFill(Color.rgb(66, 72, 116, 1.0));
            });
            handler.handle(event);
            select(btn);
        });
    }

    private void refTap(JFXButton btn, EventHandler<? super MouseEvent> handler) {
        btn.setOnMouseClicked((event) -> {
            handler.handle(event);
            selectTap(btn);
        });
    }

    public static void route(Node root) {
        ObservableList<Node> nodes = refernce.container.getChildren();

        if (nodes.size() > 1) {
            Node temp = nodes.get(0);
            nodes.clear();
            nodes.add(temp);
        }
        nodes.addAll(root);
        VBox.setVgrow(root, Priority.ALWAYS);
        refernce.nav.forEach((button, icon) -> {
            button.setStyle("-fx-background-color:  #f4eeff;-fx-border-color:  #424874;-fx-border-radius:  5;-fx-background-radius:  5;");
            button.setTextFill(Color.rgb(66, 72, 116, 1.0));
            icon.setFill(Color.rgb(66, 72, 116, 1.0));
        });
    }

    @FXML
    JFXButton comsLink;
    @FXML
    FontAwesomeIconView comsIcon;
    @FXML
    JFXButton myCompany;
    @FXML
    FontAwesomeIconView myCompanyIcon;
    @FXML
    JFXButton customersLink;
    @FXML
    MaterialDesignIconView customersIcon;
    @FXML
    JFXButton adminsLink;
    @FXML
    FontAwesomeIconView adminsIcon;
    @FXML
    JFXButton statisticsLink;
    @FXML
    FontAwesomeIconView statisticsIcon;
    @FXML
    JFXButton searchLink;
    @FXML
    FontAwesomeIconView searchIcon;
    @FXML
    JFXButton exit;
    @FXML
    FontAwesomeIconView exitIcon;
    private final HashMap<JFXButton, GlyphIcon> nav = new HashMap<>();

    private void initNav() {
        nav.put(comsLink, comsIcon);
        nav.put(myCompany, myCompanyIcon);
        nav.put(customersLink, customersIcon);
        nav.put(adminsLink, adminsIcon);
        nav.put(statisticsLink, statisticsIcon);
        nav.put(searchLink, searchIcon);
        nav.put(exit, exitIcon);

        ref(comsLink, Advisor.component("accounts/Account"));
        ref(myCompany, (event) -> {
        });
        ref(customersLink, (event) -> {
        });
        ref(adminsLink, (event) -> {
        });
        ref(statisticsLink, (event) -> {
        });
        ref(searchLink, (event) -> {
        });
        exit.setOnMouseClicked((event) -> {
            System.exit(0);
        });
//        defualt(statisticsLink);
    }

    private void defualt(JFXButton button) {
        button.getOnMouseClicked().handle(null);
    }

    public static void showAlert(String path, double width, double hieght) {
        AnchorPane alertPane = refernce.alert;
        alertPane.getChildren().clear();
        try {
            FXMLLoader loader = (new Router().view(path));
            Parent root = loader.load();
            showAlert(root, width, hieght);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static void showAlert(Node root, double width, double hieght) {
        AnchorPane alertPane = refernce.alert;
        alertPane.getChildren().clear();
        AnchorPane.setBottomAnchor(alertPane, 0d);
        AnchorPane.setRightAnchor(alertPane, 0d);
        AnchorPane.setLeftAnchor(alertPane, 0d);
        AnchorPane.setTopAnchor(alertPane, 0d);
        AnchorPane transparent = new AnchorPane();
        transparent.setStyle("-fx-background-color: #000;");
        transparent.setOpacity(0.5);
        alertPane.getChildren().add(transparent);
        AnchorPane.setBottomAnchor(transparent, 0d);
        AnchorPane.setRightAnchor(transparent, 0d);
        AnchorPane.setLeftAnchor(transparent, 0d);
        AnchorPane.setTopAnchor(transparent, 0d);
        double rightLeft = Math.max(50, (refernce.main.getWidth() - width) / 2.0);
        double top = Math.max(70, (refernce.main.getHeight() - hieght) * 2 / 3.0);
        double bottom = Math.max(50, (refernce.main.getHeight() - hieght) / 3.0);
        alertPane.getChildren().add(root);
        AnchorPane.setBottomAnchor(root, bottom);
        AnchorPane.setRightAnchor(root, rightLeft);
        AnchorPane.setLeftAnchor(root, rightLeft);
        AnchorPane.setTopAnchor(root, top);
    }

    public static void hideAlert() {
        AnchorPane alertPane = refernce.alert;
        AnchorPane.clearConstraints(alertPane);
        alertPane.getChildren().clear();
    }

    public static void appendHandler(Node node, EventHandler<? super MouseEvent> anEvent) {
        EventHandler handler = node.getOnMouseClicked();
        node.setOnMouseClicked((event) -> {
            handler.handle(event);
            anEvent.handle(event);
        });
    }

    private final Map<JFXButton, GlyphIcon> taps = new HashMap<>();

    @FXML
    JFXButton home;
    @FXML
    FontAwesomeIconView homeIcon;
    @FXML
    JFXButton control;
    @FXML
    FontAwesomeIconView controlIcon;
    @FXML
    JFXButton add;
    @FXML
    FontAwesomeIconView addIcon;
    @FXML
    JFXButton accuont;
    @FXML
    FontAwesomeIconView accountIcon;
    @FXML
    JFXButton settings;
    @FXML
    FontAwesomeIconView settingsIcon;

    private void initTap() {
        taps.put(home, homeIcon);
        taps.put(control, controlIcon);
        taps.put(add, addIcon);
        taps.put(accuont, accountIcon);
        taps.put(settings, settingsIcon);
        refTap(control, (event) -> {
        });
        refTap(add, (event) -> {
            route(Advisor.view(Advisor.component("layout/AddTap")));
        });
        refTap(settings, (event) -> {
            AdminsController.addAdmin();
        });
        refTap(control, (event) -> {
        });
        refTap(home, (event) -> {
        });
        refTap(accuont, (event) -> {
        });
    }

}

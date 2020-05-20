package advisor.views.layout;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Warning implements Initializable {

    @FXML
    Rectangle image;
    @FXML
    JFXButton close;
    @FXML
    JFXButton submit;

    private EventHandler<? super MouseEvent> handler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setFill(new ImagePattern(new Image(getClass().getResource("/resources/image/warning.png").toExternalForm())));
        close.setOnMouseClicked((event) -> {
            LayoutController.hideAlert();
        });
        submit.setOnMouseClicked((event) -> {
            handler.handle(event);
            LayoutController.hideAlert();
        });
    }

    public void bind(EventHandler<? super MouseEvent> handler) {
        this.handler = handler;
    }
}

package advisor.viewsManager;

import java.io.File;
import java.net.URI;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImagePicker {

    public static File pick(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterGIF = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterGIF, extFilterJPEG);
        File file = fileChooser.showOpenDialog(stage);
        return file;
    }

    public static ImageView pickAndView(Stage stage) {
        ImageView node = new ImageView();
        URI uri = pick(stage).toURI();
        if (uri == null) {
            return null;
        }
        node.setImage(new Image(uri.toString()));
        Rectangle clip = new Rectangle(node.getFitWidth(), node.getFitHeight());
        clip.setArcHeight(20);
        clip.setArcWidth(20);
        node.setClip(node);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = node.snapshot(parameters, null);
        node.setClip(null);
        node.setEffect(new DropShadow(20, Color.BLACK));
        node.setImage(image);
        return node;
    }
}

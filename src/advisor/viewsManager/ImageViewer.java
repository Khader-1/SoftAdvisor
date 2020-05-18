package advisor.viewsManager;

import advisor.filesManager.FilesHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;

public class ImageViewer {

    public static void view(Shape n, String path) {
        if (path != null) {
            if (!path.isEmpty()) {
                try {
                    String name = FilesHandler.get(path).toURI().toString();
                    if (name != null) {
                        if (name.startsWith("file")) {
                            Image image = new Image(name);
                            n.setFill(new ImagePattern(image));
                        }
                    }
                } catch (Exception e) {
                }
            }
        }

    }
}

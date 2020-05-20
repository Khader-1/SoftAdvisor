package advisor.viewsManager;

import advisor.storage.FilesHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;

public class ImageViewer {

    public static void view(Shape n, String path) {
        Image image = view(path);
        if (image != null) {
            n.setFill(new ImagePattern(image));
        }
    }

    public static void view(ImageView n, String path) {
        Image image = view(path);
        if (image != null) {
            double port = Math.min(image.getWidth(), image.getHeight());
            n.setViewport(new Rectangle2D(0, 0, port, port));
            n.setImage(image);
        }
    }

    public static Image view(String path) {
        if (path != null) {
            if (!path.isEmpty()) {
                try {
                    String name = FilesHandler.get(path).toURI().toString();
                    if (name != null) {
                        if (name.startsWith("file")) {
                            return new Image(name);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

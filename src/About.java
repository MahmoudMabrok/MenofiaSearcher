import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class About {
    Desktop desktop = Desktop.getDesktop();
    public About() {
        Pane root = new Pane();
        Stage aboutStage = new Stage();
        ImageView aboutImage = new ImageView(new Image("About2.png"));

        System.out.println(aboutImage.getX() + " " + aboutImage.getY());
        root.setOnMousePressed(e -> {
            aboutStage.close();
            System.out.println(e.getX() + "  mm " + e.getY());
        });
        Button sourceCode = new Button("Open GitHub");
        sourceCode.setLayoutX(208);
        sourceCode.setLayoutY(405);
        sourceCode.setOnAction(e -> {
            try {
                URL gitLink = new URL("https://github.com/MahmoudMabrok/MenofiaSearcher");
                if (Desktop.isDesktopSupported()) {
                    desktop.browse(gitLink.toURI());
                }

            } catch (URISyntaxException ux) {
                System.out.println(ux.toString());
            } catch (MalformedURLException m) {
                System.out.println(m.toString());
            } catch (IOException io) {
                System.out.println(io.toString());
            }
        });

        root.getChildren().addAll(aboutImage, sourceCode);
    Scene sc = new Scene(root) ;
    aboutStage.setScene(sc);
    aboutStage.setTitle("About App.");
        aboutStage.setResizable(false);
        aboutStage.show();
    }
}

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class About {
    public About() {
        Pane root = new Pane() ;
        Stage aboutStage = new Stage() ;
    ImageView aboutImage = new ImageView(new Image("About2.png"));
    root.getChildren().add(aboutImage) ;
    Scene sc = new Scene(root) ;
    aboutStage.setScene(sc);
    aboutStage.setTitle("About App.");
    aboutStage.showAndWait();
    }
}

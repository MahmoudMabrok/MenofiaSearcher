import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Alert {

    public static void showAlert(String text) {
        Pane root = new Pane();
        Stage alertStage = new Stage();
        Label lAlert = new Label(text);
        lAlert.setFont(Font.font(24));
        lAlert.setStyle("-fx-fill:green");
        root.getChildren().add(lAlert);

        //action
        root.setOnMousePressed(e -> {
            alertStage.close();
        });

        alertStage.setScene(new Scene(root));
        alertStage.setTitle("Info !! ");
        alertStage.show();

    }
}

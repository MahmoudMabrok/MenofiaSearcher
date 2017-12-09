import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Home extends Application {
    Scene homeScene  ;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox(20);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);

        //UI Controls
        ImageView imUniverId = new ImageView(new Image("uni_ar.png"));
        Button bCustome = new Button("Custom Seracher ") ;
        Button bPrepared = new Button("Prepared Seracher ") ;
        root.getChildren().addAll(imUniverId,bCustome ,bPrepared) ;
        homeScene = new Scene(root);

        //UI actions
        bCustome.setOnAction((ActionEvent e) ->{
            CustomeScene cs = new CustomeScene();
            primaryStage.setScene(cs.initSecne(homeScene));
        });
        bPrepared.setOnAction(e->{
            PreparedScene ps = new PreparedScene() ;
            primaryStage.setScene(ps.initSecne(homeScene));
        });

        primaryStage.setScene(homeScene );
        primaryStage.setResizable(false);
        primaryStage.setTitle("MenoufiaSeracher");
        primaryStage.show();

    }
}

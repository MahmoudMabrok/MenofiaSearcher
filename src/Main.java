import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main extends Application {

    Test t = new Test() ;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox p =  new VBox (20);
        p.setPadding(new Insets(15));
        p.setAlignment(Pos.CENTER);


      /*  t.addNeighbor(t.getCity("Sadat") ,t.getCity("Ashmon") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );
        t.addNeighbor(t.getCity("Sadat") ,t.getCity("Menouf") );

*/
      //addPane
      VBox addPane = new VBox(15) ;
        TextField tname = new TextField () ;
        TextField tx = new TextField () ;
        TextField ty = new TextField () ;

        tname.setPromptText("name of city ");
        tx.setPromptText("x of city");
        ty.setPromptText("y of city ");

        //neibour
        System.out.println("sdsa" + t.cityNames);
        ObservableList<String> cname = FXCollections.observableArrayList(t.cityNames) ;

        ComboBox<String> com  = new ComboBox<String>(cname) ;
        ComboBox<String> com2  = new ComboBox<>(cname) ;

        //feature not select same city
/*
        com.valueProperty().addListener(e->{
            System.out.println("a");
            com2.getItems().remove(com.getValue()) ;

        });
*/

        Button addR = new Button("addRelation") ;


        Button add = new Button("addCity") ;
        add.setOnAction(e-> {
            try {
                String name = tname.getText();
                int x = Integer.parseInt(tx.getText());
                int y = Integer.parseInt(ty.getText());
                t.addCity(name , x , y );
                com.getItems().setAll(t.cityNames) ;
            }catch (InputMismatchException i )
            {
            }



        });

        HBox addC = new HBox(10);
        addC.setAlignment(Pos.CENTER);
        addC.getChildren().addAll(tname , tx , ty , add) ;

        HBox haddR = new HBox(10);
        haddR.setAlignment(Pos.CENTER);
        haddR.getChildren().addAll(com , com2 ,addR) ;
        addPane.getChildren().addAll(addC , haddR) ;


        p.setOnMousePressed(e->
        {
            System.out.println(e.getX() +"   " +  e.getY());
        });

        circlePane cp = new circlePane() ;
        Pane pDrwa = new Pane ();
        p.setStyle("-fx-background-color:yellow");


        addR.setOnAction(e->{
            if (com.getValue() != "" && com2.getValue() != "") {
                t.addNeighbor(t.getCity(com.getValue()), t.getCity(com2.getValue()));
                t.addNeighbor(t.getCity(com2.getValue()), t.getCity(com.getValue()) );
                cp.addLine(t.getCity(com.getValue()), t.getCity(com2.getValue()));

            }
            t.getCity(com.getValue()).dispalyN();
        });

        pDrwa.getChildren().addAll(cp , t.getPaneLine()) ;
        //System.out.println(t.getPoly());

        p.getChildren().addAll( addPane , pDrwa ) ;

        //handle draw action
        p.setOnMousePressed(e->{
            if (e.getButton() == MouseButton.SECONDARY)
            {
                cp.setPane(t.getData());
                if (t.getData().size() > 1 )
                cp.addLine(t.getCity(com.getValue()), t.getCity(com2.getValue()));
               // t.addPoint(); //imp.
            }
        });

        Scene s = new Scene(p ,700, 600) ;
        primaryStage.setScene(s);
         primaryStage.show();

    }



}

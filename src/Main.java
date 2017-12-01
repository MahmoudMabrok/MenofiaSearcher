import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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

        VBox p =  new VBox ();
        p.setAlignment(Pos.CENTER);

       /* t.addCity( "Sadat" ,62 ,405 );
        t.addCity( "Ashmon" ,460 ,457 );
        t.addCity( "Bagor" ,509 ,327 );
        t.addCity( "Sers" ,405 ,304 );
        t.addCity( "Menouf" ,419 ,270 );//
        t.addCity( "Shohada" ,400 ,150 );
        t.addCity( "Tala" ,435 ,55 );
        t.addCity( "Shebin" ,200,480 );
        t.addCity( "quensa" ,600,215 );
        t.addCity( "Berkat" ,560,120);*/

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
        // com.getItems().addAll(cityNames.toArray()) ;


        ComboBox<String> com2  = new ComboBox<>(cname) ;

        //feature not select same city
/*
        com.valueProperty().addListener(e->{
            System.out.println("a");
            com2.getItems().remove(com.getValue()) ;

        });
*/

        Button addR = new Button("addRelation") ;
        addR.setOnAction(e->{
            if (com.getValue() != "" && com2.getValue() != "")
                t.addNeighbor(t.getCity(com.getValue()) , t.getCity(com2.getValue()));
            t.getCity(com.getValue()).dispalyN();
        });

        Button add = new Button("addCity") ;
        add.setOnAction(e-> {
            try {
                String name = tname.getText();
                //cityNames.add(name) ;
                int x = Integer.parseInt(tx.getText());
                int y = Integer.parseInt(ty.getText());
                t.addCity(name , x , y );
                com.getItems().setAll(t.cityNames) ;
            }catch (InputMismatchException i )
            {
            }



        });

        HBox addC = new HBox(10);
        addC.getChildren().addAll(tname , tx , ty , add) ;

        HBox haddR = new HBox(10);
        haddR.getChildren().addAll(com , com2 ,addR) ;
        addPane.getChildren().addAll(addC , haddR) ;


        p.setOnMousePressed(e->
        {
            System.out.println(e.getX() +"   " +  e.getY());
        });

        circlePane cp = new circlePane( t.getData() ) ;
        Pane pDrwa = new Pane ();
        p.setStyle("-fx-background-color:yellow");

        t.addPoint(); //imp.

        pDrwa.getChildren().addAll(cp , t.getPaneLine()) ;
        //System.out.println(t.getPoly());

        p.getChildren().addAll( addPane , pDrwa ) ;

        Scene s = new Scene(p ,700, 600) ;
        primaryStage.setScene(s);
         primaryStage.show();

    }



}

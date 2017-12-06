import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class Main extends Application {
    Test t = new Test() ;
    BFS b = new BFS() ;
    ArrayList <String> aloNames = new ArrayList<>() ;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        //add avaible alo
        aloNames.add( "BFS") ;
        //root pane
        VBox p =  new VBox (20);
        p.setPadding(new Insets(15));
        p.setAlignment(Pos.CENTER);

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
        ObservableList<String> cname = FXCollections.
                observableArrayList(t.cityNames) ;

        ObservableList<String> obAlnames = FXCollections.
                observableArrayList(aloNames) ;

        ComboBox<String> com  = new ComboBox<String>(cname) ;
        ComboBox<String> com2  = new ComboBox<>(cname) ;
        ComboBox<String> comAlo  = new ComboBox<String>(obAlnames) ;

        //feature not select same city
/*
        com.valueProperty().addListener(e->{
            System.out.println("a");
            com2.getItems().remove(com.getValue()) ;

        });
*/


        Button addR = new Button("addRelation") ;
        Button startAlo = new Button("yala nabhas ") ;
        Button add = new Button("addCity") ;
        add.setOnAction(e-> {
            try {
                String name = tname.getText();
                int x = Integer.parseInt(tx.getText().trim());
                int y = Integer.parseInt(ty.getText().trim());
                t.addCity(name , x , y );
                com.getItems().setAll(t.cityNames) ;
            }catch (InputMismatchException i )
            {

            }

        });

        startAlo.setOnAction(e->{
                String c1 = com.getValue() ;
                String c2 = com2.getValue() ;
                City  cStart = t.getCity(c1) ;
                City  cEnd = t.getCity(c2) ;
                if (comAlo.getValue() == "BFS")
                {
                    System.out.println("BFS");
                    ArrayList<City> temp = b.bfsStart(t.getData() , cStart ,cEnd) ;
                    System.out.println(temp );
                    if ( temp  != null)
                    {
                        System.out.println("inside");
                        for (City c :temp  ) {
                            System.out.println(c.name);
                        }
                    }
                }


        });


        HBox addC = new HBox(10);
        addC.setAlignment(Pos.CENTER);
        addC.getChildren().addAll(tname , tx , ty , add) ;

        HBox haddR = new HBox(10);
        haddR.setAlignment(Pos.CENTER);
        haddR.getChildren().addAll(com , com2 ,addR  ) ;

        HBox selectAlgo  = new HBox(10);
        selectAlgo.setAlignment(Pos.CENTER);
        selectAlgo.getChildren().addAll(comAlo ,startAlo) ;


        //global pane
        addPane.getChildren().addAll(addC , haddR ,selectAlgo ) ;

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

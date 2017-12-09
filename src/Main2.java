import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main2 extends Application {
    Test2 t = new Test2() ;
    ArrayList <String> aloNames = new ArrayList<>() ;
    Stage newOne = new Stage() ;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        //add avaible alo
        String[] algonames = new String[]{ "", "BFS" , "DFS" , "Greedy", "A*"} ;

        //  aloNames.add( algonames ) ;
        

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
                observableArrayList(algonames) ;

        ComboBox<String> com  = new ComboBox<String>(cname) ;
        ComboBox<String> com2  = new ComboBox<>(cname) ;
        ComboBox<String> comAlo  = new ComboBox<String>(obAlnames) ;


        Button addR = new Button("addRelation") ;
        Button add = new Button("addCity") ;
        Button drawPath  = new Button("drawPath ") ;
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

        drawPath.setOnAction((ActionEvent e) ->{

            String c1 = com.getValue() ;
            String c2 = com2.getValue() ;
            String c3 = comAlo.getValue() ;
            City  cStart = t.getCity(c1) ;
            City  cEnd = t.getCity(c2) ;

            System.out.println("satart " + cStart.name);
            System.out.println("end " + cEnd.name);

            HBox hroot = new HBox() ;
            hroot.setPadding(new Insets(50));
            hroot.setAlignment(Pos.CENTER);
            Pane r = new Pane();

            r.getChildren().clear();
            r.setPadding(new Insets(50));
            System.out.println(t.getData().size());
            cp.setPane(t.getData());
            r.getChildren().add(cp.getPane()); //by this i draw shapes

            Pane visted = null;
            if (c3 == "BFS") {
                BridthFirst b = new BridthFirst();
                b.BFS_search(cStart, cEnd);
                visted = getPath(b.visited) ;
                r.getChildren().add(visted );
            }
            else if (c3 == "DFS")
            {


            }
            else if (c3 == "Greedy")
            {


            }
            else if (c3 =="A*")
            {


            }


           r.setOnMousePressed((MouseEvent h) ->{
               r.getChildren().remove(1);
           });

            hroot.getChildren().add(r) ;
            newOne.setScene(new Scene(hroot));
            newOne.setTitle("Path from " + c1 + " to " + c2);
            newOne.show();
        });

        HBox addC = new HBox(10);
        addC.setAlignment(Pos.CENTER);
        addC.getChildren().addAll(tname , tx , ty , add) ;

        HBox haddR = new HBox(10);
        haddR.setAlignment(Pos.CENTER);
        haddR.getChildren().addAll(com , com2 ,addR  ) ;

        HBox selectAlgo  = new HBox(10);
        selectAlgo.setAlignment(Pos.CENTER);
        selectAlgo.getChildren().addAll(comAlo , drawPath) ;

        //global pane
        addPane.getChildren().addAll(addC , haddR ,selectAlgo ) ;

        Pane pDrwa = new Pane ();
        p.setStyle("-fx-background-color:yellow");

        addR.setOnAction(e->{
            if (com.getValue() != "" && com2.getValue() != "" &&
                    com.getValue() !=com2.getValue()) {
                t.addNeighbor(t.getCity(com.getValue()), t.getCity(com2.getValue()));
                t.addNeighbor(t.getCity(com2.getValue()), t.getCity(com.getValue()) );
            }
            t.getCity(com.getValue()).dispalyN();
        });
        pDrwa.getChildren().addAll(cp , t.getPaneLine()) ;
        p.getChildren().addAll( addPane , pDrwa ) ;

        Scene s = new Scene(p ) ;
        //handle draw action
       p.setOnMousePressed(e->{
            if (e.getButton() == MouseButton.SECONDARY)
            {
              t.getData().clear();
              cname.clear();
              t.cityNames.clear();
                System.out.println(t.getData().size());
            }
        });


        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);

        primaryStage.setScene(s );
        primaryStage.show();
    }

    public Pane   getPath (ArrayList<City> data){
        Pane pn = new Pane() ;
        Polyline p = new Polyline() ;
        ObservableList<Double> points = p.getPoints() ;


        for (City c :data
             ) {
            points.add( Double.valueOf( c.getX()) ) ;
            points.add( Double.valueOf(c.getY()) );
        }

        Circle cpath = new Circle() ;
        cpath.setRadius(15);
        cpath.setFill(Color.BLUE);


        PathTransition animation = new PathTransition() ;
        animation.setNode(cpath);
        animation.setPath(p) ;
        animation.play();
        animation.setCycleCount(2);
        animation.setDuration(Duration.seconds(5));

        pn.getChildren().addAll(cpath) ;

        return  pn ;

    }


}


import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.util.Collections;
import java.util.InputMismatchException;

public class PreparedScene {

    Test t = new Test() ;
    Stage drawStage = new Stage() ;
    circlePane cp = new circlePane() ;
    Scene custemSc ;
    boolean isHome = false ;


    public  Scene initSecne ( Scene homeScene){
        // Scene custemSc ;
        String[] algonames = new String[]{ "Draw", "BFS" , "DFS" , "Greedy", "A*"} ;
      //  String[] cityNames  =new String[]{ "Sadat", "Ashmoun" , "Bajour" , "Menouf", "Sers",
                        //                    "Shohada" ,"Tala","Shebin","Quesna","Berkat elsan3" } ;

        t.getPreparedData(); //intial  data set




        //root pane
        VBox p =  new VBox (25);
        p.setPadding(new Insets(15));
        p.setAlignment(Pos.CENTER);




        //neibour

        ObservableList<String> cname = FXCollections.
                observableArrayList(t.cityNames) ;

        ObservableList<String> obAlnames = FXCollections.
                observableArrayList(algonames) ;

        ComboBox<String> comSource  = new ComboBox<String>(cname) ;
        ComboBox<String> comDestina  = new ComboBox<>(cname) ;
        ComboBox<String> comAlo  = new ComboBox<String>(obAlnames) ;

        ToggleGroup tg = new ToggleGroup() ;
        RadioButton rPath = new RadioButton("Path") ;
        rPath.setToggleGroup(tg);
        RadioButton rTraverse = new RadioButton("Traverse") ;
        rTraverse.setToggleGroup(tg);

        Button drawPath  = new Button("drawPath ") ;

        drawPath.setOnAction((ActionEvent e) ->{

            String c1 = comSource.getValue() ;
            String c2 = comDestina.getValue() ;
            String c3 = comAlo.getValue() ;
            City  cStart = t.getCity(c1) ;
            City  cEnd = t.getCity(c2) ;


            System.out.println(cStart);
            System.out.println(cEnd);
            System.out.println("satart " + cStart.name);
            System.out.println("end " + cEnd.name);

            HBox hroot = new HBox() ;
            hroot.setPadding(new Insets(50));
            hroot.setAlignment(Pos.CENTER);
            Pane drawPane = new Pane();

            drawPane.getChildren().clear();
            drawPane.setPadding(new Insets(50));
            cp.setPane(t.getData());
            drawPane.getChildren().add(cp.getPane()); //by this i draw shapes


                Pane visted = null;
                if (c3 == "BFS") {
                    BridthFirst b = new BridthFirst();
                    b.BFS_search(cStart, cEnd);
                    //select path or traverse
                    System.out.println(tg.selectedToggleProperty().toString());
                    if (tg.selectedToggleProperty().toString().contains("Path"))
                        visted = getPath(b.visited);
                    else
                        visted = getPath(b.visited);



                } else if (c3 == "DFS") {
                    DepthFirst d = new DepthFirst();
                    d.DFS_search(cStart, cEnd);
                    //select path or traverse
                    System.out.println(tg.selectedToggleProperty().toString());
                    if (tg.selectedToggleProperty().toString().contains("Path"))
                        visted = getPath(d.path);
                    else
                        visted = getPath(d.visited);




                } else if (c3 == "Greedy") {
                    Greedy b = new Greedy();
                    b.Greedy_search(cStart, cEnd);
                    System.out.println(b.path.size());
                    //select path or traverse
                    System.out.println(tg.selectedToggleProperty().toString());
                    if (tg.selectedToggleProperty().toString().contains("Path")) {
                        visted = getPath(b.path);
                        System.out.println("Ya Allah");
                    } else
                        visted = getPath(b.visited);


                } else if (c3 == "A*") {


                }

                if(visted != null )
            drawPane.getChildren().add(visted);

            drawPane.setOnMousePressed((MouseEvent h) ->{
                if (drawPane.getChildren().size() > 1 )
                drawPane.getChildren().remove(1);
                drawStage.close();
            });

            hroot.getChildren().add(drawPane) ;
            drawStage.setScene(new Scene(hroot));
            drawStage.setTitle("Path from " + c1 + " to " + c2);
            drawStage.show();
        });


        HBox selectAlgo  = new HBox(10);
        selectAlgo.setAlignment(Pos.CENTER);
        selectAlgo.getChildren().addAll( comSource,comDestina,comAlo ,rPath,rTraverse,drawPath) ;

        Button returnHome = new Button("returnHome") ;
        p.getChildren().addAll( selectAlgo , returnHome  ) ;

        custemSc = new Scene(p);

        //clear feature


        returnHome.setOnAction(e->{
            isHome  = true ;
        });

        if (!isHome)
            return  custemSc ;
        else
            return  homeScene ;
    }

    public  static Pane getPath (ArrayList<City> data){
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
        animation.setRate(0.1);
        animation.play();
        animation.setCycleCount(2);
        animation.setDuration(Duration.seconds(50));

        pn.getChildren().addAll(cpath) ;

        return  pn ;

    }

}
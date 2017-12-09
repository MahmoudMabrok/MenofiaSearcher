import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class CustomeScene {
    Test t = new Test() ;
    ArrayList <String> aloNames = new ArrayList<>() ;
    Stage drawStage = new Stage() ;
    circlePane cp = new circlePane() ;
    Scene custemSc ;
    boolean isHome = false ;


    public  Scene initSecne ( Scene homeScene){
       // Scene custemSc ;
        String[] algonames = new String[]{ "Draw", "BFS" , "DFS" , "Greedy", "A*"} ;

        //root pane
        VBox p =  new VBox (25);
        p.setPadding(new Insets(15));
        p.setAlignment(Pos.CENTER);


        VBox addPane = new VBox(15) ;
        TextField CityName = new TextField () ;
        TextField cityX = new TextField () ;
        TextField cityY = new TextField () ;
        TextField cityRealistic = new TextField ("0") ;

        CityName.setPromptText("name of city ");
        cityX.setPromptText("x of city");
        cityY.setPromptText("y of city ");

        //neibour
        System.out.println("sdsa" + t.cityNames);
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

        Button addRelation = new Button("addRelation") ;
        Button addCity = new Button("addCity") ;
        Button drawPath  = new Button("drawPath ") ;

        addCity.setOnAction(e-> {
            try {
                String name = CityName.getText();
                int x = Integer.parseInt(cityX.getText().trim());
                int y = Integer.parseInt(cityY.getText().trim());
                t.addCity(name , x , y );
                comSource.getItems().setAll(t.cityNames) ;
            }catch (InputMismatchException i )
            {
              cityX.setText("ERROR ");
              cityY.setText("ERROR ");
            }

        });

        drawPath.setOnAction((ActionEvent e) ->{

            String c1 = comSource.getValue() ;
            String c2 = comDestina.getValue() ;
            String c3 = comAlo.getValue() ;
            City  cStart = t.getCity(c1) ;
            City  cEnd = t.getCity(c2) ;

            System.out.println("satart " + cStart.name);
            System.out.println("end " + cEnd.name);

            HBox hroot = new HBox() ;
            hroot.setPadding(new Insets(50));
            hroot.setAlignment(Pos.CENTER);
            Pane drawPane = new Pane();

            drawPane.getChildren().clear();
            drawPane.setPadding(new Insets(50));
            System.out.println(t.getData().size());
            cp.setPane(t.getData());
            drawPane.getChildren().add(cp.getPane()); //by this i draw shapes

            Pane visted = null;
            if (c3 == "BFS") {
                BridthFirst b = new BridthFirst();
                b.BFS_search(cStart, cEnd);
                //select path or traverse
                System.out.println(tg.selectedToggleProperty().toString());
                if (tg.selectedToggleProperty().toString().contains("Path"))
                    visted = getPath(b.queue) ;
                else
                    visted = getPath(b.visited) ;


                drawPane.getChildren().add(visted );
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


            drawPane.setOnMousePressed((MouseEvent h) ->{
                drawPane.getChildren().remove(1);
            });

            hroot.getChildren().add(drawPane) ;
            drawStage.setScene(new Scene(hroot));
            drawStage.setTitle("Path from " + c1 + " to " + c2);
            drawStage.show();
        });

        HBox addC = new HBox(10);
        addC.setAlignment(Pos.CENTER);
        addC.getChildren().addAll(CityName , cityX , cityY , addCity) ;

        HBox addRelPane = new HBox(10);
        addRelPane.setAlignment(Pos.CENTER);
        addRelPane.getChildren().addAll(comSource , comDestina ,cityRealistic ,addRelation  ) ;

        HBox selectAlgo  = new HBox(10);
        selectAlgo.setAlignment(Pos.CENTER);
        selectAlgo.getChildren().addAll(comAlo ,rPath,rTraverse,drawPath) ;

        //global pane
        addPane.getChildren().addAll(addC , addRelPane ,selectAlgo ) ;



        addRelation.setOnAction(e->{
            if (comSource.getValue() != "" && comDestina.getValue() != "" &&
                    comSource.getValue() !=comDestina.getValue()) {
                Double realistic = Double.parseDouble(cityRealistic.getText()) ;
                t.addNeighbor(t.getCity(comSource.getValue()),
                        t.getCity(comDestina.getValue()) ,
                        realistic );
                t.addNeighbor(t.getCity(comDestina.getValue()),
                        t.getCity(comSource.getValue()) ,
                        realistic);

            }
            t.getCity(comSource.getValue()).dispalyN();
        });

        Button returnHome = new Button("returnHome") ;
        p.getChildren().addAll( addPane , returnHome  ) ;

        custemSc = new Scene(p);

        //clear feature
        /*
        p.setOnMousePressed(e->{
            if (e.getButton() == MouseButton.SECONDARY)
            {
                t.getData().clear();
                cname.clear();
                t.cityNames.clear();
                System.out.println(t.getData().size());
            }
        });*/

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
        animation.play();
        animation.setRate(0.1);
        animation.setCycleCount(2);
        animation.setDuration(Duration.seconds(50));

        pn.getChildren().addAll(cpath) ;

        return  pn ;

    }

}

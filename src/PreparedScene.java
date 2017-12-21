import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class PreparedScene {

    Test t = new Test() ;
    Stage drawStage = new Stage() ;
    circlePane cp = new circlePane() ;
    Scene prepScene;
    Stage preparedStage = new Stage();


    public void initSecne() {
        // Scene prepScene ;
        String[] algonames = new String[]{ "Draw", "BFS" , "DFS" , "Greedy", "A*"} ;

        t.prepareData(); //intial  data set

        //root pane
        VBox p = new VBox(15);
        p.setPadding(new Insets(15));
        p.setAlignment(Pos.CENTER);

        //neibour
        ObservableList<String> cname = FXCollections.
                observableArrayList(t.cityNames);

        ObservableList<String> tempNames = FXCollections.
                observableArrayList(t.cityNames) ;

        ObservableList<String> obAlnames = FXCollections.
                observableArrayList(algonames) ;

        ComboBox<String> comSource  = new ComboBox<String>(cname) ;
        comSource.setValue("Sadat");
        ComboBox<String> comDestina  = new ComboBox<>(cname) ;
        comDestina.setValue("Tala");
        ComboBox<String> comAlo  = new ComboBox<String>(obAlnames) ;
        comAlo.setValue("Draw");

        Button drawPath = new Button("drawPath ");

        ToggleGroup tg = new ToggleGroup() ;
        RadioButton rPath = new RadioButton("Path") ;
        rPath.setToggleGroup(tg);
        rPath.setSelected(true);
        RadioButton rTraverse = new RadioButton("Traverse") ;
        rTraverse.setToggleGroup(tg);

        HBox explainPane = new HBox(55);
        Label ls = new Label("Select Source");
        Label ld = new Label("Select Goal   ");
        Label la = new Label("Select algorithm");
        explainPane.getChildren().addAll(ls, ld, la);
        p.getChildren().add(explainPane);

        comDestina.valueProperty().addListener(e -> {
            if (comDestina.getValue().equals(comSource.getValue()))
                drawPath.setDisable(true);
            else
                drawPath.setDisable(false);


        });

        comSource.valueProperty().addListener(e -> {
            if (comSource.getValue().equals(comDestina.getValue()))
                drawPath.setDisable(true);
            else
                drawPath.setDisable(false);

        });


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
            drawPane.setPadding(new Insets(15));
            cp.setPane(t.getData());
            drawPane.getChildren().add(cp.getPane()); //by this i draw shapes

            Label lAlert = new Label("");
            lAlert.setFont(Font.font(24));
            lAlert.setStyle("-fx-text-color:green");

            Pane visted = null;
                if (c3 == "BFS") {
                    BridthFirst b = new BridthFirst();
                    b.BFS_search(cStart, cEnd);
                    //select path or traverse
                    System.out.println(tg.selectedToggleProperty().toString());
                    if (tg.selectedToggleProperty().toString().contains("Path"))
                        visted = getPath(b.path);
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

                    if (b.path.get(b.path.size() - 1) != cEnd) {
                        lAlert.setText("As u know Greedy is greedy\n so not complete");
                    } else {
                        lAlert.setText("from your luck greedy find path ");
                    }
                    System.out.println("i called getPrepared");
                    // t.prepareData();//return data to its initial state
                } else if (c3 == "A*") {

                    A_Star b = new A_Star();
                    b.A_Search(cStart, cEnd);

                    System.out.println(tg.selectedToggleProperty().toString());
                    if (tg.selectedToggleProperty().toString().contains("Path")) {
                        visted = getPath(b.path);
                        System.out.println("Ya Allah");
                    } else
                        visted = getPath(b.path);

                }

            if (visted != null) {
                drawPane.getChildren().add(lAlert);
                drawPane.getChildren().add(visted);
            }
            drawPane.setOnMousePressed((MouseEvent h) ->{
                if (drawPane.getChildren().size() > 1 )
                drawPane.getChildren().remove(1);
                drawStage.close();
            });
            // hroot.setStyle("-fx-background-color:yellow");
            hroot.getChildren().add(drawPane) ;
            drawStage.setScene(new Scene(hroot));
            drawStage.setTitle("Path from " + c1 + " to " + c2);
            drawStage.getIcons().add(new Image("icon1.png")) ;
            drawStage.showAndWait();

        });

        HBox selectAlgo  = new HBox(10);
        selectAlgo.setAlignment(Pos.CENTER);
        selectAlgo.getChildren().addAll( comSource,comDestina,comAlo ,rPath,rTraverse,drawPath) ;

        Button returnHome = new Button("returnHome") ;
        p.getChildren().addAll( selectAlgo , returnHome  ) ;
        prepScene = new Scene(p);

        returnHome.setOnAction(e->{
            preparedStage.close();
        });

        preparedStage.setScene(prepScene);
        preparedStage.setTitle("Prepared Data");
        preparedStage.getIcons().add(new Image ("icon1.png")) ;
        preparedStage.show();
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
        cpath.setRadius(10);
        cpath.setFill(Color.BLUEVIOLET);

        PathTransition animation = new PathTransition() ;
        animation.setNode(cpath);
        animation.setPath(p) ;
        animation.setRate(0.1);
        animation.play();
        animation.setCycleCount(1);
        animation.setDuration(Duration.seconds(10));

        pn.getChildren().addAll(cpath) ;

        return  pn ;

    }

}
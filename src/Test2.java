

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Test2 {
    public  ArrayList<String> cityNames = new ArrayList<>() ;

    private    ArrayList<City> data  = new ArrayList<>() ;
  /*private    ArrayList<Circle> points = new ArrayList<>() ;*/

  /* Polyline pl =new Polyline();
    ObservableList<Double> point = pl.getPoints() ;
*/

    Pane pL = new Pane() ;
    ObservableList<Node> point = FXCollections.
            observableArrayList(pL.getChildren());


    public void addCity(String name , int x , int y  )
    {

        data.add(new City( x , y  , name ));
        cityNames.add(name) ;
        // point.add(Double.valueOf(x)) ;
        // point.add(Double.valueOf(y)) ;

    }

/*
    public  void addPoint() {
        System.out.println("a1  " + data.size());
        System.out.println("a2  " + point.size());
        for (City c :data
             ) {
          //  point.add(Double.valueOf(c.getX())) ;
          //  point.add(Double.valueOf(c.getY())) ;
            for (City c2 :c.getNeighbor()
                 ) {
               // point.add(Double.valueOf(c2.getX())) ;
               // point.add(Double.valueOf(c2.getY())) ;
                pL.getChildren().add(new Line(c.getX(),c.getY() ,c2.getX() , c2.getY())) ;

            }
        }
    }*/

    public void addNeighbor (City p , City c  )
    {
        p.getNeighbor().add(c);

    }
    public City getCity (String name ){
        for (City c: data
                ) {
            if (c.name == name)
                return  c ;
        }
        return  null ;
    }

    public ArrayList<City> getData() {
        City c1 = new City( 30,150,"a1");

        City c2 = new City(60,180,"a2");
        City c3 = new City(10,230,"a3");
        City c4 = new City(60,120,"a4");
        City c5 = new City(100,140,"a5");
        City c6 = new City(100,100,"a6");
        City c7 = new City(130,160,"a7");
        City c8 = new City(90,170,"a8");

        c1.addNeighbor(c2);
        c1.addNeighbor(c3);
        c1.addNeighbor(c4);
        c4.addNeighbor(c5);
        c4.addNeighbor(c6);
        c5.addNeighbor(c7);
        c5.addNeighbor(c8);

        c2.addNeighbor(c1);
        c3.addNeighbor(c1);
        c4.addNeighbor(c1);
        c5.addNeighbor(c4);
        c6.addNeighbor(c4);
        c7.addNeighbor(c5);
        c8.addNeighbor(c5);

        data.add(c1);
        data.add(c2);
        data.add(c3);
        data.add(c4);
        data.add(c5);
        data.add(c6);
        data.add(c7);
        data.add(c8);

        for (City c : data ) {
            cityNames.add(c.name) ;
        }
        return data;
    }

    public Pane getPaneLine()
    {
        System.out.println(point);
        pL.getChildren().addAll(point);
        return  pL;
    }
}

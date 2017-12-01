import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Test {
    public  ArrayList<String> cityNames = new ArrayList<>() ;

  private    ArrayList<City> data  = new ArrayList<>() ;
  private    ArrayList<Circle> points = new ArrayList<>() ;
      ArrayList<String> status = new ArrayList<>() ;
   /* Polyline pl =new Polyline();
    ObservableList<Double> point = pl.getPoints() ;
*/

   Pane pL = new Pane() ;
   ObservableList<Node> point = FXCollections.observableArrayList(pL.getChildren());


    public void addCity(String name , int x , int y  )
    {

        data.add(new City( x , y  , name ));
        cityNames.add(name) ;
       // point.add(Double.valueOf(x)) ;
       // point.add(Double.valueOf(y)) ;

    }


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
    }
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
        return data;
    }

    public Pane getPaneLine()
    {
      //  pL.getChildren().add(new  Line (10,10 ,100,100 ) ) ;
        System.out.println(point);
        pL.getChildren().addAll(point);
        return  pL;
    }
}

class circlePane extends Pane {
    public circlePane() {
    }

    public  void setPane (ArrayList<City> cl  )
    {
        for (City c  : cl ) {
            this.getChildren().add(new Circle(c.getX(), c.getY(), 10));

        }
    }
    public void addLine (City c , City c2)
    {
        this.getChildren().add(new Line(c.getX(),c.getY() ,c2.getX() , c2.getY())) ;

    }
}



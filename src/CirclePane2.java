import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.util.ArrayList;

class circlePane2 extends Pane {

    public circlePane2() {
    }

    public  void setPane (ArrayList<City> cl  )
    {
        Label l ;
        //  Polyline p = new Polyline() ;
        // ObservableList<Double> points = p.getPoints() ;
        // this.getChildren().add(p);

        /*
        points.add(Double.valueOf(c.getX())) ;
        points.add(Double.valueOf(c.getY())) ;
        */
        for (City c  : cl ) {
            this.getChildren().add(new Circle(c.getX(), c.getY(), 10));

            l=new Label(c.name) ;
            l.setLayoutX(c.getX());
            l.setLayoutY(c.getY() );
            l.setTextFill(Color.RED);
            l.setFont(Font.font(25));
            this.getChildren().add(l);
        }
        int startx ,starty ;
        int endx,endy ;
        Line ln = null  ;
        for (City c :cl){

            startx = c.getX() ;
            starty = c.getY() ;
            for(City cn : c.getNeighbor())
            {
                ln = new Line( startx,starty,cn.getX(),cn.getY()) ;
                getChildren().add(ln) ;
            }
        }

    }
    /* public void addLine (City c , City c2)
     {
         this.getChildren().add(new Line(c.getX(),c.getY()
                                         ,c2.getX() , c2.getY())) ;

     }*/
    public Pane getPane()
    {
        return this ;
    }
}



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

public class Test {
    public  ArrayList<String> cityNames = new ArrayList<>() ;

  private    ArrayList<City> data  = new ArrayList<>() ;



    public void addCity(String name , int x , int y  )
    {

        data.add(new City( x , y  , name ));
        cityNames.add(name) ;

    }

    public void addNeighbor (City p , City c   , double realistic  )
    {
        p.getNeighbor().add(c);
        p.getRealistic().add(realistic) ;

    }
    public void addNeighborPrepared (City p , City c   , double realistic  )
    {
        p.getNeighbor().add(c);
        c.getNeighbor().add(p) ;
        p.getRealistic().add(realistic) ;
        c.getRealistic().add(realistic) ;

    }
    public City getCity (String name ){
        for (City c: data
             ) {
            if (c.name == name)
                return  c ;
        }
        return  null ;
    }
    public void getPreparedData (){

        City Sadat = new City( 32,405,"Sadat");
        City Ashmoun = new City(460,457,"Ashmoun");
        City Bajour = new City(509,327,"Bajour");
        City Menouf = new City(419,285,"Menouf");
        City Sers = new City(405,304,"Sers");
        City Shohada = new City(400,150,"Shohada");
        City Tala = new City(435,55,"Tala");
        City Shebin = new City(480,200,"Shebin");
        City Quesna = new City(600,315,"Quesna");
        City Berkat_elsan3 = new City(560,120,"Berkat elsan3");

        addNeighborPrepared( Sadat,Ashmoun ,  75) ;
        addNeighborPrepared( Sadat,Menouf ,  57.7) ;
        addNeighborPrepared( Menouf,Sers ,  7.2) ;
        addNeighborPrepared( Menouf,Shohada ,  22.8) ;
        addNeighborPrepared( Sers,Bajour ,  8.3) ;
        addNeighborPrepared( Menouf,Shebin ,  17.9) ;
        addNeighborPrepared( Tala,Shebin ,  16.3) ;
        addNeighborPrepared( Shebin,Quesna ,  18.3) ;
        addNeighborPrepared( Shebin,Berkat_elsan3 ,  14.3) ;
        addNeighborPrepared( Quesna,Berkat_elsan3 ,  17.1) ;

        data.add(Sadat) ;
        data.add(Menouf) ;
        data.add(Shebin) ;
        data.add(Shohada) ;
        data.add(Sers) ;
        data.add(Ashmoun) ;
        data.add(Tala) ;
        data.add(Quesna) ;
        data.add(Bajour) ;
        data.add(Berkat_elsan3) ;

        for (City c  : data ) {
            cityNames.add(c.name);
        }

        //return data ;

    }

    public ArrayList<City> getData() {
        return data;
    }

}

class circlePane extends Pane {

    public circlePane() {
    }

    public  void setPane (ArrayList<City> cl  )
    {
        Label l ;

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

    public Pane getPane()
    {
        return this ;
    }
}



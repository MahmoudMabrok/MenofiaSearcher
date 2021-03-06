import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Test {


    public  ArrayList<String> cityNames = new ArrayList<>() ;

  private    ArrayList<City> data  = new ArrayList<>() ;



    public void addCity(String name , int x , int y  )
    {

        data.add(new City( x , y  , name ));
        cityNames.add(name) ;

    }

    public void addNeighbor(City p, City c)
    {
        p.getNeighbor().add(c);


    }

    public void addNeighborPrepared(City p, City c)
    {

        p.addNeighbor(c);
        c.addNeighbor(p);

    }
    public City getCity (String name ){
        for (City c: data
             ) {
            if (c.name == name)
                return  c ;
        }
        return  null ;
    }

    /**
     * used to compute all distance to goal
     * then sort neighbour based on this distance
     */

    public void prepareHeuristic(City goal)
    {

        for (City c :data
             ) {
            c.setHeuristic(goal);
        }
    }

    public void prepareData() {
        data.clear();//
        City Sadat = new City( 62,405,"Sadat");
        City Ashmoun = new City(460,457,"Ashmoun");
        City Bajour = new City(509,327,"Bajour");
        City Menouf = new City(419,285,"Menouf");
        City Sers = new City(405,304,"Sers");
        City Shohada = new City(400,150,"Shohada");
        City Tala = new City(435,55,"Tala");
        City Tanta = new City(460, 20, "Tanta");
        City MahalaQubra = new City(500, 5, "MahalaQubra");
        City Shebin = new City(480,200,"Shebin");
        City Quesna = new City(600,315,"Quesna");
        City Berkat_elsan3 = new City(560,120,"Berkat elsan3");
       /* City rlnoubaria = new City(33,33,"ElNoubaria");
        City Dalangate = new City(560,120,"Dalangate");*/
        City banha = new City(610, 450, "Banha");
        City Zagazig = new City(700, 450, "Zagazig");
        City ShebinQnatar = new City(750, 520, "ShebinQnatar");

      /*  City Berkat_elsan3 = new City(560,120,"Berkat elsan3");
        City Berkat_elsan3 = new City(560,120,"Berkat elsan3");
        City Berkat_elsan3 = new City(560,120,"Berkat elsan3");*/
        Font.font(15);

        addNeighborPrepared(Sadat, Ashmoun);
        addNeighborPrepared(Sadat, Menouf);
        addNeighborPrepared(Menouf, Sers);
        addNeighborPrepared(Menouf, Shohada);
        addNeighborPrepared(Sers, Bajour);
        addNeighborPrepared(Menouf, Shebin);
        addNeighborPrepared(Tala, Shebin);
        addNeighborPrepared(Shohada, Shebin);
        addNeighborPrepared(Tala, Shohada);
        addNeighborPrepared(Tala, Berkat_elsan3);
        addNeighborPrepared(Shebin, Quesna);
        addNeighborPrepared(Bajour, Shebin);
        addNeighborPrepared(Bajour, Quesna);
        addNeighborPrepared(Shebin, Berkat_elsan3);
        addNeighborPrepared(Quesna, Berkat_elsan3);

        addNeighborPrepared(banha, Shebin);
        addNeighborPrepared(banha, Bajour);
        addNeighborPrepared(banha, Quesna);

        addNeighborPrepared(banha, Zagazig);

        addNeighborPrepared(ShebinQnatar, Zagazig);
        addNeighborPrepared(ShebinQnatar, banha);

        addNeighborPrepared(Tanta, Tala);
        addNeighborPrepared(Tanta, Shebin);
        addNeighborPrepared(Tanta, Quesna);
        addNeighborPrepared(Tanta, Berkat_elsan3);

        addNeighborPrepared(Tanta, MahalaQubra);



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
        data.add(banha);
        data.add(Zagazig);
        data.add(ShebinQnatar);
        data.add(Tanta);
        data.add(MahalaQubra);

        for (City c  : data ) {
            cityNames.add(c.name);
        }

        // prepareAllDistance(); called to set Distance
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
            Circle cr = new Circle(c.getX(), c.getY(), 5);
            cr.setFill(Color.GREEN);
            if (DataSet.capital_list.contains(c.name)) {
                Rectangle rg = new Rectangle(c.getX() - 5, c.getY() - 5, 10, 10);
                rg.setFill(Color.BLUE);
                this.getChildren().add(rg);
            } else {
                this.getChildren().add(cr);
            }
            l=new Label(c.name) ;
            l.setFont(Font.font(c.name, FontWeight.BOLD, 18));
            l.setLayoutX(c.getX() - 15);
            l.setLayoutY(c.getY() - 25);
            l.setTextFill(Color.RED);
            this.getChildren().add(l);
        }
        int startx ,starty ;
        Line ln = null  ;
        QuadCurve q = null;
        for (City c :cl){

            startx = c.getX() ;
            starty = c.getY() ;
            for(City cn : c.getNeighbor())
            {
                int cx = (int) (Math.abs(startx - cn.getX())) / 2;
                int cy = (int) (Math.abs(starty - cn.getY())) / 2;
                ln = new Line( startx,starty,cn.getX(),cn.getY()) ;
                // q = new QuadCurve ( startx,starty,cx ,cy, cn.getX(),cn.getY()) ;
                // q.setStroke(Color.WHITE);
                ln.setStroke(Color.GREEN);
                getChildren().add(ln) ;
            }
        }


    }

    public Pane getPane()
    {
        return this ;
    }
}



import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class City {

    String name ;

    public ArrayList<City> getNeighbor() {
        return neighbor;
    }

    public int getX() {

        return x;
    }

    public int getY() {
        return y;
    }

    private int x ,y ;

   private ArrayList <City> neighbor = new ArrayList<>() ;
   private ArrayList <Double > distance  = new ArrayList<>() ;
   private ArrayList <Double> realistic   = new ArrayList<>() ;

    public City(String name) {
        this.name = name;
    }


    public City(int x, int y , String name) {
        this(name) ;
        this.x = x;
        this.y = y;

    }

    public void sortCityBasedDistance ()
    {
        ArrayList <Double > temp   = getRealistic() ;
        ArrayList <City > tempCity   =  new ArrayList<>() ;
        Collections.sort(realistic);




        int [] cityIndexes = new int[neighbor.size()];
        int i = 0 ;
        for(Double d : realistic)
        {
            cityIndexes[i] = realistic.indexOf(d) ;
            System.out.println("dd  "+cityIndexes[i]);
            i++;
        }
        i=0 ;

        for (City c :neighbor
             ) {
          //  System.out.println(i);
            tempCity.add( cityIndexes[i], c);
            i++;
        }
        neighbor.clear();
        for (City c: tempCity
             ) {
            this.neighbor.add(c);
        }

    }


    public ArrayList<Double > getRealistic() {
        return realistic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<Double> getDistance() {
        return distance;
    }

    public void  addNeighbor(City c ){

        double d = (Math.sqrt( Math.pow( (Math.abs(x-c.x)), 2 ) -  Math.pow( Math.abs(y-c.y), 2 ))) ;
        neighbor.add(c);
        distance.add(d);


    }

    public void dispalyN ()
    {

        System.out.println(name + " --> ");
        for (City e:neighbor) {
            System.out.println("\t\t"+ e.name + "  dis is  " );
        }
    }
}

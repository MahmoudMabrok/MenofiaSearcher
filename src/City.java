
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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
   private ArrayList <Double > heuristic    = new ArrayList<>() ;

    public City(String name) {
        this.name = name;
    }


    public City(int x, int y , String name) {
        this(name) ;
        this.x = x;
        this.y = y;

    }
   /* public   void  setDistToGoal (City goal )
    {
        double d ;
        this.distance.clear();
        for (City c : neighbor ){
            d = (Math.sqrt( Math.pow( (Math.abs(goal.x-c.x)), 2 ) +  Math.pow( Math.abs(goal.y-c.y), 2 ))) ;
            distance.add(d);
        }
        sortCityBasedDistance();

    }*/
    public   void  setDistToGoal ()
    {
        double d ;
        this.distance.clear();
        for (City c : neighbor ){
            d = (Math.sqrt( Math.pow( (Math.abs(x-c.x)), 2 ) +  Math.pow( Math.abs(y-c.y), 2 ))) ;
            distance.add(d);
        }

    }

    public ArrayList<Double> getHeuristic() {
        return heuristic;
    }

    public void setHeuristic (City goal ){

        List<String> cities = Arrays.asList(Data.cities);

        for(City c : neighbor ){
            heuristic.add( Data.hs[cities.indexOf(c.name)][cities.indexOf(goal.name)]);
        }
    }

    public void sortCityBasedDistance ()
    {
        ArrayList <Double > temp   =new ArrayList<>() ;
        for (Double d : distance
             ) {
            temp.add(d);
        }
        ArrayList <City > tempCity   =  new ArrayList<>() ;
        for (City d : neighbor
                ) {
            tempCity.add(d);
        }

        int minIndex  = 0 ;

        neighbor.clear();
       for (City c: tempCity
             ) {
            minIndex = distance.indexOf((Double) Collections.min(temp)) ;
           // System.out.println("minIndex  " + minIndex  );
            temp.remove((Double) Collections.min(temp)) ;
            neighbor.add(tempCity.get((int)minIndex))  ;
            //System.out.println("sss nn " + neighbor.size());

        }
       // System.out.println("first in ne after  " + neighbor.get(0).name);

         Collections.sort(distance);

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

      //  double d = (Math.sqrt( Math.pow( (Math.abs(x-c.x)), 2 ) -  Math.pow( Math.abs(y-c.y), 2 ))) ;
        neighbor.add(c);
       //  distance.add(d);


    }

    public void dispalyN ()
    {

        System.out.println(name + " --> ");
        for (City e:neighbor) {
            System.out.println("\t\t"+ e.name + "  dis is  " );
        }
    }


}

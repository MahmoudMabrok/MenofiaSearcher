import java.util.ArrayList;
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
   /* private  HashMap <City ,Integer > neighbor  = new HashMap<>();
    private  HashMap <City ,Integer > neighbor  = new HashMap<>();
*/
   private ArrayList <City> neighbor = new ArrayList<>() ;
   private ArrayList <Integer> distance  = new ArrayList<>() ;
   private ArrayList <Integer> realistic   = new ArrayList<>() ;

    public City(String name) {
        this.name = name;
    }


    public City(int x, int y , String name) {
        this(name) ;
        this.x = x;
        this.y = y;

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

    public void  addNeighbor(City c ){

        int d = (int)(Math.sqrt( Math.pow( (Math.abs(x-c.x)), 2 ) -  Math.pow( Math.abs(y-c.y), 2 ))) ;
        neighbor.add(c);
        distance.add(d);
    }

    public void dispalyN ()
    {
        System.out.println(name + " --> ");
        for (City e:neighbor) {
            System.out.println("\t\t"+ e.name);
        }
    }
}

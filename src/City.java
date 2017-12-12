
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class City {

    String name ;
    private int x ,y ;
    private ArrayList<City> neighbor = new ArrayList<>();
    private ArrayList<Double> heuristic = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }

    public City(int x, int y, String name) {
        this(name);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<City> getNeighbor() {
        return neighbor;
    }

    public ArrayList<Double> getHeuristic() {
        return heuristic;
    }

    /**
     * compute distance
     *
     * @param start
     * @param goal
     * @return distance
     */
    public static double getDistanceBetCity(City start, City goal) {
        return (Math.sqrt(Math.pow((Math.abs(start.x - goal.x)), 2) + Math.pow(Math.abs(start.y - goal.y), 2)));
    }

    /**
     * compute heuristic to all neighbor to goal
     *then re-arrange neighbour based on  heuristic
     * @param goal
     */
    public void setHeuristic (City goal) {
        List<String> cities = Arrays.asList(DataSet.cities);
        System.out.println("n of neighbour " + neighbor.size());
        for(City c : neighbor) {
            heuristic.add(DataSet.hs[cities.indexOf(c.name)][cities.indexOf(goal.name)]);
        }
        System.out.println("n h for  " + name + "   " + heuristic.size());
        System.out.println("heuristic for " + name + " is  " + heuristic);
        System.out.println("neghbour for " + name + " is " + neighbor);
        sortCityBasedHeuristic();
        System.out.println("after sort heuristic for " + name + " is  " + heuristic);
        System.out.println("neghbour after sort for " + name + " is " + neighbor  );
    }
    /**
     * compute heuristic between two cities
     *
     * @param start
     * @param goal
     * @return heuristic value from dataSet
     */
    public static double getHeuristicBetCity(City start, City goal) {
        List<String> cities = Arrays.asList(DataSet.cities);
        return (DataSet.hs[cities.indexOf(start.name)][cities.indexOf(goal.name)]);
    }

    /**
     * sort neighbor based on heuristic
     */
    public void sortCityBasedHeuristic  () {
        ArrayList <Double > temp   =new ArrayList<>();
        for (Double d : heuristic
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
            minIndex = heuristic.indexOf((Double) Collections.min(temp));
            System.out.println("index  " + minIndex);
            // System.out.println("minIndex  " + minIndex  );
            temp.remove((Double) Collections.min(temp)) ;
            neighbor.add(tempCity.get((int)minIndex))  ;
            //System.out.println("sss nn " + neighbor.size());
        }
        // System.out.println("first in ne after  " + neighbor.get(0).name);
        Collections.sort(heuristic);
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

    /**
     * add neighbor
     * @param c  neighbor city to add
     */
    public void  addNeighbor(City c ){
        neighbor.add(c);
    }

    /**
     * display  neighbor of this city
     */
    public void dispalyN () {
        System.out.println(name + " --> ");
        for (City e:neighbor) {
            System.out.println("\t\t"+ e.name + "  dis is  " );
        }
    }


}

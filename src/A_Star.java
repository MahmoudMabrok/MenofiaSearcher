
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Ahmed-PC
 */
public class A_Star {
    ArrayList<Data> opend = new ArrayList();
    ArrayList<City> closed = new ArrayList();
    ArrayList<City> path = new ArrayList();

    public void A_Search(City initial, City goal) {
        ArrayList<City> list = new ArrayList();
        opend.add(new Data(initial, list));
        ASearch(goal);
    }

    public void ASearch(City goal) {
//        if(opend.size()==0)
//            return;
        Data min = opend.get(0);
        for (int i = 1; i < opend.size(); i++) {
            if (min.cost + City.getHeuristicBetCity(min.city, goal) > opend.get(i).cost + City.getHeuristicBetCity(opend.get(i).city, goal)) {
                min = opend.get(i);
            }
        }
        path = new ArrayList();
        for (City c : min.path) {
            path.add(c);
        }
        closed.add(min.city);
        opend.remove(min);

        for (City city : min.city.getNeighbor()) {
            if ((closed.contains(city) == false) && (hasOpendChild(city) || city == goal)) {
                opend.add(new Data(city, path));
            }
            if (path.get(path.size() - 1) == goal) {
                return;
            }
        }
        ASearch(goal);
    }

    public boolean hasOpendChild(City c) {
        for (City city : c.getNeighbor()) {
            if (!closed.contains(city)) {
                return true;
            }
        }
        return false;
    }
}

class Data {
    public City city = new City(" ");
    ArrayList<City> path = new ArrayList();
    double cost = 0;

    public Data(City c, ArrayList<City> list) {
        city = c;
        for (City city1 : list) {
            path.add(city1);
        }
        path.add(c);
        if (path.size() > 1) {
            for (int i = 1; i < path.size(); i++) {
                cost += City.getDistanceBetCity(path.get(i - 1), path.get(i));
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Ahmed-PC
 */
public class Greedy {
    ArrayList<City> visited = new ArrayList();
    ArrayList<City> path = new ArrayList();
    boolean flag = false;

    public  void Greedy_search(City initial,City goal){
        visited.add(initial);
        path.add(initial);
        if(initial == goal){
            flag = true;
            return;
        }
        if(flag == false){
            if( ! visited.contains(initial.getNeighbor().get(0) ))
                Greedy_search(initial.getNeighbor().get(0), goal);
            else if(initial.getNeighbor().size() > 1)
                Greedy_search(initial.getNeighbor().get(1), goal);
        }
    }
}

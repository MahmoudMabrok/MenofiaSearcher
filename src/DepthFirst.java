import java.util.ArrayList;
/**
 *
 * @author Ahmed-PC
 */

public class DepthFirst {
    ArrayList<City> visited = new ArrayList<>();
    ArrayList<City> path = new ArrayList<>();
    int n = 0;
    boolean flag = false;

    public void DFS_search(City initial, City goal){
        DFS(initial, goal);
        while(path.get(path.size()-1) != goal){
            path.remove(path.size()-1);
        }
    }

    public void DFS(City initial, City goal){
        if(flag == false){
            if(n+1 > path.size())
                path.add(initial);
            else
                path.set(n, initial);
            if(initial == goal){
                flag = true;
            }
            n++;
            visited.add(initial);
            for (City city : initial.getNeighbor()) {
                if(!visited.contains(city)){
                    DFS(city, goal);
                }
            }
            n--;
        }
    }

}

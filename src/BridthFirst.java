import java.util.ArrayList;

public class BridthFirst {
    ArrayList<City> visited = new ArrayList<>();
    ArrayList<City> queue = new ArrayList<>();
    static City[] path = new City[10];
    static int n = 0;

    public  void BFS_search(City initial,City goal){
        visited.add(initial);
        if(initial == goal)
            return;
        queue.add(initial);


        while(queue.isEmpty() == false){
            if (BFS(queue.get(0),goal) == true)
                return;
        }
    }

    public  boolean BFS(City initial, City goal){
        queue.remove(0);

        for (City city : initial.getNeighbor()) {
            if(visited.contains(city) == false){
                visited.add(city);
                if(city.name.equals(goal.name)){
                    return true;
                }
                queue.add(city);
            }
        }
        return false;
    }
}

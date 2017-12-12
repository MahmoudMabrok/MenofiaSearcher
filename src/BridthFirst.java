import java.util.ArrayList;

public class BridthFirst {
    ArrayList<B_Data> VisitedWithIndex = new ArrayList<>();
    ArrayList<City> visited = new ArrayList();
    ArrayList<B_Data> queue = new ArrayList();
    ArrayList<City> path = new ArrayList();
    City[] path_array = new City[10];
    int n = 0;

    public  void BFS_search(City initial,City goal){

        if(initial == goal)
            return;
        B_Data data = new B_Data(initial, n);
        queue.add(data);
        VisitedWithIndex.add(data);
        visited.add(data.city);
        while (true) {
            if (BFS(queue.get(0),goal) == true)
                break;
        }

        System.out.println("visited");
        for (B_Data b_Data : VisitedWithIndex) {
            System.out.println(b_Data.city.name + " " + b_Data.index);
        }
        for (int i = VisitedWithIndex.size() - 1; i > -1; i--) {
            if (path_array[VisitedWithIndex.get(i).index] == null)
                path_array[VisitedWithIndex.get(i).index] = visited.get(i);
        }
        System.out.println("path");
        for (City c : path_array) {
            if (c != null) {
                path.add(c);
                System.out.println(c.name);
            }
        }
    }

    public boolean BFS(B_Data initial, City goal) {
        queue.remove(0);
        n = initial.index + 1;
        System.out.println(n);
        for (City city : initial.city.getNeighbor()) {
            if(visited.contains(city) == false){
                B_Data data = new B_Data(city, n);
                VisitedWithIndex.add(data);
                visited.add(data.city);
                if (city == goal)
                    return true;
                queue.add(data);
            }
        }
        return false;
    }
}

class B_Data {
    City city = new City(" ");
    int index = 0;

    B_Data(City c, int n) {
        city = c;
        index = n;
    }
}
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayList;
import java.util.Queue;

public class BFS {


    public ArrayList<City> bfsStart (ArrayList<City> cities ,
                                     City cStart , City cEnd )
    {
        ArrayList<City> visited = new ArrayList<>() ;
        City dequued ;
        visited.add(cStart) ;
        dispaly(visited);
        queueList q = new queueList();
        boolean exists = false ;
        q.queue(cStart);

        while (!q.isEmpty() && !exists ){
            System.out.println("inside while ");
            dequued =q.deque() ;
            for (City c :  dequued.getNeighbor()  ) {
                if (!visited.contains(c ) )
                {
                    System.out.println("inside if contains plus show quew");

                    q.queue(c);
                    dispaly(q.getData());
                    System.out.println( "after queue " + c.name );
                    if (c==cEnd) {
                        exists = true ;
                        System.out.println("i found it ");
                    }
                    visited.add(c);
                }
                //visited.add(c);

            }
          //  visited.add(dequued) ;
        }
        if (exists )
        return  visited ;
        else
            return  null ;
    }
    public static void dispaly ( ArrayList<City> v)
    {
        for (City c:
             v) {
            System.out.println("v  " + c.name );

        }
    }

}

class queueList {

  private  ArrayList<City> data  = new ArrayList<>() ;
    int size = 0 ;
    public void queue (City c ) {
            data.add(c) ;
    }

    public City deque ()
    {
        if (!data.isEmpty())
        {
           return data.remove(0) ;
        }
    return  null ;
    }
    public boolean isEmpty(){return  data.isEmpty() ; }
    public ArrayList<City> getData ()
    {
        return  data ;
    }



}
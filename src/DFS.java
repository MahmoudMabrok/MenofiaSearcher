import java.util.ArrayList;

public class DFS {


}
class Stack <T>  {

   private  ArrayList<T> data = new ArrayList<>();

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void push(T t) {
        data.add(t);
    }

    public T pop() {
        if (!isEmpty()){
            return  data.remove(data.size()-1) ;
        }
        return null ;
    }

    public ArrayList<T> getData ()
    {
        return data  ;
    }

}



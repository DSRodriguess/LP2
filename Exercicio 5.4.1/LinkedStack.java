import java.util.LinkedList;

public class LinkedStack implements IStackable {
    LinkedList<Integer> list = new LinkedList<Integer>();
   
    public int size () {
        return list.size();
    }

    public void push(int x ) {
        list.addFirst(x);
    }

    public int pop () {
        int x; 
        x = (int)list.removeFirst();
        return x;
    }
}
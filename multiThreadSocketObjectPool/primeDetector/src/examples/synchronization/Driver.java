import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Driver {
    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<Thread>();
        Queue<Long> q = new LinkedList<Long>();
        for(int i = 0; i < 20; i++) {
            if(i % 2 == 0) {
                Producer p = new Producer(i, q, 5);
                p.start();
                threads.add(p);
            } else {
                Consumer c = new Consumer(q);
                c.start();
                threads.add(c);
            }
        }
        try {
            for(Thread t: threads) {
                t.join();
            }
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
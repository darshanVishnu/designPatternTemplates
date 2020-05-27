import java.util.Queue;

public class Producer extends Thread {

    private int idNum = 0;
    private int qMaxSize = 5;
    Queue<Long> q;

    public Producer(int idIn, Queue<Long> qIn, int qMaxSizeIn) {
        this.idNum = idIn;
        this.q = qIn;
        this.qMaxSize = qMaxSizeIn;
    }
    @Override
    public void run() {
        produce();
    }

    public synchronized void produce() {
        synchronized(q) {
            try {
                while(q.size() >= qMaxSize) {
                    System.out.println("Q full, waiting for empty slots: " + this.getId());
                    for(Long elem: q) {
                        System.out.println("Element: " + elem);
                    }
                    q.wait();
                }
            }catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }   
            long num = Math.round(Math.random() * 99);
            q.add(num);
            q.notifyAll();
        }
    }
}
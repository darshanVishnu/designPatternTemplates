import java.util.Queue;

public class Consumer extends Thread{

    Queue<Long> q;

    public Consumer(Queue<Long> qIn) {
        this.q = qIn;
    }
    @Override
    public void run() {
        consume();
    }

    public synchronized void consume() {
        try {
            Thread.sleep(1000);
            synchronized(q) {
                while(q.isEmpty()) {
                    System.out.println("Consumer waiting for value: " + this.getId());
                    q.wait();
                }
                long num = q.remove();
                System.out.println(num);
                q.notifyAll();
            }
        }catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
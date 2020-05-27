import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {
        MyThreadPool threadPool = new MyThreadPool(5);
        ArrayList<Thread> borrowedThreads = new ArrayList<Thread>();
        int test = 1;
        while(test < 3) {
            try {
                for(int i = 0; i < 10; i++) {
                    Thread borrowedThread = threadPool.borrowThread();
                    if(borrowedThread != null) {
                        borrowedThreads.add(borrowedThread);
                        borrowedThread.start();
                    } else {
                        Thread.sleep(1000);
                    }
                }
                for(int i = 0; i < 5; i++) {
                    if(borrowedThreads.size() > 0) {
                        Thread t = borrowedThreads.remove(0);
                        t.join();
                        threadPool.returnThread(t);
                    }
                }
                borrowedThreads.clear();
                test++;
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        for(Thread t: borrowedThreads) {
            System.out.println(t);
        }
    }
}
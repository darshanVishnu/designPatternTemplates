import java.lang.Thread.State;
import java.util.ArrayList;

public class MyThreadPool implements ThreadPoolInterface {

    private int totalThreads = 0;
    private ArrayList<Integer> activeWorkers = new ArrayList<Integer>();
    private ArrayList<Thread> workers = new ArrayList<Thread>();

    public MyThreadPool(int numThreadsIn) {
        this.totalThreads = numThreadsIn;
        this.createWorkerThreads(numThreadsIn);
    }

    //public void setFactory(PoolableObjectFactory factory){}

    private void createWorkerThreads(int numThreadsIn) {
        for(int i = 0; i < numThreadsIn; i++) {
            Thread worker = new Thread(new WorkerThread(i));
            worker.setName(i + "");
            workers.add(worker);
        }
    }

    public synchronized Thread borrowThread() {
        for(int i = 0; i < totalThreads; i++) {
            if(!activeWorkers.contains(i) && !workers.get(i).isAlive() 
                && workers.get(i).getState().compareTo(State.TERMINATED) != 0) {
                activeWorkers.add(i);
                return workers.get(i);
            }
        }
        return null;
    }

    public synchronized void returnThread(Thread returnThreadIn) {
        if(!returnThreadIn.isAlive()) {
            Integer threadPos = Integer.parseInt(returnThreadIn.getName());
            activeWorkers.remove(threadPos);
            workers.remove(returnThreadIn);
            this.addThread(threadPos);
        }
    }

    private void addThread(int name) {
        Thread worker = new Thread(new WorkerThread(name));
        worker.setName(name + "");
        workers.add(worker);
    }

    public synchronized int getActiveCount() {
        return activeWorkers.size();
    }

    public synchronized int getIdleCount() {
        return totalThreads - activeWorkers.size();
    }
}
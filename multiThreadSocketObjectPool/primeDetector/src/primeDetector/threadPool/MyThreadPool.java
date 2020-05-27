package primeDetector.threadPool;

import primeDetector.util.FileProcessor;
import primeDetector.util.IsPrime;
import primeDetector.worker.WorkerThread;
import primeDetector.util.ResultsInterface;
import java.lang.Thread.State;
import java.util.ArrayList;
import primeDetector.util.MyLogger;

public class MyThreadPool implements ThreadPoolInterface {

    private final int totalThreads;
    private ArrayList<Integer> activeWorkers = new ArrayList<Integer>();
    private ArrayList<Thread> workers = new ArrayList<Thread>();

    public MyThreadPool(int numThreadsIn, FileProcessor fileProcIn, ResultsInterface results, IsPrime primeIdentifier) {
        MyLogger.writeMessage("MyThreadPool's Constructor is called ", MyLogger.DebugLevel.CONSTRUCTOR);
        this.totalThreads = numThreadsIn;
        this.createWorkerThreads(numThreadsIn, fileProcIn, results, primeIdentifier);
    }

    //public void setFactory(PoolableObjectFactory factory){}

    private void createWorkerThreads(int numThreadsIn, FileProcessor fileProc, ResultsInterface results, IsPrime primeIdentifier) {
        for(int i = 0; i < numThreadsIn; i++) {
            Thread worker = new Thread(new WorkerThread(i, fileProc, results, primeIdentifier));
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

    /*public synchronized void returnThread(Thread returnThreadIn) {
        if(!returnThreadIn.isAlive()) {
            Integer threadPos = Integer.parseInt(returnThreadIn.getName());
            activeWorkers.remove(threadPos);
            workers.remove(returnThreadIn);
            this.addThread(threadPos);
        }
    };

    private void addThread(int name) {
        Thread worker = new Thread(new WorkerThread(name));
        worker.setName(name + "");
        workers.add(worker);
    }*/

    public synchronized int getActiveCount() {
        return activeWorkers.size();
    }

    public synchronized int getIdleCount() {
        return totalThreads - activeWorkers.size();
    }

    public int getTotalThreads() {
        return totalThreads;
    }

    @Override
    public String toString() {
        int activeCount = getActiveCount();
        return "Thread pool: Pool Size: " + totalThreads + " Active count: " + activeCount + " Idle Count: " + (totalThreads - activeCount);
    }
}
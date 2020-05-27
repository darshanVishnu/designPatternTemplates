package primeDetector.worker;

import java.util.ArrayList;

import primeDetector.threadPool.ThreadPoolInterface;
import primeDetector.threadPool.MyThreadPool;
import primeDetector.util.FileProcessor;
import primeDetector.util.IsPrime;
import primeDetector.util.ResultsInterface;
import primeDetector.util.MyLogger;

public class CreateWorkers {

    private FileProcessor fileProc;
    private ResultsInterface results;
    private IsPrime primeIdentifier;
    private ThreadPoolInterface threadPool;

    private static final int threadPoolSize = 5;

    public CreateWorkers(FileProcessor fileProcIn, ResultsInterface resultsIn, IsPrime primeIdentifierIn) {
        MyLogger.writeMessage("CreateWorkers's Constructor is called" , MyLogger.DebugLevel.CONSTRUCTOR);
        this.fileProc = fileProcIn;
        this.results = resultsIn;
        this.primeIdentifier = primeIdentifierIn;
        this.createWorkerPool();
    }

    private void createWorkerPool() {
        this.threadPool = new MyThreadPool(threadPoolSize, fileProc, results, primeIdentifier);
    }

    public void startWorkers(int numThreads) {
        ArrayList<Thread> borrowedThreads = new ArrayList<Thread>();
        for(int i = 0; i < numThreads; i++) {
            Thread worker = threadPool.borrowThread();
            worker.start();
        }
        while(borrowedThreads.size() > 0) {
            int tryNum = 0;
            boolean success = false;
            Thread worker = borrowedThreads.get(0);
            while(tryNum < 3 && !success)
            try {
                worker.join();   
                success = true;
                borrowedThreads.remove(worker);
            } catch(InterruptedException ie) {
                tryNum += 1;
            }
            // Try again until it is successfull if it failed for three times
        }
    }
}
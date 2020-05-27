package primeDetector.worker;

import primeDetector.util.FileProcessor;
import primeDetector.util.IsPrime;
import primeDetector.util.ResultsInterface;
import java.io.IOException;
import primeDetector.util.MyLogger;

public class WorkerThread implements Runnable {

    private int id = 0;
    private FileProcessor fileProc;
    private ResultsInterface results;
    private IsPrime primeIdentifer;
  //  private static boolean stopMessageUpdated = false;
    private static final String STOP = "STOP";

    public WorkerThread(int idIn, FileProcessor fileProcIn, ResultsInterface resultsIn, IsPrime primeIdentifierIn) {
        MyLogger.writeMessage("WorkerThread's Constructor is called" , MyLogger.DebugLevel.CONSTRUCTOR);
        this.id = idIn;
        this.fileProc = fileProcIn;
        this.results = resultsIn;
        this.primeIdentifer = primeIdentifierIn;
    }
    
    @Override
    public void run() {
        MyLogger.writeMessage("Thread run is  called and ThreadId is " + getThreadId(), MyLogger.DebugLevel.THREAD_RUN);
        String message = "";
        try {
            while(message != null) {
                message = fileProc.poll();
                if(message == null) {
                    this.addElement("STOP");
                    break;
                    //    stopPublishingMessages(results);
                } else {
                    Integer num = Integer.parseInt(message);
                    if(primeIdentifer.isNumPrime(num)) {
                        this.addElement(message);
                    }
                }
            }
        } catch(NumberFormatException ne) {
            //TODO: Log that input is not proper
            System.out.println("Input is not proper");
            System.exit(0);
        } catch(IOException ioe) {
            //TODO: Log that input is not proper
            System.out.println("Input is not proper");
            System.exit(0);
        }
    }
/*
    private synchronized static void stopPublishingMessages(ResultsInterface results) {
        if(!stopMessageUpdated) {
            int tryNum = 0;
            boolean success = false;
            while(tryNum < 3 && !success) {
                try {
                    results.addElement(STOP);   
                    stopMessageUpdated = true;
                    success = true;
                } catch(InterruptedException ie) {
                    // Try again
                    tryNum += 1;
                }
            }
            if(tryNum == 3) {
                //TODO: Log that this is not successful, let some other thread send stop
                System.out.println("this is not successful, let some other thread send stop");
            }
        }
    }*/

    public void addElement(String message) {
        int tryNum = 0;
        boolean success = false;
        while(tryNum < 3 && !success) {
            try {
                results.addElement(message);
                success = true;
            } catch(InterruptedException ie) {
                //Try again
                tryNum += 1;
            }
        }
        if(tryNum == 3) {
            //TODO: Log that adding to the queue was not successful even after three trys
            System.out.println("Adding to the queue was not successful even after three trys");
        }
    }

    public int getThreadId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Worker Thread id: " + this.id;
    }
}   
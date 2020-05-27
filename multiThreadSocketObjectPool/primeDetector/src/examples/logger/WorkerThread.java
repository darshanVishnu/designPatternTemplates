//package persisterServiceClient;

public class WorkerThread implements Runnable {
    public Results results;
    public WorkerThread(Results resultsIn){
        MyLogger.writeMessage("Constructor called - " + this.toString(), MyLogger.DebugLevel.CONSTRUCTOR);
        results =resultsIn;
    }

    @Override
    public void run() {
        MyLogger.writeMessage("Thread run is  called - " + this.toString(), MyLogger.DebugLevel.THREAD_RUN);
        try {
            results.addDataToList(1);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

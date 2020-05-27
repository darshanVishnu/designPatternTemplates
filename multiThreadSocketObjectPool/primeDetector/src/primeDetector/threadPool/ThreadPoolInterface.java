package primeDetector.threadPool;

public interface ThreadPoolInterface {
    public Thread borrowThread();
    //public void returnThread(Thread thread);
    public int getActiveCount();
    public int getIdleCount();
    public int getTotalThreads();
}
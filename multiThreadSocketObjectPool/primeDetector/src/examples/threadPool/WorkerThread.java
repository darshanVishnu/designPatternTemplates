public class WorkerThread implements Runnable {

    private int id = 0;

    public WorkerThread(int idIn) {
        this.id = idIn;
    }
    
    @Override
    public void run() {
        System.out.println("My ID is - " + id);
    }
}
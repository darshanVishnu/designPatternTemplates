

public class Driver {

    public static  Integer DEBUG_VALUE = null;
    public static void main(String[] args)  {
        try {
            if (args.length != 1) {
                System.err.println("Please enter Debug level");
                System.exit(0);
            }

//    DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
//    DEBUG_VALUE=3 [Print to stdout everytime a thread's run() method is called]
//    DEBUG_VALUE=2 [Print to stdout everytime an entry is added to the Results data structure]
//    DEBUG_VALUE=1 [Print to stdout the contents of the data structure in the store Results instance]
//    DEBUG_VALUE=0 [No output should be printed from the application, except the line "The sume of all the numbers is: XYZ" ]
            DEBUG_VALUE =Integer.parseInt(args[0]);
            MyLogger.setDebugValue(DEBUG_VALUE);
            Results dataResults =new Results();
            Runnable workerThread = new WorkerThread(dataResults);
            for(int i=0 ;i<10;i++) {
                workerThread.run();
            }
            dataResults.sumListResults();

        }
        catch (Exception exception){
            exception.printStackTrace();
            System.err.println("Error in Io operation");
            System.exit(0);
        }
    }
}
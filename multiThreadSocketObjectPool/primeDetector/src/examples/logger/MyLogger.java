//package persisterServiceClient;


//package primeDetector.util;

public class MyLogger {

    private static DebugLevel debugLevel;

    // FIXME: Add more enum values as needed for the assignment
    public static enum DebugLevel {
        CONSTRUCTOR,THREAD_RUN,DATA_STRUCTURE_ENTRY,DATA_STRUCTURE_CONTENT,NONE,DATA_STRUCTURE_SUM
    }

    ;

    //    The DEBUG_VALUE, read from the command line, should be set in the Logger class. Use the DEBUG_VALUE in the following way:
//    DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
//    DEBUG_VALUE=3 [Print to stdout everytime a thread's run() method is called]
//    DEBUG_VALUE=2 [Print to stdout everytime an entry is added to the Results data structure]
//    DEBUG_VALUE=1 [Print to stdout the contents of the data structure in the store Results instance]
//    DEBUG_VALUE=0 [No output should be printed from the application, except the line "The sume of all the prime numbers is: XYZ" ]
    // FIXME: Add switch cases for all the levels
    public static void setDebugValue(int levelIn) {
        switch (levelIn) {
            case 4:
                debugLevel = DebugLevel.CONSTRUCTOR;
                break;
            case 3:
                debugLevel = DebugLevel.THREAD_RUN;
                break;
            case 2:
                debugLevel = DebugLevel.DATA_STRUCTURE_ENTRY;
                break;
            case 1:
                debugLevel = DebugLevel.DATA_STRUCTURE_CONTENT;
                break;
            case 0:
                debugLevel = DebugLevel.DATA_STRUCTURE_SUM;
                break;
            default:
                debugLevel = DebugLevel.NONE;
                break;
        }
    }

    public static void setDebugValue(DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    public static void writeMessage(String message,
                                    DebugLevel levelIn) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }

}
package primeDetector.util;

public class StandardException  extends Exception {
    String message;

    public StandardException(String message) {
        MyLogger.writeMessage("StandardException's Constructor is called" , MyLogger.DebugLevel.CONSTRUCTOR);
        this.message = message;
    } 

    /**
     * Overriding implementation to print custom message
     * @return String message
     */
    @Override
    public String toString() {
        return this.getMessage();
    }

    /**
     * Overriding implementation to print custom message
     * @return String message
     */
    @Override
    public String getMessage() {
        return "Custom Exception generated: " + this.message;
    }
}
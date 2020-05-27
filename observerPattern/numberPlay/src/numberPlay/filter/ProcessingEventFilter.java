package numberPlay.filter;

public class ProcessingEventFilter implements FilterI {

    /**
     * ProcessingEventFilter class used as ProcessingComplete event filter for observer
     */
    private Decision event = null;

    /**
     * return the enum
     * @return enum which indicates Decision
     */
    public ProcessingEventFilter() {
        event = Decision.PROCESSINGCOMPLETE;
    }
    public Decision getDecision() {
        return event;
    }
    /**
     * process the input number and decides processescomplete
     *
     * @param must be of String
     * @throws Exceptions when input is not a Number
     * @return boolean
     */
    public boolean eventDecider(String numberIn) throws Exception {
        try {
            if (numberIn.equals("processescomplete"))
                return true;

        } catch (Exception error) {
            // caused
            error.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    @Override
    public String toString() {
        return "ProcessingEventFilter class[event :" + event + "]";
    }

    @Override
    public int hashCode() {
        return ("ProcessingEventFilter".hashCode()) *13;
    }
}

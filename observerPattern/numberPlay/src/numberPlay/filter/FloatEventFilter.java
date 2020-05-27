package numberPlay.filter;

/**
 * FloatEventFilter class used as floating event filter for observer
 */
public class FloatEventFilter implements FilterI {

    private Decision event = null;
    /**
     * constructor FloatEventFilter sets the event Decsion

     **/
    public FloatEventFilter() {
        this.event = Decision.FLOAT;
    }

    /**
     * return the enum
     * @return enum which indicates Decision
     */
    public Decision getDecision() {
        return event;
    }
    /**
     * process the input number and decide int or float
     *
     * @param must be of String
     * @throws Exceptions when input is not a Number
     * @return boolean
     */
    public boolean eventDecider(String numberIn) throws Exception {
        try {
            int numint = Integer.parseInt(numberIn);
            return false;
            // integerEvent(numint);
        } catch (NumberFormatException e) {
            //call thefloating event
            //check if float event
            try {
                float numfloat = Float.parseFloat(numberIn);
                return true;
                // FloatingPointEvent(numfloat);
                //call the float event
            } catch (NumberFormatException e1) {
                //not float
                throw new Exception("Invalid input please format the input file and enter only numbers");
            }
        }
    }
    @Override
    public String toString() {
        return "FloatEventFilter class[event :" +event+"]";
    }
    @Override
    public int hashCode() {
        return ("FloatEventFilter".hashCode()) *13;
    }
}

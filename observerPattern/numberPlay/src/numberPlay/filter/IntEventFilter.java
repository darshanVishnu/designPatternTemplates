package numberPlay.filter;

public class IntEventFilter implements FilterI {

    /**
     * IntEventFilter class used as Int event filter for observer
     */
    private Decision event=null;
    /**
     * constructor FloatEventFilter sets the event Decsion

     **/
    public IntEventFilter(){
        event= Decision.INTEGER;
    }

    /**
     * return the enum
     * @return enum which indicates Decision
     */
    public Decision getDecision(){
        return event;
    }

    /**
     * process the input number and decide int or float
     *
     * @param must be of String
     * @throws Exceptions when input is not a Number
     * @return boolean
     */
    public boolean  eventDecider(String numberIn) throws Exception {
        try {
            int numint = Integer.parseInt(numberIn);
            return true;
            // integerEvent(numint);
        } catch (NumberFormatException e) {
            //call thefloating event
            //check if float event
            try {
                float numfloat = Float.parseFloat(numberIn);
                return false;
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
        return "IntEventFilter class[event :" +event+"]";
    }
    @Override
    public int hashCode() {
        return ("IntEventFilter".hashCode()) *13;
    }
}
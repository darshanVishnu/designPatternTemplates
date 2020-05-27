package numberPlay.util;

import observer.ObserverI;

import java.util.ArrayList;
/*
TopKNumbersObserverI class is used to maintain Observers of top k number
 */
public class TopKNumbersObserver implements ObserverI {
    private TopKNumbersData topKNumbersData ;

    /**
     *
     * @param args is used pass the file,window details to other class type string
     */
    public TopKNumbersObserver(String[] args) {
        topKNumbersData = new TopKNumbersData(Integer.parseInt(args[3]),args[4]);
    }

    /**
     * used to call topk calculator
     * @param intergerNumber of class Number
     */
    @Override
    public void numberPassed(Number intergerNumber) {
        try{
            double number=  intergerNumber.doubleValue();
            topKNumbersData.topKcalculator(number);
        }
        catch (Exception e){
            System.err.println("Failed at topk obsever");
            System.err.println(e);
            System.exit(0);
        }
    }

    /**
     * used to trigger complete of read of input and prcosessing result
     * @param m must be a String
     */
    @Override
    public void processComplete(String m) {
        topKNumbersData.writeToFile();
        topKNumbersData.close();

    }

    /**
     *
     * @return string it returns the class details
     */
    @Override
    public String toString() {
        return String.format("TopKNumbersObserver[topKNumbersData : %s]",topKNumbersData);
    }
    @Override
    public int hashCode() {
        return ("TopKNumbersObserver".hashCode()) *13;
    }
}

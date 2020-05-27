package numberPlay.util;

import observer.ObserverI;
/**
NumberPeaksObserverI class is used to maintain Observers of number peak
 */
public class NumberPeaksObserver implements ObserverI {
    private NumberPeaksData numberPeaksData;

    /**
     *
     * @param args is used pass the file,window details to other class type string
     */
    public NumberPeaksObserver(String[] args) {
        numberPeaksData = new NumberPeaksData(args[5]);
    }

    /**
     * used to write the file and close resource after all the input is read
     * @param m is of type string
     */
    @Override
    public void processComplete(String m) {
        numberPeaksData.writeToFile();
        numberPeaksData.close();
    }

    /**
     * used to convert the number to float and call peakcalculator
     * @param intergerNumber is of type number
     */
    @Override
    public void numberPassed(Number intergerNumber) {
        try {
            double number = intergerNumber.doubleValue();
            numberPeaksData.peakcalculator(number);
        }catch (NumberFormatException e){
            System.err.println("Failed at NumberPeaksObserverI observer");
            System.err.println(e);
            System.exit(0);
        }
    }
    /**
     *
     * @return string it returns the class details
     */
    @Override
    public String toString() {
        return String.format("NumberPeaksObserver[numberPeaksData : %s]",numberPeaksData);
    }

}

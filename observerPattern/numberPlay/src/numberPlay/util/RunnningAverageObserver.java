package numberPlay.util;

import observer.ObserverI;
/**
 RunnningAverageObserver class is used to maintain Observers of runningAverageData
 */
public class RunnningAverageObserver implements ObserverI {
    private RunningAverageData runningAverageData;

    /**
     *
     * @param args is used pass the file,window Size details to other class type string
     */
    public RunnningAverageObserver(String[] args) {
        runningAverageData = new RunningAverageData(Integer.parseInt(args[1]),args[2]);
    }

    /**
     * used to convert the number to float and call runningAverageCalculator
     * @param intergerNumber is of type number
     */

    @Override
    public void numberPassed(Number intergerNumber) {
        try {
            runningAverageData.runningAverageCalculator((Integer) intergerNumber);
        }catch (NumberFormatException e){
            System.err.println("Failed at RunnningAverageObserverI observer");
            System.err.println(e);
            System.exit(0);
        }

    }

    /**
     * used to write the file and close resource after all the input is read
     * @param m is of type string
     */
    @Override
    public void processComplete(String m) {
        runningAverageData.writeToFile();
        runningAverageData.close();
    }

    /**
     *
     * @return string it returns the class details
     */
    @Override
    public String toString() {
        return String.format("RunnningAverageObserver[runningAverageData : %s]",runningAverageData);
    }
    @Override
    public int hashCode() {
        return ("RunnningAverageObserver".hashCode()) *13;
    }

}

package numberPlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * RunningAverageData used to maintain data form peak Running Average
 */
public class RunningAverageData implements PersisterI, RunningAverageResultsI {
    public BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    public Queue<Integer> runningAverageDataq = new LinkedList<>();
    public ArrayList<Double> averageList = new ArrayList<Double>();
    public Integer windowSize=0;

    /**
     * constructors creates filewiter to write the data
     * @param fileName path of the file
     * @param windowSizeIn must be of int
     */
    public RunningAverageData(int windowSizeIn,String fileName){
        this.windowSize =windowSizeIn;
        try {
            fileWriter = new FileWriter(fileName);
        } catch (IOException e) {
            System.err.println("Error :fail to read the file please provide proper path" + fileName);
            e.printStackTrace();
        }
    }

    /**
     * runningAverageCalculator used to calculate AVerage
     * @param number type Integer
     */
    public void runningAverageCalculator(Integer input) {
        Integer average=0;
        if(runningAverageDataq.size() < windowSize) {
            runningAverageDataq.add(input);
        }
        else if(runningAverageDataq.size() == windowSize){
            runningAverageDataq.remove();
            runningAverageDataq.add(input);
        }
        for (Integer item: runningAverageDataq) {
            average=average+item;
        }
        // System.out.println((double)average/(double)runningAverageDataq.size());
        store((double)average);
    }

    /**
     * returns input value rounded up to 2 decimal points.
     *
     * @param input must be a type double
     * @return type double .
     */
    public double rounder(double input) {
        long roundedInt = Math.round(input * 100);
        return (double) roundedInt / 100;
    }

    /**
     * used to store peaklist data
     * @param inputIn must be of type double
     */
    @Override
    public void store(Double averageIn) {
        averageIn=rounder(averageIn/(double)runningAverageDataq.size());
        averageList.add(averageIn);

    }

    /**
     * writeToFile used to write the output to a file
     */
    @Override
    public void writeToFile() {
        try {
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Double item: averageList) {
                bufferedWriter.write(String.valueOf(item)+"\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            close();
        } catch (IOException e) {
            System.err.println("Error :fail  to write the fileline");
            e.printStackTrace();
        }
    }

    /**
     * used to close the opened resources
     */
    @Override
    public void close() {
        try {
            fileWriter.close();
        }
        catch (IOException e) {
            System.err.println("Error :fail to close the fileline");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     *
     * @return string it returns the class details
     */
    @Override
    public String toString() {
        return String.format("RunningAverageData[runningAverageDataq: %s, averageList: %s ,windowSize: %s,bufferedWriter %s ,fileWriter : %s]",runningAverageDataq,averageList,windowSize,fileWriter, bufferedWriter);
    }
    @Override
    public int hashCode() {
        return (windowSize.hashCode()) *13;
    }
}

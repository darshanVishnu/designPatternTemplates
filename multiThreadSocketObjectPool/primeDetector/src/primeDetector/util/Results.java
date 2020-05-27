package primeDetector.util;

import java.util.LinkedList;
import java.util.Queue;

import primeDetector.socketClient.DataSender;
import primeDetector.util.MyLogger;

/**
 * Results class is used  to store primeNumber.
 */
public class Results implements ResultsInterface {
    private Queue<String> queue;//Because we need a queue implementation
    private final int maxQueueSize;
    private DataSender dSender;
    private long sumPrimeNumber;
    private int counter;
    private int numThreads;
    /**
     * Results Constructor is used to set values
     * @param capacity is the capacity of the queue
     * @param ipAddrIn is the servers ip address
     * @param numThreadsIn is the total threads which are used for executing
     * @param portIn is the servers port
     */
    public Results(int capacity, String ipAddrIn, Integer portIn, int numThreadsIn) {
        MyLogger.writeMessage("Results's Constructor is called", MyLogger.DebugLevel.CONSTRUCTOR);
        maxQueueSize = capacity;
        // Because this is a nonblocking implementation of the Queue which is what we need
        // as we are synchronizing wait and notifyAll
        queue = new LinkedList<String>();
        dSender = new DataSender(this, ipAddrIn, portIn);
        this.numThreads=numThreadsIn;
        this.startDataSender();

    }

    /**
     * getter method of Sum prime numbers
     * @return sumPrimeNumber
     */
    public synchronized long getSumPrimeNumber() {
        return this.sumPrimeNumber;
    }

    /**
     * setter method oof Sum prime number
     * @param sumPrimeNumberIn
     */
    public synchronized void setSumPrimeNumber(long sumPrimeNumberIn) {
        this.sumPrimeNumber = sumPrimeNumberIn;
    }

    /**
     * used to start to send the data
     */
    private void startDataSender() {
        Thread dSenderThread = new Thread(dSender);
        dSenderThread.start();
    }

    /**
     * addElement adds a element to the queue and calls notifyAll
     * @param element input number
     * @throws InterruptedException
     */
    public void addElement(String element) throws InterruptedException {
        synchronized(this) {
            while(queue.size() >= maxQueueSize) {
                //TODO: Log that the thread has to wait, from
                //System.out.println("Q full, waiting for empty slots: ");
                MyLogger.writeMessage("Q full, waiting for empty slots: ", MyLogger.DebugLevel.CONSTRUCTOR);
                wait();
            }
            if(element.equals("STOP")){
                counter++;
                if(counter == numThreads){
                    queue.add(element);
                }
            }
            else {
                queue.add(element);
                setSumPrimeNumber(getSumPrimeNumber() + Long.parseLong(element));
                MyLogger.writeMessage("queue ENTRY  added is "+ element, MyLogger.DebugLevel.DATA_STRUCTURE_ENTRY);
            }
//            if (!element.equals("STOP")) {
//            }
            printContentResultDataStructure();
            notifyAll();
        }
    }

    /**
     * it removed a leemnt from queue and returns the removed element
     * @return element of type String
     * @throws InterruptedException
     */

    public String getElement() throws InterruptedException {
        String element = null;
        synchronized (this) {
            while (queue.isEmpty()) {
                //TODO: log that the thread has to wait
               // System.out.println("Consumer waiting for value: ");
                MyLogger.writeMessage("Consumer waiting for value", MyLogger.DebugLevel.CONSTRUCTOR);
                wait();
            }
            element = queue.remove();
            notifyAll();
        }
        return element;
    }

    /**
     * check is the queue is empty and returns bool
     * @return type bool
     */
    public synchronized boolean isQueueEmpty() {
        return queue.isEmpty();
    }

    /**
     * ToString
     * @return string
     */
    @Override
    public String toString() {
        String result = "Results: Queue contents: {";
        for (String elem : queue) {
            result += elem + ", ";
        }
        result += "}, Queue Max Size: " + maxQueueSize;
        return result;
    }

    /**
     * is used to for logging Sum of prime numbers
     */
    public void printSumPrimeNumber() {
        MyLogger.writeMessage("The sum of all the prime numbers is " + getSumPrimeNumber(), MyLogger.DebugLevel.DATA_STRUCTURE_SUM);
    }

    /**
     * is used to for logging conents of queued prime numbers
     */
    public void printContentResultDataStructure() {
        String contents = "The Contents in the result queue ";
        for (String item : queue) {
            contents += item + " ";
        }
        MyLogger.writeMessage(contents, MyLogger.DebugLevel.DATA_STRUCTURE_CONTENT);
    }
}
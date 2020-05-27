package primeDetector.socketClient;

import java.net.Socket;
import primeDetector.util.Results;
import java.io.IOException;
import java.net.SocketException;
import java.io.DataOutputStream;
import primeDetector.util.MyLogger;

public class DataSender implements Runnable{
    private Results results;
    private String ipAddr;
    private int port;
    private static final String STOP = "STOP";

    public DataSender(Results resultInstanceIn, String ip, Integer portIn) {
        MyLogger.writeMessage("DataSender's Constructor is called " , MyLogger.DebugLevel.CONSTRUCTOR);
        this.results = resultInstanceIn;
        this.ipAddr = ip;
        this.port = portIn;
    }

    @Override
    public void run() {
        Socket socket;
        boolean stopped = false;
        DataOutputStream dataOutputStream;
        String element = null;
        while(true) {
            try {
                socket = new Socket(ipAddr, port);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while(true) {
                    try {
                        element = results.getElement(); 
                    } catch (InterruptedException ie) {
                        //Nothing to write, try again in next iteration
                        continue;
                    }

                    dataOutputStream.writeUTF(element);
                    dataOutputStream.flush();
                    if(element.equals(STOP)) {
                        stopped = true;
                        //continue;
                        break;
                    }
//                 //   if(results.isQueueEmpty() && stopped) {
//                        results.printSumPrimeNumber();
//                        dataOutputStream.writeUTF("STOP");
//                        dataOutputStream.flush();
//                     //   break;
//                   // }
                }
                results.printSumPrimeNumber();
                dataOutputStream.close();
                socket.close();
                break;
            } catch(SocketException se) {
                //TODO: Log Exception
                // Might be while sending too
                try {
                    if(element != null) {
                        results.addElement(element);
                    }
                } catch(InterruptedException ie) {
                    //Too much to handle, leave this element
                    MyLogger.writeMessage("DataSender: Too much to handle, leave this element" , MyLogger.DebugLevel.CONSTRUCTOR);
                }

            } catch (IOException exception) {
                //TODO: Log exception
                // Failed in closing dataOputputStream or socket, so no problem as the functionality isn't affected
                MyLogger.writeMessage("DataSender:Failed in closing dataOputputStream or socket, so no problem as the functionality isn't affected" , MyLogger.DebugLevel.CONSTRUCTOR);

            }
        }
    }

    @Override 
    public String toString() {
        return "DataSender: Server IP: " + ipAddr + " Server port: " + port;
    }
}
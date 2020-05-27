package persisterService.util;
import java.net.Socket;

import persisterService.driver.Driver;

import java.io.DataInputStream;
import java.io.IOException;

public class SocketHandler extends Thread {

    final private static String STOP = "STOP";

    private Socket socket = null;
    private Results resultHandler = null;

    /**
     * Constructor used to set the socket and result instances
     * @param socketIn is the socket object
     * @param resultHandlerIn is the result class object
     */
    public SocketHandler(Socket socketIn, Results resultHandlerIn) {
        this.socket = socketIn;
        this.resultHandler = resultHandlerIn;
    }

    /**
     * checks for the data input and writes to the result file and terminates the program when stop message is received
     */
    @Override
    public void run() {
        while(true) {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String receivedMessage = dataInputStream.readUTF();
                if (receivedMessage.equals(STOP)) {
                    //TODO: Add logs
                    //System.out.println("Stoopiong server now");
                    closeResources();
                    Driver.closeSocketAndExit();
                    break;
                }
                resultHandler.writeSingleLine(receivedMessage);
            } catch (IOException e) {
                closeResources();
                System.out.println(e.getMessage());
                break;
            }
        }
        System.exit(0);
    }

    /**
     * closeResources is used to close the socket and resources used for file writer.
     */
    private void closeResources() {
        try {
            socket.close();
            resultHandler.resourceCloser();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("SocketHandler[Socket:%s  ,resultHandler:%s]", socket,resultHandler);
    }

    @Override
    public int hashCode() {
        return ("SocketHandler".hashCode()) * 13;
    }

}
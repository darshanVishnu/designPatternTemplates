//package socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class Server {

    public static void main(String[] args) throws IOException {
        try {
            if(args.length != 2){
                System.err.println("Please enter port followed by file name");
                System.exit(0);
            }
            Integer port = Integer.parseInt(args[0]);
            //    String filename = "/home/darshan/Documents/Design_pattern/practice/socket/src/inpu.txt";
            String filename =args[1];
            String[] fileNameParts = filename.split("\\.");
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started at " + InetAddress.getLocalHost() + " port " + port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    Results resultsHandler = new Results(fileNameParts[0] +  "_" + (socket.getInetAddress().toString().split("/"))[1] + "." + fileNameParts[1]);
                    SocketHandler sHandler = new SocketHandler(socket, resultsHandler);
                    sHandler.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            serverSocket.close();
            System.exit(0);
        } catch (SocketException exception) {
            System.err.println("Error in Socket operation");
            System.exit(0);
        }
        catch (NumberFormatException exception) {
            exception.printStackTrace();
            System.err.println("Error in Number Formatting");
            System.exit(0);
        }
        catch (IOException exception){
            exception.printStackTrace();
            System.err.println("Error in Io operation");
            System.exit(0);
        }
    }
}

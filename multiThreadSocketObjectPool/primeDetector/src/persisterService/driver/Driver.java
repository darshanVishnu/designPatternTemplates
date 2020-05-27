package persisterService.driver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


import java.io.IOException;
import  persisterService.util.*;
public class Driver {

    private static ServerSocket serverSocket;
    private static boolean stop = false;
    public static void main(String[] args) {
        try {
            CmdChecker cmd = new CmdChecker(args);
            cmd = null;
            Integer port = Integer.parseInt(args[0]);
            String filename =args[1];
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started at " + InetAddress.getLocalHost() + " port " + port);
            Results resultsHandler = new Results(filename);
            while (!stop) {
                try {
                    Socket socket = serverSocket.accept();
                    SocketHandler sHandler = new SocketHandler(socket, resultsHandler);
                    sHandler.start();
                } catch(SocketException se) {
                    System.out.println("Server closing at " + InetAddress.getLocalHost() + "port" + port);
                    System.exit(0);
                } catch(IOException io) {
                    System.out.println(io.getMessage());
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
        catch(Exception exception){
            System.out.println(exception.getMessage());
            System.exit(0);
        }
    }

    public static void closeSocketAndExit() throws IOException {
        stop = true;
        serverSocket.close();
    }
}
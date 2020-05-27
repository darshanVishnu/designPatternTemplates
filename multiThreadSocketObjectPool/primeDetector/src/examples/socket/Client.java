//package socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    public static void main(String[] args) {
        try {
            if(args.length != 2){
                System.err.println("Please enter IP  followed by port");
                System.exit(0);
            }
            String message = "Message from client";
            String ip = args[0];
            Integer port = Integer.parseInt(args[1]);
            Socket socket = new Socket(ip, port);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            for (int i = 0; i < 12; i++) {
                if (i == 11)
                    message = "STOP";
                String tosend = message;
                dataOutputStream.writeUTF(tosend);
                dataOutputStream.flush();
            }
            dataOutputStream.close();
            socket.close();
        }  catch (SocketException exception) {
            exception.printStackTrace();
            System.err.println("Error in Socket ");
            System.exit(0);
        } catch (IOException exception) {
            exception.printStackTrace();
            System.err.println("Error in Io Operations");
            System.exit(0);
        }
        catch (NumberFormatException exception) {
            exception.printStackTrace();
            System.err.println("Error in Number Formatting");
            System.exit(0);
        }
    }

}



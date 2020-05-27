import java.net.Socket;
import java.io.DataInputStream;
import java.io.IOException;

public class SocketHandler extends Thread {

    final private static String STOP = "STOP";

    private Socket socket = null;
    private Results resultHandler = null;
    public SocketHandler(Socket socketIn, Results resultHandlerIn) {
        this.socket = socketIn;
        this.resultHandler = resultHandlerIn;
    }

    @Override 
    public void run() {
        while(true) {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String receivedMessage = dataInputStream.readUTF();
            if (receivedMessage.equals(STOP)) {
                closeResources();
                break;
            }
            resultHandler.writeSingleLine(receivedMessage);
            } catch (IOException e) {
                closeResources();
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    private void closeResources() {
        try {
            socket.close();
            resultHandler.resourceCloser();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
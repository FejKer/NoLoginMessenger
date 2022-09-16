package ovh.fejker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private String serverAddress;
    private int port;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public static void main(String[] args) throws IOException {
        Client c = new Client("localhost", 25000);
    }

    public Client(String serverAddress, int port) throws IOException {
        socket = new Socket(serverAddress, port);

    }

}

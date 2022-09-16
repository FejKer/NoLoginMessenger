package ovh.fejker;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private String serverAddress;
    private int port;
    private Socket socket;


    public static void main(String[] args) throws IOException {
        Client c = new Client("localhost", 25000);
    }

    public Client(String serverAddress, int port) throws IOException {
        socket = new Socket(serverAddress, port);
    }

}

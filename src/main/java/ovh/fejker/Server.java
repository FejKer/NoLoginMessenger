package ovh.fejker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private ServerSocket ss;


    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(25000);
    }

    public void start(int port) throws IOException {
        this.port = port;
        ss = new ServerSocket(port);
        System.out.println("Server started on port " + port);
        while (true) {
            Socket s = ss.accept();
            System.out.println("Client connected: " + s.getInetAddress() + ":" + s.getPort());
        }
    }
}
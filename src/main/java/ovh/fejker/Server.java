package ovh.fejker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private int port;
    private ServerSocket ss;
    private ArrayList<ServerThread> threads;
    private DataInputStream in;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("SERVER'S STARTING");
        new Server(26000);
    }

    public Server(int port) throws IOException, ClassNotFoundException {
        threads = new ArrayList<ServerThread>();
        ss = new ServerSocket(port);
        while (true){
            Socket socket = ss.accept();
            System.out.println(socket.isConnected());
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter printWriter = new PrintWriter(output, true);

            ServerThread serverThread = new ServerThread(socket, input, output, reader, printWriter, this);
            threads.add(serverThread);
        }
    }

    public ArrayList<ServerThread> getThreads(){
        return threads;
    }
}

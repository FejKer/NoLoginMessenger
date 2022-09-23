package ovh.fejker;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private BufferedReader reader;
    private PrintWriter printWriter;
    private Server server;
    private ObjectInputStream objectInputStream;
    private String clientName;

    public ServerThread(Socket socket, InputStream input, OutputStream output, BufferedReader reader, PrintWriter printWriter, Server server) throws IOException, ClassNotFoundException {
        this.socket = socket;
        this.input = input;
        this.output = output;
        this.reader = reader;
        this.printWriter = printWriter;
        this.server = server;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        Client client = (Client) objectInputStream.readObject();
        clientName = client.getClientName();
        start();
    }

    @Override
    public void run(){
        while(true){
            try {
                System.out.println("LISTENING FOR CLIENT");
                String clientMessage = reader.readLine();
                System.out.println(clientMessage);
                threadLoop(clientMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void sendMessage(String clientMessage) {
        printWriter.println(clientMessage);
    }
    private void threadLoop(String clientMessage){
        ArrayList<ServerThread> threads = server.getThreads();
        for (ServerThread thread: threads) {
            System.out.println("LOOPING THROUGH THREADS");
            thread.sendMessage(clientName + ": " + clientMessage);
        }
    }

}

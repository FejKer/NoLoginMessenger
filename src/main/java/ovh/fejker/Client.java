package ovh.fejker;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Serializable{
    private String clientName;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("CLIENT'S STARTING");
        Client c = new Client("localhost", 26000);
    }

    private Client(String ip, int port) throws IOException {
        System.out.println("Enter client name.");
        Scanner scanner = new Scanner(System.in);
        this.clientName = "client_" + scanner.nextLine();

        Socket socket = new Socket(ip, port);
        OutputStream output = socket.getOutputStream();
        InputStream input = socket.getInputStream();
        PrintWriter printWriter = new PrintWriter(output, true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        String clientInput;

        System.out.println(socket.isConnected());

        ClientThread clientThread = new ClientThread(socket, input, bufferedReader);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
        objectOutputStream.writeObject(this);

        while (true) {
            clientInput = scanner.nextLine();
            if(clientInput.equals("exit")) break;
            printWriter.println(clientInput);
        }
    }

    public String getClientName(){
        return this.clientName;
    }

}

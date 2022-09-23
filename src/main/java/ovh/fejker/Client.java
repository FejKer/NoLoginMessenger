package ovh.fejker;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Serializable{
    private String clientName;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ip address:");
        String ip = scanner.nextLine();
        new Client(ip, 26000);
    }

    Client(String ip, int port) throws IOException {
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

        new ClientThread(socket, input, bufferedReader);
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

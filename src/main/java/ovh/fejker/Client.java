package ovh.fejker;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("CLIENT'S STARTING");
        Client c = new Client("localhost", 26000);
    }

    private Client(String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);
        OutputStream output = socket.getOutputStream();
        InputStream input = socket.getInputStream();
        PrintWriter printWriter = new PrintWriter(output, true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        Scanner scanner = new Scanner(System.in);
        String clientInput;

        System.out.println(socket.isConnected());

        while (true) {
            clientInput = scanner.nextLine();
            if(clientInput.equals("exit")) break;
            printWriter.println(clientInput);
            System.out.println(bufferedReader.readLine());
        }
    }
}

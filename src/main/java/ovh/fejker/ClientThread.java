package ovh.fejker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket;
    private InputStream input;
    private BufferedReader bufferedReader;

    public ClientThread(Socket socket, InputStream input, BufferedReader bufferedReader) throws IOException {
        this.socket = socket;
        this.input = input;
        this.bufferedReader = bufferedReader;
        start();
    }

    @Override
    public void run(){
        while(true){
            try {
                System.out.println(bufferedReader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

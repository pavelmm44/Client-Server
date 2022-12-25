import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable{

    Socket clientSocket;
    ChatServer chatServer;
    int numberClient;

    public ClientThread(Socket clientSocket, ChatServer chatServer, int number){
        this.clientSocket = clientSocket;
        this.chatServer = chatServer;
        numberClient = number;
    }

    @Override
    public void run(){
        BufferedReader inpt = null;
        try {
            inpt = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Client #" + numberClient + " is online.");
        try {
            new PrintWriter(clientSocket.getOutputStream(), true).println("Client #" + numberClient + ".");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String clientMessage = null;
        while (true){
            try {
                clientMessage = inpt.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (!"exit".equals(clientMessage)){
                System.out.println("Client #" + numberClient + ": " + clientMessage);
                chatServer.sendMessageForAllClients(numberClient, clientMessage);
            } else {
                break;
            }
        }
    }
}
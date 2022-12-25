import java.io.*;
import java.net.Socket;

public class ConnectInputMessage implements Runnable{
    private Socket serverConnect;
    private InputStream inputStreamServer;

    public InputStream getInputStreamServerServer() {
        return inputStreamServer;
    }

    public ConnectInputMessage(){
        try{
            serverConnect = new Socket("localhost", 4444);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        try {
            inputStreamServer = serverConnect.getInputStream();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(){
        BufferedReader tmp = new BufferedReader(new InputStreamReader(inputStreamServer));
        String serverMessage;

        while (true){
            try {
                serverMessage = tmp.readLine();
            } catch (IOException e){
                throw new RuntimeException(e);
            }

            if (serverMessage != null) {
                System.out.println(serverMessage + "\n");
                break;
            }
        }

        PrintWriter outp = null;
        BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));

        String userMessage = null;
        while (true){
            System.out.print("Enter Message: ");

            try {
                userMessage = inputUser.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                outp = new PrintWriter(serverConnect.getOutputStream(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            outp.println(userMessage);

            if("exit".equals(userMessage)){
                break;
            }
        }
    }
}
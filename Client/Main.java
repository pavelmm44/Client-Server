public class Main {
    public static void main(String[] args) {
        ConnectInputMessage connectWithServer = new ConnectInputMessage();
        Thread threadConnectInputMessage = new Thread(connectWithServer);
        threadConnectInputMessage.start();

        ReceiveMessageFromServer receiveMessage = new ReceiveMessageFromServer(connectWithServer.getInputStreamServerServer());
        Thread threadReceiveMessage = new Thread(receiveMessage);
        threadReceiveMessage.start();
    }
}
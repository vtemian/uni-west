public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        //server.start();

        Client client = new Client();
        client.run();
    }
}
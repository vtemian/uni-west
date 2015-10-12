import java.io.*;
import java.net.*;


public class Server implements Runnable{
    private Thread thread;

    @Override
    public void run() {
        try {
            runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runServer() throws IOException{
        //Creating the server socket:
        ServerSocket socket = null;
        socket = new ServerSocket();

        //Binding to the local socket address -- this is the one the clients should be connecting to:
        InetAddress localIpAddress = null;
        localIpAddress = InetAddress.getByName("0.0.0.0");

        int localIpPort = 20000;
        SocketAddress localSocketAddress = new InetSocketAddress(localIpAddress, localIpPort);
        assert socket != null;
        socket.bind (localSocketAddress);

        while (true) {

            //For each connection accepting a client socket, and:
            Socket client = null;
            client = socket.accept();

            // Starting a new Thread for each client
            final Socket finalClient = client;
            new Thread () {

                public void run () {
                    try {
                        //Receiving and/or sending data;
                        assert finalClient != null;
                        BufferedReader reader = new BufferedReader (new InputStreamReader(finalClient.getInputStream()));
                        BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(finalClient.getOutputStream()));

                        // Reading the request
                        String request = reader.readLine ();
                        // Write the response
                        String response = "Welcome";
                        writer.write(response);
                        writer.newLine();
                        // Do not forget to flush!
                        writer.flush();

                        finalClient.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

    }

    public void start () {
        if (thread == null) {
            thread = new Thread(this, "Server");
            thread.start ();
        }
    }
}

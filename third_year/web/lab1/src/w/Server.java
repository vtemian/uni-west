package w;


import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server {
    private Config config;
    private RequestParser requestParser;
    private Processor processor;

    public Server(){
        config = new Config();
        config.loadConfig();

        requestParser = new RequestParser();
        processor = new Processor(config);
    }

    public void start() throws IOException{
        //Creating the server socket:
        final ServerSocket socket = new ServerSocket ();

        //Binding to the local socket address -- this is the one the clients should be connecting to:
        InetAddress localIpAddress = InetAddress.getByName("0.0.0.0");
        int localIpPort = 20000;
        SocketAddress localSocketAddress = new InetSocketAddress(localIpAddress, localIpPort);
        socket.bind (localSocketAddress);

        while (true) {

            //For each connection accepting a client socket, and:
            final Socket client = socket.accept ();

            // Starting a new Thread for each client
            new Thread () {

                public void run () {
                    try {
                        //Receiving and/or sending data;
                        BufferedReader reader = new BufferedReader (new InputStreamReader(client.getInputStream ()));
                        BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(client.getOutputStream ()));

                        // Process the request
                        HashMap<String, String> request = requestParser.parseRequest(reader);
                        writer.write(processor.process(request));
                        writer.newLine();
                        // Do not forget to flush!
                        writer.flush();

                        client.close ();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }

        //Closing the server socket;
        // TODO: close socket when you finish the program and all the threads are finished
    }
}

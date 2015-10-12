import java.io.*;
import java.net.*;

public class Client implements Runnable{
    private Thread thread;

    @Override
    public void run() {
        try {
            runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runClient() throws IOException{
        //Creating the client socket:
        Socket socket = new Socket ();

        //Binding to the local socket address:
        InetAddress localIpAddress = null;
        localIpAddress = InetAddress.getByName("0.0.0.0");
        int localIpPort = 0;
        SocketAddress localSocketAddress = new InetSocketAddress(localIpAddress, localIpPort);
        socket.bind (localSocketAddress);

        //Connecting to the remote socket address:
        InetAddress remoteIpAddress = null;
        remoteIpAddress = InetAddress.getByName("google.ro");
        int remoteIpPort = 443;
        SocketAddress remoteSocketAddress = new InetSocketAddress (remoteIpAddress, remoteIpPort);
        socket.connect (remoteSocketAddress);

        //Receiving and/or sending data through inbound and outbound streams:
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream ()));
        BufferedWriter writer = null;
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream ()));
        String request = "GET / HTTP/1.1\n" +
                "Accept: text/html\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)\n" +
                "Host: google.ro\n\n";
        writer.write (request);

        // Do not forget to flush
        writer.flush ();

        // Reading the response
        String response;
        while((response=reader.readLine()) != null) {
            System.out.println(response);
        }

        //Shutting-down the inbound and outbound streams:
        socket.shutdownInput ();
        socket.shutdownOutput ();

        //Closing the socket:
        socket.close ();
    }

    public void start () {
        if (thread == null) {
            thread = new Thread(this, "Client");
            thread.start ();
        }
    }
}

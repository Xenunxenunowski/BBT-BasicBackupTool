package filetransport.sockets;

import filetransport.transportlayer.TransportDataModel;
import net.lingala.zip4j.ZipFile;

import java.net.*;
import java.io.*;

public class Server
{
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private ObjectInputStream in       =  null;

    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");
            for (;;){
                System.out.println("Waiting for a client ...");

                new Thread(new ServerChild(server.accept())).start();
                if(false){
                    break;
                }
            }
        }
        catch(IOException i) {
            i.printStackTrace();
        }
    }

    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}
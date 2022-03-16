package filetransport.sockets;

import backupcomponents.BackupRecord;
import filetransport.transportlayer.InitiationDataModel;
import filetransport.transportlayer.TransportDataModel;
import filetransport.zipActions.ZipCompression;
import net.lingala.zip4j.model.enums.CompressionMethod;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    // initialize socket and input output streams
    private Socket socket = null;
    private InputStream input = null;
    private OutputStream out = null;

    /**
     * Client for connecting to server once and sending a backup
     * @param address
     * @param port
     * @param backupRecord
     */
    public Client(String address, int port, BackupRecord backupRecord) {
        // establish a connection
        try {

            socket = new Socket(address, port);
            System.out.println("Connected");
            input = new DataInputStream(System.in);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
            out = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(new InitiationDataModel((byte)1,backupRecord));
            objectOutputStream.flush();
            System.out.println("WEEWEWE");
            ZipCompression.zipFolder(backupRecord.getFiles(),backupRecord.getNameOfRecord(),objectOutputStream,backupRecord);
            input.close();
            socket.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();

            //close conn
            try {
                input.close();
                socket.close();
            } catch (IOException i) {
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
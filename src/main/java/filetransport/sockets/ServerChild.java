package filetransport.sockets;

import backupcomponents.BackupRecord;
import filetransport.transportlayer.InitiationDataModel;
import filetransport.transportlayer.TransportDataModel;
import net.lingala.zip4j.ZipFile;
import swing.BackupConfigurator;

import java.io.*;
import java.net.Socket;

public class ServerChild implements Runnable{
    private Socket socket;
    private ObjectInputStream in;
    private InitiationDataModel initiationDataModel;
    public ServerChild(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        try {
            System.out.println("Client accepted");
        System.out.println("Working as " + Thread.currentThread().getName());
        // takes input from the client socket


            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Waiting for Initiation");
            initiationDataModel = (InitiationDataModel) in.readObject();
            System.out.println("Recived Initiation");
            File f = new File(initiationDataModel.backupRecord().getUUID()+".zip");
            FileOutputStream outputStream = new FileOutputStream(f);
        TransportDataModel transportDataModel;
            while (true) {
                System.out.println("STARTED LOOP");
                    transportDataModel = (TransportDataModel) in.readObject();
                    System.out.println("recived Data");
                    if (transportDataModel.flag() == -1) {
                        break;
                    }
                    if (transportDataModel.data().length != 0) {
                        outputStream.write(transportDataModel.data());
                    }
                System.out.println("GOTCHA");

            }
            outputStream.flush();
            outputStream.close();
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
            ZipFile zipFile = new ZipFile("outzzz.zip");
            zipFile.extractAll("/home/xenu/IdeaProjects/BBT-BasicBackupTool/outzip");
            System.out.println("SUCCCCCCCCCCCCCCCCCCCCCCES");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

import backupcomponents.BackupManager;
import backupcomponents.BackupRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import filetransport.sockets.Client;
import filetransport.sockets.Server;
import serverconfiguration.ServerConfigurator;
import swing.MainForm;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static BackupManager backupManager = new BackupManager();
    public static ServerConfigurator serverConfigurator = ServerConfigurator.getInstance();
    public static void main(String[] args) throws InterruptedException {
        //load all settings
        File pathToConfig = new File(System.getProperty("user.home")+"/BBT/config/config.json");
        if(pathToConfig.exists()){
            System.out.println(pathToConfig);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                serverConfigurator = objectMapper.readValue(pathToConfig,ServerConfigurator.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
        {
            new File(System.getProperty("user.home")+"/BBT/config").mkdirs();
        }

        File f = new File("/home/xenu/Downloads/Piskel");
        List<File> files = new ArrayList<>();
        files.add(f);
        new Thread(GUI).start();
        new Thread(server).start();
        try {
            Thread.sleep(1000);
            //System.out.println(backupManager.getBackupRecords().get(0).nameOfRecord());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //WORKS
        new Thread(client).start();
        new Thread(client2).start();

    }
    static Runnable server =()->{
        Server server = new Server(5000);
    };
    static Runnable GUI =()->{
        MainForm mainForm = new MainForm(backupManager);
        mainForm.start();
    };
    static Runnable client =()->{
        List<File> fileList2 = new ArrayList<>();
        fileList2.add(new File("/home/xenu/ang/17.12.2021"));
        fileList2.add(new File("/home/xenu/ang/10.12.2021"));
        fileList2.add(new File("/home/xenu/ang/04.03.2022"));
        Client client = new Client(serverConfigurator.getServerDomain(), serverConfigurator.getPortNumber(),new BackupRecord(fileList2,"Noice Backup","asdfaefew", DayOfWeek.WEDNESDAY,10,40));
    };
    static Runnable client2 =()->{
        List<File> fileList2 = new ArrayList<>();
        fileList2.add(new File("/home/xenu/ang/17.12.2021"));
        fileList2.add(new File("/home/xenu/ang/10.12.2021"));
        Client client = new Client(serverConfigurator.getServerDomain(), serverConfigurator.getPortNumber(),new BackupRecord(fileList2,"BETTTER","4345r24tfg4fr5", DayOfWeek.MONDAY,12,32));
    };
}

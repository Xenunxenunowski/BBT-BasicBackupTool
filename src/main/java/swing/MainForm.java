package swing;

import backupcomponents.BackupManager;
import backupcomponents.BackupRecord;
import backupcomponents.DirectoryPreperer;
import com.fasterxml.jackson.databind.ObjectMapper;
import serverconfiguration.ServerConfigurator;
import javax.swing.*;
import java.io.File;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainForm {
    private JPanel yes;
    private JLabel hymm;
    private JFormattedTextField formattedTextField1;
    private JRadioButton autoLoginRadioButton;
    private JPasswordField passwordField1;
    private JPanel servConfig;
    private JPanel Backups;
    private JTabbedPane Tabs;
    private JList<BackupRecord> list1;
    private JButton addButton;
    private JButton removeButton;
    private JButton backup;
    private JButton applyButton;
    private JButton cancelButton;
    private JRadioButton clientRadioButton;
    private JFormattedTextField portField;
    private JLabel backupRecordName;
    private JLabel estimatedRecordSize;
    private JLabel backupHour;
    private JButton editButton;
    private JProgressBar progressBar1;
    private BackupManager backupManager;
    private ServerConfigurator serverConfigurator = new ServerConfigurator();
    private BackupRecord currentBackupRecord;
    public MainForm (BackupManager backupManager, ServerConfigurator serverConfigurator){
        DefaultListModel<BackupRecord> listModel = new DefaultListModel<>();


        this.serverConfigurator=serverConfigurator;
        this.backupManager = backupManager;
        //set values as in config
        {
            formattedTextField1.setText(serverConfigurator.getServerDomain());
            passwordField1.setText(String.valueOf(serverConfigurator.getServerPassword()));
            autoLoginRadioButton.setSelected(serverConfigurator.isAutoLogin());
            portField.setText(String.valueOf(serverConfigurator.getPortNumber()));
            clientRadioButton.setSelected(serverConfigurator.isWorkAsClient());
        }

        //settings stuff
        applyButton.addActionListener((actionEvent)->{
            System.out.println("Applying settings ...");
            try {
                serverConfigurator.setServerDomain(formattedTextField1.getText());
                serverConfigurator.setServerPassword(passwordField1.getPassword());
                serverConfigurator.setAutoLogin(autoLoginRadioButton.isSelected());
                serverConfigurator.setPortNumber(Integer.parseInt(portField.getText()));
                serverConfigurator.setWorkAsClient(clientRadioButton.isSelected());
                File pathToConfig = new File(System.getProperty("user.home")+"/BBT/config/config.json");
                if(!pathToConfig.isFile()){
                    new File(System.getProperty("user.home")+"/BBT/config").mkdirs();
                }
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(pathToConfig,serverConfigurator);
                System.out.println("Successfully applied settings");
            }catch (Exception e)
            {
                System.out.println("Could not apply settings "+ e.getLocalizedMessage());
                e.printStackTrace();
            }

        });

        //Add BackupRecord Button
        addButton.addActionListener((actionEvent)->{

            listModel.addElement(new BackupRecord(new ArrayList<File>(),"TEST", UUID.randomUUID().toString(),DayOfWeek.WEDNESDAY,12,43));
            new Thread(new BackupConfigurator(listModel.lastElement())).run();
        });

        List<File> fileList = new ArrayList<>();
        fileList.add(new File("/home/xenu/ang/17.12.2021"));
        fileList.add(new File("/home/xenu/ang/10.12.2021"));
        listModel.addElement(new BackupRecord(fileList,"Noice Backup","asdfaefew",DayOfWeek.WEDNESDAY,10,40));
        list1.setModel(listModel);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //list ActionListener
        list1.addListSelectionListener((event)->{
            currentBackupRecord = listModel.get(list1.getSelectedIndex());
            backupRecordName.setText(currentBackupRecord.getNameOfRecord());
            estimatedRecordSize.setText("calculating");
            backupHour.setText("Backup hour: "+(currentBackupRecord.getHour()));
            new Thread(CalculateSize).start();
        });
    }
    Runnable CalculateSize=()->{
        estimatedRecordSize.setText("Size: "+String.format("%.2f", DirectoryPreperer.directorySize(currentBackupRecord.getFiles())/1048576f)+ " MB");
    };
    public void start(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(UIManager.getLookAndFeel());
        JFrame frame = new JFrame("Basic Backup Tool");
        frame.setName("Basic Backup Tool");
        frame.setContentPane(new MainForm(backupManager,serverConfigurator).Tabs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}

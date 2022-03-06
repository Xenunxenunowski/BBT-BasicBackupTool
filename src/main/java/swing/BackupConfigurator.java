package swing;

import backupcomponents.BackupRecord;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.Objects;

public class BackupConfigurator implements Runnable{
    private JTabbedPane tabs;
    private JPanel foldersAndFilesConfig;
    private JPanel BackupConfig;
    private JScrollPane folderandFilelist;
    private JPanel controlMenu;
    private JButton addEntryButton;
    private JButton removeEntryButton;
    private JTree tree1;
    private JList list1;
    private JComboBox dayOfWeekCombo; // TODO: 06.01.2022  use ckeckboxes inside of day of week and month combo
    private JComboBox hourCombo;
    private JComboBox minuteCombo;
    private JRadioButton notificationOnSuccesfullBackupRadioButton;
    private JRadioButton notificationOnUnsuccesfullBackupRadioButton;
    private JLabel backupEditor;
    private JButton backup;
    private JTextField backupName;
    private BackupRecord backupRecord;
    public BackupConfigurator(BackupRecord backupRecord) {
        this.backupRecord = backupRecord;
        ComboBoxModel comboBoxModel = new DefaultComboBoxModel(DayOfWeek.values());
        dayOfWeekCombo.setModel(comboBoxModel);
        for(int x=0; x<=59; x++){
            minuteCombo.addItem(x);
        }
        backupName.setText(backupRecord.getNameOfRecord());
        backup.addActionListener(e -> {
            System.out.println("yey");
        });
        hourCombo.addActionListener((actionEvent) ->{
            System.out.println(hourCombo.getSelectedItem());
            backupRecord.setHour(Integer.parseInt((String) Objects.requireNonNull(hourCombo.getSelectedItem())));
        });
        backupName.addActionListener((actionEvent)->{
           backupRecord.setNameOfRecord(backupName.getText());
        });

    }

    @Override
    public void run() {
        System.out.println("EMMM ?");
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(UIManager.getLookAndFeel());
        JFrame frame = new JFrame(backupRecord.getNameOfRecord());
        backup.setActionCommand("backup");
        frame.setContentPane(new BackupConfigurator(backupRecord).tabs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}

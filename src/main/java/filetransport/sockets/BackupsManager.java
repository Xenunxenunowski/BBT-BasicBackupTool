package filetransport.sockets;

import backupcomponents.BackupRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BackupsManager{
    List<BackupRecord> listOfBackups = new ArrayList<>();
    
    public void addBackupRecord(BackupRecord backupRecord){
        listOfBackups.add(backupRecord);
    }
    public void removeBackupRecord(String uuid){
        listOfBackups.removeIf(backupRecord -> backupRecord.getUUID().equals(uuid));
    }
    public BackupsManager(){
        
    }
    Runnable checkforUpdates=()->{
        LocalDateTime localDateTime;
        while(true)
        {
            localDateTime = LocalDateTime.now();
            for (BackupRecord backup: listOfBackups) {
                if(backup.getDayOfWeek().equals(localDateTime.getDayOfWeek()))
                {
                    if(backup.getMinute() == localDateTime.getMinute() && backup.getHour()==localDateTime.getHour())
                    {

                    }
                }
            }
        }
    };
}

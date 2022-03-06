package backupcomponents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BackupManager {
    private List<BackupRecord> backupRecords = new ArrayList<>();
    private List<BackupRecord> backupRecordsToBackup;

    public List<BackupRecord> getBackupRecords() {
        return backupRecords;
    }

    public List<BackupRecord> getBackupRecordsToBackup() {
        return backupRecordsToBackup;
    }

    public void setBackupRecords(List<BackupRecord> backupRecords) {
        this.backupRecords = backupRecords;
    }

    public void setBackupRecordsToBackup(List<BackupRecord> backupRecordsToBackup) {
        this.backupRecordsToBackup = backupRecordsToBackup;
    }

    //this is nasty
    public void thisThingy(){
      new Thread(yupNastyThing).start();
    }

    Runnable yupNastyThing =() -> {
        if(backupRecordsToBackup.size()!=0)
        {
            for (BackupRecord backupRecord:backupRecordsToBackup) {
                //zip and send thingy here
            }
            backupRecordsToBackup.clear();
        }
        else {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    public void addRecord(BackupRecord backupRecord){
        backupRecords.add(backupRecord);
    }
    public void removeRecord(BackupRecord backupRecord){
        for (int x=0; x<backupRecords.size(); x++){
            if(Objects.equals(backupRecords.get(x).getUUID(), backupRecord.getUUID())){
                backupRecords.remove(x);
            }
        }
    }
}

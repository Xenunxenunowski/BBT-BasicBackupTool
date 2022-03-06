package backupcomponents.timing;

import backupcomponents.BackupRecord;

import java.time.LocalDateTime;
import java.util.List;

public class TimeManager {
    public void lookAfterTime(List<BackupRecord> backupRecordList){
        LocalDateTime localDateTime = LocalDateTime.now();

        while(true)
        {
            localDateTime = LocalDateTime.now();
            for (BackupRecord backup: backupRecordList) {
                if(backup.getDayOfWeek().equals(localDateTime.getDayOfWeek()))
                {
                    if(backup.getMinute() == localDateTime.getMinute() && backup.getHour()==localDateTime.getHour())
                    {

                    }
                }
            }
        }
    }
}

package filetransport;

import backupcomponents.BackupRecord;
import filetransport.transportlayer.TransportDataModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileTransportController {

    List<BackupRecord> toBackup= new ArrayList<BackupRecord>();
    List<TransportDataModel> toSend = new ArrayList<TransportDataModel>();
    private void writeDataToFile(TransportDataModel dataModel){
        
    }
    Runnable uploadThreadManager=() ->{
        while (true)
        {
            try {
                LocalDateTime localDateTime = LocalDateTime.now();
                localDateTime = LocalDateTime.now();
                for (BackupRecord backup: toBackup) {
                    if(backup.getDayOfWeek().equals(localDateTime.getDayOfWeek()))
                    {
                        if(backup.getHour()==localDateTime.getHour() && backup.getMinute() == localDateTime.getMinute())
                        {
                            Runnable r =() ->{
                                //ZipCompression.addMultipleNestedDirectoriesToZipFileUsingPackets(backup.files(), CompressionMethod.DEFLATE,);
                            };
                        }
                    }
                }

                Thread.sleep(30000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}

package backupcomponents;

import java.io.File;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;

public class BackupRecord implements Serializable {
    private List<File> files;
    private String nameOfRecord,UUID;
    private DayOfWeek dayOfWeek;

    public BackupRecord(List<File> files, String nameOfRecord, String UUID, DayOfWeek dayOfWeek, int hour, int minute) {
        this.files = files;
        this.nameOfRecord = nameOfRecord;
        this.UUID = UUID;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.minute = minute;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getNameOfRecord() {
        return nameOfRecord;
    }

    public void setNameOfRecord(String nameOfRecord) {
        this.nameOfRecord = nameOfRecord;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    private int hour,minute;


    @Override
    public String toString() {
        return nameOfRecord;
    }
}

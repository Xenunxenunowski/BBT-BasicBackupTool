package filetransport.transportlayer;

import backupcomponents.BackupRecord;

import java.io.Serializable;

public record InitiationDataModel(byte id, BackupRecord backupRecord) implements Serializable {
}

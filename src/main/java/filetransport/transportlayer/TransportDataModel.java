package filetransport.transportlayer;

import java.io.Serializable;

public record TransportDataModel(byte flag, byte[] data, byte uuid) implements Serializable {
}

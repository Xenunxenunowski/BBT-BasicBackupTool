package filetransport.zipActions;
import backupcomponents.BackupRecord;
import filetransport.transportlayer.TransportDataModel;

import java.io.*;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompression {
    public static void zipFolder(List<File> srcFolder, String destZipFile, ObjectOutputStream  obj, BackupRecord backupUUID) throws Exception {
        ZipOutputStream zip = null;
        FileOutputStream fileWriter = null;

        fileWriter = new FileOutputStream(destZipFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zip = new ZipOutputStream(byteArrayOutputStream);
        for (File file: srcFolder) {
            addFileToZip(backupUUID.getUUID(), file.toString(), zip , byteArrayOutputStream,obj);
        }
        zip.flush();
        zip.close();
        obj.writeObject(new TransportDataModel((byte) 1, byteArrayOutputStream.toByteArray(),(byte) 1));
        obj.writeObject(new TransportDataModel((byte) -1, byteArrayOutputStream.toByteArray(),(byte) 1));
    }

    private static void addFileToZip(String path, String srcFile, ZipOutputStream zip, ByteArrayOutputStream baos, ObjectOutputStream  obj) throws Exception {

        File folder = new File(srcFile);
        if (folder.isDirectory()) {
            addFolderToZip(path, srcFile, zip,baos,obj);
        } else {
            byte[] buf = new byte[1024];
            int len;
            FileInputStream in = new FileInputStream(srcFile);
            zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
            while ((len = in.read(buf)) > 0) {
                zip.write(buf, 0, len);
                TransportDataModel data= new TransportDataModel((byte) 1,baos.toByteArray(),(byte) 1);
                obj.writeObject(data);
                System.out.println("compressed chunk");
                baos.reset();
            }
        }
    }

    private static void addFolderToZip(String path, String srcFolder,ZipOutputStream zip,ByteArrayOutputStream baos,ObjectOutputStream  obj) throws Exception {
        File folder = new File(srcFolder);

        for (String fileName : folder.list()) {
            if (path.equals("")) {
                addFileToZip(folder.getName(), srcFolder + "/" + fileName,zip,baos,obj);
            } else {
                addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip,baos,obj);
            }
        }
    }
}

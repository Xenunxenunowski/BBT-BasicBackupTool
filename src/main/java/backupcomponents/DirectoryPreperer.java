package backupcomponents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryPreperer {

    public static List<File> generateFolderUploadStructure(File dir)
    {
        List<File> readyDirectories = new ArrayList<>();
        if(directorySize(dir)/(1048576)< 100){
            //zip
            readyDirectories.add(dir);
            List<File> directories = new ArrayList<>();
            directories = yep(dir);
            directories.add(new File(""));
            System.out.println(directories.get(0).length());
        }else
        {
            readyDirectories = yep(dir);
            System.out.println(readyDirectories.get(0).length());
        }
        return readyDirectories;

    }
    private static List<File> yep(File dir){
        List<File> directories = new ArrayList<>();
        if(dir.isDirectory())
        {
            for(File file : dir.listFiles())
            {
                if(directorySize(file)/(1048576)<100){
                    directories.add(file);
                }else
                {
                    directories.addAll(yep(file));
                }
            }
        }
        return directories;
    }
    /**
     * @param directory directory to calculate the size of
     * @return  returns long type of size in Bytes
     */
    public static long directorySize(File directory)
    {
        long length = 0;

        File[] files = directory.listFiles();

        if(files != null)
        {
            // loop for traversing the directory
            for (File file : files) {
                if (file.isFile()) {
                    length += file.length();
                } else {
                    length += directorySize(file);
                }
            }
        }
        return length;
    }

    /**
     * @param directory list of files to calculate the size of
     * @return  returns long type of size in Bytes
     */
    public static long directorySize(List<File> directory)
    {
        long length = 0;
        for (File singleFile:directory) {
            File[] files = singleFile.listFiles();

            if(files != null)
            {
                // loop for traversing the directory
                for (File file : files) {
                    if (file.isFile()) {
                        length += file.length();
                    } else {
                        length += directorySize(file);
                    }
                }
            }
        }

        return length;
    }
    private static boolean containsDirectory(File directory){
        for (File file: directory.listFiles()) {
            if(file.isDirectory())
            {
                return true;
            }
        }
        return false;
    }
    //approximate whether file should be sent separately or can be bundled with other files
}

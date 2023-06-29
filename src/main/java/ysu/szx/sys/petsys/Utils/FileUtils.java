package ysu.szx.sys.petsys.Utils;

import org.springframework.stereotype.Component;
import ysu.szx.sys.petsys.Pojo.LocalFile;

import java.io.File;
import java.util.ArrayList;

@Component
public class FileUtils {
    public static ArrayList<Object> TraverseFiles(File folder){
        ArrayList<Object> list = new ArrayList<>();
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file : files) {
                list.add(new LocalFile(file.getName()));
            }
        }
        return list;
    }
    public static boolean DeleteFile(File file){
        return file.delete();
    }
}

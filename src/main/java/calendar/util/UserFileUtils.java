package calendar.util;

import calendar.model.UserFile;

import java.io.*;

/**
 * Created by Marat on 12.04.2015.
 */
public class UserFileUtils {
    private static final String path = "C:\\Users\\Niyaz\\Desktop\\Diary\\target\\calendar_app-1.0-SNAPSHOT\\resources\\userfiles\\";

    public static byte[] readFile(String name) throws IOException {
        RandomAccessFile file = new RandomAccessFile(new File(path + name), "rw");
        byte[] bytes = new byte[0];
        file.read(bytes);
        return bytes;
    }

    public static void writeFile(byte[] bytes, String name) throws IOException {
        File file = new File(path + name);
        file.createNewFile();


        RandomAccessFile Ffile = new RandomAccessFile(file, "rw");
        Ffile.write(bytes);
        Ffile.close();
    }
}

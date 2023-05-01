package entity;

import java.io.*;

public class FileToByte implements Serializable {

    public static byte[] getBytes(File file) {
        byte[] arr;
        try {
            FileInputStream fl = new FileInputStream(file);
            arr = new byte[(int) file.length()];
            fl.read(arr);
            fl.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arr;
    }
}

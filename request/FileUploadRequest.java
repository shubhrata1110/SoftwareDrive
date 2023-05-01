package request;

public class FileUploadRequest extends Request{
    public  static String userName;
    public static String fileName;
    public static byte[] fileInbytes;

    public FileUploadRequest(String userName, String fileName, byte[] fileInbytes) {
        this.userName=userName;
        this.fileName=fileName;
        this.fileInbytes=fileInbytes;
    }

    public static byte[] getFileInbytes() {
        return fileInbytes;
    }
}

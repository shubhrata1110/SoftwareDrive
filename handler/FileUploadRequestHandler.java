package handler;
import main.Main1;
import request.FileUploadRequest;
import response.FileUploadResponse;

import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.io.IOException;

import static request.FileUploadRequest.fileName;

public class FileUploadRequestHandler extends Handler implements Serializable {
    private  ObjectInputStream ois;
    private FileUploadRequest request;
    private FileUploadRequest fileUploadRequest;
    private  ObjectOutputStream oos;
    private  Connection connection;
    private String path;

    private int fileid;
    public FileUploadRequestHandler(Connection connection, ObjectInputStream ois, ObjectOutputStream oos, FileUploadRequest request) {
        this.request = request;
        this.oos = oos;
        this.connection = connection;
        this.ois = ois;
        this.path = "C://Users//shubhrata//Desktop//PlacementDrive//storage/";


    }
    @Override
    public void sendResponse(String userId) {
       try {
           Main1.sendResponseToClient(oos, new FileUploadResponse(true));
           byte[] file = (byte[]) ois.readObject();
           System.out.println("creating a file");
           createfile(file, userId,fileName);
       } catch (IOException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }

    }

    private void createfile(byte[] data, String userId, String fileName) {
        try {
            File myfile = new File(path+userId);
            if (!myfile.exists())
                myfile.mkdirs();
            File newFile = new File(path + fileName + "");
            newFile.createNewFile();
            Files.write(newFile.toPath(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






//    @Override
//    public void handlerResponse(int fileid) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("insert into files(fileLink,fileOwnerID) values(?,?)");
//            preparedStatement.setString(2, path + fileid + ".txt");
//            preparedStatement.setInt(3, fileid);
//
//            preparedStatement.executeUpdate();
//            Main1.sendResponse(oos, new FileUploadResponse(true));
//
//            byte[] fileData = (byte[]) ois.readObject();
//            createfile(fileData, fileid);
//
//        } catch (SQLException | IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

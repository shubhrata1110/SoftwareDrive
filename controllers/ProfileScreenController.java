package controllers;

import client.ClientMain;
import client.GuiUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import jdk.jfr.Frequency;
import main.Main1;
import request.FileUploadRequest;
import response.FileUploadResponse;


import javax.imageio.IIOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import static client.ClientMain.userName;
import static entity.FileToByte.getBytes;

public class ProfileScreenController implements Initializable {

        @FXML
        public Button bt1;
        public ListView ls1;
        private String name;
        @FXML
        public Label heyNameLabel;

        public void fileUpload(javafx.event.ActionEvent actionEvent){
            FileChooser fc=new FileChooser();
            File file= fc.showOpenDialog(null);
            if(file!= null)
            {

                FileUploadRequest request=new FileUploadRequest(userName,file.getName(),getBytes(file));
                ClientMain.sendRequestToServer(request);
                FileUploadResponse response=(FileUploadResponse) ClientMain.getResponseFromServer();
                if (response.isSendFileStream()){
                    try {
                        byte[] fileByte = Files.readAllBytes(file.toPath());
                        ClientMain.oos.writeObject(fileByte);
                        ls1.getItems().add(file.getName());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }else {
                    GuiUtil.alert(Alert.AlertType.ERROR, "Kuch locha ho gaya. Fail tum.");
                }

            }
            else {
                System.out.println("file doesnt exist");

            }







}

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            
        }

        public void first(String name) {
            ClientMain.profileScreenController = this;
            this.name=name;
            heyNameLabel.setText("Hey, "+name);
            System.out.println("inside the first method ");
        }
    }

package controllers;

import client.ClientMain;
import client.GuiUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import request.LoginRequest;
import response.LoginResponse;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    public AnchorPane loginPane;
    @FXML
    public Label passwordlb;
    @FXML
    public Button loginBt;
    @FXML
    public Label userNamelb;
    @FXML
    public TextField usernametf;
    @FXML
    public Hyperlink signuplink;
    @FXML
    public PasswordField password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void login(ActionEvent actionEvent) {
        LoginRequest request=new LoginRequest(usernametf.getText(),password.getText());
        ClientMain.sendRequestToServer(request);
        LoginResponse response=(LoginResponse) ClientMain.getResponseFromServer();
        if(response==null)
            GuiUtil.alert(Alert.AlertType.ERROR,"Incorrect Information.Please try again.");
        else{
            ClientMain.userName = response.getUserName();

            FXMLLoader homepageLoader= new FXMLLoader(getClass().getResource("../views/ProfileScreen.fxml"));
            Stage currentStage=(Stage)loginBt.getScene().getWindow();
            Scene scene=null;
            try {
                scene=new Scene(homepageLoader.load(), loginBt.getScene().getWidth(), loginBt.getScene().getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentStage.setScene(scene);
            currentStage.setMaximized(true);
            currentStage.setTitle("Welcome");
            currentStage.setOnCloseRequest(event -> {
                try {
                    ClientMain.oos.close();
                    ClientMain.ois.close();
                    ClientMain.oos = null;
                    ClientMain.ois = null;
                } catch (IOException e) {
                    ClientMain.oos = null;
                    ClientMain.ois = null;
                    e.printStackTrace();
                }
            });
          ClientMain.userFullName = response.getFirstName() + " " + response.getLastName();
           ProfileScreenController profileScreenController=homepageLoader.getController();
            profileScreenController.first(response.getFirstName()+" "+response.getLastName());
        }
    }


    public void goToSignup(ActionEvent actionEvent) {
        FXMLLoader registerLoader=new FXMLLoader(getClass().getResource("../views/Register.fxml"));
        Scene scene=null;
        Stage stage=(Stage)signuplink.getScene().getWindow();
        try {
            scene=new Scene(registerLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        RegisterController registerController=registerLoader.getController();
        registerController.first();
        stage.setTitle("Sign Up");

    }
}


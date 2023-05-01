package controllers;

import client.ClientMain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import request.RegisterRequest;
import response.RegisterResponse;

import java.awt.*;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class RegisterController {
   
   public boolean check;
    public Label matchLabel;
    public TextField firstNametb;
    public TextField lastNametb;
    public TextField userNameSignUptb;
    public TextField emailSignUptb;
    public PasswordField passwordSignUptb;
    public Button registerbt;
    public Hyperlink backToLogin;
    public TextField ConfirmPasswordtf;

    public void first() {
        firstNametb.setTextFormatter(new TextFormatter<>(c -> c.getControlNewText().matches(".{0,15}") ? c : null));
        lastNametb.setTextFormatter(new TextFormatter<>(c -> c.getControlNewText().matches(".{0,15}") ? c : null));
        emailSignUptb.setTextFormatter(new TextFormatter<>(c -> c.getControlNewText().matches(".{0,50}") ? c : null));
        userNameSignUptb.setTextFormatter(new TextFormatter<>(c -> c.getControlNewText().matches(".{0,8}") ? c : null));
    }


    public void checkIfMatches(ActionEvent actionEvent) {
        if (passwordSignUptb.getText().equals(ConfirmPasswordtf.getText())) {
            check = true;
            matchLabel.setText("Passwords Match");
        } else {
            check = false;
            matchLabel.setText("Passwords don't match");
        }
    }



    public void register(javafx.event.ActionEvent actionEvent) {
        FXMLLoader loginLoader=new FXMLLoader(getClass().getResource("../views/Login.fxml"));
        if(passwordSignUptb.getText().equals(ConfirmPasswordtf.getText())){
            RegisterRequest registerRequest=new RegisterRequest(firstNametb.getText(),lastNametb.getText(),emailSignUptb.getText(),
                    passwordSignUptb.getText(),userNameSignUptb.getText());
            ClientMain.sendRequestToServer(registerRequest);
            System.out.println("Register request sent");
            RegisterResponse response=(RegisterResponse)ClientMain.getResponseFromServer();
            assert response != null;
            if(response.getMessage().length()==0) System.out.println("Please Try Again");
            else {
                Stage stage=(Stage)registerbt.getScene().getWindow();
                Scene scene=null;
                try {
                    scene=new Scene(loginLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("Login");
                stage.setScene(scene);
            }
        }
        else System.out.println("Please enter correct info");

    }

    public void switchToLogin(javafx.event.ActionEvent actionEvent) {
        FXMLLoader loginLoader=new FXMLLoader(getClass().getResource("../views/Login.fxml"));
        Stage stage=(Stage)backToLogin.getScene().getWindow();
        Scene scene=null;
        try {
            scene=new Scene(loginLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle("Login");

    }
}

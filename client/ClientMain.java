package client;

import controllers.LoginController;
import controllers.ProfileScreenController;
import request.Request;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


    public class ClientMain extends Application {
        public static ObjectInputStream ois=null;
        public static ObjectOutputStream oos=null;
        public static Socket socket=null;
        public static String userName;
        public static String userFullName;
        public static ProfileScreenController profileScreenController;

        public static void main(String[] args) {
            launch(args);
        }
            @Override
            public void start(Stage primaryStage) {
                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../views/Login.fxml"));
                try{
                    System.out.println("connecting");
                    socket=new Socket("localhost",3200);
                    System.out.println(socket);
                    oos=new ObjectOutputStream(socket.getOutputStream());
                    ois=new ObjectInputStream(socket.getInputStream());
                    System.out.println("Connection established and io streams created");
                    System.out.println(Thread.currentThread());
                }catch (IOException e){
                    e.printStackTrace();
                }
                primaryStage.setTitle("Sign In");
                try {
                    primaryStage.setScene(new Scene(fxmlLoader.load()));
                    LoginController login=fxmlLoader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                primaryStage.show();
            }

        public static void sendRequestToServer(Request request){
            try {
                oos.writeObject(request);
                oos.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static Object getResponseFromServer(){
            try {

                return ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
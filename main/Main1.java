package main;

import response.Response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;

public class Main1 {
    static int port = 3200;
    static Connection connection;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket;


        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }


        while (true) {
            try {
                assert serverSocket != null;
                Connection connection1 = getConnection();
                //socket getting accepted by server socket
                socket = serverSocket.accept();

               Thread thread = new Thread(new RequestTypes(socket));
                //starting thread
               thread.start();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }


    public static Connection getConnection() {
        if (connection != null) return connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/serverdb";
            connection = DriverManager.getConnection(url, "root", "@Abhiveer18");
            System.out.println("Database connected");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    public static void sendResponseToClient(ObjectOutputStream outputStream, Response response){
        try {
            outputStream.writeObject(response);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Object receiveRequestFromClient(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        return inputStream.readObject();
    }}


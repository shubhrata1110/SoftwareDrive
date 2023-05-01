package main;

import handler.FileUploadRequestHandler;
import handler.LoginRequestHandler;
import handler.RegisterRequestHandler;
import request.FileUploadRequest;
import request.LoginRequest;
import request.RegisterRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;


    public  class RequestTypes implements Runnable {
        Socket socket;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ServerSocket serverSocket;
        public String userId;
        private int fileId;

        public RequestTypes(Socket socket) {
            this.socket = socket;
            this.serverSocket = serverSocket;
            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

        @Override
        public void run() {
            while (socket.isConnected()) {
                Object request;
                // Waiting for the request to arrive
                try {
                    request = Main1.receiveRequestFromClient(ois);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }

                if (request instanceof LoginRequest) {
                    userId = ((LoginRequest) request).getUsername();
                    LoginRequestHandler loginRequestHandler = new LoginRequestHandler(oos, (LoginRequest) request, Main1.getConnection());
                    loginRequestHandler.sendResponse(userId);
                } else if (request instanceof RegisterRequest) {
                    RegisterRequestHandler registerRequestHandler = new RegisterRequestHandler((RegisterRequest) request, oos, Main1.getConnection());
                    registerRequestHandler.sendResponse(userId);
                }
                else if (request instanceof FileUploadRequest) {
                    FileUploadRequestHandler fileUploadRequestHandler = new FileUploadRequestHandler(Main1.getConnection(), ois, oos, (FileUploadRequest) request);
                   fileUploadRequestHandler.sendResponse(userId);
                }
            }
        }}
    //}


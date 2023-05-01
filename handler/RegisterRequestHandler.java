package handler;

import request.RegisterRequest;
import response.RegisterResponse;
import tables.UsersTable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterRequestHandler extends handler.Handler {
    private RegisterRequest registerRequest;
    private ObjectOutputStream oos;
    private Connection connection;
    public RegisterRequestHandler(RegisterRequest registerRequest, ObjectOutputStream oos, Connection connection) {
        this.registerRequest = registerRequest;
        this.oos = oos;
        this.connection = connection;

    }

    @Override
    public void sendResponse(String userId) {

        PreparedStatement preparedStatement;

        int result = 0;
        try {
            preparedStatement=connection.prepareStatement(UsersTable.query_register);
            preparedStatement.setString(1,registerRequest.getUserName());
            preparedStatement.setString(2,registerRequest.getPassword());
            preparedStatement.setString(3,registerRequest.getFirstName());
            preparedStatement.setString(4,registerRequest.getLastName());
            preparedStatement.setString(5,registerRequest.getEmail());



            System.out.println(preparedStatement);
            result=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result==0) {
            try {
                oos.writeObject(new RegisterResponse(""));
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                oos.writeObject(new RegisterResponse("Registered successfully"));
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

package handler;

import request.LoginRequest;
import response.LoginResponse;
import tables.UsersTable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRequestHandler extends Handler {
    private ObjectOutputStream oos;
    private LoginRequest loginRequest;
    private Connection connection;
    private boolean loginSuccessful;
    public LoginRequestHandler(ObjectOutputStream oos, LoginRequest loginRequest, Connection connection) {
        this.oos=oos;
        this.loginRequest=loginRequest;
        this.connection=connection;
        loginSuccessful = false;


    }

@Override
    public void sendResponse(String userId) {
        PreparedStatement preparedStatement;
        try{
             preparedStatement=connection.prepareStatement(UsersTable.query_login);
             preparedStatement.setString(1,loginRequest.getUsername());
             preparedStatement.setString(2,loginRequest.getPassword());
             System.out.println(loginRequest.getUsername() + " : " + loginRequest.getPassword());
             ResultSet resultSet=preparedStatement.executeQuery();
             LoginResponse response=null;
             if(resultSet.next()){
                response=new LoginResponse(resultSet.getString(UsersTable.col_userName),resultSet.getString(UsersTable.col_firstName),resultSet.getString(UsersTable.col_lastName),resultSet.getString(UsersTable.col_email),resultSet.getInt(UsersTable.col_id));
                preparedStatement.execute();
                loginSuccessful=true;

             }
               try{
                oos.writeObject(response);
                oos.flush();
                } catch(IOException e){
                e.printStackTrace();
                }
            }catch (SQLException e){
                e.printStackTrace();
        }

    }
    public boolean isLoginSuccessful(){
        return loginSuccessful;
    }
}

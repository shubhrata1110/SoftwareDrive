//import java.sql.*;
//
//public class Usersdb {
//    static Connection connection;
//
//    public static void main(String[] args) {
//        connection = getConnection();
//        ResultSet resultSet;
//        String firstName = "d";
//        String lastName = "f";
//        String userName="f";
//        String passcode="dfdf";
//        String email="dfd";
//
//        try {
//
//            Statement s= connection.createStatement();
//
//            String q = "insert into users(firstName, lastName, email, passcode,userName)"+ "values(?,?,?,?,?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(q);
//
//
//            preparedStatement.setString(1, firstName);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setString(3, email);
//            preparedStatement.setString(4, passcode);
//            preparedStatement.setString(5, UserName);
//
//
//            int m = preparedStatement.executeUpdate();
//            if (m==1)
//                System.out.println("inserted successfully : ");
//            else
//                System.out.println("insertion failed");
//
//
////            resultSet = preparedStatement.executeQuery("into into users");
////
////
////            while (resultSet.next())
////                System.out.println((resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+ " "+resultSet.getString(4)));
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//        static Connection getConnection() {
//        if (connection != null) return connection;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:3306/serverdb";
//            connection = DriverManager.getConnection(url, "root", "@Abhiveer18");
//            System.out.println("Database Connected");
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//}

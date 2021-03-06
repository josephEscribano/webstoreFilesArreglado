package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConection {


    public Connection getConnection() throws SQLException {
        Connection connection = DBConPool.getInstance().getConnection();

        System.out.println("Connected to DB");
        return connection;
    }

    public void closeConnection(Connection connection){
        try{
            if (connection != null){
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void releaseResources(PreparedStatement preparedStatement,ResultSet resultSet) {

        try {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(DBConection.class.getName()).log(Level.SEVERE,null,e);
        }

    }

}

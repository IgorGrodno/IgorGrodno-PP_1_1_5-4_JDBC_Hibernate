package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable(){
        Connection connection=null;
        try  {
            connection = util.getConnection(); Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS `usersdb`.`users` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NULL,`lastName` VARCHAR(45) NULL,`age` TINYINT(3) NULL,PRIMARY KEY (`id`));");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public void dropUsersTable(){
        Connection connection=null;
        try {
            connection = util.getConnection(); Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS Users");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age){
        String queryString = "INSERT INTO Users (name, lastName,age) VALUES (?,?,?)";
        Connection connection=null;
        try  {
            connection = util.getConnection(); Statement statement = connection.createStatement();
            PreparedStatement preparedStatement=connection.prepareStatement(queryString);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id){
        String queryString = "DELETE FROM Users WHERE Id=?";
        Connection connection=null;
        try {
            connection = util.getConnection(); Statement statement = connection.createStatement();
            PreparedStatement preparedStatement=connection.prepareStatement(queryString);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(){
        List<User> result = new ArrayList<User>();
        Connection connection=null;
        try {
            connection = util.getConnection(); Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                User tmpUser = new User();
                tmpUser.setId(resultSet.getLong(1));
                tmpUser.setName(resultSet.getString(2));
                tmpUser.setLastName(resultSet.getString(3));
                tmpUser.setAge(resultSet.getByte(4));
                result.add(tmpUser);
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable(){
        Connection connection=null;
        try {
             connection = util.getConnection(); Statement statement = connection.createStatement();
            statement.execute("DELETE FROM Users");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }
}

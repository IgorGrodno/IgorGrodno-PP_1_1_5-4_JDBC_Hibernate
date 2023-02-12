package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String queryString = "CREATE TABLE IF NOT EXISTS `usersdb`.`users` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NULL,`lastName` VARCHAR(45) NULL,`age` TINYINT(3) NULL,PRIMARY KEY (`id`));";
        qeryStringExtcuter(queryString);
    }

    public void dropUsersTable() {
        String queryString = "DROP TABLE IF EXISTS Users";
        qeryStringExtcuter(queryString);
    }

    public void saveUser(String name, String lastName, byte age) {
        StringBuilder queryString= new StringBuilder();
        queryString.append("INSERT INTO Users (name, lastName,age) VALUES (").
                append("'").
                append(name).
                append("'").
                append(",").
                append("'").
                append(lastName).
                append("'").
                append(",").
                append(age).
                append(");");
        qeryStringExtcuter(queryString.toString());
    }

    public void removeUserById(long id) {
        String queryString = "DELETE FROM Users WHERE Id=id";
        qeryStringExtcuter(queryString);
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<User>();
        Util util = new Util();
        try (Connection connection = util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                User tmpUser = new User();
                tmpUser.setId(resultSet.getLong(1));
                tmpUser.setName(resultSet.getString(2));
                tmpUser.setLastName(resultSet.getString(3));
                tmpUser.setAge(resultSet.getByte(4));
                result.add(tmpUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        String queryString = "DELETE FROM Users";
        qeryStringExtcuter(queryString);
    }

    private void qeryStringExtcuter(String qeryString) {
        Util util = new Util();
        try (Connection connection = util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(qeryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

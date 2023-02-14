package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Util {
   private static Connection connection;

    public Util() {
    }

    public static Connection getConnection() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/UsersDB", "root", "root");
        connection.setAutoCommit(false);
        return connection;
    }
}

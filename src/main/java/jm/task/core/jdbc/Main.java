package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("name", "lastName", (byte) 1);
        User tmpUser = userService.getAllUsers().get(userService.getAllUsers().size() - 1);
        System.out.println("User с именем – " + tmpUser.getName() + " добавлен в базу данных");

        userService.saveUser("name1", "lastName1", (byte) 2);
        tmpUser = userService.getAllUsers().get(userService.getAllUsers().size() - 1);
        System.out.println("User с именем – " + tmpUser.getName() + " добавлен в базу данных");

        userService.saveUser("name2", "lastName2", (byte) 3);
        tmpUser = userService.getAllUsers().get(userService.getAllUsers().size() - 1);
        System.out.println("User с именем – " + tmpUser.getName() + " добавлен в базу данных");

        userService.saveUser("name3", "lastName3", (byte) 4);
        tmpUser = userService.getAllUsers().get(userService.getAllUsers().size() - 1);
        System.out.println("User с именем – " + tmpUser.getName() + " добавлен в базу данных");
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

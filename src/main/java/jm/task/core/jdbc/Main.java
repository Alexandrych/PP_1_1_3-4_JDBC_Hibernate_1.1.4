package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        for (int i = 0; i < 3; i++) {
            userService.createUsersTable();
        }

        userService.saveUser("Станислав", "Богданов", (byte) 29);
        userService.saveUser("John", "Kramer", (byte) 49);
        userService.saveUser("Царь", "Горох", (byte) 35);
        userService.saveUser("Anthony", "Hopkins", (byte) 84);

        userService.removeUserById(3);

        userService.getAllUsers();

        userService.cleanUsersTable();

        for (int i = 0; i < 3; i++) {
            userService.dropUsersTable();
        }
    }
}

package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;
import notebook.util.UserValidator;

import java.util.Scanner;
import java.util.Stack;

import static notebook.model.repository.impl.UserRepository.createUser;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    System.out.println(userController.readAll());
                case UPDATE:
                    String userId = prompt("Enter user id: ");
                    userController.updateUser(userId, createUser());
                case DELETE:
                    String delUsers = prompt("Enter user id: ");
                    userController.delete(delUsers);
            }
        }
    }

    public static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

}

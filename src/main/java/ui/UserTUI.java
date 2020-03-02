package ui;

import functionality.*;
import functionality.IFunc;
import functionality.text.Func;

import java.util.Scanner;

public class UserTUI implements  ITUI {
    private IFunc func;
    private Scanner input;
    private String lineBreak = "--------------------";

    public UserTUI() {
        input = new Scanner(System.in);
    }
    public void chooseDataBase() {
        input = new Scanner(System.in);
        System.out.println(lineBreak);
        System.out.println("Welcome choose the database form you wish to use:");
        System.out.println("1. Text based database");
        System.out.println("2. Memory based database");
        System.out.println("3. SQL database");

        int selectValue = input.nextInt();
        showMenu(selectValue);

        switch (selectValue) {
            case 1:
                showMenu(selectValue);
                break;
            case 2:
                showMenu(selectValue);
                break;
            case 3:
                showMenu(selectValue);
                break;
        }
    }
    public void showMenu(int value) {
        if (value == 1) {
            func = (IFunc) new Func();
        } else if (value == 2) {
            func = new functionality.sql.Func();
        } else {
            // TODO IMPLEMENT SQL FUNC
        }

        System.out.println(lineBreak);
        System.out.println("1. Create user");
        System.out.println("2. Delete user");
        System.out.println("3. Update user");
        System.out.println("4. Show user list");
        System.out.println("5. exit");

    }
    public void createUser() {
        System.out.println(lineBreak);
        System.out.println("Create user");
        System.out.println("Please enter the users name");
        String userName = input.nextLine();
        // TODO
        // Implement name check

        System.out.println("Name registered correctly");
        System.out.println("Please enter user CPR");
        String userCPR = input.nextLine();
        // TODO
        // Implement CPR check
        System.out.println("CPR entered correctly");
        // TODO
        //func.createUser(userName, userCPR);
    }
    public void showUserList() {

    }
    public void deleteUser() {

    }
    public void updateUser() {

    }
    public void exit() {

    }
}
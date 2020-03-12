package ui;

import data.IUserDAO;
import data.UserDTO;
import data.text.DALException;
import functionality.*;
import functionality.IFunc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserTUI implements  ITUI {
    private int database;
    private IFunc func;
    private Scanner input;
    private String lineBreak = "--------------------";

    public UserTUI() {
        input = new Scanner(System.in);

    }
    public void chooseDataBase() throws DALException {
        input = new Scanner(System.in);
        System.out.println(lineBreak);
        System.out.println("Welcome choose the database form you wish to use:");
        System.out.println("1. Text based database");
        System.out.println("2. Memory based database");
        System.out.println("3. SQL database");

        database = input.nextInt();
        switch (database) {
            case 1:
                showMenu(database);
                break;
            case 2:
                showMenu(database);
                break;
            case 3:
                showMenu(database);
                break;
        }
    }
    public void showMenu(int value) throws DALException {
        if (value == 1) {
            func = new Func(1);
        } else if (value == 2) {
            func = new Func(2);
        } else {
            func = new Func(3);
        }

        System.out.println(lineBreak);
        System.out.println("Please select which function you wish to perform");
        System.out.println("1. Create user");
        System.out.println("2. Delete user");
        System.out.println("3. Update user");
        System.out.println("4. Show user list");
        System.out.println("5. exit");

        int selectValue = input.nextInt();

        switch (selectValue) {
            case 1:
                createUser();
                break;
            case 2:
                deleteUser();
                break;
            case 3:
                updateUser();
                break;
            case 4:
                showUserList();
                break;
            case 5:
                exit();
                break;
        }
    }
    public void createUser() throws DALException {
        System.out.println(lineBreak);
        System.out.println("Create user");
        String userName = validateString("Please enter the users name");
        System.out.println("Name registered correctly as " + userName);

        System.out.println(lineBreak);
        String init = validateString("Please enter the users initials");
        System.out.println("Initials entered as " + init);


        System.out.println(lineBreak);
        String userCPR = validateCPR("Please enter user CPR");
        System.out.println("CPR entered correctly as " + userCPR);
        System.out.println(lineBreak);

        ArrayList<String> roles = new ArrayList<>();
        boolean moreRoles;
        do {
            String role = validateString("Please enter the users role");
            roles.add(role);
            System.out.println("Role entered correctly as " + role);
            System.out.println("Does the user have more roles?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int v = input.nextInt();
            if (v == 1) {
                moreRoles = true;
            } else {
                moreRoles = false;
            }
        } while(moreRoles == true);
        System.out.println("All roles entered correctly");

        System.out.println(lineBreak);
        String userPassword = validateString("Please enter users password");
        System.out.println("Password entered correctly");
        try {
            func.createUser(userName, init, userPassword, roles, userCPR);
            System.out.println("User "+ userName + " created Succesfully");
        } catch (Exception e) {

        }
        showMenu(database);

    }
    public void showUserList() throws DALException {
        System.out.println("Showing user list");
        List<UserDTO> users = func.getUserList();

        //TODO: Try to use System.out.format, and either for or foreach loop
        //
        //

        String leftAlignFormat = "| %-15s | %-4d | %n";
        System.out.format("+--------+----------+---------+------+------+%n");
        System.out.format("| Name   | Initials |  Roles  |  Id  |  CPR |%n");
        System.out.format("+--------+----------+---------+------+------+%n");


        showMenu(database);
    }
    public void deleteUser() throws DALException {
        System.out.println("Enter the id of the user you wish to delete");
        int id = input.nextInt();
        func.deleteUser(id);
        System.out.println("User " + id + " Succesfully deleted");

        showMenu(database);
    }
    public void updateUser() {

    }

    public String validateString(String val) {
        String value;
        do {
            System.out.println(val);
            while(input.hasNext("\\d+")) {
                System.out.println("the entered value must be valid string");
                input.next();
            }
            value = input.next();
        } while(!( value instanceof String));
         return value;
    }

    public String validateCPR(String val) {
        int output;
        do {
            System.out.println(val);
            while(!input.hasNextInt()) {
                System.out.println("Please enter a valid CPR number");
                input.next();
            }
            output = input.nextInt();
        } while (String.valueOf(output).length() < 10);
        return String.valueOf(output);
    }

    public void exit() {
        System.exit(0);
    }
}
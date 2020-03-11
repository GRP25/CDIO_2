package ui;

import data.text.DALException;
import functionality.*;
import functionality.IFunc;

import java.util.Scanner;

public class UserTUI implements  ITUI {
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
    public void showMenu(int value) throws DALException {
        if (value == 1) {
            func = (IFunc) new Func();
        } else if (value == 2) {
            func = new functionality.Func();
        } else {
            // TODO IMPLEMENT SQL FUNC
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
    public void createUser() {
        System.out.println(lineBreak);
        System.out.println("Create user");
        String userName = validateString("Please enter the users name");
        System.out.println("Name registered correctly as " + userName);


        System.out.println(lineBreak);
        String userCPR = validateCPR("Please enter user CPR");
        System.out.println("CPR entered correctly as " + userCPR);

        System.out.println(lineBreak);
        String roles = validateString("Please enter the users role");
        System.out.println("Role entered correctly as " + roles);
        //TODO
        //func.createUser(userName, userCPR);
    }
    public void showUserList() throws DALException {
        System.out.println("Showing user list");
        func.getUserList();
    }
    public void deleteUser() throws DALException {
        System.out.println("Enter the id of the user you wish to delete");
        int id = input.nextInt();
        func.deleteUser(id);
        System.out.println("User " + id + " Succesfully deleted");
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
        System.out.println("tjek" + value);
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

    }
}
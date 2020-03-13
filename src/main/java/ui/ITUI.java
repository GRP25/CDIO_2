package ui;

import data.text.DALException;

public interface ITUI {
    void chooseDataBase() throws DALException;
    void showMenu() throws DALException;
    void createUser() throws DALException;
    void showUserList() throws DALException;
    void deleteUser() throws DALException;
    void updateUser() throws DALException;
    void exit();
}
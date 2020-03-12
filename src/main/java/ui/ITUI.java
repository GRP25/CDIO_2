package ui;

import data.text.DALException;

public interface ITUI {
    void chooseDataBase() throws DALException;
    void showMenu(int i) throws DALException;
    void createUser();
    void showUserList() throws DALException;
    void deleteUser() throws DALException;
    void updateUser();
    void exit();
}
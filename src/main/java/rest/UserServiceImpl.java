package rest;

import data.IUserDAO;
import data.UserDTO;
import data.sql.UserDAO;

import java.util.ArrayList;

public class UserServiceImpl  implements UserService{


    IUserDAO userDao = new UserDAO();


    @Override
    public UserDTO getUser(int userID) {
         return userDao.getUser( userID );
    }

    @Override
    public void updateUser(UserDTO user) {
        userDao.updateUser( user );
    }

    @Override
    public void createUser(UserDTO user) {
        userDao.createUser( user );
    }

    @Override
    public void deleteUser(int userID) {
        userDao.deleteUser( userID );
    }

    @Override
    public ArrayList<UserDTO> getUserList() {
        return userDao.getUserList();
    }
}
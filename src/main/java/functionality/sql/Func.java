package functionality.sql;

import data.IUserDAO;
import data.UserDTO;
import data.sql.UserDAO;
import functionality.IFunc;

import java.util.ArrayList;

public class Func implements IFunc {
    IUserDAO dao;
    public Func(){
        this.dao = new UserDAO();
    }
    public UserDTO getUser(int userID) {
        return dao.getUser(userID);
    }
    public void updateUser(int userID, String Username, String initials, int CPR, String password, ArrayList<Integer> roles) {
        dao.updateUser(userID,Username,initials,CPR,password,roles);
    }
    public void createUser(String Username, String initials, int CPR, String password, ArrayList<Integer> roles) {
        dao.createUser(Username,initials,CPR,password,roles);
    }
    public void deleteUser(int userID) {
        dao.deleteUser(userID);
    }
    public ArrayList<UserDTO> getUserList() {
        return dao.getUserList();
    }
}

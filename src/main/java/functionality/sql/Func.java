package functionality.sql;

import data.IUserDAO;
import data.UserDTO;
import data.sql.UserDAO;
import data.text.DALException;
import functionality.IFunc;

import java.util.ArrayList;

public class Func implements IFunc {
    IUserDAO dao;
    public Func(){
        this.dao = new UserDAO();
    }
    public UserDTO getUser(int userID) throws DALException {
        return dao.getUser(userID);
    }/* //TODO syntax fejl i øjeblikket, udkommenterer så vi kan teste nogle metoder
    public void updateUser(int userID, String Username, String initials, String CPR, String password, ArrayList<Integer> roles) {
        dao.updateUser(userID,Username,initials,CPR,password,roles);
    }
    public void createUser(String Username, String initials, String CPR, String password, ArrayList<Integer> roles) {
        dao.createUser(Username,initials,CPR,password,roles);
    }*/
    public void deleteUser(int userID) throws DALException {
        dao.deleteUser(userID);
    }

    @Override
    public void updateUser(UserDTO user) {

    }

    @Override
    public void createUser(UserDTO user) {

    }

    public ArrayList<UserDTO> getUserList() throws DALException {
        return dao.getUserList();
    }
}

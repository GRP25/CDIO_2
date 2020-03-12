package functionality;

import data.UserDTO;
import data.text.DALException;
import data.*;


import java.util.ArrayList;
import java.util.List;

public class Func {

    IUserDAO data;

    public Func(int database) {
        if (database == 1) {
            this.data = new data.text.TextUserDAO();
        } else if (database == 2) {
            this.data = new data.memory.MemoryUserDAO();
        } else {
            this.data = new data.sql.UserDAO();
        }
    }

    public void deleteUser(int userID) throws DALException {
        this.data.deleteUser(userID);    
    }

    public void updateUser(UserDTO user) throws DALException {
        this.data.updateUser(user);
    }

    public void createUser(String name, String initials, String password, ArrayList<String> roles, long cpr) throws DALException {

        int id = this.data.getUserList().size() + 1;

        UserDTO user = new UserDTO(name, initials, password, roles, id, cpr);

        this.data.createUser(user);
    }

    public UserDTO getUser(int userID) throws DALException {

        return this.data.getUser(userID);
    }

    public List<UserDTO> getUserList() throws DALException {
        return this.data.getUserList();
    }
}

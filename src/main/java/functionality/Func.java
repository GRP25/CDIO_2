package functionality;

import data.UserDTO;
import data.text.DALException;
import data.*;


import java.util.ArrayList;
import java.util.List;

public class Func implements IFunc {

    IUserDAO data;

    public Func(int database) {
        if (database == 1) {
            this.data = new data.text.TextUserDAO();
        } else if (database == 2) {
            this.data = new data.memory.MemoryUserDAO();
        } else {
            this.data = new data.sql.SQLUserDAO();
        }
    }

    @Override
    public void deleteUser(int userID) throws DALException {
        this.data.deleteUser(userID);    
    }

    @Override
    public void updateUser(int id, int attribute, String change, ArrayList roles) throws DALException {

        UserDTO o = getUser(id);
        UserDTO user;


        switch (attribute) {
            case 1:
                user = new UserDTO(change, o.getInitials(), o.getPassword(), o.getRoles(),o.getId(), o.getCpr());
                this.data.updateUser(user);
                break;
            case 2:
                user = new UserDTO(o.getName(), change, o.getPassword(), o.getRoles(),o.getId(), o.getCpr());
                this.data.updateUser(user);
                break;
            case 3:
                user = new UserDTO(o.getName(), o.getInitials(), change, o.getRoles(),o.getId(), o.getCpr());
                this.data.updateUser(user);
                break;
            case 4:
                user = new UserDTO(o.getName(), o.getInitials(), o.getPassword(), roles,o.getId(), o.getCpr());
                this.data.updateUser(user);
                break;
            case 5:
                long cprNr = Long.parseLong(change);
                user = new UserDTO(o.getName(), o.getInitials(), o.getPassword(), o.getRoles(),o.getId(), cprNr);
                this.data.updateUser(user);
                break;
        }
    }

    @Override
    public void createUser(String name, String initials, String password, ArrayList<String> roles, String cpr) throws DALException {

        int id = this.data.getUserList().size() + 1;
        long cprNR = Long.parseLong(cpr);

        UserDTO user = new UserDTO(name, initials, password, roles, id, cprNR);

        this.data.createUser(user);
    }

    @Override
    public UserDTO getUser(int userID) throws DALException {

        return this.data.getUser(userID);
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return this.data.getUserList();
    }
}

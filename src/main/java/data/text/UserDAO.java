package data.text;

import data.IUserDAO;
import data.UserDTO;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAO implements Serializable {

    UserStore userStore;
    String fileName = "C:..";

    private UserStore loadUsers() throws DALException {
        UserStore userStore = new UserStore();
        ObjectInputStream oIS = null;
        try {
            FileInputStream fIS = new FileInputStream(fileName);
            oIS = new ObjectInputStream(fIS);
            Object inObj = oIS.readObject();
            if (inObj instanceof UserStore){
                userStore = (UserStore) inObj;
            } else {
                throw new DALException("Wrong object in file");
            }
        } catch (FileNotFoundException e) {
            //No problem - just returning empty userstore
        } catch (IOException e) {
            throw new DALException("Error while reading disk!", e);
        } catch (ClassNotFoundException e) {
            throw new DALException("Error while reading file - Class not found!", e);
        } finally {
            if (oIS!=null){
                try {
                    oIS.close();
                } catch (IOException e) {
                    throw new DALException("Error closing pObjectStream!", e);
                }
            }
        }
        return userStore;
    }
    private void saveUsers(UserStore users) throws DALException {
        ObjectOutputStream oOS =null;
        try {

            FileOutputStream fOS = new FileOutputStream(fileName);
            oOS = new ObjectOutputStream(fOS);
            oOS.writeObject(users);

        } catch (FileNotFoundException e) {
            throw new DALException("Error locating file", e);
        } catch (IOException e) {
            throw new DALException("Error writing to disk", e);
        } finally {
            if (oOS!=null) {
                try {
                    oOS.close();
                } catch (IOException e) {
                    throw new DALException("Unable to close ObjectStream", e);
                }
            }
        }
    }

        public UserDTO getUser(int userID) {

            for (int i = 0; i < userStore.size(); i++)
                if (userStore.get(i).getId() == userID)
                    return userStore.get(i);

            return null;
        }

        //TODO jeg ved ikke hvordan jeg skal lave den her metode lige nu da der er kommet flere parametre ind.

        public void updateUser(int userID, String Username, String initials, int CPR, String password, ArrayList<String> roles, int id) {

            for (int i = 0; i < userStore.size(); i++)
                if (userStore.get(i).getId() == userID){

                    userStore.get(i).setName(Username);
                    userStore.get(i).setInitials(initials);
                    userStore.get(i).setCpr(CPR);
                    userStore.get(i).setPassword(password);
                    userStore.get(i).setRoles(roles);
                    userStore.get(i).setId(id);
                }
        }

        //TODO snak igennem alle de her parametre fordi nogle af dem giver ingen megning.

        public void createUser(UserDTO userDTO){
            userStore.add(userDTO);
        }


        public void deleteUser(int userID) {
            for (int i = 0; i < userStore.size(); i++)
                if (userStore.get(i).getId() == userID)
                    userStore.remove(i);

        }


        public List<UserDTO> getUserList() {
            return userStore;
        }

}
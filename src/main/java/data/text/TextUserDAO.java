package data.text;

import data.IUserDAO;
import data.UserDTO;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TextUserDAO implements IUserDAO {

    UserStore userStore = new UserStore();
    String fileName = "storeUser.txt";

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

            OutputStream fOS = new FileOutputStream(fileName);
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

        public UserDTO getUser(int userID) throws DALException {

        ArrayList<UserDTO> getUser = loadUsers();
            for (UserDTO userDTO : getUser) {
                if (userDTO.getId() == userID)
                    return userDTO;
            }

       // Iterator<UserDTO> iterator = userStore.iterator();




        for (int i = 0; i < userStore.size(); i++)
                if (userStore.get(i).getId() == userID)
                    return userStore.get(i);

            return null;
        }

    //TODO jeg ved ikke hvordan jeg skal lave den her metode lige nu da der er kommet flere parametre ind.

    @Override
    public void updateUser(UserDTO user) throws DALException {
        deleteUser(user.getId());
        createUser(user);
    }

        //TODO snak igennem alle de her parametre fordi nogle af dem giver ingen megning.

        public void createUser(UserDTO userDTO) throws DALException {
            userStore.add(userDTO); // todo do we need this line ????
            saveUsers(userStore); //TODO check hvis den nye arraylist overwriter den gamle
        }

        public void deleteUser(int userID) throws DALException {
            for (int i = 0; i < userStore.size(); i++)
                if (userStore.get(i).getId() == userID)
                    userStore.remove(i);
            saveUsers(userStore); //TODO check hivs den nye arraylist overwriter den gamle
        }

        public ArrayList<UserDTO> getUserList() throws DALException {
            return loadUsers();
        }

}
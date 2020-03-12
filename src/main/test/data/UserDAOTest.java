
import data.UserDTO;
import data.text.DALException;
import data.text.TextUserDAO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserDAOTest {


    TextUserDAO userdao = new TextUserDAO();
    ArrayList<String> arrayList = new ArrayList<>();
    UserDTO  userDTO1 = new UserDTO("Andrey", "AB", "0000", arrayList, 1, "091194-3441");

    @org.junit.Test
    public void getUser() throws DALException {
        UserDTO actual = userdao.getUser(1);
        //System.out.println(actual.getName());

        assertEquals(userDTO1.getId(), actual.getId());
    }

    @org.junit.Test
    public void updateUser() throws DALException {
        UserDTO user = new UserDTO("Andrey", "AB", "0000", arrayList, 1, "091194-3441");
        arrayList.add("Chif");
        userdao.updateUser(user);
        System.out.println(userdao.getUser(111).getName());
        //assertTrue(userDTO1.getName() == userdao.getUser(111).getName());

    }

    @org.junit.Test
    public void createUser() throws DALException {
        arrayList.add("Admin");
        userdao.createUser(userDTO1);

        // TODO how to add users in a loop instead of manually add each user
    }
    @org.junit.Test
    public void deleteUser() throws DALException {
        userdao.deleteUser(1);
        assertEquals(null, userdao.getUser(1));
    }

    @org.junit.Test
    public void getUserList() throws DALException {

        for (UserDTO userDTO : userdao.getUserList()) {
            System.out.printf("%-12s %-12s %-12s %s \n", userDTO.getName(),
                    userDTO.getPassword(), userDTO.getId(), userDTO.getCpr());

        }

        System.out.println(userdao.getUserList().get(0).getCpr());
    }
}
package data;

import data.memory.MemoryUserDAO;
import data.text.DALException;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class MemoryUserDAOTest1 {

    private MemoryUserDAO memoryUserDAO = new MemoryUserDAO();
    private ArrayList<String> arrayList = new ArrayList<>();
    private UserDTO user1 = new UserDTO("Daniel Hohnen","DH","1234",arrayList,195455,"061260-1234");


    @org.junit.Test
    public void getUser() throws DALException {
        memoryUserDAO.createUser(user1);
        UserDTO real = memoryUserDAO.getUser(195455);
        System.out.println(real.getName());

        assertEquals(user1, real);
    }

}
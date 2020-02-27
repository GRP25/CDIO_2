package data;

import java.util.List;

<<<<<<< HEAD

=======
>>>>>>> origin/userDTO
public class UserDTO{
    String name;
    String password;
    List<String> roles;
    int id;
    int cpr;

    public UserDTO(String name, String password, List<String> roles, int id, int cpr) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.id = id;
        this.cpr = cpr;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public int getId() {
        return id;
    }

    public int getCpr() {
        return cpr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCpr(int cpr) {
        this.cpr = cpr;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/userDTO

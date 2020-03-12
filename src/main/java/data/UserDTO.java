package data;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDTO implements Serializable {
    private String name;
    private String initials;
    private String password;
    private ArrayList<String> roles;
    private int id;
    private String cpr;


    public UserDTO(String name, String initials, String password, ArrayList<String> roles, int id, String cpr) {
        this.name = name;
        this.initials = initials;
        this.password = password;
        this.roles = roles;
        this.id = id;
        this.cpr = cpr;

    }

    public String getInitials() {
        return initials;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<String> getRoles() {
        return roles;
    }
    public int getId() {
        return id;
    }
    public String getCpr() {
        return cpr;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInitials(String initials) {
        this.initials = initials;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCpr(String cpr) {
        this.cpr = cpr;
    }
}

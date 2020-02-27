package data;

import java.util.ArrayList;

public class UserDTO {
    private String       name;

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    private String    initials;
    private String    password;
    private ArrayList<Integer> roles;
    private int          id;
    private int          cpr;

    public UserDTO(String name, String password, ArrayList<Integer> roles, int id, int cpr) {
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

    public ArrayList<Integer> getRoles() {
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

    public void setRoles(ArrayList<Integer> roles) {
        this.roles = roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCpr(int cpr) {
        this.cpr = cpr;
    }
}

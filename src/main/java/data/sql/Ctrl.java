package data.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Ctrl {

    static Connection connect() {
        String user, pass, url;
        user = "cdio";
        pass = "HmDjHb0b4123";
        url = "jdbc:mariadb://mama.sh:4123/cdio2";
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    static String groupToString(ArrayList<String> groups){
        StringBuilder str = new StringBuilder();
        for(String group: groups){
            str.append(group).append(",");
        }
        return str.toString();
    }

    static ArrayList<String> stringToGroup(String str){
        String[] groups = str.split(",");
        return new ArrayList<>(Arrays.asList(groups));
    }

}

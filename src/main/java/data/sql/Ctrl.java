package data.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Ctrl {
    static String       database    = "DB/test.db";
    static Connection connect() {
        // SQLite connection string
        String     url  = "jdbc:sqlite:" + database;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
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

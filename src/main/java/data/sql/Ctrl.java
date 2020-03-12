package data.sql;

import java.sql.*;
import java.util.ArrayList;

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


}

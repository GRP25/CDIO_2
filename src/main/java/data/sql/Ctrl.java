package data.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ctrl {
    //PRIVATE METHODS
    static Connection connect() {
        /**
         * Connect to the test.db database
         * @return the Connection object
         */
        // SQLite connection to database
        String url = "jdbc:sqlite:" + "db.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }



}

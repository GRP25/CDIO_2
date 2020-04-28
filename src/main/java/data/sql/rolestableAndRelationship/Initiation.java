package datalayer.MySQL;

import datalayer.InitDAO;

import javax.enterprise.context.RequestScoped;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import static datalayer.MySQL.Ctrl.connect;

@RequestScoped
public class Initiation implements InitDAO {

    @Override
    public void createNewUserTable() {
        String sql = "CREATE TABLE user (\n" +
                "   user_id INT AUTO_INCREMENT,\n" +
                "   user_name VARCHAR(36) NOT NULL, \n" +
                "   user_init VARCHAR(5) NOT NULL, \n" +
                "   user_cpr INT NOT NULL UNIQUE, \n" +
                "   user_password VARCHAR(50) NOT NULL,\n" +
                "   PRIMARY KEY ( user_id ) \n" +
                ");";
        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createNewGroupTable() {
        String sql = "CREATE TABLE usergroup (\n" +
                "   group_id INT AUTO_INCREMENT,\n" +
                "   group_title VARCHAR(36) NOT NULL, \n" +
                "   PRIMARY KEY ( group_id ) \n" +
                ");";

        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createNewGroupRelationshipTable() {
        String sql = "CREATE TABLE has_group (\n" +
                "   user_id INT NOT NULL,\n" +
                "   group_id INT NOT NULL,\n" +
                "   FOREIGN KEY (user_id) REFERENCES user(user_id),\n" +
                "   FOREIGN KEY (group_id) REFERENCES usergroup(group_id),\n" +
                "   UNIQUE (group_id, user_id)\n" +
                ");";

        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createDatabase() {
        try (Connection conn = connect()) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

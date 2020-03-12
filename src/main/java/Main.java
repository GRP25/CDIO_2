import data.sql.Initiation;
import data.sql.UserDAO;
import data.text.DALException;
import ui.ITUI;
import ui.UserTUI;

public class Main {
    public static void main(String[] args) throws DALException {
        ITUI tui = new UserTUI();
        tui.chooseDataBase();
    }
}


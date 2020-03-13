import data.text.DALException;
import ui.UserTUI;

public class Main {
    public static void main(String[] args) throws DALException {
        UserTUI tui = new UserTUI();
        tui.chooseDataBase();
    }
}


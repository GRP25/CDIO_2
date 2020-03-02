import ui.ITUI;
import ui.UserTUI;

public class Main {
    public static void main(String[] args){
        ITUI tui = new UserTUI();
        tui.showMenu(2);
    }
}

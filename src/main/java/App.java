import service.menu.MainMenu;
import utils.HibernateUtil;

public class App {

    public static void main(String[] args) {
        if (HibernateUtil.checkDbConnection()) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.startMenu();
        } else {
            System.out.println("Check Database Connection!");
        }
    }

}
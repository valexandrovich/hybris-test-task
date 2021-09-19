package service.menu;

import lombok.Data;

import java.util.List;
import java.util.Scanner;

@Data
public abstract class AbstractMenu {
    private String menuHeader;
    private String menuBody;
    private List<String> menuItems;

    public abstract void startMenu();

    abstract void initialize();

    void printMenu() {
        printMenuHeader();
        printMenuBody();
        printMenuItems();
    }

    Integer getUserChoice() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        try {
            Integer userChoice = Integer.parseInt(userInput);
            return userChoice;
        } catch (Exception ex) {
            return -1;

        }
    }

    void printMenuHeader() {
        if (this.menuHeader == null) return;
        int headerLen = menuHeader.length();
        int swap = (48 - headerLen) / 2;
        System.out.println("--------------------------------------------------");
        System.out.println("|" + new String(new char[swap]).replace("\0", " ")
                + menuHeader + new String(new char[swap]).replace("\0", " ") + "|");
        System.out.println("--------------------------------------------------");
    }

    void printMenuBody() {
        if (this.menuBody == null) return;
        System.out.println(this.menuBody);
    }

    void printMenuItems() {
        for (String item : menuItems) {
            System.out.println((menuItems.indexOf(item) + 1) + " - " + item);
        }
    }

    public AbstractMenu() {
    }

    @Deprecated
    void cleanConsoleScreen() {
        for (int i = 0; i < 2; i++) {
            System.out.println("");
        }
    }

}

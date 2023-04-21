
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    private static final String cls = "\033[H\033[2J";
    Users users = new Users();


    public Menu() {
        startMenu();
    }

    public static void inputError() {
        System.out.print(cls + "Incorrect input , try again!! \n\n");
    }

    private void startMenu() {
        String input = "0";
        while (!input.equals("3")) {
            printMenu();
            input = scanner.next();
            switch (input) {
                case "1" -> users.signIn();
                case "2" -> users.signUp();
                case "3" -> {
                }
                default -> inputError();
            }
        }
    }

    private void printMenu() {
        System.out.print("""
                --------------------------------------------------
                      WELCOME TO AIRLINE RESERVATION SYSTEM
                --------------------------------------------------
                   1- sign in
                   2- sign up
                   3-exist
                """);
    }
}

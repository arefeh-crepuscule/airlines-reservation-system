
import java.util.Scanner;

public class Menu {
    static Scanner scanner =new Scanner(System.in);
    private final String cls = "\033[H\033[2J";


    public Menu(){
        startMenu();
    }

    private void startMenu() {

        System.out.println("--------------------------------------------------");
        System.out.println("     WELCOME TO AIRLINE RESERVATION SYSTEM");
        System.out.println("--------------------------------------------------");

        String input = "0";
        while (input != "3") {
            System.out.println(cls);
            System.out.println("1- sign in");
            System.out.println("2- sign up");
            System.out.println("3-exist");

            input = scanner.next();
            switch (input) {
                //case "1" -> signIn();
                //case "2" -> sigbUp();
                case "3" -> {
                }
                default -> {
                    System.out.println("Invalid input !! choose another");
                    input = scanner.next();
                }
            }

        }

    }}

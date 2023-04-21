
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    private static final String cls = "\033[H\033[2J";
    Users users = Users.getInstance();



    public Menu() {
        PrimaryData primaryData=new PrimaryData();
        primaryData.makePrimaryFlights();
        startMenu();
    }

    public static void inputError() {
        System.out.print(cls + "Incorrect input , try again!! \n\n");
    }

    /**
     * start menu just for choose want to sign in or sign up
      */
    private void startMenu() {
        String input = "0";
        while (!input.equals("3")) {
            printMenu();
            input = scanner.next();
            switch (input) {
                case "1" -> signIn();
                case "2" -> signUp();
                case "3" -> {
                }
                default -> inputError();
            }
        }
    }

    /**
     * the table of start menu for calling in start menu function to print that
     */
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

    /**
     * sing in menu function to print and input something  and call functions of users class
     */
    public void signIn() {
        String input = "";
        while (!input.equals("0")) {
            System.out.print(cls + "pleas enter your username :\t");
            String user = scanner.nextLine();
            System.out.print("\npleas enter your password:\t");
            String pass = scanner.nextLine();
            if (!users.checkSignIn(user, pass)) {
                System.out.print("There is no account whit this username and password!!");
                System.out.print("\nif you wanna back enter 0 otherwise enter another :\t");
                input = scanner.nextLine();
            }else {
                input="0";
            }
        }
        System.out.println(cls);
    }

    /**
     *  sing up menu function to print and input something  and call functions of users class to make a new passenger account
     */
    public void signUp() {
        String input = "";
        while (!input.equals("0")) {
            System.out.println(cls);
            System.out.print("pleas enter your username :\t");
            String user = scanner.nextLine();
            System.out.print("\npleas enter your password:\t");
            String pass = scanner.nextLine();
            if (users.checkSignIn(user, pass)) {
                System.out.println("There is an account with this username and password\n");
                System.out.print("If you wanna back enter 0 otherwise enter another :\t");
                input = scanner.nextLine();
            } else {
                users.makeNewPassenger(user, pass);
                input ="0";
            }
        }
    }
}

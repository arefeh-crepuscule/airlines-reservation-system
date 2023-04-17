import java.util.ArrayList;
import java.util.Scanner;

public class Users {
    public Scanner scanner = new Scanner(System.in);
    private final String cls = "\033[H\033[2J";
    private static Admin admin = getInstance1();
    private static ArrayList<User> passengers = getInstance2();
    private User temp = new User();


    public void signIn() {
        String input = "-1";
        while (!input.equals("0")) {
            System.out.print(cls + "pleas enter your username :\t");
            String user = scanner.nextLine();
            System.out.print("\npleas enter your password:\t");
            String pass = scanner.nextLine();
            if (!checkSignIn(user, pass)) {
                Menu.inputError();
                System.out.print("\nif you wanna back enter 0 otherwise enter another :\t");
                input = scanner.nextLine();
            }
        }
        System.out.println(cls);
    }

    public void signUp() {
        String input = null;
        while (input.equals("0")) {
            System.out.println(cls);
            System.out.print("pleas enter your username :\t");
            String user = scanner.nextLine();
            System.out.print("\npleas enter your password:\t");
            String pass = scanner.nextLine();
            if (checkSignIn(user, pass)) {
                System.out.println("there is on account whit this username and password\n");
                System.out.print("if you wanna back enter 0 otherwise enter another :\t");
                input = scanner.nextLine();
            } else {
                makeNewPassenger(user, pass);
            }
        }
    }

    private void makeNewPassenger(String user, String pass) {
        User passenger = new User(user, pass);
        passengers.add(passenger);
    }

    public boolean checkSignIn(String user, String pass) {
        boolean res = false;
        if (checkUserInfo(user, pass, admin.getUserName(), admin.getPassWord())) {
            admin.adminMenu();
            res = true;
        } else {
            for (User temp : passengers) {
                if (checkUserInfo(user, pass, temp.getUserName(), temp.getPassWord())) {
                    passengers.remove(temp);
                    temp.passengerMenu();
                    passengers.add(temp);
                    res = true;
                    break;
                }
            }
        }
        return res;
    }

    public boolean checkUserInfo(String user, String pass, String mainUser, String mainPass) {
        return user.equals(mainUser) && pass.equals(mainPass);
    }


    public Users() {
        getInstance1();
        getInstance2();
    }

    public static ArrayList getInstance2() {
        if (passengers == null)
            passengers = new ArrayList();
        return passengers;
    }

    public static Admin getInstance1() {
        if (admin == null)
            admin = new Admin();
        return admin;
    }
}

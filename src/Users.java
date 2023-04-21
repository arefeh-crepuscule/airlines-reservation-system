import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Users {
    public Scanner scanner = new Scanner(System.in);
    private final String cls = "\033[H\033[2J";
    private  Admin admin = Admin.getInstance();
    private  ArrayList<User> passengers = new ArrayList<>();
    private final HashMap <String , User> passengerMap = new HashMap<>();
    private static Users instance = new Users();
    private Users (){}
    public static Users getInstance(){
        return instance;
    }


    public HashMap<String, User> getPassengerMap() {
        return passengerMap;
    }
    public void signIn() {
        String input = "";
        while (!input.equals("0")) {
            System.out.print(cls + "pleas enter your username :\t");
            String user = scanner.nextLine();
            System.out.print("\npleas enter your password:\t");
            String pass = scanner.nextLine();
            if (!checkSignIn(user, pass)) {
                System.out.print("There is no account whit this username and password!!");
                System.out.print("\nif you wanna back enter 0 otherwise enter another :\t");
                input = scanner.nextLine();
            }else {
                input="0";
            }
        }
        System.out.println(cls);
    }

    public void signUp() {
        String input = "";
        while (!input.equals("0")) {
            System.out.println(cls);
            System.out.print("pleas enter your username :\t");
            String user = scanner.nextLine();
            System.out.print("\npleas enter your password:\t");
            String pass = scanner.nextLine();
            if (checkSignIn(user, pass)) {
                System.out.println("There is an account whit this username and password\n");
                System.out.print("If you wanna back enter 0 otherwise enter another :\t");
                input = scanner.nextLine();
            } else {
                makeNewPassenger(user, pass);
                input ="0";
            }
        }
    }

    private void makeNewPassenger(String user, String pass) {
        User passenger = new User(user, pass);
        passengers.add(passenger);
        passengerMap.put(user+pass, passenger);
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


/*
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
*/

}

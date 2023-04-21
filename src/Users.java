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

    /**
     * with input parameters make new passenger with new instance from user class and add to passengers arraylist
     * @param user username of new passenger
     * @param pass password of new passenger
     */
    public void makeNewPassenger(String user, String pass) {
        User passenger = new User(user, pass);
        passengers.add(passenger);
        passengerMap.put(user+pass, passenger);
    }

    /**
     * check if there are any acount with input username and password parameters
      * @param user input username
     * @param pass input password
     * @return
     */
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

    /**
     * just check if there are such username and password
     * @param user input username
     * @param pass input password
     * @param mainUser username of one of user instance
     * @param mainPass password of one of user instance
     * @return true or false mean there or some instance with the same information
     */
    public boolean checkUserInfo(String user, String pass, String mainUser, String mainPass) {
        return user.equals(mainUser) && pass.equals(mainPass);
    }




}

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    private  String userName  ;
    private String notification ;

    public void setNotification(String notification) {
        this.notification += notification;
    }

    private  String passWord ;
    private int  charge = 0;
    private final ArrayList<FlightsInfo> userFlight = new ArrayList<>();
    private final String cls = "\033[H\033[2J";
    public Scanner scanner = new Scanner(System.in);
    private  Flights flights = new Flights();


    public void signIn(){
        if(notification != null ){
            System.out.println(cls);
            System.out.print("There is some notification for you:\n");
            printNotif();
            notification = null;
            System.out.print("For go ahead enter something :\t");
            scanner.nextLine();
        }
    }

    private void printNotif() {
        if (notification != null){
            System.out.print ( """

                    This flight removed :
                    |  |FlightId  |Origin    |Destination  |Date      |Time |Price    |Seats |
                    ..........................................................................
                    """+notification);
        }
    }

    public void printUserMenu() {
        System.out.println(cls);
        System.out.print("--------------------------------------------------\n");
        System.out.print("               Passenger Menu Options             \n");
        System.out.print("--------------------------------------------------\n\n");
        System.out.println("   <1> Change password ");
        System.out.println("   <2> Search flight tickets");
        System.out.println("   <3> Booking ticket");
        System.out.println("   <4> Ticket cancellation");
        System.out.println("   <5> Booked tickets");
        System.out.println("   <6> Add charge");
        System.out.println("   <0> Sign out");
    }

    /**
     * give the extra charge that user want to charge and call the update function
     */
    public void addCharge (){
        System.out.printf("Your current charge :\t%10d\n", charge);
        System.out.print("How much do you want to charge?\t");
        int extraCharge = scanner.nextInt();
        chargeUpdate(extraCharge);
        System.out.println(cls);
        System.out.printf("Your charge has been updated :\t%10s",charge);
    }

    /**
     * updated private charge with extra parameter
      * @param extraCharge the variable for add or subtract charge variable
     */
    private void chargeUpdate(int extraCharge) {
        charge += extraCharge;
    }

    /**
     * check input pass that be as same as saved pass in password variable
     * @param inputPass new pass that we want to check it
     * @return true or false mean new pass is same or not
     */
    private boolean checkPass(String inputPass){
        if ( passWord.equals(inputPass))
            return true;
        return false;
    }

    /**
     * change user password with check old password , get new password and update pass word
      */
    public void changePass(){
        System.out.print("enter your current password :\t");
        String temp = scanner.nextLine();
        if(checkPass(temp)){
            passWord = temp;
            System.out.printf(cls+"\nYour password has been updated");
        }else {
            inputError();
        }
    }

    /**
     * this function print error input means user input is wrong and need try again
      */
    private void inputError() {
        System.out.println(cls);
        System.out.print("Incorrect input , try again!! \n\n");
    }
}

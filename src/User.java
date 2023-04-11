import java.util.Scanner;

public class User {

    private  String userName  ;
    private  String passWord ;
    private int  charge = 0;
    private final String cls = "\033[H\033[2J";
    public Scanner scanner = new Scanner(System.in);


    public void printUserMenu() {
        System.out.println(cls);
        System.out.println("--------------------------------------------------");
        System.out.println("               Passenger Menu Options             ");
        System.out.print("--------------------------------------------------\n\n");
        System.out.println("   <1> Change password ");
        System.out.println("   <2> Search flight tickets");
        System.out.println("   <3> Booking ticket");
        System.out.println("   <4> Ticket cancellation");
        System.out.println("   <5> Booked tickets");
        System.out.println("   <6> Add charge");
        System.out.println("   <0> Sign out");
    }
    public void addCharge (){
        System.out.printf("Your current charge :\t%10d\n", charge);
        System.out.print("How much do you want to charge?\t");
        int extraCharge = scanner.nextInt();
        chargeUpdate(extraCharge);
        System.out.println(cls);
        System.out.printf("Your charge has been updated :\t%10s",charge);
    }

    private void chargeUpdate(int extraCharge) {
        charge += extraCharge;
    }


}

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    private final String userName = "admin user";
    private final String passWord = "passadmin2023";
    private final String cls = "\033[H\033[2J";
    public Scanner scanner = new Scanner(System.in);
    public final ArrayList<FlightsInfo> flightsInfo = new ArrayList<>();


    // check if the enter user and pass word are for admin
    public boolean checkAdmin(String user, String pass) {
        return user.equals(userName) && pass.equals(passWord);
    }

    //just print admin menu
    public void printAdminMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println("               Admin Menu Options");
        System.out.printf("--------------------------------------------------\n\n");
        System.out.println("   <1> Add");
        System.out.println("   <2> Update");
        System.out.println("   <3> Remove");
        System.out.println("   <4> Flight schedules");
        System.out.println("   <0> Sign out");
    }

    //get information of new flight to add
    public void add() {
        System.out.println(cls);
        FlightsInfo flight = new FlightsInfo();
        originGetting(flight);
        destinationGetting(flight);
        dateGetting(flight);
        timeGetting(flight);
        priceGetting(flight);
        seatsGetting(flight);
        idMaking(flight, flight.getDate(), flight.getTime(), flight.getOrigin(), flight.getDestination());
        System.out.println(cls);
        flightsInfo.add(flight);
        System.out.println("new flight added.");
        System.out.println("|FlightId  |Origin    |Destination  |Date      |Time |Price    |Seats |");
        System.out.printf("|%10s|%10s|%13s|%10s|%5s|%9s|%6s|\n", flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats());

    }

    //set origin of ticket in new object
    public void originGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.printf("origin : \t");
        flight.setOrigin(scanner.nextLine());
    }

    //get and set destination of ticket in new object
    public void destinationGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.printf("\ndestination : \t");
        flight.setDestination(scanner.nextLine());
    }

    //get and set date of ticket in new object
    public void dateGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.printf("\n Date:\n");
        System.out.printf("year :\t");
        int[] date = new int[3];
        date[2] = scanner.nextInt();
        System.out.printf("\nmonth :\t");
        date[1] = scanner.nextInt();
        while (true) {
            if (date[1] > 12 || date[1] < 1) {
                System.out.println("Incorrect input , try again!!");
                date[1] = scanner.nextInt();
            } else {
                break;
            }
        }
        System.out.printf("\nday :\t");
        date[0] = scanner.nextInt();
        while (true) {
            if ((date[0] > 31 || date[0] < 1) || (date[1] > 6 && date[0] == 31)) {
                System.out.println("Incorrect input , try again!!");
                date[0] = scanner.nextInt();
            } else {
                break;
            }
        }
        flight.setDate(date);
    }

    //get and set time of ticket in new object
    public void timeGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.printf("\n Time:\n");
        System.out.printf("hour :\t");
        int[] time = new int[2];
        time[0] = scanner.nextInt();
        while (true) {
            if (time[0] > 24 || time[0] < 0) {
                System.out.println("Incorrect input , try again!!");
                time[0] = scanner.nextInt();
            } else {
                break;
            }
        }
        System.out.printf("\n minute :\t");
        time[1] = scanner.nextInt();
        while (true) {
            if (time[1] > 59 || time[1] < 0) {
                System.out.println("Incorrect input , try again!!");
                time[1] = scanner.nextInt();
            } else {
                break;
            }
        }
        flight.setTime(time);

    }

    //get and set price of ticket in new object
    public void priceGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.printf("\n price :\t");
        int price = scanner.nextInt();
        while (true) {
            if (price < 0) {
                price = scanner.nextInt();
            } else {
                break;
            }
        }
        flight.setPrice(price);
    }

    //get and set seat of ticket in new object
    public void seatsGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.printf("\n seats :\t");
        int seats = scanner.nextInt();
        while (true) {
            if (seats < 0) {
                seats = scanner.nextInt();
            } else {
                break;
            }
        }
        flight.setSeats(seats);
    }

    //making id for ticket according ticket information
    public void idMaking(FlightsInfo flight, int[] date, int[] time, String origin, String destination) {
        String flightId = origin.charAt(0) + destination.charAt(0) + "-" + Integer.toString(date[1]) + Integer.toString(date[0]) + "-" + Integer.toString(time[1]) + Integer.toString(time[0]);
        flight.setFlightId(flightId);
    }

   // remove a flight according enter id from admin
    public void remove() {

        System.out.println(cls);
        int checkLoop = 1;
        int index;
        while (checkLoop != 0) {
            System.out.print("pleas enter ticket ID :\t");
            String inputId = scanner.nextLine();
            if ((index = search(inputId)) != -1) {
                flightsInfo.remove(index);
                System.out.println("This flight removed!");
                checkLoop = 0;
            } else {
                System.out.println(cls);
                System.out.println("This flight was not found");
                System.out.println("if you wanna try again , pleas enter 1 otherwise enter 0");
                checkLoop = scanner.nextInt();
            }

        }
    }

    //searching in arraylist for find a ticket with specific id
    public int search(String id) {
        for (FlightsInfo flight : flightsInfo) {
            if (flight.getFlightId() != null && flight.getFlightId().contains(id)) {
                return flightsInfo.indexOf(flight);
            }
        }
        return -1;
    }
}

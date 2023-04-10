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
        System.out.println(cls);
        System.out.println("--------------------------------------------------");
        System.out.println("               Admin Menu Options");
        System.out.print("--------------------------------------------------\n\n");
        System.out.println("   <1> Add flight ");
        System.out.println("   <2> Update ticket information");
        System.out.println("   <3> Remove flight");
        System.out.println("   <0> Sign out");

    }

    public void update() {
        System.out.println(cls);
        String checkLoop = "1";
        int index;
        while (checkLoop.equals("1")) {

            System.out.println("which one do you want to update? enter its number!");
            flightSchedules();
            index = scanner.nextInt();
            if (index - 1 <= flightsInfo.size()) {
                featureSelection(index - 1);
                System.out.println("This flight is changed!");
                System.out.println("if you wanna changed another enter 1 otherwise enter something else:");
                checkLoop = scanner.nextLine();
                System.out.println(cls);
            } else {
                inputError();
            }
        }
    }

    private void featureSelection(int i) {
        System.out.println(cls);
        System.out.println("Which one of feature do you want to change ?");
        System.out.print("1-Origin\n2-Destination\n3-Date\n4-Time\n5-Price\n6-seats\n0-back");
        int index = scanner.nextInt();
        int j = 1;
        while (j !=0 ) {
            if (index > -1 && index < 7) {
                FlightsInfo flight = flightsInfo.get(i);
                switch (index) {
                    case 1 -> originGetting(flight);
                    case 2 -> destinationGetting(flight);
                    case 3 -> dateGetting(flight);
                    case 4 -> timeGetting(flight);
                    case 5 -> priceGetting(flight);
                    case 6 -> seatsUpdate(flight);
                    case 0 ->{j = 0;}
                }
                flightsInfo.set(i,flight);
            } else {
                inputError();
            }
        }
    }

    private void inputError() {
        System.out.println(cls);
        System.out.print("Incorrect input , try again!! \n\n");
    }

    private void seatsUpdate(FlightsInfo flight) {
        System.out.println("How many seats wanna added?");
        System.out.print("\n seats :\t");
        int seats = scanner.nextInt();
        while (true) {
            if (seats < 1) {
                inputError();
                seats = scanner.nextInt();
            } else {
                break;
            }
        }
        flight.setSeats(flight.getSeats() + seats);
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
        flight.setFlightId();
        System.out.println(cls);
        flightsInfo.add(flight);
        System.out.println("New Flight Added.");
        System.out.println("|FlightId  |Origin    |Destination  |Date      |Time |Price    |Seats |");
        System.out.println(".......................................................................");
        System.out.printf("|%10s|%10s|%13s|%10s|%5s|%9s|%6s|\n", flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats());

    }

    //set origin of ticket in new object
    public void originGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.print("origin : \t");
        flight.setOrigin(scanner.nextLine());
    }

    //get and set destination of ticket in new object
    public void destinationGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.print("\ndestination : \t");
        flight.setDestination(scanner.nextLine());
    }

    //get and set date of ticket in new object
    public void dateGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.print("\n Date:\n");
        System.out.print("year :\t");
        int[] date = new int[3];
        date[2] = scanner.nextInt();
        System.out.print("\nmonth :\t");
        date[1] = scanner.nextInt();
        while (true) {
            if (date[1] > 12 || date[1] < 1) {
                inputError();
                date[1] = scanner.nextInt();
            } else {
                break;
            }
        }
        System.out.print("\nday :\t");
        date[0] = scanner.nextInt();
        while (true) {
            if ((date[0] > 31 || date[0] < 1) || (date[1] > 6 && date[0] == 31)) {
                inputError();
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
        System.out.print("\n Time:\n");
        System.out.print("hour :\t");
        int[] time = new int[2];
        time[0] = scanner.nextInt();
        while (true) {
            if (time[0] > 24 || time[0] < 0) {
                inputError();
                time[0] = scanner.nextInt();
            } else {
                break;
            }
        }
        System.out.print("\n minute :\t");
        time[1] = scanner.nextInt();
        while (true) {
            if (time[1] > 59 || time[1] < 0) {
                inputError();
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
        System.out.print("\n price :\t");
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
        System.out.print("\n seats :\t");
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


    // remove a flight according enter id from admin
    //this function first show the table of flight information and then give its number and remove that
    public void remove() {
        System.out.println(cls);
        String checkLoop = "1";
        int index;
        while (checkLoop.equals("1")) {
            System.out.println("which one do you want to remove? enter its number!");
            flightSchedules();
            index = scanner.nextInt();
            if (index - 1 <= flightsInfo.size()) {
                flightsInfo.remove(index - 1);
                System.out.println("this flight is removed!");
                System.out.println("if you wanna remove another enter 1 otherwise enter something else:");
                checkLoop = scanner.nextLine();
                System.out.println(cls);
            } else {
                inputError();
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

    // print available flight schedules for admin
    public void flightSchedules() {
        System.out.println(cls);
        System.out.println("|  |FlightId  |Origin    |Destination  |Date      |Time |Price    |Seats |");
        System.out.println("..........................................................................");
        for (FlightsInfo flight : flightsInfo) {
            System.out.printf("|%2d|%10s|%10s|%13s|%10s|%5s|%9s|%6s|\n", flightsInfo.indexOf(flight) + 1, flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats());
            System.out.println("..........................................................................");
        }
    }

}

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    private final String userName = "admin user";
    private final String passWord = "passadmin2023";
    private final String cls = "\033[H\033[2J";
    public Scanner scanner = new Scanner(System.in);
    public ArrayList<FlightsInfo> flightsInfo = new ArrayList<>();
    public Flights flights = Flights.getInstance();

    public static Admin instance = new Admin();

    private Admin() {
    }

    public static Admin getInstance() {
        return instance;
    }

    // check if the enter user and pass word are for admin
    public boolean checkAdmin(String user, String pass) {
        return user.equals(userName) && pass.equals(passWord);
    }

    public void adminMenu() {
        System.out.println(cls);
        String input = "-1";
        while (!input.equals("0")) {
            printAdminMenu();
            input = scanner.nextLine();
            switch (input) {
                case "0" -> System.out.println(cls);
                case "1" -> add();
                case "2" -> update();
                case "3" -> remove();
                case "4" -> flightSchedules();
                default -> Menu.inputError();
            }
        }

    }

    //just print admin menu
    public void printAdminMenu() {
        System.out.print("""
                --------------------------------------------------
                                 Admin Menu Options
                --------------------------------------------------
                   <1> Add flight
                   <2> Update ticket information
                   <3> Remove flight
                   <4> Flights schedule
                   <0> Sign out
                """);
    }

    public void update() {
        System.out.println(cls);
        String checkLoop = "1";
        int index;
        while (checkLoop.equals("1")) {
            flightsInfo = flights.getFlightsInfo();
            System.out.println("which one do you want to update? enter its number!");
            flightSchedules();
            index = Integer.parseInt(scanner.nextLine());
            if (index - 1 <= flightsInfo.size() && index > 0) {
                featureSelection(flightsInfo.get(index - 1));
                System.out.println("This flight is changed!");
                System.out.println("if you wanna changed another enter 1 otherwise enter something else:");
                checkLoop = scanner.nextLine();
                System.out.println(cls);
            } else {
                Menu.inputError();
            }
        }
        System.out.println(cls);
    }

    private void featureSelection(FlightsInfo flight) {
        System.out.println(cls);
        flightsInfo.remove(flight);
        String index = "";
        while (!index.equals("0")) {
            System.out.println("Which one of feature do you want to change ?");
            System.out.print("1-Origin\n2-Destination\n3-Date\n4-Time\n5-Price\n6-seats\n0-back\n");
            index = scanner.nextLine();
            switch (index) {
                case "1" -> originGetting(flight);
                case "2" -> destinationGetting(flight);
                case "3" -> dateGetting(flight);
                case "4" -> timeGetting(flight);
                case "5" -> priceGetting(flight);
                case "6" -> seatsUpdate(flight);
                case "0" -> {
                }
                default -> Menu.inputError();
            }
        }
        flight.setFlightId();
        flightsInfo.add(flight);
        flights.setFlightsInfo(flightsInfo);
        System.out.println(cls);
    }


    private void seatsUpdate(FlightsInfo flight) {
        System.out.println("How many seats wanna added?");
        System.out.print("\n seats :\t");
        int seats = scanner.nextInt();
        while (true) {
            if (seats < 1) {
                Menu.inputError();
                seats = scanner.nextInt();
            } else {
                break;
            }
        }
        flight.setSeats(flight.getSeats() + seats);
    }

    //get information of new flight to add
    public void add() {
        FlightsInfo flight = new FlightsInfo();
        makeNewFlight(flight);
        System.out.print("""
                New Flight Added.
                |FlightId     |Origin    |Destination  |Date      |Time |Price    |Seats |
                ..........................................................................
                """);
        System.out.printf("|%13s|%10s|%13s|%10s|%5s|%9s|%6s|\n", flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats());
        scanner.nextLine();
        System.out.println(cls);
    }

    private void makeNewFlight(FlightsInfo flight) {
        originGetting(flight);
        destinationGetting(flight);
        dateGetting(flight);
        timeGetting(flight);
        priceGetting(flight);
        seatsGetting(flight);
        flight.setFlightId();
        System.out.println(cls);
        flightsInfo = flights.getFlightsInfo();
        flightsInfo.add(flight);
        flights.setFlightsInfo(flightsInfo);
        flights.newFlight(flight);

    }

    //set origin of ticket in new object
    public void originGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.print("origin of flight : \t");
        flight.setOrigin(scanner.nextLine());
    }

    //get and set destination of ticket in new object
    public void destinationGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.print("\ndestination of flight: \t");
        flight.setDestination(scanner.nextLine());
    }

    //get and set date of ticket in new object
    public void dateGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.print("\n Date of flight :\n");
        System.out.print("year :\t");
        int[] date = new int[3];
        date[2] = scanner.nextInt();
        monthGetting(date);
        dayGetting(date);
        flight.setDate(date);
    }

    public void dayGetting(int[] date) {
        while (true) {
            System.out.print("\nday :\t");
            date[0] = scanner.nextInt();
            if ((date[0] > 31 || date[0] < 1) || (date[1] > 6 && date[0] == 31)) {
                Menu.inputError();
            } else {
                break;
            }
        }
    }

    public void monthGetting(int[] date) {
        while (true) {
            System.out.print("\nmonth :\t");
            date[1] = scanner.nextInt();
            if (date[1] > 12 || date[1] < 1) {
                Menu.inputError();
            } else {
                break;
            }
        }
    }

    //get and set time of ticket in new object
    public void timeGetting(FlightsInfo flight) {
        System.out.println(cls);
        System.out.print("\n Time of flight :\n");
        System.out.print("hour :\t");
        int[] time = new int[2];
        time[0] = scanner.nextInt();
        while (true) {
            if (time[0] > 24 || time[0] < 0) {
                Menu.inputError();
                time[0] = scanner.nextInt();
            } else {
                break;
            }
        }
        System.out.print("\n minute :\t");
        time[1] = scanner.nextInt();
        while (true) {
            if (time[1] > 59 || time[1] < 0) {
                Menu.inputError();
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
        System.out.print("\n price of flight :\t");
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
        System.out.print("\n seats of flight:\t");
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
            flightsInfo = flights.getFlightsInfo();
            System.out.println("which one do you want to remove? enter its number! (0 to back)");
            flightSchedules();
            index = Integer.parseInt(scanner.nextLine());
            if (index - 1 <= flightsInfo.size() && index > 0) {
                flights.remove(index);
                System.out.println(cls);
                System.out.println("this flight is removed!");
                System.out.println("if you wanna remove another enter 1 otherwise enter something else:");
                checkLoop = scanner.nextLine();
            } else if (index == 0) {
                break;
            } else {
                Menu.inputError();
            }
        }
        System.out.println(cls);
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
        flightsInfo = flights.getFlightsInfo();
        System.out.println(cls);
        System.out.print("""
                |  |FlightId     |Origin    |Destination  |Date      |Time |Price    |Seats |
                .............................................................................
                """);
        for (FlightsInfo flight : flightsInfo) {
            System.out.printf("|%2d|%13s|%10s|%13s|%10s|%5s|%9s|%6s|\n", flightsInfo.indexOf(flight) + 1, flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats());
            System.out.println(".............................................................................");
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

}

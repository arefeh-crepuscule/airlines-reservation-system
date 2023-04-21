import java.util.*;

public class User {

    private String userName;
    private String passWord;
    private String notification = "";
    private int charge = 0;
    private ArrayList<FlightsInfo> temp = new ArrayList<>();
    private final String cls = "\033[H\033[2J";
    public Scanner scanner = new Scanner(System.in);
    private Flights flights = Flights.getInstance();
    private HashMap<String, FlightsInfo> tempHashMap = new HashMap<>();
    private HashMap<String, FlightsInfo> userFlights = new HashMap<>();
    private HashMap<String, User> tempPassengerMap = new HashMap<>();
    private Users users = Users.getInstance();


    public User(String user, String pass) {
        this.passWord = pass;
        this.userName = user;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setNotification(String notification) {
        this.notification += notification;
    }

    public void passengerMenu() {
        signInNotify();
        System.out.println(cls);
        String input = "";
        while (!input.equals("0")) {
            printPassengerMenu();
            input = scanner.nextLine();
            switch (input) {
                case "0" -> System.out.println(cls);
                case "1" -> changePass();
                case "2" -> searchFlight();
                case "3" -> bookTicket();
                case "4" -> ticketCancellation();
                case "5" -> bookedTickets();
                case "6" -> addCharge();
                default -> inputError();
            }
        }

    }

    public void signInNotify() {
        if (notification !="") {
            System.out.println(cls);
            System.out.print("There is some notification for you:\n");
            printNotify();
            notification = null;
            System.out.print("Enter for continue... :\t");
            scanner.nextLine();
        }
    }

    private void printNotify() {
        if (notification != null) {
            System.out.print("""

                    This flight removed :
                    |  |FlightId  |Origin    |Destination  |Date      |Time |Price    |Seats |
                    ..........................................................................
                    """ + notification);
        }
    }

    public void printPassengerMenu() {
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

    private void ticketCancellation() {
        System.out.println(cls);
        System.out.print("Pleas enter ticket ID of flight that you want cancelled : \t");
        String ticketId = scanner.nextLine();
        if (userFlights.containsKey(ticketId)) {
            cancel(ticketId);
        } else {
            System.out.println("There is not any flights in your reserved with this information ");
        }
    }

    private void cancel(String ticketId) {
        FlightsInfo flight = userFlights.get(ticketId);
        chargeUpdate(flight.getPrice());
        flights.updateSeats(flight, 1);
        flights.removeHash(flight, users.getPassengerMap().get(userName + passWord));
        userFlights.remove(ticketId);
    }

    private void bookedTickets() {
        System.out.println(cls);
        System.out.println("You booked this flight already :");
        printFlights(userFlights);
    }

    private void printFlights(HashMap<String, FlightsInfo> userFlights) {
        FlightsInfo flight;
        System.out.print("""
                |FlightId  |Origin    |Destination  |Date      |Time |Price    |Seats |Ticket ID           |\s
                ............................................................................................
                """);
        for (String ticketId : userFlights.keySet()) {
            flight = userFlights.get(ticketId);
            System.out.printf("|%10s|%10s|%13s|%10s|%5s|%9s|%6s|%20s|\n", flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats(), ticketId);
            System.out.println("............................................................................................");

        }
    }

    private void bookTicket() {
        System.out.print(">pleas enter ID of flight you want to book :\t");
        String inputId = scanner.nextLine();
        FlightsInfo flight = checkExist(inputId);
        if (flight != null) {
            if (checkCharge(flight)) {
                booking(flight);
                chargeUpdate(-(flight.getPrice()));
                System.out.print(cls + "Booking done!");
            } else {
                System.out.println("Your charge is not enough for booking this flight");
            }
        } else {
            System.out.println("there is no such flight with this ID!!");
        }
    }

    private boolean checkCharge(FlightsInfo flight) {
        return (charge > flight.getPrice());
    }

    private void booking(FlightsInfo flight) {
        String ticketId = "WH" + Integer.toString(userFlights.size() + 1) + "-" + userName + "-" + flight.getFlightId();
        userFlights.put(ticketId, flight);
        flights.updateSeats(flight, -1);
        flights.addHash(flight, users.getPassengerMap().get(userName + passWord));
    }

    private FlightsInfo checkExist(String inputId) {
//        tempHashMap = flights.getIdKey();
        temp = flights.getFlightsInfo();
        for (FlightsInfo flight : temp) {
            if (inputId.equals(flight.getFlightId()))
                return flight;
        }
        return null;
    }

    /**
     * give the extra charge that user want to charge and call the update function
     */
    public void addCharge() {
        System.out.printf("Your current charge :\t%10d\n", charge);
        System.out.print("How much do you want to charge?\t");
        int extraCharge = Integer.valueOf(scanner.nextLine());
        chargeUpdate(extraCharge);
        System.out.println(cls);
        System.out.printf("Your charge has been updated :\t%10s", charge);
    }

    /**
     * updated private charge with extra parameter
     *
     * @param extraCharge the variable for add or subtract charge variable
     */
    private void chargeUpdate(int extraCharge) {
        charge += extraCharge;
    }

    /**
     * check input pass that be as same as saved pass in password variable
     *
     * @param inputPass new pass that we want to check it
     * @return true or false mean new pass is same or not
     */
    private boolean checkPass(String inputPass) {
        if (passWord.equals(inputPass))
            return true;
        return false;
    }

    /**
     * change user password with check old password , get new password and update pass word
     */
    public void changePass() {
        System.out.print("enter your current password :\t");
        String temp = scanner.nextLine();
        if (checkPass(temp)) {
            System.out.print(cls + "New password :\t");
            passWord = scanner.nextLine();
            System.out.printf(cls + "\nYour password has been updated");
        } else {
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

    private void searchFlight() {
        ArrayList<FlightsInfo> tempFlights = new ArrayList<>();
        String index = "";
        while (!index.equals("-1")) {
            printFilterMenu();
            index = scanner.nextLine();
            System.out.println(cls);
            index = checkFilterInput(index, tempFlights);
            System.out.println(flights.getFlightsInfo());
        }
        System.out.println(cls);

    }

    private String checkFilterInput(String index, ArrayList<FlightsInfo> tempFlights) {
        String filterNameInput = null;
        String[] times = new String[3];
        switch (index) {
            case "1", "2", "3", "6" -> {
                System.out.print("\nSearcher word:\t");
                filterNameInput = scanner.nextLine();
            }
            case "4" -> {
                getDateFilter(times);
                filterNameInput = times[2] + "-" + times[1] + "-" + times[0];
            }
            case "5" -> {
                getTimeFilter(times);
                filterNameInput = times[1] + ":" + times[0];
            }
            case "7" -> {
                showResultOfSearch(tempFlights);
                System.out.println("Enter to continue...");
                scanner.nextLine();
                return "-1";
            }
            default -> {
                inputError();
                return index;
            }
        }
        checkFlightObjects(index, filterNameInput, tempFlights);
        return index;
    }

    private void getTimeFilter(String[] times) {
        System.out.print("\nhour:\t");
        times[1] = scanner.nextLine();
        System.out.print("\nminute:\t");
        times[0] = scanner.nextLine();
        System.out.println(cls);
    }

    private void getDateFilter(String[] times) {
        System.out.print("\n>day:\t");
        times[0] = scanner.nextLine();
        System.out.print("\n>month:\t");
        times[1] = scanner.nextLine();
        System.out.print("\n>year:\t");
        times[2] = scanner.nextLine();
        System.out.println(cls);
    }

    private void checkFlightObjects(String index, String searchWord, ArrayList<FlightsInfo> tempFlights) {

        for (int i = 0; i < tempFlights.size(); i++) {
            FlightsInfo flight = flights.getFlightsInfo().get(i);
            switch (index) {
                case "1" -> {
                    if (flight.getFlightId().equals(searchWord)) {
                        tempFlights.add(flight);
                        i -= 1;
                    }
                }
                case "2" -> {
                    if (flight.getOrigin().equals(searchWord)){
                        tempFlights.add(flight);

                    }
                }
                case "3" -> {
                    if (flight.getDestination().equals(searchWord))
                        tempFlights.add(flight);
                }
                case "4" -> {
                    if (flight.getDatePrinted().equals(searchWord))
                        tempFlights.add(flight);
                }
                case "5" -> {
                    if (flight.getTimePrinted().equals(searchWord))
                        tempFlights.add(flight);
                }
                case "6" -> {
                    if (flight.getPricePrinted().equals(searchWord))
                        tempFlights.add(flight);
                }

            }
        }
    }


    private void showResultOfSearch(ArrayList<FlightsInfo> tempFlights) {
        System.out.println(cls);
        System.out.println("The result of your filter:");
        if (tempFlights.isEmpty()) {
            System.out.println("There is no flight with this information!!");

        } else {
            System.out.print("""
                    |FlightId     |Origin    |Destination  |Date      |Time |Price    |Seats |
                    ..........................................................................
                    """);
            for (FlightsInfo flight : tempFlights) {
                System.out.printf("|%13s|%10s|%13s|%10s|%5s|%9s|%6s|\n", flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats());
                System.out.println("..........................................................................");
//            }
            }
        }
    }

    private void printFilterMenu() {
        System.out.print("""
                Exert your filters ...
                   
                   1- Flight ID
                   2- Origin
                   3- Destination
                   4- Date
                   5- Time
                   6- Price
                   7- Show
                """);
    }


    public void removeAdmin(FlightsInfo flight) {
        if(userFlights.containsValue(flight)){
            for (String kye : userFlights.keySet()){
                if (userFlights.get(kye).equals(flight)){
                    userFlights.remove(kye,flight);
                    break;
                }
            }
        }
    }
}

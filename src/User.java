import java.util.*;

public class User {

    private final String userName;
    private String passWord;
    private String notification = "";
    private int charge = 0;
    private final String cls = "\033[H\033[2J";
    public Scanner scanner = new Scanner(System.in);
    private final Flights flights = Flights.getInstance();
    private final Users users = Users.getInstance();
    private final Tickets tickets = Tickets.getInstance();


    public User(String user, String pass) {
        this.passWord = pass;
        this.userName = user;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    /**
     * add new notification
     *
     * @param notification new message need to add
     */
    public void setNotification(String notification) {
        this.notification += notification;
    }

    /**
     * function first print the passenger menu and then call other function to do something
     */

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
                case "7" -> {flights.allFlightsSchedule();
                    System.out.println("Enter to continue...");
                scanner.nextLine();}
                default -> inputError();
            }
        }

    }

    /**
     * function that call when passenger sign in and if there are any notify for passenger show it
     */
    public void signInNotify() {
        if (!notification.equals("")) {
            System.out.println(cls);
            System.out.print("There is some notification for you:\n");
            printNotify();
            notification = "";
            System.out.print("Enter for continue... :\t");
            scanner.nextLine();
        }
    }

    /**
     * print to notify
     */
    private void printNotify() {
        if (!notification.equals("")) {
            System.out.print("""

                    This flight removed :
                    |  |FlightId  |Origin    |Destination  |Date      |Time |Price    |Seats |
                    ..........................................................................
                    """ + notification);
        }
    }

    /**
     * table of passenger menu for calling to print
     */
    public void printPassengerMenu() {
        System.out.println(cls);
        System.out.print("""
                --------------------------------------------------
                             Passenger Menu Options
                --------------------------------------------------
                       <1> Change password
                       <2> Search flight tickets
                       <3> Booking ticket
                       <4> Ticket cancellation
                       <5> Booked tickets
                       <6> Add charge
                       <7> Show all flights schedule
                       <0> Sign out
                """);
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

    private void searchFlight() {
        ArrayList<FlightsInfo> tempFlights = new ArrayList<>();
        String index = "";
        while (!index.equals("-1")) {
            printFilterMenu();
            index = scanner.nextLine();
            System.out.println(cls);
            index = checkFilterInput(index, tempFlights);
        }
        System.out.println(cls);

    }

    public void printFilterMenu() {
        System.out.print("""
                Exert your filters ...
                   
                   1- Flight ID
                   2- Origin
                   3- Destination
                   4- Date
                   5- Time
                   6- Price
                   7- Show
                   0- Back
                """);
    }

    private String checkFilterInput(String index, ArrayList<FlightsInfo> tempFlights) {
        String filterNameInput;
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
            case "0" -> {
                return "-1";
            }
            default -> {
                inputError();
                return index;
            }
        }
        flights.checkFlightObjects(index, filterNameInput, tempFlights);
        return index;
    }


    private void ticketCancellation() {
        System.out.println(cls);
        System.out.print("Pleas enter ticket ID of flight that you want cancelled : \t");
        String ticketId = scanner.nextLine();
        if (tickets.checkExistTicket(ticketId)) {
            cancel(ticketId);
        } else {
            System.out.println("There is not any flights in your reserved with this information ");
        }
    }

    private void cancel(String ticketId) {
        flights.updateSeats(tickets.getFlight(ticketId).getFlight() ,1);
        chargeUpdate(tickets.ticketCancellation(ticketId));
    }

    private void bookedTickets() {
        System.out.println(cls);
        System.out.println("You booked this flight already :");
        if(!(tickets.userBooked(users.getPassengerMap().get(userName+passWord))))
            System.out.println("There is nothing yet");

    }

    private void bookTicket() {
        System.out.print(">pleas enter ID of flight you want to book :\t");
        String inputId = scanner.nextLine();
        if (flights.checkExist(inputId)) {
            if (checkCharge(flights.flightGetting(inputId))) {
                String ticketId = tickets.book(users.getPassengerMap().get(userName+passWord), inputId);
                 booking(tickets.getFlight(ticketId).getFlight());
                chargeUpdate(-(flights.flightGetting(inputId).getPrice()));
                System.out.println(cls + "Booking done!");
                System.out.println("Your ticket ID : "+ ticketId);
                System.out.println("Enter to continue...");
                scanner.nextLine();
            } else {
                System.out.println("Your charge is not enough for booking this flight");
            }
        } else {
            System.out.println("there is no such flight with this ID!!");
        }
    }

    /**
     * check if charge of passenger enough for booking this flight
     *
     * @param flight for get its price
     * @return true or false mean the passenger can book or not
     */
    private boolean checkCharge(FlightsInfo flight) {
        return (charge > flight.getPrice());
    }

    private void booking(FlightsInfo flight) {
        flights.updateSeats(flight, -1);
    }



    /**
     * give the extra charge that user want to charge and call the update function
     */
    public void addCharge() {
        System.out.printf("Your current charge :\t%10d\n", charge);
        System.out.print("How much do you want to charge?\t");
        int extraCharge = Integer.parseInt(scanner.nextLine());
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
        return passWord.equals(inputPass);
    }


    /**
     * this function print error input means user input is wrong and need try again
     */
    private void inputError() {
        System.out.println(cls);
        System.out.print("Incorrect input , try again!! \n\n");
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


    public void showResultOfSearch(ArrayList<FlightsInfo> tempFlights) {
        System.out.println(cls);
        System.out.println("The result of your filter:");
        if (tempFlights.isEmpty()) {
            System.out.println("There is no flight with this information!!");

        } else {
            flights.showFlightSchedules(tempFlights);
        }
    }

}

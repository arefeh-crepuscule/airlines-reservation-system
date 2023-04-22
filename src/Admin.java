import java.util.Scanner;

public class Admin {
    private final String userName = "admin user";
    private final String passWord = "passadmin2023";
    private final String cls = "\033[H\033[2J";
    public Scanner scanner = new Scanner(System.in);
    public Flights flights = Flights.getInstance();

    public static Admin instance = new Admin();

    private Admin() {
    }

    public static Admin getInstance() {
        return instance;
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
    //get information of new flight to add
    public void add() {
        makeNewFlight();
        System.out.println(cls+"New flight added .");
        scanner.nextLine();
        System.out.println(cls);
    }

    public void update() {
        System.out.println(cls);
        String checkLoop = "1";
        int index;
        while (checkLoop.equals("1")) {
            System.out.println("which one do you want to update? enter its number!");
            flights.allFlightsSchedule();
            index = Integer.parseInt(scanner.nextLine());
            if (index - 1 <= flights.flightsInfoSize() && index > 0) {
                featureSelection(index - 1);
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
    // remove a flight according enter id from admin
    //this function first show the table of flight information and then give its number and remove that
    public void remove() {
        System.out.println(cls);
        String checkLoop = "1";
        int index;
        while (checkLoop.equals("1")) {
            System.out.println("which one do you want to remove? enter its number! (0 to back)");
            flights.allFlightsSchedule();
            index = Integer.parseInt(scanner.nextLine());
            if (index - 1 <= flights.flightsInfoSize() && index > 0) {
                flights.remove(index);
                System.out.println(cls);
                System.out.println("this flight is removed!");
                System.out.println("Back(0) or Remove an other (something else) :\t");
                checkLoop = scanner.nextLine();
            } else if (index == 0) {
                break;
            } else {
                Menu.inputError();
            }
        }
        System.out.println(cls);
    }
    public void flightSchedules() {
        System.out.println(cls);
        flights.allFlightsSchedule();
    }
    private void makeNewFlight() {
        int flightOrder = flights.newFlight();
        flights.newOrigin(flightOrder,originGetting());
        flights.newDestination(flightOrder,destinationGetting());
        flights.newDate(flightOrder,dateGetting());
        flights.newTime(flightOrder,timeGetting());
        flights.newPrice(flightOrder,priceGetting());
        flights.newSeats(flightOrder,seatsGetting());
        flights.newFlightId(flightOrder);
        flights.addNewFlight(flightOrder);
        System.out.println(cls);
    }


    private void featureSelection(int flightOrder) {
        System.out.println(cls);
        String index = "";
        while (!index.equals("0")) {
            System.out.println("Which one of feature do you want to change ?");
            System.out.print("1-Origin\n2-Destination\n3-Date\n4-Time\n5-Price\n6-seats\n0-back\n");
            index = scanner.nextLine();
            switch (index) {
                case "1" -> flights.newOrigin(flightOrder, originGetting());
                case "2" -> flights.newDestination(flightOrder, destinationGetting());
                case "3" -> flights.newDate(flightOrder, dateGetting());
                case "4" -> flights.newTime(flightOrder,timeGetting());
                case "5" -> flights.newPrice(flightOrder,priceGetting());
                case "6" -> flights.newSeats(flightOrder, seatsUpdate());
                case "0" -> {
                }
                default -> Menu.inputError();
            }
        }
        System.out.println(cls);
    }


    private int seatsUpdate( ) {
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
        return seats;
    }

    //set origin of ticket in new object
    public String originGetting() {
        System.out.println(cls);
        System.out.print("origin of flight : \t");
        return (scanner.nextLine());
    }

    //get and set destination of ticket in new object
    public String destinationGetting() {
        System.out.println(cls);
        System.out.print("\ndestination of flight: \t");
        return (scanner.nextLine());
    }

    //get and set date of ticket in new object
    public int[] dateGetting() {
        System.out.println(cls);
        System.out.print("\n Date of flight :\n");
        System.out.print("year :\t");
        int[] date = new int[3];
        date[2] = scanner.nextInt();
        monthGetting(date);
        dayGetting(date);
        return date;
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
    public int[] timeGetting() {
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
        return time;

    }

    //get and set price of ticket in new object
    public int priceGetting() {
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
        return price;
    }

    //get and set seat of ticket in new object
    public int seatsGetting() {
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
        return seats;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

}

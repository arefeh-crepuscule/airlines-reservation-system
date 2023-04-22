import java.util.*;

public class Flights {
    private final ArrayList<FlightsInfo> flightsInfo = new ArrayList<>();
    private final HashMap<String, FlightsInfo> idFlight = new HashMap<>();
    private static final Flights instance = new Flights();
    private final Tickets tickets= Tickets.getInstance();



    private Flights() {
    }

    public static Flights getInstance() {
        return instance;
    }

    /**
     * @return flightsInfo arraylist size
     */
    public int flightsInfoSize(){
        return flightsInfo.size();
    }

    /**
     * add a new flight to flightsInfo arraylist
     * @param flight new flight
     */
    public void addFlight(FlightsInfo flight) {
        flightsInfo.add(flight);
        tickets.newFlightAdded(flight);
        idFlight.put(flight.getFlightId(), flight);
    }

    /**
     * show every exist flight
      */
    public  void allFlightsSchedule(){
        showFlightSchedules(flightsInfo);
    }

    /**
     * print an arraylist of flight
     * @param tempFlights an arraylist include flight object
     */
    public void showFlightSchedules(ArrayList <FlightsInfo> tempFlights) {
        System.out.println("|  |FlightId  |Origin    |Destination  |Date      |Time |Price    |Seats |");
        System.out.println("..........................................................................");
        for (FlightsInfo flight : tempFlights) {
            System.out.printf("|%2d|%13s|%10s|%13s|%10s|%5s|%9s|%6s|\n", tempFlights.indexOf(flight) + 1, flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats());
            System.out.println(".............................................................................\n");
        }
    }

    /**
     * remove flight from flightsInfo arraylist and remove the tickets
      * @param index
     */
    public void remove(int index) {
        FlightsInfo flight = flightsInfo.get(index - 1);
        tickets.removeFlight(flight);
        flightsInfo.remove(index - 1);
    }

    /**
     * update seats of flight like add or remove
     * @param flight the flight want to update it
     * @param amount amount that we want update
     */
    public void updateSeats(FlightsInfo flight, int amount) {
        flight.setSeats(flight.getSeats() + amount);


    }

    /**
     * check flight object with specific parameters and collect them
     * @param index show which parameters of flight object n=want to check
     * @param searchWord the word that we want to know if there is thing as same as it
     * @param tempFlights an arraylist include collected flights
     */
    public void checkFlightObjects(String index, String searchWord, ArrayList<FlightsInfo> tempFlights) {
        for (FlightsInfo flight : flightsInfo) {

            switch (index) {
                case "1" -> {
                    if (flight.getFlightId().equals(searchWord)) {
                        tempFlights.add(flight);
                    }
                }
                case "2" -> {
                    if (flight.getOrigin().equals(searchWord)) {
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

    /**
     * set destination in flight destination parameter
     * @param index index of flight in flightsInfo arraylist
     * @param destination new destination need to set
     */
    public void newDestination(int index, String destination) {
        flightsInfo.get(index).setDestination(destination);
    }

    /**
     * set date in flight date parameter
      * @param index index of flight in flightsInfo arraylist
     * @param date new date need to set
     */
    public void newDate(int index, int[] date) {
        flightsInfo.get(index).setDate(date);
    }

    /**
     * set time in flight time parameter
     * @param index index of flight in flightsInfo arraylist
     * @param time new time need to set
     */
    public void newTime(int index, int[] time) {
        flightsInfo.get(index).setTime(time);
    }

    /**
     * set price in flight price parameter
     * @param index index of flight in flightsInfo arraylist
     * @param price new price need to set
     */
    public void newPrice(int index, int price) {
        flightsInfo.get(index).setPrice(price);
    }

    /**
     * set seats in flight seats parameter
     * @param index index of flight in flightsInfo arraylist
     * @param seats new seats need to set
     */
    public void newSeats(int index, int seats) {
        flightsInfo.get(index).setSeats(seats);
    }

    /**
     * set origin in flight origin parameter
     * @param index index of flight in flightsInfo arraylist
     * @param origin new origin need to set
     */
    public void newOrigin(int index, String origin) {
        flightsInfo.get(index).setOrigin(origin);
    }

    /**
     * set flight id in flight id parameter
     * @param index index of flight in flightsInfo arraylist
     */
    public void newFlightId(int index){
        flightsInfo.get(index).setFlightId();
    }

    /**
     * make new null flight and save it arraylist
      * @return index of flight in arraylist
     */
    public int newFlight(){
        FlightsInfo flight = new FlightsInfo();
        flightsInfo.add(flight);
        return flightsInfo.indexOf(flight);
    }

    /**
     * update database when creat new flight
      * @param flightOrder index flight in arraylist
     */
    public void addNewFlight(int flightOrder) {
        idFlight.put(flightsInfo.get(flightOrder).getFlightId(), flightsInfo.get(flightOrder));
        tickets.newFlightAdded(flightsInfo.get(flightOrder));
    }

    /**
     * check exist if there is such flight with this input id
      * @param inputId input id
     * @return true or false mean there is or not
     */
    public boolean checkExist(String inputId) {
        return idFlight.containsKey(inputId);
    }

    /**
     * find flight with specific  id
     * @param id input id
     * @return flight that include input id
     */
    public FlightsInfo flightGetting (String id){
        return idFlight.get(id);
    }
    public int getFlightSeats (int index){
        return flightsInfo.get(index).getSeats();
    }
}

import java.util.*;

public class Flights {
    private final String cls = "\033[H\033[2J";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<FlightsInfo> flightsInfo = new ArrayList<>();
    private HashMap<String, FlightsInfo> idFlight = new HashMap<>();
    private static Flights instance = new Flights();
    private Tickets tickets= Tickets.getInstance();


    private Flights() {
    }

    public static Flights getInstance() {
        return instance;
    }

    public int flightsInfoSize(){
        return flightsInfo.size();
    }
    public void addFlight(FlightsInfo flight) {
        flightsInfo.add(flight);
    }

    public void removeFlight(FlightsInfo flight) {
        flightsInfo.remove(flight);
    }

    public  void allFlightsSchedule(){
        showFlightSchedules(flightsInfo);
    }
    public void showFlightSchedules(ArrayList <FlightsInfo> tempFlights) {
        System.out.println("|  |FlightId  |Origin    |Destination  |Date      |Time |Price    |Seats |");
        System.out.println("..........................................................................");
        for (FlightsInfo flight : tempFlights) {
            System.out.printf("|%2d|%10s|%10s|%13s|%10s|%5s|%9s|%6s|\n", tempFlights.indexOf(flight) + 1, flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats());
            System.out.println("..........................................................................\n");
        }
    }

    public void remove(int index) {
        FlightsInfo flight = flightsInfo.get(index - 1);
        tickets.removeFlight(flight);
        flightsInfo.remove(index - 1);
    }

    public void updateSeats(FlightsInfo flight, int amount) {
        flightsInfo.remove(flight);
        flight.setSeats(flight.getSeats() + amount);
        flightsInfo.add(flight);

    }

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

    public void newDestination(int index, String destination) {
        flightsInfo.get(index).setDestination(destination);
    }

    public void newDate(int index, int[] date) {
        flightsInfo.get(index).setDate(date);
    }

    public void newTime(int index, int[] time) {
        flightsInfo.get(index).setTime(time);
    }

    public void newPrice(int index, int price) {
        flightsInfo.get(index).setPrice(price);
    }

    public void newSeats(int index, int seats) {
        flightsInfo.get(index).setSeats(seats);
    }

    public void newOrigin(int index, String origin) {
        flightsInfo.get(index).setOrigin(origin);
    }

    public void newFlightId(int index){
        flightsInfo.get(index).setFlightId();
    }
    public int newFlight(){
        FlightsInfo flight = new FlightsInfo();
        flightsInfo.add(flight);
        return flightsInfo.indexOf(flight);
    }

    public void addNewFlight(int flightOrder) {
        idFlight.put(flightsInfo.get(flightOrder).getFlightId(), flightsInfo.get(flightOrder));
        tickets.newFlightAdded(flightsInfo.get(flightOrder));
    }
    public boolean checkExist(String inputId) {
        return idFlight.containsKey(inputId);
    }
    public FlightsInfo flightGetting (String id){
        return idFlight.get(id);
    }
}

import java.util.*;

public class Flights {
    private final String cls = "\033[H\033[2J";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<FlightsInfo> flightsInfo = new ArrayList<>();
    private ArrayList<FlightsInfo> TflightsInfo = new ArrayList<>();
    private String notification ="" ;
    private HashMap <String , ArrayList<User>>  map = new HashMap<>();

    private HashMap <String , FlightsInfo> idKey = new HashMap<>();
    private static Flights  instance = new Flights();
    private Flights(){}
    public static Flights getInstance(){
        return instance;
    }

    public HashMap<String, FlightsInfo> getIdKey() {
        return idKey;
    }
    public ArrayList<FlightsInfo> getFlightsInfo() {
        TflightsInfo =flightsInfo;
        return flightsInfo;
    }
    public void back()
    {
        flightsInfo=TflightsInfo;
    }

    public void addFlight(FlightsInfo flight){
        flightsInfo.add(flight);
    }

    public void setFlightsInfo(ArrayList<FlightsInfo> flightsInfo) {
       this.flightsInfo =flightsInfo;
    }
    public void removeNotification(FlightsInfo flight){
        String id = flight.getFlightId();
        if (map.containsKey(id)) {
            ArrayList<User> user = map.get(id);
            for (User temp : user) {
                setNotification(flight);
                temp.setNotification(notification);
                temp.removeAdmin(flight);
                notification = null;
            }
        }
    }

    public void flightSchedules() {
        System.out.println(cls);
        System.out.println("|  |FlightId  |Origin    |Destination  |Date      |Time |Price    |Seats |");
        System.out.println("..........................................................................");
        for (FlightsInfo flight : flightsInfo) {
            System.out.printf("|%2d|%10s|%10s|%13s|%10s|%5s|%9s|%6s|\n", flightsInfo.indexOf(flight) + 1, flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats());
            System.out.println("..........................................................................\n");
        }
    }

    public void setNotification(FlightsInfo flight  ) {
      notification +=  String.format("|%2d|%10s|%10s|%13s|%10s|%5s|%9s|%6s|\n", flightsInfo.indexOf(flight) + 1, flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats())
        +("..........................................................................\n");

    }

    public void remove ( int index){
        FlightsInfo flight = flightsInfo.get(index-1);
        removeNotification(flight);
        flightsInfo.remove(index -1);
    }

    public  void  setArrayList (int index ,FlightsInfo flight ){
        if (index >= 0){
            flightsInfo.set(index,flight);
        }else{
            flightsInfo.add(flight);
        }
    }

    public void newFlight(FlightsInfo flight) {
        idKey.put(flight.getFlightId() , flight);
    }


    public void newInMap(FlightsInfo flight){

    }




    public  void addHash(FlightsInfo flight , User passenger){
        String id = flight.getFlightId();
        ArrayList <User> tempPassenger = map.get(id);
        tempPassenger .add(passenger);
        map.replace(id , tempPassenger);
    }

    public  void removeHash(FlightsInfo flight , User passenger){
        String id = flight.getFlightId();
        ArrayList <User> tempPassenger = map.get(id);
        tempPassenger .remove(passenger);
        map.replace(id , tempPassenger);
    }

    public String getNotification() {
        String notification1 = notification;
        notification = null;
        return notification1;
    }

    public  void updateSeats (FlightsInfo flight , int amount){
     flightsInfo.remove(flight);
     flight.setSeats(flight.getSeats()+amount);
     flightsInfo.add(flight);

    }


    public void makeNewHash(FlightsInfo flight) {
        ArrayList<User> passenger = new ArrayList<>();
        map.put(flight.getFlightId(), passenger);
    }
}

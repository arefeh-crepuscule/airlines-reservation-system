import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Flights {
    protected final String cls = "\033[H\033[2J";
    protected Scanner scanner = new Scanner(System.in);
    protected ArrayList<FlightsInfo> flightsInfo = new ArrayList<>();
    protected String notification ;

    protected HashMap <String , ArrayList<User>>  map = new HashMap<>();

    protected HashMap <String , FlightsInfo> idKey = new HashMap<>();

    public HashMap<String, FlightsInfo> getIdKey() {
        return idKey;
    }
    public ArrayList<FlightsInfo> getFlightsInfo() {
        return flightsInfo;
    }

}
class AdminAccess extends Flights{
    public void setFlightsInfo(ArrayList<FlightsInfo> flightsInfo) {
        this.flightsInfo = flightsInfo;

    }
    public void removeNotification(FlightsInfo flight){
        String id = flight.getFlightId();
        ArrayList<User>user = map.get(id);
        for(User temp : user){
            setNotification(flight);
            temp.setNotification(notification);
            notification = null;
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
}




class  UserAccess extends Flights{

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


}

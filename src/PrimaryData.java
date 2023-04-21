import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PrimaryData {
    private ArrayList<FlightsInfo> primary = new ArrayList<>();
    private ArrayList<String> city = new ArrayList<>(Arrays.asList("yazd","kerman","mashhad", "shiraz", "tehran", "esfahan", "ardabil", "kish","ahvaz","hamadan","karaj","semnan","ilam","gheshm","amol","tabas","savah","kashan"));

    private Flights flights = Flights.getInstance();
    private String tempCity;
    Random rand = new Random();

    public PrimaryData(){

    }

    public void makePrimaryFlights() {
        for (int i = 0; i < 10; i++) {
            FlightsInfo flight = new FlightsInfo();
            setCities(flight);
            setDate(flight);
            setTime(flight);
            setPrice(flight);
            flight.setSeats(245);
            flight.setFlightId();
            flights.addFlight(flight);
            flights.makeNewHash(flight);
        }
    }

    private void setPrice(FlightsInfo flight) {
        int []price = {1500000, 1400000,1700000,1100000,1350000,1240000,1432000,1640000,1030000,1090000,1300000};
        flight.setPrice(price[rand.nextInt(price.length)]);
    }

    private void setTime(FlightsInfo flight) {
        int [] time = new int[2];
        time[0]= rand.nextInt(59);
        time[1]= rand.nextInt(23);
        flight.setTime(time);

    }

    private void setDate(FlightsInfo flight) {
        int [] date=new int[3];
        date[0] = rand.nextInt(1,30);
        date[1] = rand.nextInt(1,12);
        date[2] = rand.nextInt(1401,1410);
        flight.setDate(date);
    }

    private void setCities(FlightsInfo flight) {
        tempCity = city.get(rand.nextInt(city.size()));
        flight.setOrigin(tempCity);
        city.remove(tempCity);
        flight.setDestination(city.get(rand.nextInt(city.size())));
        city.add(tempCity);
    }

}

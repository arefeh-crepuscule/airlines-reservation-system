import java.util.ArrayList;
import java.util.Random;

public class PrimaryData {
    private ArrayList<FlightsInfo> primary = new ArrayList<>();
    private ArrayList<String> city = new ArrayList<>();
    private String tempCity;
    Random rand = new Random();
    private FlightsInfo flight = new FlightsInfo();
    private AdminAccess flights = new AdminAccess();

    public PrimaryData(){
        makePrimaryFlights();
    }

    private void makePrimaryFlights() {
        for (int i = 0; i < 10; i++) {
            setCities();
            setDate();
            setTime();
            setPrice();
            flight.setSeats(245);
            flight.setFlightId();
            primary.add(flight);
        }
        flights.setFlightsInfo(primary);
    }

    private void setPrice() {
        int []price = {1500000, 1400000,1700000,1100000,1350000,1240000,1432000,1640000,1030000,1090000,1300000};
        flight.setPrice(price[rand.nextInt(price.length)]);
    }

    private void setTime() {
        int [] time = new int[2];
        time[0]= rand.nextInt(59);
        time[1]= rand.nextInt(23);
        flight.setTime(time);

    }

    private void setDate() {
        int [] date=new int[3];
        date[0] = rand.nextInt(1,30);
        date[1] = rand.nextInt(1,12);
        date[2] = rand.nextInt(1401,1410);
        flight.setDate(date);
    }

    private void setCities() {
        tempCity = city.get(rand.nextInt(city.size()));
        flight.setOrigin(tempCity);
        city.remove(tempCity);
        flight.setDestination(city.get(rand.nextInt(city.size())));
        city.add(tempCity);
    }

}

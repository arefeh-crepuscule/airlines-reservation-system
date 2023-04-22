import java.util.ArrayList;
import java.util.HashMap;

public class Tickets {
    private final ArrayList<Ticket> tickets = new ArrayList<>();
    private final HashMap<String, Ticket> ticketsMapId = new HashMap<>();
    private final HashMap<FlightsInfo, ArrayList<Ticket>> ticketMapFlight = new HashMap<>();
    private final Flights flights = Flights.getInstance();
    public static Tickets instance = new Tickets();

    private Tickets() {
    }

    public static Tickets getInstance() {
        return instance;
    }

    public void addTicket(User user, FlightsInfo flight) {
        makeTicket(user, flight);
    }

    public void newFlightAdded(FlightsInfo flight) {
        ArrayList<Ticket> ticket = new ArrayList<>();
        ticketMapFlight.put(flight, ticket);
    }

    private void makeTicket(User user, FlightsInfo flight) {
        Ticket ticket = new Ticket(user, flight);
        tickets.add(ticket);
        ticketsMapId.put(ticket.getTicketId(), ticket);
        (ticketMapFlight.get(flight)).add(ticket);
    }

    public void removeFlight(FlightsInfo flight) {
        for (Ticket ticket : ticketMapFlight.get(flight)) {
            setUserNotify(ticket.getUser(), flight);
            ticketsMapId.remove(ticket.getTicketId());
            tickets.remove(ticket);
        }
        ticketMapFlight.remove(flight);
    }

    private void setUserNotify(User user, FlightsInfo flight) {
        user.setNotification(String.format("|%13s|%10s|%13s|%10s|%5s|%9s|%6s|\n", flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats())
                + ("..........................................................................\n"));
    }


    public int ticketCancellation(String ticketId) {
        FlightsInfo flight = ticketsMapId.get(ticketId).getFlight();
        removeTicket(ticketId);
        return flight.getPrice();
    }

    public void removeTicket(String ticketId) {
        Ticket temp = ticketsMapId.get(ticketId);
        flights.updateSeats(temp.getFlight(), -1);
        tickets.remove(temp);
        ticketMapFlight.get(temp.getFlight()).remove(temp.getUser());
        ticketsMapId.remove(ticketId);
    }
    public boolean checkExistTicket(String ticketId){
        return ticketsMapId.containsKey(ticketId);
    }
    public void userBooked (User user){
        ArrayList <FlightsInfo> temp = new ArrayList<>();
        for (Ticket ticket : tickets){
            if (ticket.getUser().equals(user)){
                temp.add(ticket.getFlight());
            }
        }
        flights.showFlightSchedules(temp);
    }
}

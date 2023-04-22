import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Tickets {
    Scanner scanner = new Scanner(System.in);
    private final ArrayList<Ticket> tickets = new ArrayList<>();
    private final TreeMap<String, Ticket> ticketsMapId = new TreeMap<>();
    private final HashMap<FlightsInfo, ArrayList<Ticket>> ticketMapFlight = new HashMap<>();
    private final Flights flights = Flights.getInstance();
    public static Tickets instance = new Tickets();

    private Tickets() {
    }

    public static Tickets getInstance() {
        return instance;
    }

    /**
     * making ticket when a passenger book a flight
     *
     * @param user   passenger account
     * @param flight flight that passenger want to book
     */
    public String makeTicket(User user, FlightsInfo flight) {
        return addTicket(user, flight);

    }

    /**
     * when admin make a flight, here make a hash map for handling passengers will want to book or cancel it
     *
     * @param flight new flight that admin making
     */
    public void newFlightAdded(FlightsInfo flight) {
        ArrayList<Ticket> ticket = new ArrayList<>();
        ticketMapFlight.put(flight, ticket);
    }

    /**
     * update tickets array . ticketMapId and ticketMapFlight hashmap as database
     *
     * @param user   passenger account that book a flight
     * @param flight a flight that passenger booked it
     */
    private String addTicket(User user, FlightsInfo flight) {
        Ticket ticket = new Ticket(user, flight);
        tickets.add(ticket);
        ticketsMapId.put(ticket.getTicketId(), ticket);
        (ticketMapFlight.get(flight)).add(ticket);
        return  ticket.getTicketId();
    }

    /**
     * handling database when a flight canceled
     *
     * @param flight flight canceled
     */
    public void removeFlight(FlightsInfo flight) {
        for (Ticket ticket : ticketMapFlight.get(flight)) {
            setUserNotify(ticket.getUser(), flight);
            ticketsMapId.remove(ticket.getTicketId());
            tickets.remove(ticket);
        }
        ticketMapFlight.remove(flight);
    }

    /**
     * when a flight removed this function set notify for every user that booked it before
     *
     * @param user   user booked before
     * @param flight flight canceled
     */
    private void setUserNotify(User user, FlightsInfo flight) {
        user.setNotification(String.format("|%13s|%10s|%13s|%10s|%5s|%9s|%6s|\n", flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), flight.getSeats())
                + ("..........................................................................\n"));
    }


    /**
     * handel data when a passenger cancel its flight
     *
     * @param ticketId personal id for passenger when book flight
     * @return price of flight that canceled
     */
    public int ticketCancellation(String ticketId) {
        FlightsInfo flight = ticketsMapId.get(ticketId).getFlight();
        removeTicket(ticketId);
        return flight.getPrice();
    }

    /**
     * handel data of tickets class when ticketCancellation function called
     *
     * @param ticketId personal id for passenger when book a flight
     */
    public void removeTicket(String ticketId) {
        Ticket temp = ticketsMapId.get(ticketId);
        ticketMapFlight.get(temp.getFlight()).remove(temp.getUser());
        ticketsMapId.remove(ticketId);
        tickets.remove(temp);
    }

    /**
     * check if there is any key in ticketMapId same as input ticket id
     *
     * @param ticketId input ticket
     * @return true or false mean there is or not
     */
    public boolean checkExistTicket(String ticketId) {
        return ticketsMapId.containsKey(ticketId);
    }

    /**
     * when a passenger want see its booked flight call this function to collect if there is flight for this passenger
     *
     * @param user passenger account
     */
    public boolean userBooked(User user) {
        ArrayList<Ticket> temp = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getUser().equals(user)) {
                temp.add(ticket);
            }
        }
        if (temp.isEmpty())
            return false;
        System.out.println("|  |FlightId     |Origin    |Destination  |Date      |Time |Price    |Seats |Ticket ID     |");
        System.out.println("...........................................................................................");
        for (Ticket ticket : temp) {
            FlightsInfo flight = ticket.getFlight();
            System.out.printf("|%2d|%13s|%10s|%13s|%10s|%5s|%9s|%16s|\n", temp.indexOf(ticket)+1, flight.getFlightId(), flight.getOrigin(), flight.getDestination(), flight.getDatePrinted(), flight.getTimePrinted(), flight.getPrice(), ticket.getTicketId());
            System.out.println("...........................................................................................\n");
        }
        System.out.println("Enter to continue ...");
        scanner.nextLine();
        return true;
    }

    public String book(User user, String inputId) {
        for (FlightsInfo flight : ticketMapFlight.keySet()) {
            if (flight.getFlightId().equals(inputId)) {
                return makeTicket(user, flight);

            }
        }
        return null;
    }

    public Ticket getFlight(String ticketId) {
        return ticketsMapId.get(ticketId);
    }
}

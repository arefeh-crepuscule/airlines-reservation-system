public class Ticket {

   private final FlightsInfo flight ;
    private final User user;
    private String ticketId ;


    public  Ticket(User user , FlightsInfo flight){
        this.flight = flight;
        this.user = user;
        makePersonalId(user, flight);
    }

    private void makePersonalId(User user, FlightsInfo flight) {
        ticketId = (user.getUserName()).toCharArray()[0]+(user.getUserName()).toCharArray()[1]+"-"+flight.getFlightId()+"-"+ ((flight.getDate())[0] + (flight.getDate())[1]);
    }
    public FlightsInfo getFlight() {
        return flight;
    }
    public User getUser() {
        return user;
    }
    public String getTicketId() {
        return ticketId;
    }

}

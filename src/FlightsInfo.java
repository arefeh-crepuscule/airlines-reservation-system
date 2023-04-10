public class FlightsInfo {
    private String flightId ;
    private String origin;
    private String destination;
    private int [] date =new int[3];
    private String datePrinted ;
    private int [] time = new int[2];
    private String timePrinted;

    public String getDatePrinted() {
        return datePrinted;
    }

    public String getTimePrinted() {
        return timePrinted;
    }

    private int price;
    private int seats;

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId() {
        String flightId = origin.charAt(0) + destination.charAt(0) + "-" + Integer.toString(date[1]) + Integer.toString(date[0]) + "-" + Integer.toString(time[1]) + Integer.toString(time[0]);
        this.flightId = flightId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int[] getDate() {
        return date;
    }

    public void setDate(int[] date) {
        this.date = date;
        datePrinted = Integer.toString(date[2])+"-"+Integer.toString(date[1])+"-"+Integer.toString(date[0]);
    }

    public int[] getTime() {
        return time;
    }

    public void setTime(int[] time) {
        this.time = time;
        timePrinted = Integer.toString(time[1])+":"+Integer.toString(time[0]);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

}

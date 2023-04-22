

public class FlightsInfo {
    private String flightId ;
    private String origin;
    private String destination;
    private int [] date =new int[3];
    private String datePrinted ;
    private int [] time = new int[2];
    private String timePrinted ;
    private String pricePrinted;
    private int price;
    private int seats;

    public FlightsInfo(){}
    public FlightsInfo(String flightId, String origin, String destination, String date, String time, String price) {
        this.flightId =flightId;
        this.origin=origin;
        this.destination = destination;
        this.datePrinted=date;
        this.timePrinted =time;
        this.pricePrinted=price;
    }


    public String getDatePrinted() {
        return datePrinted;
    }

    public String getTimePrinted() {
        return timePrinted;
    }


    public String getFlightId() {
        return flightId;
    }

    public void setFlightId() {
        this.flightId = origin.charAt(0) + destination.charAt(0) + "-" + date[1] + date[0] + "-" + time[1] + time[0];
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
        datePrinted = date[2] +"-"+ date[1] +"-"+ date[0];
    }
    public String getPricePrinted(){
        return pricePrinted;
    }

    public void setTime(int[] time) {
        this.time = time;
        timePrinted = time[1] +":"+ time[0];
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        this.pricePrinted = Integer.toString(price);
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }



}

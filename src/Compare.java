public class Compare {
    private FlightsInfo temp ;
    private FlightsInfo main ;
    public boolean compare (FlightsInfo temp , FlightsInfo main){
        this.temp =temp;
        this.main=main;
        return flightId() && origin() && destination() && date() && time() && price();
    }

    private boolean price() {
        if(temp.getPricePrinted()==null)
            return true;
        else{
            return temp.getPricePrinted().equals(main.getPricePrinted());
        }
    }

    private boolean time() {
        if(temp.getTimePrinted()==null)
            return true;
        else{
            return temp.getTimePrinted().equals(main.getTimePrinted());
        }
    }

    private boolean date() {
        if(temp.getDatePrinted()==null)
            return true;
        else{
            return temp.getDatePrinted().equals(main.getDatePrinted());
        }
    }

    private boolean destination() {
        if(temp.getDestination()==null)
            return true;
        else{
            return temp.getDestination().equals(main.getDestination());
        }
    }

    private boolean origin() {
        if(temp.getOrigin()==null)
            return true;
        else{
            return temp.getOrigin().equals(main.getOrigin());
        }
    }

    private boolean flightId() {
        if(temp.getFlightId()==null)
            return true;
        else{
            return temp.getFlightId().equals(main.getFlightId());
        }
    }

}

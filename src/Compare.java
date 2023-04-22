public class Compare {
    private FlightsInfo temp ;
    private FlightsInfo main ;
    public boolean compare (FlightsInfo temp , FlightsInfo main){
        this.temp =temp;
        this.main=main;
        if (flightId() && origin() && destination()&&date() && time() && price())
            return true;

        return false;
    }

    private boolean price() {
        if(temp.getPricePrinted()==null)
            return true;
        else{
            if(temp.getPricePrinted().equals(main.getPricePrinted()))
                return true;
            return false;
        }
    }

    private boolean time() {
        if(temp.getTimePrinted()==null)
            return true;
        else{
            if(temp.getTimePrinted().equals(main.getTimePrinted()))
                return true;
            return false;
        }
    }

    private boolean date() {
        if(temp.getDatePrinted()==null)
            return true;
        else{
            if(temp.getDatePrinted().equals(main.getDatePrinted()))
                return true;
            return false;
        }
    }

    private boolean destination() {
        if(temp.getDestination()==null)
            return true;
        else{
            if(temp.getDestination().equals(main.getDestination()))
                return true;
            return false;
        }
    }

    private boolean origin() {
        if(temp.getOrigin()==null)
            return true;
        else{
            if(temp.getOrigin().equals(main.getOrigin()))
                return true;
            return false;
        }
    }

    private boolean flightId() {
        if(temp.getFlightId()==null)
            return true;
        else{
            if(temp.getFlightId().equals(main.getFlightId()))
                return true;
            return false;
        }
    }

}

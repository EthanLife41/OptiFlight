public class cheapestFlight {
    private String airline;
    private String flightNumber;
    private String airplane;
    private String travelClass;
    private String price;
    private String duration;
    private String airports;
    private String extensions;

    /**
     * Constructor to initialize the cheapest flight details based on the provided flights array.
     * Precondition: The flights parameter must not be null and must contain at least one entry. The entry in the 'flights' 2D array must contain flight information in the specific format as per FlightSearch.java
     * Postcondition: initializes the instance variables of the 'cheapestFlight' object with the details of the cheapest flight.
     * 
     * @param flights 2D array of flight information
    */
    public void CheapestFlight(String[][] flights) {
        // Calls the findCheapestFlight method to find the cheapest flights (see cheapestFlightFinder.java)
        String[] cheapFlight = cheapestFlightFinder.findCheapestFlight(flights);

        // Assign cheapest flight details to instance variables
        this.airline = cheapFlight[0];
        this.flightNumber = cheapFlight[1];
        this.airplane = cheapFlight[2];
        this.travelClass = cheapFlight[3];
        this.price = cheapFlight[4];
        this.duration = cheapFlight[5];
        this.airports = cheapFlight[6];
        this.extensions = cheapFlight[7];
    }

    /**
     * Accessor method to get the airline name of the cheapest flight.
     * 
     * @return Airline name
    */
    public String getAirline() {
        return airline;
    }

    /**
     * Accessor method to get the flight number of the cheapest flight.
     * 
     * @return Flight number
    */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Accessor method to get the type of airplane for the cheapest flight.
     * 
     * @return Type of airplane
    */
    public String getAirplane() {
        return airplane;
    }

    /**
     * Accessor method to get the travel class of the cheapest flight (ie. First Class, Business etc.).
     * 
     * @return Travel class
    */
    public String getTravelClass() {
        return travelClass;
    }

    /**
     * Accessor method to get the price of the cheapest flight.
     * 
     * @return Price of the flight
    */
    public String getPrice() {
        return price;
    }

    /**
     * Accessor method to get the duration of the cheapest flight.
     * 
     * @return Duration of the flight
    */
    public String getDuration() {
        return duration;
    }

    /**
     * Accessor method to get the departure and arrival airports of the cheapest flight.
     * 
     * @return Departure and arrival airports
    */
    public String getAirports() {
        return airports;
    }

    /**
     * Accessor method to get the additional details about the cheapest flight.
     * 
     * @return additional details
    */

    public String getExtensions() {
        return extensions;
    }
}

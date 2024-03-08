public class cheapestFlightPrint {
    private String airline;
    private String flightNumber;
    private String airplane;
    private String travelClass;
    private String price;
    private String duration;
    private String airports;
    private String extensions;

    /**
    * Prints the cheapest flight information to the console.
    * Precondition: Must pass a valid result from cheapestFlight.java
    * 
    * @param cheapest the cheapest flight information as a result from cheapestFlight.java
    */
    public void printCheapestFlight(cheapestFlight cheapest){
        this.airline = cheapest.getAirline();
        this.flightNumber = cheapest.getFlightNumber();
        this.airplane = cheapest.getAirplane();
        this.travelClass = cheapest.getTravelClass();
        this.price = cheapest.getPrice();
        this.duration = cheapest.getDuration();
        this.airports = cheapest.getAirports();
        this.extensions = cheapest.getExtensions();

        System.out.println("\n__________Cheapest Flight__________\n");
        System.out.println("The cheapest " + travelClass + " flight from " + airports + " is:\n \n" + airline + " Flight " + flightNumber + " for " + price + "\n"); 
        System.out.println("Info:\n \nPlane Type: " + airplane);
        System.out.println("Flight Time: " + duration);
        System.out.println("Details: " + extensions);
        System.out.println();  
    }
}

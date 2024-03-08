public class cheapestFlightFinder {

    /**
     * Finds the cheapest flight among the provided 2D array of flights.
     * Precondition: The 'flights' array must not be null and must contain at least one flight entry. Each 'flights' array entry must follow the format given in FlightSearch.java.
     * Postcondition: Returns the information of the cheapest flight found among the provided flights in a String array, or null if no flights have available prices.
     * 
     * @param flights 2D array of flight information from FlightSearch.java
     * @return String array containing the information of the cheapest flight found, or null if no flights are provided or if all prices are unavailable
    */
    public static String[] findCheapestFlight(String[][] flights) {
        // Checks if the 'flights' array is not null and contains at least one flight entry
        if (flights == null || flights.length == 0) {
            System.out.println("ERROR: No flights found.");
            return null; 
        }

        // Creates instance String array to store the cheapest flight information
        String[] cheapestFlight = flights[0];

        //Uses Linear Search Algorithm to find the cheapest flight
        for (int i = 1; i < flights.length; i++) {
            // Extracting price from flight information and removing "$" and "CAD", and then converting to double in order to do comparison
            double currentPrice = Double.parseDouble(flights[i][4].replace("$", "").replace(" CAD", ""));
            double cheapestPrice = Double.parseDouble(cheapestFlight[4].replace("$", "").replace(" CAD", ""));

            // Compare current flight's price with the cheapest price found so far
            // If current flight is cheaper than the cheapest flight, update cheapest flight. If current price = 0, that means that this price is currently unavaliable so this condition is not met.
            if (currentPrice < cheapestPrice && currentPrice != 0) { 
                // Update cheapest flight if current flight is cheaper
                cheapestFlight = flights[i]; 
            }
        }

        return cheapestFlight;
    }
}

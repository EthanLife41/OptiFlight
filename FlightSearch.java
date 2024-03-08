import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FlightSearch {

    /**
     * Static method that formats the JSONarray on the "extensions" (aka details) of the flight into a string.
     * Precondition: "extensionsArray" must be a valid JSONArray containing the correct "extensions" regarding the flight.
     * Postcondition: Returns the formatted string of "extensions" regarding the flight.
     * 
     * @param extensionsArray JSONArray containing extensions of the flight from the API response
     * @return Formatted string of extensions
    */
    private static String formatExtensions(JSONArray extensionsArray) {
        String extensions = "";
        for (int j = 0; j < extensionsArray.size(); j++) {
            extensions += extensionsArray.get(j);
            if (j < extensionsArray.size() - 1) {
                extensions += ", ";
            }
        }
        return extensions;
    }

    /**
     * Formats the airport details into a string (including the full name and the airport code).
     * Precondition: airport must be a valid JSONObject containing airport details.
     * Postcondition: Returns a formatted string of airport details. Formatted in format "Airport Name (Airport Code)"
     * 
     * @param airport JSONObject containing airport details
     * @return Formatted string of airport details
    */
    private static String formatAirport(JSONObject airport) {
        String name = (String) airport.get("name");
        String code = (String) airport.get("id");
        return name + " (" + code + ")";
    }
    /**
     * Extracts flight information from JSON response.
     * Precondition: jsonResponse must be a valid JSON response string from the API
     * Postcondition: Returns a 2D array of flight information extracted from the JSON response. May throw a ParseException if there is an error parsing the JSON response.
     * 
     * @param jsonResponse JSON response string from the API
     * @return 2D array of flight information
     * @throws ParseException If there is an error parsing the JSON response from the API
     * @throws price = $0 CAD if there is no price information in the JSON response
    */
    public static String[][] extractFlights(String jsonResponse) throws ParseException {
        // Create a JSON parser & parse the JSON response string
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);

        JSONArray flightsArray;
        
        // Determine which key contains the flight information
        if (jsonObject.containsKey("best_flights")) {
            // If "best_flights" key exists, get its value
            flightsArray = (JSONArray) jsonObject.get("best_flights");
        } else if (jsonObject.containsKey("other_flights")) {
            // If "best_flights" key doesn't exist, but "other_flights" key exists, get its value
            flightsArray = (JSONArray) jsonObject.get("other_flights");
        } else {
            // Handle the case where neither "best_flights" nor "other_flights" exist. Returns error message.
            System.out.println("ERROR: No flights found.\n \nThere are NO flights on that date between the specified Airports.");
            return null;
        }

        // Create a instance 2D Array
        String[][] flights = new String[flightsArray.size()][8]; 

        // Iterate over each flight in the flights array
        for (int i = 0; i < flightsArray.size(); i++) {
            JSONObject flightObj = (JSONObject) flightsArray.get(i);

            JSONObject flightDetails = (JSONObject) ((JSONArray) flightObj.get("flights")).get(0);

            // Extract relevant flight details
            String airline = (String) ((JSONObject) ((JSONArray) flightObj.get("flights")).get(0)).get("airline");
            String flightNumber = (String) ((JSONObject) ((JSONArray) flightObj.get("flights")).get(0)).get("flight_number");
            String airplane = (String) ((JSONObject) ((JSONArray) flightObj.get("flights")).get(0)).get("airplane");
            String travelClass = (String) ((JSONObject) ((JSONArray) flightObj.get("flights")).get(0)).get("travel_class");
            //All these values have to be in long
        
            //Sometimes price is unavaliable. This code handles it accordingly and sets it as 0.
            Object priceObj = flightObj.get("price");
            long price = 0;
            if (priceObj != null) {
                price = (long) priceObj;
            } 
            long duration = (long) ((JSONObject) ((JSONArray) flightObj.get("flights")).get(0)).get("duration");
            JSONObject departureAirport = (JSONObject) flightDetails.get("departure_airport");
            JSONObject arrivalAirport = (JSONObject) flightDetails.get("arrival_airport");
            JSONArray extensionsArray = (JSONArray) flightDetails.get("extensions");

            // uses formatExtensions to format the extensions array into a string
            String extensionsString = formatExtensions(extensionsArray);
    
            flights[i][0] = airline;
            flights[i][1] = flightNumber;
            flights[i][2] = airplane;
            flights[i][3] = travelClass;
            flights[i][4] = "$" + String.valueOf(price) + " CAD";
            flights[i][5] = String.valueOf(duration) + "min";
            // uses formatAirport to format the airport information into a string
            flights[i][6] = formatAirport(departureAirport) + " to " + formatAirport(arrivalAirport);
            flights[i][7] = extensionsString;
        }

        return flights;
    }
}

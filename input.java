import java.util.*;

public class input {

  //Create an empty HashMap
  private Map<String, String> parameter;

  /**
   * This method collects user input for flight search parameters and stores them in a HashMap.
   * Postcondition: Returns a HashMap containing flight parameters provided by the user.
   * 
   * @return HashMap containing flight parameters
  */
  public Map<String, String> userInput(){
    Scanner input = new Scanner(System.in);

    //Uses HashMap to store items in "key/value" pairs, and can access them by an index of another type
    this.parameter = new HashMap<>();
    parameter.put("engine", "google_flights");

    System.out.println("---");

    System.out.print("Use: https://www.iata.org/en/publications/directories/code-search to find the 3-letter location code\n \nEnter your departure location: ");
    String departureLocation = input.nextLine();
    parameter.put("departure_id", departureLocation);
    System.out.println("---");
    System.out.print("Use: https://www.iata.org/en/publications/directories/code-search to find the 3-letter location code\n \nEnter your arrival location: ");
    String arrivalLocation = input.nextLine();
    parameter.put("arrival_id", arrivalLocation);
    System.out.println("---");
    System.out.print("Enter your preferred type of flight\n1 - Round trip\n2 - One way\n \nEnter your Response: ");
    String type = input.nextLine();
    parameter.put("type", type);
    System.out.println("---");
    System.out.print("Enter your outbound date (YYYY-MM-DD): ");
    String outbound = input.nextLine();
    parameter.put("outbound_date", outbound);

    //If type is 1, which indicates this is a round trip, it asks for a return date to search for the flights
    if (type.equals("1")) {
        System.out.println("---");
        System.out.print("Enter your return date (YYYY-MM-DD): ");
        String inbound = input.nextLine();
        parameter.put("return_date", inbound);
    }

    System.out.println("---");
    System.out.print("Enter your travel class\n1 - Economy\n2 - Premium economy\n3 - Business\n4 - First class\n \nEnter your Response: ");
    String travelClass = input.nextLine();
    parameter.put("travel_class", travelClass);

    System.out.println("---");
    System.out.print("Enter number of adults: ");
    String adults = input.nextLine();
    parameter.put("adults", adults);

    System.out.println("---");
    System.out.print("Enter number of chldren: ");
    String children = input.nextLine();
    parameter.put("children", children);

    //Defines the currency as Canadian Dollars
    parameter.put("currency", "CAD");
    //Defines the language to use for the Google Flights search
    parameter.put("hl", "en");
    //Replace "Secret API Key" with your API key from https://serpapi.com/google-flights-api
    parameter.put("api_key", "REMOVED_API_KEY");
    //returns the hash map
    return parameter;
  }    
  

  /**
  * Mutator method to change the value of a parameter identified by the specified key.
  * Precondition: A valid key that exists in the parameter map. If the key does not exist, no action will be taken.
  * Postcondition: The corresponding value to a key will be replaced with the new value
  *
  * @param key   the key of the parameter to set
  * @param value the new value to set for the parameter
  */
  public void setParameter(String key, String value) {
    parameter.replace(key, value);
  }

  
}

import java.util.*;
import java.net.*;
import java.io.*;
//NOTE: THIS PROGRAM REQUIRES JSON-SIMPLE PACKAGE
import org.json.simple.parser.ParseException;


public class MyProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Create an instance of the 'input' class to get user input (see input.java). Recieves a hashmap of all inputs.
        input userInput = new input();
        Map<String, String> parameter = userInput.userInput();

        //Ask the user if there were any mistakes when inputting the code
        boolean hasMistakes = true;
        while (hasMistakes) {
            System.out.println("---");

            System.out.print("Do you want to make any changes (yes/no)?: ");
            String changeResponse = input.nextLine();
        
            if (changeResponse.equalsIgnoreCase("yes")) {
                // Prompt user for the parameter to change
                System.out.println("---");
                System.out.print("\nEnter the parameter you want to change in the specific format\n \nValid changes: departure_id, arrival_id, type, outbound_date, " + (parameter.get("type").equals("1")? "return_date, " : "") + "travel_class, adults, children\n \nYour Response: ");
                String paramToChange = input.nextLine();

                // Prompt user for the new value
                System.out.print("\nEnter the new value for " + paramToChange + ": ");
                String newValue = input.nextLine();
        
                // Use mutator method to update the parameter
                userInput.setParameter(paramToChange, newValue);
        
                hasMistakes = true;
            } else {
                // If no mistakes, exit the loop
                hasMistakes = false;
            } 
       }
        
        // Construct the URL for the API request using user input
        String urlString = "https://serpapi.com/search.json?" +
        "engine=google_flights" +
        "&departure_id="+ parameter.get("departure_id") +
        "&arrival_id=" + parameter.get("arrival_id") +
        "&type=" + parameter.get("type") +
        "&outbound_date="+ parameter.get("outbound_date") +
        // Conditionally adds return date if the type is a round trip (if statement)
        (parameter.get("type").equals("1") ? "&return_date=" + parameter.get("return_date") : "") + 
        "&travel_class=" + parameter.get("travel_class") +
        "&adults=" + parameter.get("adults")+
        "&children=" + parameter.get("children")+ 
        "&stops=1" +
        "&currency=CAD" +
        "&hl=en" +
        "&api_key=REMOVED_API_KEY";   

        
        //Source for GET: https://www.baeldung.com/java-http-request
        try {
            // Create a URL object
            URL url = new URL(urlString);
            // Open a connection to the URL
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // Set request method to GET
            con.setRequestMethod("GET");

            // Gets the status code from an HTTP response message.
            int responseCode = con.getResponseCode();

            // Check if response code is OK
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // This is code to print the JSON API response for error checking: Not being used in the code.
                // System.out.println();
                // System.out.println("--- API JSON Response ---");
                // System.out.println(response.toString());

                // Handle the JSON response with Try Catch block
                try{
                    String jsonResponse = response.toString();
                    String[][] flights = FlightSearch.extractFlights(jsonResponse);
            
                    //If there are no flights, break the code and exit.
                    if (flights == null){
                        return;
                    }

                    //Prints the typical price range
                    String typicalPriceRange = priceRange.getPriceRange(jsonResponse);
                    System.out.println("\n________Typical Price Range________\n");
                    System.out.println(typicalPriceRange);

                    //Prints information about the cheapest price of a flight route right now
                    cheapestFlight cheapest = new cheapestFlight();
                    cheapest.CheapestFlight(flights);
                    //Creates a new class of cheapPrint, and print the results
                    cheapestFlightPrint cheapPrint = new cheapestFlightPrint();
                    cheapPrint.printCheapestFlight(cheapest);    

                    //Used to print the top flight choices
                    System.out.println("___");
                    System.out.print("Do you want to print the top flight choices (yes/no)?: ");
                    String printResponse = input.nextLine();
                    // If yes, prints the top three
                    if (printResponse.equalsIgnoreCase("yes")) {
                        topThreeFlights.topThree(flights);
                    } else {
                        // If not, uses the same method but overloads so nothing can be done.
                        topThreeFlights.topThree();
                    } 
                        
                } catch (ParseException e) {
                    // Handle parsing exception
                    e.printStackTrace();
                }
            //Catch any errors from GET
            } else {
                System.out.println("Failed to retrieve data. Please ensure you inputted the correct parameters.\n \nResponse code: " + responseCode);
            }
        //Catch any errors from GET
        } catch (IOException e) {
            e.printStackTrace();
        } 
        input.close();
    } 
}
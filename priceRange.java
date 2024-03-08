import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class priceRange {
    /**
     * From the JSON response of the API, gives price range information from said response.
     * Precondition: a valid JSON response string as input. The JSON response should contain the needed structure with "price_insights", "lowest_price", "price_level", and "typical_price_range" array.
     * Postcondition: returns a formatted string containing price range information extracted from the API response.
     * 
     * @param jsonResponse is the JSON response string from the API, passed into the method
     * @return String containing price range information in a String already formatted
     * @throws ParseException If there is an error parsing the JSON response
    */
    public static String getPriceRange(String jsonResponse) throws ParseException {
        // Create a JSON parser
        JSONParser parser = new JSONParser();
        // Parse the JSON response string into a JSON Object
        JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);

        // Extract the relevant section from JSON object
        JSONObject priceInsights = (JSONObject) jsonObject.get("price_insights");

        // Get the lowest price
        long lowestPrice = (long) priceInsights.get("lowest_price");
        // Get the price level (low, medium, high)
        String priceLevel = (String) priceInsights.get("price_level");
        // Get the specific typical price range (array)
        JSONArray typicalPriceRange = (JSONArray) priceInsights.get("typical_price_range");
        //Get the highs and lows of the typical price range
        long typicalPriceMin = (long) typicalPriceRange.get(0);
        long typicalPriceMax = (long) typicalPriceRange.get(1);

        // Construct the response string containing price range information
        String PriceString = "Current Lowest Price: $" + lowestPrice + "CAD\nTypical Price Range: $" + typicalPriceMin + "CAD - $" + typicalPriceMax + "CAD\n \nPrices are currently " + priceLevel + " compared to usual";
       
        return PriceString;
    }
}

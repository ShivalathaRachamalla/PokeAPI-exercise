import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

public class HttpRequest {
    private static HttpRequest httpRequest = null;
    JSONParser jsonParser = new JSONParser();

    /*
     * Returns the Singleton object of HttpRequest class
     */
    static HttpRequest getInstance() {
        if (httpRequest == null){
            httpRequest = new HttpRequest();
        }
        return httpRequest;
    }

    /*
     * Method to connect to given web Url to get Json data
     */
    String makeHttpRequest(String urlString) {
        String response = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNext()) {
                response = sc.nextLine();
            }
        }catch (FileNotFoundException e) {

        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return response;
    }

    /*
     * get the details of Pokemon
     */
    void fetchPokemonInfo(String name){

        try {
            String urlString = "https://pokeapi.co/api/v2/pokemon/" + name + "/";
            String data = this.makeHttpRequest(urlString);
            if (data == null) {
                System.out.println("Sorry.. No such pokemon available");
            } else {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
                System.out.println("-----Pokemon details for the name: "+name+"-----");
                System.out.println("Name: "+name);
                System.out.println("Id: "+jsonObject.get("id"));
                System.out.println("Weight: "+jsonObject.get("weight"));
                System.out.println("Height: "+jsonObject.get("height"));
                System.out.println("----------");
            }
        } catch (Exception  e) {

        }
    }

    /*
     * get the details of language
     */
    void fetchLanguages(String location) {
        try {
            String urlString = "https://pokeapi.co/api/v2/location/"+location+"/";
            String data = this.makeHttpRequest(urlString);
            if (data == null) {
                System.out.println("Sorry , no such location available");
            } else {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
                JSONArray names = (JSONArray) jsonObject.get("names");
                System.out.println("-----Below are the languages available in given location: "+location+"-----");
                for (Object o : names) {
                    JSONObject name = (JSONObject) o;
                    String language = ((JSONObject)name.get("language")).get("name").toString();
                    System.out.println(language);
                }
                System.out.println("----------");
            }
        } catch (Exception e) {

        }
    }
}

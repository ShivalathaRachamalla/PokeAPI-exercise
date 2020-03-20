/* This class displays User options
and deligates requests to HttpRequest class
 */
import java.util.Scanner;
public class UserInterface {
    static Scanner sc = new Scanner(System.in);
    /*
     * Process the user input deligates request to appropriate methods
     */
    void displayOptions(){
        while (true){
            try {
                this.printOptions();
                int option = this.readInputInt();

                switch (option){
                    case 1:
                        System.out.println("Enter the name of Pokemon");
                        String name = this.readInputString();
                        HttpRequest.getInstance().fetchPokemonInfo(name);
                        break;
                    case 2:
                        System.out.println("Enter the name of the location");
                        String location = this.readInputString();
                        HttpRequest.getInstance().fetchLanguages(location);
                        break;
                    case 3:
                        System.out.println("Exiting, Welcome back!");
                        System.exit(0);
                    default:
                        System.out.println("Please enter correct option");
                }
            } catch (Exception e){
               System.out.println(e.toString());
            }
        }
    }

    /*
     * Displaying options to the user
     */
    private void printOptions() {
        System.out.println("[1] Enter the name of Pokemon to get details" );
        System.out.println("[2] Enter the name of location");
        System.out.println("[3] Exit");
        System.out.println("Please enter your option");

    }

    /*
     *  Read input from user as integer
     */
    int readInputInt() {
        int n = 0;
        try {
            n = Integer.parseInt(sc.next());
        } catch(Exception e) {
            System.out.print(e.toString());
            throw e;
        }
        return n;
    }

    /*
     * Read input from user as string
     */
    String readInputString() throws Exception{
        String path = null;
        Exception e1 = null;
        try {
            //sc.nextLine(); // Consuming the \n character
            path = sc.next();
            Integer.parseInt(path);
        } catch(Exception e) {
            e1 = e;
        } finally {
            if (e1 == null) {
                throw e1;
            }
        }
        return path;
    }
}

/*
 * This is the main class of Pokemon Application
 */
public class PokemonApi {
    public static void main(String[] args){
        System.out.println("Welcome to Pokemon Application");
        UserInterface userInterface = new UserInterface();
        userInterface.displayOptions();
    }
}

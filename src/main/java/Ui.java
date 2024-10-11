import java.util.Scanner;

/**
 * Handles the user interaction, reads input and prints outputs in the AnBot application
 */
public class Ui {

    private static final String WELCOME_MESSAGE = "\t Hello! Welcome to "; 
    private static final String ASKING_MESSAGE  = "\t What can I do for you? "; 
    private static final String EXIT_MESSAGE = "\t Bye. Hope to see you again soon!"; 
    private static final String SEPARATOR = "\t ___________________________"; 
    private static final String LOGO = "AnBot"; 

    private Scanner scanner; 

    /**
     * Constructs a Ui object that initializes the scanner for reading user input
     */
    public Ui() {
        this.scanner = new Scanner(System.in);  
    }

    /**  
     * Print the welcome message 
     */
    public void printWelcome() {
        System.out.println(SEPARATOR);
        System.out.println(WELCOME_MESSAGE + LOGO);
        System.out.println(ASKING_MESSAGE);
        System.out.println(SEPARATOR);
    }

    /**  
     * Print the exit message. 
     */
    public void printExit() {
        System.out.println(SEPARATOR);
        System.out.println(EXIT_MESSAGE); 
        System.out.println(SEPARATOR);
    }

    /**  
     * Print the error message. 
     * 
     * @param message The specified message for that error. 
     */
    public void printError(String message) {
        System.out.println("ERROR: " + message); 
    }

    /**
     * Reads user's input and returns it as a trimmed string
     * 
     * @return The user's input as a trimmed string.
     */
    public String readInput() {
        return scanner.nextLine().trim(); 
    }
}

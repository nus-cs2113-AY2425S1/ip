package amy.ui;
import java.util.Scanner;
/**
 * Represents the user interface of the chatbot.
 * The Ui class is responsible for displaying messages to the user and reading user input.
 */
public class Ui {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Print the response of the chatbot.
     * @param text The response of the chatbot.
     */
    public static void print(String text){
        System.out.println(text);
        printFiller();
    }

    /**
     * Print a line of filler to separate different sections of the chatbot.
     */
    public static void printFiller(){
        System.out.println("____________________________________________________________");
    }

    /**
     * Display the start screen of the chatbot.
     */
    public static void printGreeting(){
        String logo =    "     /   | |  \\/  |\\ \\ / /\n"
                    +    "    / /| | | \\  / | \\ V / \n"
                    +    "   / /_| | | |\\/| |  \\ /\n"     
                    +    "  /  __  | | |  | |  | |\n"
                    +    " /_/   |_| |_|  |_|  |_|  ";
        System.out.println(logo);
        String greet = """
                       Hello! I'm Amy ~ 
                       What can I do for you? (^v^) 
                       """;
        System.out.println(greet);
        printFiller();
    }

    /**
     * Display the end screen of the chatbot.
     */
    public static void printBye(){
        String bye = "Bye. Hope to see you again soon! (^////^)\n";
        System.out.println(bye);
        printFiller();
    }

    /**
     * Read the command input by the user.
     * @return The command input by the user.
     */
    public static String readCommand(){
        String command = scanner.nextLine();
        System.out.println("");
        return command;        
    }

    /**
     * Display the error message when the chatbot is unable to load tasks.
     */
    public static void showLoadingError(){
        String text = "Unable to load tasks due to some reason T_T";
        print(text);
    }

}

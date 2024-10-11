package ui;
import java.util.Scanner;

public class Ui {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Print the response of the chatbot.
     * @param text The response of the chatbot.
     */
    public static void print(String text){
        System.out.println(text);
    }

    public static void printFiller(){
        System.out.println("____________________________________________________________");
    }

    /**
     * Display the start screen of the chatbot.
     */
    public static void printGreeting(){
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
    public static String readCommand(){
        return scanner.nextLine();
    }

    public static void showLoadingError(){
        String text = "Unable to load tasks due to some reason T_T";
        print(text);
    }

}

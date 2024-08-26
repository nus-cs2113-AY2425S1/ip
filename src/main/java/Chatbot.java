import java.util.Scanner;

public class Chatbot {
    private Scanner in;

    // Constructor for chatbot
    public Chatbot() {
        this.in = new Scanner(System.in);
    }

    // Starting Chatbot
    public void start() {
        printWelcomeMessage();
        printByeMessage();
    }

    // Welcome Screen
    private void printWelcomeMessage() {
        System.out.println("_________________________________________________________");
        System.out.println("Hello! I'm Uranus, the only Chatbot you'll ever need.");
        System.out.println("How can I be of service?");
        System.out.println("_________________________________________________________");
    }

    // Goodbye Screen
    private void printByeMessage() {
        System.out.println("Bye. Hope to see you again!");
        System.out.println("_________________________________________________________");
    }

}

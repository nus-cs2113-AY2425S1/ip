import java.util.Scanner;

public class Chatbot {
    private static final Scanner in = new Scanner(System.in);

    // Constructor for chatbot
    public Chatbot() {
    }

    // Starting Chatbot
    public void start() {
        printWelcomeMessage();
        Task.taskmaster();
    }

    // Welcome Screen
    public static void printWelcomeMessage() {
        System.out.println("_________________________________________________________");
        System.out.println("Hello! I'm Uranus, the only Chatbot you'll ever need.");
        System.out.println("How can I be of service?");
        System.out.println("_________________________________________________________");
    }

    // Goodbye Screen
    public static void printByeMessage() {
        System.out.println("_________________________________________________________");
        System.out.println("Bye. Hope to see you again!");
        System.out.println("_________________________________________________________");
    }

    // Echo
    private void echo(){
        while(true){
            String input = in.nextLine();
            if(input.equals("bye")){
                printByeMessage();
                break;
            }
            System.out.println("_________________________________________________________");
            System.out.println(input);
            System.out.println("_________________________________________________________");
        }
    }

}

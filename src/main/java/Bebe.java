import java.util.Scanner;

public class Bebe {

    public static void main(String[] args) {
        // main function to run the chatbot
        welcomeMessage();
        runChatbot();
        exitMessage();
    }

    private static void welcomeMessage(){
        //Function is used to print out the welcome message
        System.out.println("Hello! I'm Bebe");
        System.out.println("What can I do for you?");
    }

    private static void exitMessage(){
        //Function is used to print out exit message
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void runChatbot() {
        //Function is used to execute the chatbot functions
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            echoInput(userInput);
        }

        scanner.close();
    }
    
    private static void echoInput(String input) {
        // Function is used to echo the user input
        System.out.println(input);
    }
}

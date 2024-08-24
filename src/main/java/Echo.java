import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        // Utilise Scanner object to allow user input
        Scanner scanner = new Scanner(System.in);

        // Define the greeting message and the chatbot name as variables
        String chatbotName = "Echo";
        String greetingMessage = "Hello! I'm " + chatbotName + "\nWhat can I do for you?\n";

        // Define the exit message as variable
        String exitMessage = "Bye. Hope to see you again soon!";

        // Print the greeting message from Echo
        System.out.println("____________________________________________________________");
        System.out.print(greetingMessage);
        System.out.println("____________________________________________________________");

        String userInput;
        do{
            // Read user input
            userInput = scanner.nextLine();

            // Print user input
            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
        }
        while (!userInput.equalsIgnoreCase("bye"));

        // Print the exit message from Echo
        System.out.println(exitMessage);
        System.out.println("____________________________________________________________");
    }
}

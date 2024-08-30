import java.util.Scanner;

public class Main {
    /**
     * The entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Create an instance of the Niwa chatbot
        Niwa chatBot = new Niwa();

        // Continue running the chatbot as long as it is active
        // Retrieve and process the next command from the user
        Scanner scanner = new Scanner(System.in);
        while (chatBot.getRunning() && scanner.hasNextLine()) {
            String command = scanner.nextLine();
            // Process the input
            chatBot.processCommand(command);
        }
    }
}
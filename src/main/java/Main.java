import niwa.Niwa;

/**
 * The entry point of the Niwa chatbot.
 */
public class Main {
    /**
     * The entry point of the program
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Create an instance of the Niwa chatbot
        Niwa chatBot = new Niwa();

        // Start the chatbot
        chatBot.run();
    }
}

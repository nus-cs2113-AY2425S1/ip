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
        while (chatBot.getRunning()) {
            // Retrieve and process the next command from the user
            String command = chatBot.getCommand();
            chatBot.processCommand(command);
        }
    }
}
/**
 * The Quag class contains the main method which is the entry point for the application.
 * It manages the application's main loop, where user input is read, commands are parsed,
 * and the task list is displayed and updated accordingly.
 */
public class Quag {

    /**
     * The main method initializes the UI, loads tasks from the storage, and continuously
     * prompts the user for commands to update and manipulate the task list until an
     * exit command is given.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        UI ui = new UI();
        ui.showWelcome();
        Storage.loadFromFile();
        TaskList.displayList();
        String userInput;

        while (true) {

            userInput = ui.readCommand();

            // Parse user input to execute command
            boolean shouldContinue = Parser.parse(userInput);

            if (!shouldContinue) {
                break;
            }
        }
    }
}
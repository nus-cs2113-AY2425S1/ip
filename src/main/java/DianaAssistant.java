import java.io.IOException;

/**
 * The main entry point for the Diana task management assistant.
 *
 * This class handles the primary interaction with the user, including:
 * - Printing welcome and goodbye messages
 * - Reading user input
 * - Parsing and executing user commands
 * - Managing task list persistence
 */
public class DianaAssistant {
    private TaskList tasklist;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for DianaAssistant.
     * Initializes the task list and UI components.
     * Attempts to load tasks from storage, and if unsuccessful,
     * initializes an empty task list.
     *
     * @throws IOException if there is an error during task loading.
     */
    public DianaAssistant() throws IOException {
        ui = new Ui();
        tasklist = new TaskList();

        try {
            tasklist = Storage.loadTasks();  // Load tasks from file
            parser = new Parser(tasklist);   // Initialize the parser with tasks
        } catch (IOException e) {
            // If loading fails, show error message and use an empty task list
            ui.showLoadingError();
            tasklist = new TaskList();       // Initialize an empty task list
            parser = new Parser(tasklist);   // Initialize parser with empty task list
        }
    }

    /**
     * Starts interaction with the user.
     * This method continuously reads user input, processes commands,
     * and saves tasks upon exit.
     */
    public void interact() {
        ui.printWelcomeMessage();  // Show welcome message

        String input;
        while (true) {
            input = ui.readInput();  // Read user input
            if ("bye".equals(input)) {  // Exit condition
                try {
                    Storage.saveTasks(tasklist);  // Save tasks before exit
                } catch (IOException e) {
                    System.out.println("Saving tasks failed: " + e.getMessage());
                }
                break;  // Exit the loop when "bye" is entered
            }
            parser.determineCommand(input);  // Process the command
        }

        ui.printGoodByeMessage();  // Show goodbye message
        ui.closeScanner();  // Close the input scanner
    }
}

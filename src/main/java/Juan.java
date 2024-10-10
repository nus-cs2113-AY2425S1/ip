import taskpackage.TaskList;

/**
 * Main class for the Juan task manager.
 * This class handles the initialization of the system, including user interface, storage, and task list management.
 */
public class Juan {

    private final static String dataFilePath = "data.text";
    private final UI ui;
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;

    /**
     * Constructor for Juan.
     * Initializes the user interface, storage, and task list.
     *
     * @param dataFilePath Path to the file for storing task data.
     */
    public Juan(String dataFilePath) {
        ui = new UI();
        storage = new Storage(dataFilePath, ui);
        try {
            tasks = storage.readData();
        } catch (Exception e) {
            ui.porFavorMessage(e.getMessage());
        }
        parser = new Parser(ui, tasks);
    }

    /**
     * Main running function of Juan.
     * Displays welcome message, handles user input, and writes task data to the file upon termination.
     */
    public void run() {
        ui.helloMessage(); // Display welcome message

        boolean continueChatting = true;
        while (continueChatting) {
            continueChatting = parser.chatFeature(ui.readUserInput());
        }

        storage.writeDate(parser.getTasks()); // Write task data to file
        ui.lineMessage();
        ui.byeMessage(); // Display goodbye message
    }

    /**
     * Main method to start the Juan application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Juan(dataFilePath).run();
    }
}

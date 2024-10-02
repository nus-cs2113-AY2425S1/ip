package blossom;

/**
 * The <code>Blossom</code> class serves as the main class for the Blossom chatbot.
 * It initialises the user interface, list of tasks, and storage components, and
 * starts the application.
 */

public class Blossom {
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;

    /**
     * Constructs a new Blossom instance with specified storage file path.
     * Initialises the Ui TaskList, and Storage components and attempts to load
     * existing tasks from the text file.
     * If loading fails, initialises a new empty TaskList.
     *
     * @param filePath the path to the text file where tasks are stored
     * @throws RuntimeException if there is a problem initialising the storage and tasks
     */
    Blossom(String filePath) {
        ui = new Ui();
        tasks = new TaskList(ui);
        storage = new Storage(filePath, tasks.getTasks());

        ui.printIntro();
        try {
            storage.loadTasks();
        } catch (BlossomException e) {
            tasks = new TaskList(ui);
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts Blossom. Initialises the parser and begins the interaction loop.
     */
    public void run() {
        ui.printIntro();
        Parser parser = new Parser(ui, tasks, storage);
        parser.start();
    }
    public static void main(String[] args) {
        new Blossom("./data/blossom.txt").run();
    }
}

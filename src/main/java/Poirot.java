/**
 * The main class for the Poirot task management application.
 * This class initializes the application, loads saved tasks, and runs the main command loop.
 */
public class Poirot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Poirot application instance.
     * Initializes the UI, storage, and task list, and loads any previously saved tasks.
     *
     * @param filePath The file path for storing and loading tasks.
     */
    public Poirot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();

        Task[] loadedTasks = storage.loadTasksFromFile();
        for (Task task : loadedTasks) {
            if (task != null) {
                tasks.add(task);
            }
        }
    }

    /**
     * Runs the main command loop of the application.
     * Continuously reads user input, executes commands, and displays results until an exit command is given.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (PoirotException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point for the Poirot application.
     * Creates a new Poirot instance and starts running it.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Poirot("./data/duke.txt").run();
    }
}

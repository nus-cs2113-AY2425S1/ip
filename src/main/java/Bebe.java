/**
 * Main class representing the Bebe application that runs the task manager.
 * It initializes the necessary components such as UI, storage, and task list.
 */
public class Bebe {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Bebe instance with the specified file path for task storage.
     *
     * @param filePath The file path where tasks are saved and loaded from.
     */
    public Bebe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BebeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Bebe application and enters the main command loop.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BebeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method to start the Bebe application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Bebe("data/tasks.txt").run();
    }
}

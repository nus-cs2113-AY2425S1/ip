package main.java;

/**
 * The main class for KenChat application, managing the UI, task storage and command execution.
 */
public class KenChat {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initialises the KenChat application with the specific file path for storage.
     *
     * @param filePath The file path where task list is stored.
     */
    public KenChat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (KenChatException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    /**
     * Runs the KenChat application, processing user commands until exit is prompted.
     */
    public void run() {
        ui.showStart();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isRunning = command.isRunning();
            } catch (KenChatException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showEnd();
    }

    /**
     * The main entry point of the KenChat application.
     *
     * @param args Command line arguments (not used).
     * @throws KenChatException If there is an error during initialization.
     */
    public static void main(String[] args) throws KenChatException {
        new KenChat("data/KenChat.txt").run();
    }
}
public class AirBorder {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public AirBorder(String filePath) {
        // Initialize the UI, Storage, and TaskList components
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            // Load existing tasks from storage
            tasks = new TaskList(storage.load());
        } catch (AirBorderException e) {
            // If an error occurs, show loading error and start with an empty task list
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        // Display the welcome message
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                // Read the user's input command
                String fullCommand = ui.readCommand();
                ui.showLine();
                // Parse and execute the command
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                // Check if the command signals an exit
                isExit = command.isExit();
            } catch (AirBorderException e) {
                // Display any errors to the user
                ui.showError(e.getMessage());
            } finally {
                // Display a divider line for readability
                ui.showLine();
            }
        }
        // Close the UI resources
        ui.close();
    }

    public static void main(String[] args) {
        // Start the application with the specified file path for storage
        new AirBorder("airborder.txt").run();
    }
}

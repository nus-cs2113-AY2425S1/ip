/**
 * The CodyChen class is the main application that manages the task list.
 * It initializes the user interface, storage, and task list, and runs
 * the main application loop to handle user commands.
 */
public class CodyChen {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a CodyChen object with the specified file path for storage.
     * It initializes the user interface and loads tasks from the storage.
     *
     * @param filePath The file path to load tasks from.
     */
    public CodyChen(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    /**
     * Runs the main application loop, displaying a welcome message,
     * reading user commands, parsing them, and executing the corresponding actions.
     * The loop continues until the user decides to exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            try{
                c.execute(tasks, ui, storage);
            } catch (NullPointerException e){
                System.out.println("Please re-enter a value ");
            }
            if (fullCommand.contains("exit")) {
                isExit = true;
            }
        }
    }
    /**
     * The entry point of the application.
     * It creates a new instance of CodyChen and starts the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new CodyChen("./harddisk.txt").run();
    }
}
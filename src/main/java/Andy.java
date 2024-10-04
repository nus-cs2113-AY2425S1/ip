import java.util.List;

/**
 * Main class for the Andy task manager application. It handles user input,
 * manages tasks, and interacts with storage, UI, and other components.
 */
public class Andy {
    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for Andy. Initializes UI, Storage, TaskList, and Parser.
     *
     * @param filePath the file path where the tasks are stored.
     */
    public Andy(String filePath) {
        ui = new UI();  // Initialize UI
        storage = new Storage(filePath);  // Initialize Storage with file path
        tasks = new TaskList(storage.loadTasks());  // Load tasks from storage
        parser = new Parser();  // Initialize Parser
    }

    /**
     * Starts the main loop of the Andy task manager.
     * Continuously processes user input until the user enters "bye".
     */
    public void run() {
        ui.showWelcomeMessage();  // Show the welcome message

        String input;
        // Main loop to process user input
        while (!(input = ui.getUserInput()).equals("bye")) {
            try {
                String command = parser.parseCommand(input);  // Get the command

                switch (command) {
                case "list":
                    ui.showTaskList(tasks);  // Display task list
                    break;
                case "todo":
                    String todoDescription = parser.parseTaskDescription(input, "todo");
                    tasks.addTask(new TodoTask(todoDescription));  // Add new todo task
                    ui.showTaskAddedMessage(todoDescription);
                    break;
                case "deadline":
                    String[] parts = parser.parseTaskDescription(input, "deadline").split(" /by ");
                    tasks.addTask(new DeadlineTask(parts[0], parts[1]));  // Add deadline task
                    ui.showTaskAddedMessage(parts[0]);
                    break;
                case "event":
                    parts = parser.parseTaskDescription(input, "event").split(" /from ");
                    String[] timeParts = parts[1].split(" /to ");
                    tasks.addTask(new EventTask(parts[0], timeParts[0], timeParts[1]));  // Add event task
                    ui.showTaskAddedMessage(parts[0]);
                    break;
                case "find":
                    String keyword = parser.parseTaskDescription(input, "find");
                    List<Task> foundTasks = tasks.findTasks(keyword);  // Find tasks by keyword
                    ui.showFoundTasks(foundTasks);  // Display matching tasks
                    break;
                case "delete":
                    int index = parser.parseTaskIndex(input);  // Parse the index to delete
                    Task deletedTask = tasks.getTask(index);  // Get the task to be deleted
                    tasks.deleteTask(index);  // Delete the task
                    ui.showTaskDeletedMessage(deletedTask.getDescription());  // Show confirmation
                    break;
                case "mark":
                    int markIndex = parser.parseTaskIndex(input);  // Parse the index to mark as done
                    tasks.getTask(markIndex).setDone(true);  // Mark the task as done
                    ui.showTaskMarkedAsDone(tasks.getTask(markIndex));  // Show confirmation
                    break;
                case "unmark":
                    int unmarkIndex = parser.parseTaskIndex(input);  // Parse the index to unmark
                    tasks.getTask(unmarkIndex).setDone(false);  // Unmark the task as not done
                    ui.showTaskUnmarked(tasks.getTask(unmarkIndex));  // Show confirmation
                    break;
                default:
                    throw new AndyException("Invalid command.");
                }

                // Save updated task list after each command
                storage.saveTasks(tasks.getTasks());

            } catch (AndyException e) {
                ui.showErrorMessage(e.getMessage());  // Handle any exceptions with an error message
            }
        }

        // Show goodbye message before exiting
        ui.showGoodbyeMessage();
    }

    /**
     * Main method to start the Andy task manager application.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Andy("./data/andy.txt").run();  // Run the application
    }
}
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
     */
    public Andy() {
        ui = new UI();  // Initialize UI
        storage = new Storage();  // Initialize Storage without file path
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
                case "list" -> handleList();
                case "todo" -> handleTodo(input);
                case "deadline" -> handleDeadline(input);
                case "event" -> handleEvent(input);
                case "find" -> handleFind(input);
                case "delete" -> handleDelete(input);
                case "mark" -> handleMark(input);
                case "unmark" -> handleUnmark(input);
                default -> throw new AndyException("Invalid command.");
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

    private void handleList() {
        ui.showTaskList(tasks);  // Display task list
    }

    private void handleTodo(String input) throws AndyException {
        String todoDescription = parser.parseTaskDescription(input, "todo");
        tasks.addTask(new TodoTask(todoDescription));  // Add new todo task
        ui.showTaskAddedMessage(todoDescription);
    }

    private void handleDeadline(String input) throws AndyException {
        String[] parts = parser.parseTaskDescription(input, "deadline").split(" /by ");
        tasks.addTask(new DeadlineTask(parts[0], parts[1]));  // Add deadline task
        ui.showTaskAddedMessage(parts[0]);
    }

    private void handleEvent(String input) throws AndyException {
        String[] parts = parser.parseTaskDescription(input, "event").split(" /from ");
        String[] timeParts = parts[1].split(" /to ");
        tasks.addTask(new EventTask(parts[0], timeParts[0], timeParts[1]));  // Add event task
        ui.showTaskAddedMessage(parts[0]);
    }

    private void handleFind(String input) throws AndyException {
        String keyword = parser.parseTaskDescription(input, "find");
        List<Task> foundTasks = tasks.findTasks(keyword);  // Find tasks by keyword
        ui.showFoundTasks(foundTasks);  // Display matching tasks
    }

    private void handleDelete(String input) throws AndyException {
        int deleteIndex = parser.parseTaskIndex(input);  // Parse the index to delete
        validateTaskIndex(deleteIndex);
        Task deletedTask = tasks.getTask(deleteIndex);  // Get the task to be deleted
        tasks.deleteTask(deleteIndex);  // Delete the task
        ui.showTaskDeletedMessage(deletedTask.getDescription());  // Show confirmation
    }

    private void handleMark(String input) throws AndyException {
        int markIndex = parser.parseTaskIndex(input);  // Parse the index to mark as done
        validateTaskIndex(markIndex);
        tasks.getTask(markIndex).setDone(true);  // Mark the task as done
        ui.showTaskMarkedAsDone(tasks.getTask(markIndex));  // Show confirmation
    }

    private void handleUnmark(String input) throws AndyException {
        int unmarkIndex = parser.parseTaskIndex(input);  // Parse the index to unmark
        validateTaskIndex(unmarkIndex);
        tasks.getTask(unmarkIndex).setDone(false);  // Unmark the task as not done
        ui.showTaskUnmarked(tasks.getTask(unmarkIndex));  // Show confirmation
    }

    private void validateTaskIndex(int index) throws AndyException {
        if (index >= tasks.getTasks().size() || index < 0) {
            throw new AndyException("Invalid task index.");
        }
    }

    /**
     * Main method to start the Andy task manager application.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Andy().run();  // Run the application without specifying file path
    }
}
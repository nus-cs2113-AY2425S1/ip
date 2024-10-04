public class Andy {
    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    // Constructor initializes UI, Storage, TaskList, and Parser
    public Andy(String filePath) {
        ui = new UI();  // Initialize UI
        storage = new Storage(filePath);  // Initialize Storage with file path
        tasks = new TaskList(storage.loadTasks());  // Load tasks from storage
        parser = new Parser();  // Initialize Parser
    }

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
                case "delete":
                    int index = parser.parseTaskIndex(input);  // Parse the index to delete
                    Task deletedTask = tasks.getTask(index);  // Get the task to be deleted
                    tasks.deleteTask(index);  // Delete the task
                    ui.showTaskDeletedMessage(deletedTask.getDescription());  // Show confirmation
                    break;
                default:
                    throw new AndyException("Invalid command.");
                }
                storage.saveTasks(tasks.getTasks());  // Save updated task list after each command
            } catch (AndyException e) {
                ui.showErrorMessage(e.getMessage());  // Handle any exceptions with an error message
            }
        }
        ui.showGoodbyeMessage();  // Show goodbye message before exiting
    }

    public static void main(String[] args) {
        new Andy("./data/andy.txt").run();  // Run the application
    }
}
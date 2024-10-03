public class CommandHandler {
    private TaskManager taskManager;

    public CommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Executes the specified command with the provided arguments
     * This method interprets the command and delegates the execution to the
     * appropriate methods in {@code TaskManager} performing actions such as
     * listing tasks, marking/unmarking tasks, adding new tasks (todo, deadline, event) etc.
     * If the command is unknown, it throws an {@code EvaException}.
     *
     * @param command The command to be executed
     * @param args An array of strings containing the arguments required for a command
     * @throws EvaException If the command is unknown
     */
    public void executeCommand(String command, String[] args) throws EvaException {
        switch (command) {
        case "list":
            taskManager.printTaskList();
            break;
        case "mark":
            taskManager.markTask(args[0]);
            break;
        case "unmark":
            taskManager.unmarkTask(args[0]);
            break;
        case "todo":
            taskManager.printTodo(args[0]);
            break;
        case "deadline":
            taskManager.printDeadline(args[0], args[1]);
            break;
        case "event":
            taskManager.printEvent(args[0], args[1], args[2]);
            break;
        case "delete":
            taskManager.deleteTask(args[0]);
            break;
        case "find":
            taskManager.findTasks(args[0]);
            break;
        case "bye":
            break;
        default:
            throw new EvaException("Unknown command: " + command);
        }
    }
}

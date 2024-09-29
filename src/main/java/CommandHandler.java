public class CommandHandler {
    private TaskManager taskManager;

    public CommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

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
        case "bye":
            // No specific action needed here, handled in Eva
            break;
        default:
            throw new EvaException("Unknown command: " + command);
        }
    }
}

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
            taskManager.addTodo(args[0]);
            break;
        case "deadline":
            taskManager.addDeadline(args[0], args[1]);
            break;
        case "event":
            taskManager.addEvent(args[0], args[1], args[2]);
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

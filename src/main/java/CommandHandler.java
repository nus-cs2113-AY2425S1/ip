public class CommandHandler {
    private TaskManager taskManager;
    private final int LINE_LENGTH = 50;

    public CommandHandler(TaskManager taskManager){
        this.taskManager = taskManager;
    }

    public void handleInput(String input){
        String[] inputArguments = input.split(" ", 2);
        String commandString = inputArguments[0];
        String argumentString = inputArguments.length > 1 ? inputArguments[1] : "";

        Command command = Command.parseString(commandString);

        printLine();
        handleCommand(command, argumentString);
        printLine();
    }

    private void printLine(){
        System.out.println("-".repeat(LINE_LENGTH));
    }

    private void handleCommand(Command command, String argumentString) {
        if (command == null) {
            handleInvalidCommand();
            return;
        }

        switch (command) {
            case LIST:
                handleListCommand();
                break;
            case MARK:
                handleTaskDoneUpdate(argumentString, true);
                break;
            case UNMARK:
                handleTaskDoneUpdate(argumentString, false);
                break;
            case TODO:
                handleTodoCommand(argumentString);
                break;
            case DEADLINE:
                handleDeadlineCommand(argumentString);
                break;
            case EVENT:
                handleEventCommand(argumentString);
                break;
        }
    }

    private void handleInvalidCommand(){
        throw new MedeaException("Unrecognized command. Please use a valid command.");
    }

    private void handleListCommand() {
        taskManager.listTasks();
    }

    private void handleTaskDoneUpdate(String taskIndex, boolean isDone) {
        int index = parseTaskIndex(taskIndex);
        int currentTaskIndex = taskManager.getCurrentTaskIndex();

        if (index < 0 || index >= currentTaskIndex) {
            String errorMsg = String.format("Invalid %s command. Please provide a valid task number.%n", isDone ? "mark":"unmark");
            throw new MedeaException(errorMsg);
        }

        taskManager.updateTaskDoneStatus(index, isDone);
    }

    private int parseTaskIndex(String taskIndex) {
        try {
            return Integer.parseInt(taskIndex.trim()) - 1;
        } catch (Error e) {
            return -1;
        }
    }

    private void handleTodoCommand(String description) {
        if (description.isEmpty()){
            throw new MedeaException("Invalid todo command. Please provide a task description.");
        }

        taskManager.addTodo(description);
    }

    private String[] parseArguments(String argumentString, String... delimiters) {
        String delimiterPattern = String.join("|", delimiters);
        return argumentString.split(delimiterPattern);
    }

    private void handleDeadlineCommand(String argumentString) {
        String[] arguments = parseArguments(argumentString, " /by ");

        if (arguments.length != 2) {
            throw new MedeaException("Invalid deadline command. Please provide a task description and a deadline using '/by'.");
        }

        String description = arguments[0];
        String deadlineDate = arguments[1];

        taskManager.addDeadline(description, deadlineDate);
    }

    private void handleEventCommand(String argumentString) {
        String[] arguments = parseArguments(argumentString, " /from ", " /to ");

        if (arguments.length != 3) {
            throw new MedeaException("Invalid event command. Please provide a task, start time, and end time using '/from' and '/to'.");
        }

        String description = arguments[0];
        String eventStart = arguments[1];
        String eventEnd = arguments[2];

        taskManager.addEvent(description, eventStart, eventEnd);
    }
}

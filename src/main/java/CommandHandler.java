public class CommandHandler {
    private final TaskManager taskManager;
    private final int LINE_LENGTH = 50;

    public CommandHandler(TaskManager taskManager){
        this.taskManager = taskManager;
    }

    public void handleInput(String input){
        String[] inputArguments = input.split(" ", 2);
        String commandString = inputArguments[0];
        String argumentString = inputArguments.length > 1 ? inputArguments[1] : "";

        Command command = Command.parseString(commandString);

        processCommand(command, argumentString);
    }

    private void processCommand(Command command, String argumentString) {
        if (command == null) {
            handleInvalidCommand();
            return;
        }

        printLine();

        switch (command) {
            case LIST:
                handleListCommand();
                break;
            case MARK:
                handleMarkCommand(argumentString);
                break;
            case UNMARK:
                handleUnmarkCommand(argumentString);
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

        printLine();
    }

    private void printLine(){
        System.out.println("-".repeat(LINE_LENGTH));
    }

    private void handleListCommand() {
        taskManager.listTasks();
    }

    private int parseTaskIndex(String taskIndex) {
        try {
            return Integer.parseInt(taskIndex.trim()) - 1;
        } catch (Error e) {
            return -1;
        }
    }

    private void handleMarkCommand(String taskIndex) {
        int markIndex = parseTaskIndex(taskIndex);

        if (markIndex == -1) {
            System.out.println("Invalid mark command. Please provide a valid task number.");
            return;
        }

        taskManager.markTask(markIndex);
    }

    private void handleUnmarkCommand(String taskIndex) {
        int unmarkIndex = parseTaskIndex(taskIndex);

        if (unmarkIndex == -1) {
            System.out.println("Invalid unmark command. Please provide a valid task number.");
            return;
        }

        taskManager.unmarkTask(unmarkIndex);
    }

    private void handleTodoCommand(String description) {
        if (description.isEmpty()){
            System.out.println("Invalid todo command. Please provide a task description.");
            return;
        }

        taskManager.addTodo(description);
    }

    private String[] parseArguments(String argumentString, String... delimiters) {
        String splitBy = String.join("|", delimiters);
        return argumentString.split(splitBy);
    }

    private void handleDeadlineCommand(String argumentString) {
        String[] arguments = parseArguments(argumentString, " /by ");

        if (arguments.length != 2) {
            System.out.println("Invalid deadline command. Please provide a task description and a deadline using '/by'.");
            return;
        }

        String description = arguments[0];
        String deadlineDate = arguments[1];

        taskManager.addDeadline(description, deadlineDate);
    }

    private void handleEventCommand(String argumentString) {
        String[] arguments = parseArguments(argumentString, " /from ", " /to ");

        if (arguments.length != 3) {
            System.out.println("Invalid event command. Please provide a task, start time, and end time using '/from' and '/to'.");
            return;
        }

        String description = arguments[0];
        String eventStart = arguments[1];
        String eventEnd = arguments[2];

        taskManager.addEvent(description, eventStart, eventEnd);
    }

    private void handleInvalidCommand(){
        System.out.println("I don't recognize that command.");
    }
}

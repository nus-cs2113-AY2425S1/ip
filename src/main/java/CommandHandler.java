public class CommandHandler {
    private final TaskList taskList;


    public CommandHandler(TaskList taskList){
        this.taskList = taskList;
    }

    public void handleInput(String input) {
        String[] inputArguments = input.split(" ", 2);
        String commandString = inputArguments[0];
        String argumentString = inputArguments.length > 1 ? inputArguments[1] : "";

        Command command = Command.parseString(commandString);

        if (command == null) {
            handleInvalidCommand();
            return;
        }

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
    }

    private void handleListCommand() {
        taskList.listTasks();
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

        taskList.markTask(markIndex);
    }

    private void handleUnmarkCommand(String taskIndex) {
        int unmarkIndex = parseTaskIndex(taskIndex);

        if (unmarkIndex == -1) {
            System.out.println("Invalid unmark command. Please provide a valid task number.");
            return;
        }

        taskList.unmarkTask(unmarkIndex);
    }

    private void handleTodoCommand(String description) {
        if (description.isEmpty()){
            System.out.println("Invalid todo command. Please provide a task description.");
            return;
        }

        taskList.addTodo(description);
    }

    private String[] parseArguments(String arguments, String... delimiters) {
        String splitBy = String.join("|", delimiters);
        return arguments.split(splitBy);
    }

    private void handleDeadlineCommand(String arguments) {
        String[] parts = parseArguments(arguments, " /by ");

        if (parts.length != 2) {
            System.out.println("Invalid deadline command. Please provide a task description and a deadline using '/by'.");
            return;
        }

        String description = parts[0];
        String deadlineDate = parts[1];

        taskList.addDeadline(description, deadlineDate);
    }

    private void handleEventCommand(String arguments) {
        String[] parts = parseArguments(arguments, " /from ", " /to ");

        if (parts.length != 3) {
            System.out.println("Invalid event command. Please provide a task, start time, and end time using '/from' and '/to'.");
            return;
        }

        String description = parts[0];
        String eventStart = parts[1];
        String eventEnd = parts[2];

        taskList.addEvent(description, eventStart, eventEnd);
    }

    private void handleInvalidCommand(){
        System.out.println("I don't recognize that command.");
    }
}

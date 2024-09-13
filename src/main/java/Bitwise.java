import java.util.Scanner;

public class Bitwise {

    private static Task[] tasksList = new Task[100];
    private static int numberOfTasks = 0;

    public static void main(String[] args) {
        OutputManager.printWelcomeMessage();
        mainManager();
        OutputManager.printExitMessage();
    }

    public static void mainManager() {
        String userInput;
        Status status = Status.RUNNING;
        while (status != Status.EXIT) {
            userInput = getUserInput();
            try {
                status = inputHandler(userInput);
            } catch (InvalidCommandException e) {
                OutputManager.printMessage("Invalid command: " + userInput);
                OutputManager.printListCommands();
            }
        }
    }

    public static Status inputHandler(String userInput) {
        if (userInput.equalsIgnoreCase(Constants.COMMAND_END)) {
            return Status.EXIT;
        }
        OutputManager.printLineBreak();
        if (userInput.equalsIgnoreCase(Constants.COMMAND_LIST)) {
            OutputManager.printTasksList(tasksList, numberOfTasks);
        }
        else if (userInput.startsWith(Constants.COMMAND_UNMARK)) {
            int taskNumber = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
            markCompletionStatus(taskNumber, false);
        }
        else if (userInput.startsWith(Constants.COMMAND_MARK)) {
            int taskNumber = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
            markCompletionStatus(taskNumber, true);
        }
        else {
            addToList(userInput);
        }
        OutputManager.printLineBreak();
        return Status.RUNNING;
    }

    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void echoUserInput() {
        String userInput;
        while (true) {
            userInput = getUserInput();
            if (userInput.equalsIgnoreCase(Constants.COMMAND_END)) {
                return;
            }
            OutputManager.printEchoInput(userInput);
        }
    }

    public static void addToList(String userInput) {
        Task newTask;
        String description;
        if (userInput.startsWith(Constants.COMMAND_TODO)) {
            description = userInput.substring(userInput.indexOf(" ") + 1);
            newTask = new Todo(description);
        }
        else if (userInput.startsWith(Constants.COMMAND_DEADLINE)) {
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(Constants.COMMAND_INFIX_BY));
            String deadline = userInput.substring(userInput.indexOf(Constants.COMMAND_INFIX_BY) + 4);
            newTask = new Deadline(description, deadline);
        }
        else if (userInput.startsWith(Constants.COMMAND_EVENT)) {
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(Constants.COMMAND_INFIX_FROM));
            String eventFrom = userInput.substring(userInput.indexOf(Constants.COMMAND_INFIX_FROM) + 6, userInput.indexOf(Constants.COMMAND_INFIX_TO));
            String eventTo = userInput.substring(userInput.indexOf(Constants.COMMAND_INFIX_TO) + 4);
            newTask = new Event(description, eventFrom, eventTo);
        }
        else {
            throw new InvalidCommandException(userInput);
        }
        tasksList[numberOfTasks] = newTask;
        numberOfTasks++;
        OutputManager.printMessageAddedTask(newTask.toString());
        OutputManager.printNumberOfTasks(numberOfTasks);
    }

    public static void markCompletionStatus(int taskNumber, boolean isCompleted) {
        int taskIndex = taskNumber - 1;
        tasksList[taskIndex].markCompletionStatus(isCompleted);
        String message = isCompleted ? Constants.MESSAGE_MARKED : Constants.MESSAGE_UNMARKED;
        OutputManager.printMessage(message);
        OutputManager.printTasksList(tasksList, numberOfTasks);
    }
}

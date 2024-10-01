import java.util.HashMap;
import java.util.Scanner;

public class Ui {
    public TaskList taskList;

    public Ui() {
        taskList = new TaskList();

        try {
            taskList.loadTaskFromFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean matchCommand(String command, HashMap<String, String> commandArguments) {
        final String goodByeMessage = "--------------------------------------------\n" +
                "Bye! Hope to see you again soon :)\n" +
                "--------------------------------------------";

        final String unrecognizedCommand = "--------------------------------------------\n" +
                "Unrecognized command!\n" +
                "--------------------------------------------\n";

        switch (command) {
            case "list":
                taskList.listTasks();
                break;

            case "mark":
                try {
                    taskList.markTaskAsDone(commandArguments);
                } catch (TulipTaskException.InvalidTaskIndexException e) {
                    System.out.println("--------------------------------------------");
                    System.out.printf("Invalid task index! Please input a number between 1 and %d \n", taskList.taskList.size());
                    System.out.println("--------------------------------------------");
                }
                break;

            case "unmark":
                try {
                    taskList.markTaskAsNotDone(commandArguments);
                } catch (TulipTaskException.InvalidTaskIndexException e) {
                    System.out.println("--------------------------------------------");
                    System.out.printf("Invalid task index! Please input a number between 1 and %d \n", taskList.taskList.size());
                    System.out.println("--------------------------------------------");
                }
                break;

            case "todo":
                try {
                    taskList.addToDo(commandArguments);
                } catch (TulipTaskException.InvalidTaskDescriptionException e) {
                    System.out.println("--------------------------------------------");
                    System.out.println("Task description was not given :(");
                    System.out.println("--------------------------------------------");
                }
                break;

            case "deadline":
                try {
                    taskList.addDeadline(commandArguments);
                } catch (TulipTaskException.InvalidTaskDescriptionException e) {
                    System.out.println("--------------------------------------------");
                    System.out.println("Task description was not given :(");
                    System.out.println("--------------------------------------------");
                } catch (TulipTaskException.InvalidDeadlineException e) {
                    System.out.println("--------------------------------------------");
                    System.out.println("Task deadline was not given, add a deadline by using /by to indicate task end date!");
                    System.out.println("--------------------------------------------");
                }
                break;

            case "event":
                try {
                    taskList.addEvent(commandArguments);
                } catch (TulipTaskException.InvalidTaskDescriptionException e) {
                    System.out.println("--------------------------------------------");
                    System.out.println("Task description was not given :(");
                    System.out.println("--------------------------------------------");
                } catch (TulipTaskException.InvalidStartDateException e) {
                    System.out.println("--------------------------------------------");
                    System.out.println("Event start date was not given, add a start date by using /from to indicate task start date!");
                    System.out.println("--------------------------------------------");
                } catch (TulipTaskException.InvalidEndDateException e) {
                    System.out.println("--------------------------------------------");
                    System.out.println("Event end date was not given, add a end date by using /to to indicate task end date!");
                    System.out.println("--------------------------------------------");
                }
                break;

            case "delete":
                taskList.deleteTask(commandArguments);
                break;

            case "save":
                taskList.saveTaskToFile();
                break;

            case "load":
                taskList.loadTaskFromFile();
                break;

            case "bye":
                taskList.saveTaskToFile();
                System.out.println(goodByeMessage);
                return false;

            default:
                System.out.println(unrecognizedCommand);
                break;
        }
        return true;
    }

    public void commandEntry() {
        HashMap<String, String> commandArguments;
        String input;
        Scanner scanner = new Scanner(System.in);

        boolean isAcceptingInput = true;

        while (isAcceptingInput) {
            input = scanner.nextLine();
            commandArguments = InputParser.parseCommands(input);
            String command = commandArguments.get(InputParser.COMMAND);

            try {
                isAcceptingInput = matchCommand(command, commandArguments);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void displayWelcome() {
        String logo = "                                         \n" +
                "--.--     |    o     --.--          |    \n" +
                "  |  .   .|    .,---.  |  ,---.,---.|__/ \n" +
                "  |  |   ||    ||   |  |  ,---|`---.|  \\ \n" +
                "  `  `---'`---'`|---'  `  `---^`---'`   `\n" +
                "                |";

        final String welcomeMessage = "--------------------------------------------\n" +
                "Hello, I'm TulipTask\n" +
                "What can I do for you today?\n" +
                "--------------------------------------------";

        System.out.println(logo);
        System.out.println(welcomeMessage);
    }

    public void run() {
        displayWelcome();
        commandEntry();
    }
}

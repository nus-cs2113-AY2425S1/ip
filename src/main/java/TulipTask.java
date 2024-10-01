import java.util.HashMap;
import java.util.Scanner;

public class TulipTask {
    public static void main(String[] args) throws TulipTaskException.InvalidTaskDescriptionException, TulipTaskException.InvalidEndDateException, TulipTaskException.InvalidStartDateException, TulipTaskException.InvalidDeadlineException, TulipTaskException.InvalidTaskIndexException {
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

        final String goodByeMessage = "--------------------------------------------\n" +
                "Bye! Hope to see you again soon :)\n" +
                "--------------------------------------------";

        final String unrecognizedCommand = "--------------------------------------------\n" +
                "Unrecognized command!\n" +
                "--------------------------------------------\n";

        HashMap<String, String> commandArguments;

        System.out.println(logo);
        System.out.println(welcomeMessage);

        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            commandArguments = InputParser.parseCommands(input);
            String command = commandArguments.get(InputParser.COMMAND);

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

                case "find":
                    taskList.findTask(commandArguments);
                    break;

                case "bye":
                    System.out.println(goodByeMessage);
                    return;

                default:
                    System.out.println(unrecognizedCommand);
                    break;
            }

        }
    }
}


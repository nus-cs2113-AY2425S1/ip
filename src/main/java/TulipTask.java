import java.util.HashMap;
import java.util.Scanner;

public class TulipTask {
    public static void main(String[] args) {
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
                    taskList.markTaskAsDone(commandArguments);
                    break;

                case "unmark":
                    taskList.markTaskAsNotDone(commandArguments);
                    break;

                case "todo":
                    taskList.addToDo(commandArguments);
                    break;

                case "deadline":
                    taskList.addDeadline(commandArguments);
                    break;

                case "event":
                    taskList.addEvent(commandArguments);
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
                    System.out.println(goodByeMessage);
                    return;

                default:
                    System.out.println(unrecognizedCommand);
                    break;
            }

        }
    }
}


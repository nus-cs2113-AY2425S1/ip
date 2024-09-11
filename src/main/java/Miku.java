import java.util.Scanner;

public class Miku {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        printGreeting();

        String line = inputScanner.nextLine();
        printDivider();

        while (!line.equals("bye")) {
            try {
                handleInput(line, taskList);
            } catch (Exception e) {
                System.out.println("Please enter valid commands");
            }
            printDivider();
            line = inputScanner.nextLine();
            printDivider();
        }

        printGoodbye();

        inputScanner.close();
    }

    private static void printGoodbye() {
        System.out.println("Bye, see you later!");
        printDivider();
    }

    private static String[] splitCommand(String line) {
        return line.split(" ", 2);
    }

    private static void handleInput(String line, TaskList taskList) throws Exception {
        if (line.equals("list")) {
            taskList.printTaskList();
            return;
        }
        String[] parts = splitCommand(line);

        if (parts.length < 2) {
            System.out.println("Insufficient arguments");
            return;
        }

        String[] parameters = parts[1].split("/");

        switch (parts[0]) {
        case "mark":
            taskList.attemptToMarkTask(parameters[0]);
            break;
        case "unmark":
            taskList.attemptToUnmarkTask(parameters[0]);
            break;
        case "todo":
            taskList.addTask(new Todo(parameters[0]));
            break;
        case "deadline":
            String dueDate = splitCommand(parameters[1])[1];
            taskList.addTask(new Deadline(parameters[0], dueDate));
            break;
        case "event":
            String fromDate = splitCommand(parameters[1])[1];
            String toDate = splitCommand(parameters[2])[1];
            taskList.addTask(new Event(parameters[0], fromDate, toDate));
            break;
        default:
            System.out.println("Invalid command");
            break;
        }
    }

    /**
     * Prints a greeting to the user
     */
    public static void printGreeting() {
        String logo =
                """
                         __  __   _   _           \s
                        |  \\/  | (_) | |          \s
                        | \\  / |  _  | | __  _   _\s
                        | |\\/| | | | | |/ / | | | |
                        | |  | | | | |   <  | |_| |
                        |_|  |_| |_| |_|\\_\\  \\__,_|
                        """;
        //Text to Ascii generated through https://patorjk.com/software/taag/
        System.out.println(logo);

        printDivider();
        System.out.println("Hello! I'm Miku\nWhat can I do for you?");
        printDivider();
    }

    /**
     * Prints a line divider consisting of _ characters
     */
    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }
}

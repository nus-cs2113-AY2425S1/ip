import java.util.HashMap;
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

    private static HashMap<String,String> parseOptions(String line) {
        String[] parameters = line.split("/");
        HashMap<String,String> options = new HashMap<>();
        for (String parameter : parameters) {
            String[] parts = splitCommand(parameter);
            options.put(parts[0], parts[1]);
        }
        return options;
    }

    private static boolean doesOptionsMapContainNull(HashMap<String,String> options,String[] requiredOptions) {
        for (String option:requiredOptions){
            if (!options.containsKey(option)){
                return true;
            }
        }
        return false;
    }

    private static void handleInput(String line, TaskList taskList) {
        if (line.equals("list")) {
            taskList.printTaskList();
            return;
        }

        // Split the command into one part and the options into another
        String[] parts = splitCommand(line);

        // Every command other than list takes in at least one option
        if (parts.length < 2) {
            System.out.println("Insufficient arguments or Invalid command");
            return;
        }

        HashMap<String,String> options = parseOptions(parts[1]);

        switch (parts[0]) {
        case "mark":
            taskList.attemptToMarkTask(parts[1]);
            break;
        case "unmark":
            taskList.attemptToUnmarkTask(parts[1]);
            break;
        case "todo":
            if (doesOptionsMapContainNull(options,new String[] {"desc"})) {
                System.out.println("Please provide required options");
                break;
            }
            taskList.addTask(new Todo(options.get("desc")));
            break;
        case "deadline":
            if (doesOptionsMapContainNull(options,new String[] {"desc","by"})) {
                System.out.println("Please provide required options");
                break;
            }
            taskList.addTask(new Deadline(options.get("desc"), options.get("by")));
            break;
        case "event":
            if (doesOptionsMapContainNull(options,new String[] {"desc","from","to"})) {
                System.out.println("Please provide required options");
                break;
            }
            taskList.addTask(new Event(options.get("desc"), options.get("from"), options.get("to")));
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

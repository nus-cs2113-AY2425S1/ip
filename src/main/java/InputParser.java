import java.util.Arrays;
import java.util.HashMap;

public class InputParser {
    private static final String[] TODO_REQUIRED_OPTIONS = {"desc"};
    private static final String[] DEADLINE_REQUIRED_OPTIONS = {"desc", "by"};
    private static final String[] EVENT_REQUIRED_OPTIONS = {"desc", "from", "to"};

    private String[] splitCommand(String line) {
        return line.split(" ", 2);
    }

    private HashMap<String, String> parseOptions(String line) {
        if (!line.contains("/")) {
            return new HashMap<>();
        }
        String[] parameters = line.split("/");
        HashMap<String, String> options = new HashMap<>();
        for (String parameter : parameters) {
            if (parameter.isEmpty()) {
                continue;
            }
            String[] parts = splitCommand(parameter);
            options.put(parts[0], parts[1]);
        }
        return options;
    }

    private boolean doesOptionsMapContainNull(HashMap<String, String> options, String[] requiredOptions) {
        for (String option : requiredOptions) {
            if (!options.containsKey(option)) {
                return true;
            }
        }
        return false;
    }

    public void handleInput(String line, TaskList taskList) {
        if (line.equals("bye")) {
            return;
        }
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
        HashMap<String, String> options = parseOptions(parts[1]);

        switch (parts[0]) {
        case "mark":
            taskList.attemptToMarkTask(parts[1]);
            break;
        case "unmark":
            taskList.attemptToUnmarkTask(parts[1]);
            break;
        case "delete":
            taskList.attemptToDelete(parts[1]);
            break;
        case "find":
            taskList.searchTasks(parts[1]);
            break;
        case "todo":
            if (doesOptionsMapContainNull(options, TODO_REQUIRED_OPTIONS)) {
                System.out.println("Please provide required options: " + Arrays.toString(TODO_REQUIRED_OPTIONS));
                break;
            }
            taskList.addTask(new Todo(options.get("desc")));
            break;
        case "deadline":
            if (doesOptionsMapContainNull(options, DEADLINE_REQUIRED_OPTIONS)) {
                System.out.println("Please provide required options: " + Arrays.toString(DEADLINE_REQUIRED_OPTIONS));
                break;
            }
            taskList.addTask(new Deadline(options.get("desc"), options.get("by")));
            break;
        case "event":
            if (doesOptionsMapContainNull(options, EVENT_REQUIRED_OPTIONS)) {
                System.out.println("Please provide required options: " + Arrays.toString(EVENT_REQUIRED_OPTIONS));
                break;
            }
            taskList.addTask(new Event(options.get("desc"), options.get("from"), options.get("to")));
            break;
        default:
            System.out.println("Invalid command");
            break;
        }
    }

}

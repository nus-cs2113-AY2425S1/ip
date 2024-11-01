/**
 * Parses user input and determines the corresponding task list command
 *
 * This class recieves user input, extract the command keyword, and delegate the
 * execution of the command to the appropriate method in the TaskList class
 */

public class Parser {
    private String input;
    private TaskList tasklist;

    public Parser(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    public String processCommand(String input) {
        return input.split(" ")[0];
    }

    public void determineCommand(String input) {
        String inputTrim = input.trim();
        try {
            switch (processCommand(inputTrim)) {
            case "list":
                tasklist.printList();
                break;
            case "mark":
                tasklist.toMark(inputTrim, true);
                break;
            case "unmark":
                tasklist.toMark(inputTrim, false);
                break;
            case "todo":
                tasklist.addTodo(inputTrim);
                break;
            case "event":
                tasklist.addEvent(inputTrim);
                break;
            case "deadline":
                tasklist.addDeadline(inputTrim);
                break;
            case "delete":
                tasklist.deleteTask(inputTrim);
                break;
            case "find":
                tasklist.findTasks(input.substring("find".length()).trim());
                break;
            case "date":
                tasklist.findDueDate(inputTrim);
                break;
            default:
                throw new DianaException("Unknown Command: " + inputTrim);
            }
        } catch (DianaException e) {
                System.out.println(e.getMessage());
        }
    }
}

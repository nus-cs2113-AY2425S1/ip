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
        try {
            switch (processCommand(input)) {
            case "list":
                tasklist.printList();
                break;
            case "mark":
                tasklist.toMark(input, true);
                break;
            case "unmark":
                tasklist.toMark(input, false);
                break;
            case "todo":
                tasklist.addTodo(input);
                break;
            case "event":
                tasklist.addEvent(input);
                break;
            case "deadline":
                tasklist.addDeadline(input);
                break;
            case "delete":
                tasklist.deleteTask(input);
                break;
            case "find":
                tasklist.findTasks(input.substring("find".length()).trim());
                break;
            default:
                throw new DianaException("Unknown Command: " + input);
            }
        } catch (DianaException e) {
                System.out.println(e.getMessage());
        }
    }
}

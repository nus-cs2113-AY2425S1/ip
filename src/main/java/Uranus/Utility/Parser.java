package Uranus.Utility;

public class Parser extends Functions {

    protected static final String MARK_COMMAND = "mark";
    protected static final String UNMARK_COMMAND = "unmark";
    protected static final String DELETE_COMMAND = "delete";
    protected static final String BYE_COMMAND = "bye";
    protected static final String LIST_COMMAND = "list";
    protected static final String ECHO_COMMAND = "echo";
    protected static final String HELP_COMMAND = "help";
    protected static final String CLEAR_COMMAND = "clear";
    protected static final String TODO_COMMAND = "todo";
    protected static final String TASK_COMMAND = "task";
    protected static final String EVENT_COMMAND = "event";
    protected static final String DEADLINE_COMMAND = "deadline";
    protected static final String FIND_COMMAND = "find";

    protected static void processCommand(String input) {
        if (input.startsWith(MARK_COMMAND) || input.startsWith(UNMARK_COMMAND)) {
            TaskList.handleMarking(input);
        } else if (input.startsWith(DELETE_COMMAND)) {
            TaskList.handleDelete(input);
        } else if (input.startsWith(FIND_COMMAND)) {
            TaskList.handleFind(input);
        } else {
            switch (input) {
            case BYE_COMMAND:
                Ui.printByeMessage();
                System.exit(0);
                // Fallthrough

            case LIST_COMMAND:
                TaskList.listTasks(taskList);
                break;

            case ECHO_COMMAND:
                Ui.echo();
                break;

            case HELP_COMMAND:
                Ui.printFunctions();
                break;

            case CLEAR_COMMAND:
                Ui.clearScreen();
                break;

            default:
                TaskList.addTask(input);
                break;
            }
        }
    }
}

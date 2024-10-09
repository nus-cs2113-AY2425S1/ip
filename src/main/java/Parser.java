import taskpackage.TaskList;

/**
 * The parser class handles user input and interprets commands for the task manager.
 */
public class Parser {

    private final UI ui;
    private final TaskList tasks;

    /**
     * Constructor for Parser.
     *
     * @param ui    The user interface to interact with the user.
     * @param tasks The task list to manage tasks.
     */
    public Parser(UI ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Returns the current task list.
     *
     * @return The task list.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Handles user input and executes the corresponding commands based on the input.
     *
     * @param line The user input.
     * @return True if the chat should continue, false if the "bye" command was given.
     */
    public boolean chatFeature(String line) {
        ui.lineMessage(); // Print separator line

        if (line.equals(CommandHandling.BYE_COMMAND)) {
            return false; // End the conversation
        } else if (line.equals(CommandHandling.LIST_COMMAND)) {
            tasks.printTasksList();
        } else if (line.startsWith(CommandHandling.DELETE_COMMAND)) {
            CommandHandling.deleteCommand(tasks, line, ui);
        } else if (line.startsWith(CommandHandling.FIND_COMMAND)) {
            CommandHandling.findCommand(tasks, line, ui);
        } else if (line.startsWith(CommandHandling.MARK_COMMAND)) {
            CommandHandling.markCommand(tasks, line, ui);
        } else if (line.startsWith(CommandHandling.UNMARK_COMMAND)) {
            CommandHandling.unmarkCommand(tasks, line, ui);
        } else if (line.startsWith(CommandHandling.TODO_COMMAND)) {
            CommandHandling.addTodoCommand(tasks, line, ui);
        } else if (line.startsWith(CommandHandling.DEADLINE_COMMAND)) {
            CommandHandling.addDeadlineCommand(tasks, line, ui);
        } else if (line.startsWith(CommandHandling.EVENT_COMMAND)) {
            CommandHandling.addEventCommand(tasks, line, ui);
        } else {
            ui.porFavorMessage("UNRECOGNIZED REQUEST"); // Inform user about an unrecognized command
        }

        ui.lineMessage(); // Print separator line
        return true; // Continue conversation
    }
}

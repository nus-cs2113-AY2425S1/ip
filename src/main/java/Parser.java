import taskpackage.TaskList;

public class Parser {

    private final UI ui;
    private final TaskList tasks;

    public Parser(UI ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public TaskList getTasks() { return tasks; }

    // Handles user input and executes corresponding actions
    public boolean chatFeature(String line) {

        ui.lineMessage(); // Print separator line

        // Handle "bye" command to end chat
        if (line.equals(CommandHandling.BYE_COMMAND)) {
            return false; // End the conversation
        } else if (line.equals(CommandHandling.LIST_COMMAND)) {
            tasks.printTasksList(); // Print task list from Task class
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

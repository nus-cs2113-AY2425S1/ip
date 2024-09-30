package Commands;

import Tasks.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo to the list. "
            + "\tdescription details must be added\n"
            + "\tUsage: " + COMMAND_WORD + " <description>\n"
            + "\tExample: " + COMMAND_WORD + " do assignment week 7";

    public static final String MESSAGE_SUCCESS = "New todo added: %1$s";

    private final Todo toAdd;

    public TodoCommand(String description) {
        this.toAdd = new Todo(description);
    }

    @Override
    public CommandResult execute() {
        try {
            tasksList.addTask(toAdd);
            System.out.println("Got it. I've added this Todo:\n" + tasksList.get(tasksList.size() - 1).toString());
            System.out.println("now you have " + tasksList.size() + " tasks in the list");
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (Exception e) {
            return new CommandResult("An error occurred: " + e.getMessage());
        }
    }
}

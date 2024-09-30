package Commands;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all tasks in the list.\n"
            + "\tExample: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list:\n";

    @Override
    public CommandResult execute() {
        String[] inputed_tasks = new String[tasksList.size()];
        for (int i = 0; i < tasksList.size(); i++) {
            inputed_tasks[i] = tasksList.get(i).toString();
        }
        return new CommandResult(MESSAGE_SUCCESS + String.join("\n", inputed_tasks));
    }
}
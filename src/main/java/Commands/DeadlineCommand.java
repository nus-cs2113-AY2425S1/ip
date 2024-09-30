package Commands;

import Tasks.Deadline;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to the list. "
            + "Description and time details must be added\n"
            + "\tUsage: " + COMMAND_WORD + " <description> /by <time>\n"
            + "\tExample: " + COMMAND_WORD + " do assignment week 7 /by 2021-03-19 23:59";

    public static final String MESSAGE_SUCCESS = "New deadline added: %1$s";

    private final Deadline toAdd;

    public DeadlineCommand(String description, String by) {
        this.toAdd = new Deadline(description, by);
    }

    @Override
    public CommandResult execute() {
        try {
            tasksList.addTask(toAdd);
            System.out.println("Got it. I've added this Deadline:\n" + tasksList.get(tasksList.size() - 1).toString());
            System.out.println("now you have " + tasksList.size() + " tasks in the list");
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (Exception e) {
            return new CommandResult("An error occurred: " + e.getMessage());
        }
    }
}

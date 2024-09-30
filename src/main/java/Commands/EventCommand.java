package Commands;

import Tasks.Event;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a event to the list. "
            + "Description and time details (from and to) must be added\n"
            + "\tUsage: " + COMMAND_WORD + " <description> /from <time1> /to <time2>\n"
            + "\tExample: " + COMMAND_WORD + " do assignment week 7 /from 2024-09-19 23:59 /to 2024-09-20 23:59";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";

    private final Event toAdd;

    public EventCommand(String description, String from, String to) {
        this.toAdd = new Event(description, from, to);
    }

    @Override
    public CommandResult execute() {
        try {
            tasksList.addTask(toAdd);
            System.out.println("Got it. I've added this Event:\n" + tasksList.get(tasksList.size() - 1).toString());
            System.out.println("now you have " + tasksList.size() + " tasks in the list");
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (Exception e) {
            return new CommandResult("An error occurred: " + e.getMessage());
        }
    }
}

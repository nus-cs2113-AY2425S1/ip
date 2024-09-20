package niwa.command;

import niwa.messages.NiwaMesssages;
import niwa.data.task.TaskList;

public class ClearCommand extends Command{
    public ClearCommand() {
        setFormat("");
        setWord("clear");
        setGuide("clear: Clear all tasks in the list.");
    }

    @Override
    public String[] parseArguments(String command) {
        if (!command.isEmpty()) {
            return null;
        }
        return new String[0];
    }

    /**
     * Clear all tasks in the list
     *
     * @param rawArgumentString should be null.
     */
    @Override
    public void execute(String rawArgumentString) {
        super.execute(rawArgumentString);
        TaskList.getInstance().clearTaskList();

        // Prepare the message to confirm deletion.
        String message = "OK, I've clear your task list.%n"
                + PREFIX + NiwaMesssages.MESSAGE_LIST_SIZE_INFORM;

        // Print out a confirmation message with task details and remaining task count.
        System.out.printf(PREFIX + message, TaskList.getInstance().getTaskListSize());
    }
}

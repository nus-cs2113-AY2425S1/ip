package niwa.command;

import niwa.task.Task;

import java.util.List;

public class ClearCommand extends TaskCommand{
    public ClearCommand(List<Task> tasks) {
        super(tasks);
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
        tasks.clear();

        // Prepare the message to confirm deletion.
        String message = "OK, I've clear your task list.%n"
                + PREFIX + "You currently have %d tasks in the list.%n";;

        // Print out a confirmation message with task details and remaining task count.
        System.out.printf(PREFIX + message, tasks.size());

        super.saveTasks();
    }
}

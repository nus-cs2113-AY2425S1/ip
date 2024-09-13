package niwa.command;

import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.task.Task;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommand extends TaskCommand{

    public DeleteCommand(List<Task> tasks) {
        super(tasks);
        setFormat("^\\d+$");
        setWord("delete");
        setGuide("delete [task index]: Delete the task at the given index.");
    }

    @Override
    public String[] convertArguments(String command) {
        // Compile the regex pattern for matching the command format
        Pattern pattern = Pattern.compile(argumentFormat);

        // Create a matcher for the input command string
        Matcher matcher = pattern.matcher(command);

        // Check if the command string matches the expected pattern
        if (matcher.matches()) {
            // Return the segments as an array
            return command.split(" ");
        } else {
            // Return null if the command does not match the expected format
            return null;
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param indexString The index of the task to delete.
     */
    @Override
    public void execute(String indexString) throws NumberFormatException, NiwaTaskIndexOutOfBoundException{
        super.execute(indexString);
        int index = Integer.parseInt(arguments[0]) - 1; // Convert to zero-based index
        if (index < 0 || index >= tasks.size()) {
            throw new NiwaTaskIndexOutOfBoundException(tasks.size());
        }
        Task temp = tasks.get(index);
        tasks.remove(index);

        String message = "OK, I've deleted this task:%n"
                + PREFIX + "   %s%n"
                + PREFIX + "You currently have %d tasks in the list.%n";
        System.out.printf(PREFIX + message, temp.getFullInfo(), tasks.size());

        super.saveTasks();
    }
}

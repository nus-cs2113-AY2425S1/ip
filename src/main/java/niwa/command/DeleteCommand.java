package niwa.command;

import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.task.Task;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommand extends TaskCommand {

    public DeleteCommand(List<Task> tasks) {
        super(tasks);
        setFormat("^\\d+$"); // The expected format is a single number representing the task index.
        setWord("delete"); // Command word for this action is "delete".
        setGuide("delete [task index]: Delete the task at the given index."); // Help guide for the delete command.
    }

    /**
     * Parses the command string to extract delete details.
     * The command should be in the format: "delete [index: int]".
     *
     * @param command The command string that the user input.
     * @return An array of strings containing the parsed arguments, or null if the format is incorrect.
     */
    @Override
    public String[] parseArguments(String command) {
        // Compile the regex pattern for matching the command format.
        Pattern pattern = Pattern.compile(argumentFormat);

        // Create a matcher for the input command string.
        Matcher matcher = pattern.matcher(command);

        // Check if the command string matches the expected pattern.
        if (matcher.matches()) {
            // Return the command split into an array of segments (i.e., arguments).
            return command.split(" ");
        } else {
            // If the command does not match the expected format, return null.
            return null;
        }
    }

    /**
     * Executes the deletion of a task based on the provided index.
     *
     * @param indexString The index of the task to delete, passed as a string.
     * @throws NumberFormatException if the index is not a valid number.
     * @throws NiwaTaskIndexOutOfBoundException if the index is out of bounds of the task list.
     */
    @Override
    public void execute(String indexString) throws NumberFormatException, NiwaTaskIndexOutOfBoundException {
        // Call the parent execute method to parse arguments
        super.execute(indexString);

        // Parse the index from the arguments array (convert to zero-based index).
        int index = Integer.parseInt(arguments[0]) - 1;

        // Check if the index is valid (within task list bounds).
        if (index < 0 || index >= tasks.size()) {
            // Throw an exception if the index is out of bounds.
            throw new NiwaTaskIndexOutOfBoundException(tasks.size());
        }

        // Store the task to be deleted temporarily for displaying later.
        Task temp = tasks.get(index);
        // Remove the task from the task list.
        tasks.remove(index);

        // Prepare the message to confirm deletion.
        String message = "OK, I've deleted this task:%n"
                + PREFIX + "   %s%n"
                + PREFIX + "You currently have %d tasks in the list.%n";

        // Print out a confirmation message with task details and remaining task count.
        System.out.printf(PREFIX + message, temp.getFullInfo(), tasks.size());

        super.saveTasks();
    }
}

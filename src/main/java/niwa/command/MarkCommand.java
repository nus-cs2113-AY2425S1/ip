package niwa.command;

import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.task.Task;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkCommand extends TaskCommand {

    public MarkCommand(List<Task> tasks) {
        super(tasks);
        setFormat("^\\d+$");
        setWord("mark");
        setGuide("mark [task index]: Mark the task at the given index as done.");
    }

    @Override
    public String[] parseArguments(String command) {
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
     * Marks a task as done.
     *
     * @param indexString The index of the task to mark.
     */
    @Override
    public void execute(String indexString) throws NumberFormatException, NiwaTaskIndexOutOfBoundException {
        super.execute(indexString);
        int index = Integer.parseInt(arguments[0]) - 1; // Convert to zero-based index
        if (index < 0 || index >= TaskCommand.tasks.size()) {
            throw new NiwaTaskIndexOutOfBoundException(TaskCommand.tasks.size());
        }

        Task temp = TaskCommand.tasks.get(index);
        temp.markAsDone();

        String message  = "OK, I've marked this task as done:%n"
                + PREFIX + "%s%n";
        System.out.printf(PREFIX + message, temp.getFullInfo());

        super.saveTasks();
    }
}

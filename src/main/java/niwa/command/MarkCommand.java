package niwa.command;

import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.messages.NiwaExceptionMessages;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkCommand extends Command {

    public MarkCommand() {
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
        try {
            int index = Integer.parseInt(arguments[0]) - 1; // Convert to zero-based index

            Task temp = TaskList.getInstance().findTask(index);
            temp.markAsDone();

            String message = "OK, I've marked this task as done:%n"
                    + PREFIX + "%s%n";
            System.out.printf(PREFIX + message, temp.getFullInfo());

            ExecutedCommand.saveTasks();

        } catch (NiwaTaskIndexOutOfBoundException e) {
            System.out.println (PREFIX + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(PREFIX + NiwaExceptionMessages.MESSAGE_INDEX_NUMBER_FORMAT);
        }
    }
}

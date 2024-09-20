package niwa.command;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.messages.NiwaMesssages;
import niwa.data.task.Deadline;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command{
    public DeadlineCommand() {
        setFormat("(.+?)\\s*/by\\s*(.+?)");
        setWord("deadline");
        setGuide("deadline [task description] /by [time]:"
                + "Add a new deadline to our list.");
    }

    /**
     * Parses the command string to extract deadline details.
     * The command should be in the format: "deadline description /by dueDate".
     *
     * @param command The command string to parse
     * @return An array containing the description and dueDate, or null if the command format is invalid
     */
    @Override
    public String[] parseArguments(String command) {
        // Compile the regex pattern for matching the command format
        Pattern pattern = Pattern.compile(argumentFormat);

        // Create a matcher for the input command string
        Matcher matcher = pattern.matcher(command);

        // Check if the command string matches the expected pattern
        if (matcher.matches()) {
            // Extract and trim the captured groups
            String segment1 = matcher.group(1).trim(); // Description
            String segment2 = matcher.group(2).trim(); // Due date

            // Return the segments as an array
            return new String[]{segment1, segment2};
        } else {
            // Return null if the command does not match the expected format
            return null;
        }
    }

    /**
     * Adds a new deadline to the task list.
     *
     * @param taskInfo The task details to add.
     */
    @Override
    public void execute(String taskInfo) {
        super.execute(taskInfo);
        try {
            Task temp = new Deadline(arguments[0], arguments[1]);
            TaskList.getInstance().addTask(temp);

            String message = "Got it. I've added this deadline:%n"
                    + PREFIX + "%s%n"
                    + PREFIX + NiwaMesssages.MESSAGE_LIST_SIZE_INFORM;
            System.out.printf(PREFIX + message, temp.getFullInfo(), TaskList.getInstance().getTaskListSize());

            ExecutedCommand.saveTasks();
        } catch (NiwaDuplicateTaskException e) {
            System.out.printf(PREFIX + e.getMessage());
        }
    }
}

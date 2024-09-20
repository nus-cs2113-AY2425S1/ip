package niwa.command;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.messages.NiwaMesssages;
import niwa.data.task.Event;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends Command{
    public EventCommand() {
        setFormat("(.+?)\\s*/from\\s*(.+?)\\s*/to\\s*(.+)");
        setWord("event");
        setGuide("event [task description] /from [time] /to [time]: "
                + "Add a new event to our list.");
    }

    /**
     * Parses the command string to extract event details.
     * The command should be in the format: "event description /from startDay /to endDay".
     *
     * @param command The command string to parse
     * @return An array containing the description, startDay, and endDay, or null if the command format is invalid
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
            String segment2 = matcher.group(2).trim(); // Start day
            String segment3 = matcher.group(3).trim(); // End day

            // Return the segments as an array
            return new String[]{segment1, segment2, segment3};
        } else {
            // Return null if the command does not match the expected format
            return null;
        }
    }

    /**
     * Adds a new event to the task list.
     *
     * @param taskInfo The task details to add.
     */
    @Override
    public void execute(String taskInfo) {
        super.execute(taskInfo);
        try {
            Task temp = new Event(arguments[0], arguments[1], arguments[2]);
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

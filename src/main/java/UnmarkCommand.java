import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnmarkCommand extends TaskCommand{

    public UnmarkCommand(List<Task> tasks) {
        super(tasks);
        setFormat("^\\d+$");
        setWord("unmark");
        setGuide("unmark [task index]: unmark the task at the given index.");
    }

    @Override
    public String[] convertArguments(String command) {
        // Compile the regex pattern for matching the command format
        Pattern pattern = Pattern.compile(argumentFormat);

        // Create a matcher for the input command string
        Matcher matcher = pattern.matcher(command);

        // Check if the command string matches the expected pattern
        if (matcher.matches()) {
            return command.split(" ");
        } else {
            // Return null if the command does not match the expected format
            return null;
        }
    }

    /**
     * Marks a task as done.
     *
     * @param indexString The index of the task to unmark.
     */
    @Override
    public void execute(String indexString) throws NumberFormatException, NiwaTaskIndexOutOfBoundException{
        super.execute(indexString);
        int index = Integer.parseInt(arguments[0]) - 1; // Convert to zero-based index
        if (index < 0 || index >= tasks.size()) {
            throw new NiwaTaskIndexOutOfBoundException(tasks.size());
        }

        Task temp = tasks.get(index);
        temp.markAsUndone();

        String message  = "OK, I've marked this task as undone:%n"
                + PREFIX + "%s%n";
        System.out.printf(PREFIX + message, temp.getFullInfo());
    }
}

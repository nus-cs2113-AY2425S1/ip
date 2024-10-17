package niwa.command;

import niwa.data.task.Task;
import niwa.data.task.TaskList;

import niwa.exception.NiwaInvalidArgumentException;

import niwa.messages.NiwaMesssages;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The {@code FindCommand} class handles the searching of tasks by keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_GUIDE = "find [keyword]: Find tasks by keyword.";
    public static final String[] COMMAND_KEYWORDS = {""};

    /**
     * Checks if the provided arguments are valid.
     *
     * @return true if valid; false otherwise.
     */
    public boolean isValidArguments() {
        return arguments.size() == COMMAND_KEYWORDS.length &&
                arguments.containsKey(COMMAND_KEYWORDS[0]);
    }

    /**
     * Finds tasks in the task list that contain the specified keyword.
     *
     * @return A {@code CommandResult} containing the results of the search.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        String keyword = arguments.get(COMMAND_KEYWORDS[0]); // Keyword to search for

        ArrayList<Task> currentTaskList = TaskList.getInstance().getTaskList(); // Get current tasks
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) currentTaskList.stream()
                .filter(t -> isContainingKeyword(t, keyword)) // Filter tasks containing the keyword
                .collect(Collectors.toList());

        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        if (filteredTaskList.isEmpty()) {
            messages.add(NiwaMesssages.MESSAGE_FIND_EMPTY); // No tasks found
        } else {
            messages.add(NiwaMesssages.MESSAGE_FIND_SUCCESS); // Tasks found
        }

        return new CommandResult(messages, filteredTaskList);
    }

    /**
     * Checks if the task description contains the specified keyword.
     *
     * @param task The task to check.
     * @param keyword The keyword to search for.
     * @return {@code true} if the task contains the keyword; {@code false} otherwise.
     */
    public boolean isContainingKeyword(Task task, String keyword) {
        if (task == null || keyword == null) {
            return false; // Invalid task or keyword
        }
        return task.getDescription().toLowerCase().contains(keyword.toLowerCase()); // Check for keyword
    }
}

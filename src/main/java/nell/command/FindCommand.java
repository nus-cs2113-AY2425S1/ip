package nell.command;

import nell.common.Messages;
import nell.list.TaskList;

/**
 * Represents an executable find command
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String keyword;

    /**
     * Constructs a new FindCommand object, with the specified task list and keyword
     *
     * @param tasks The specified task list
     * @param keyword The specified keyword
     */
    public FindCommand(TaskList tasks, String keyword) {
        super(tasks);

        String trimmedKeyword = keyword.split(" ")[0].trim(); // Takes first word in input, discards the rest
        this.keyword = trimmedKeyword;
    }

    /**
     * Executes command - gets a list of tasks that contain keyword
     */
    @Override
    public void execute() {
        System.out.println(Messages.MATCHING_TASKS_MESSAGE);
        System.out.print(tasks.getMatchingTaskList(keyword));
    }
}

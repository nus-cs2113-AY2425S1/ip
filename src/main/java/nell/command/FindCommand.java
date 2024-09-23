package nell.command;

import nell.list.TaskList;

/**
 * Represents an executable find command
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a new FindCommand object, with the specified task list and keyword
     *
     * @param tasks The specified task list
     * @param keyword The specified keyword
     */
    public FindCommand(TaskList tasks, String keyword) {
        super("find", tasks);
        this.keyword = keyword;
    }

    /**
     * Executes command - gets a list of tasks that contain keyword
     */
    public void execute() {
        System.out.println("-> The matching tasks are as follows:");
        System.out.println(tasks.getMatchingTaskList(keyword));
    }
}

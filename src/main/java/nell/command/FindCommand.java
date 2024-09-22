package nell.command;

import nell.TaskList;

/**
 * Represents an executable find command
 */
public class FindCommand extends Command{
    private final String keyword;

    public FindCommand(TaskList tasks, String keyword) {
        super("find", tasks);
        this.keyword = keyword;
    }

    public void execute() {
        System.out.println("-> The matching tasks are as follows:");
        System.out.println(tasks.getMatchingTaskList(keyword));
    }
}

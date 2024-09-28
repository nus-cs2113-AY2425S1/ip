package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

/**
 * Represents a command to find and display tasks that match a specified filter.
 * This command allows the user to search for tasks based on a keyword or phrase.
 */
public class FindCommand extends Command {

    /** The filter keyword used to search for tasks. */
    private String filter;

    /** The command word for the find command. */
    public static final String COMMAND_WORD = "find";

    /**
     * Constructs a FindCommand with the specified filter.
     *
     * @param filter the keyword or phrase to search for in the task list
     */
    public FindCommand(String filter) {
        this.filter = filter;
    }

    /**
     * Executes the find command, retrieving and displaying tasks that match the filter.
     *
     * @param tasks the TaskList to search through
     * @param ui the user interface for displaying messages
     * @param storage the storage system (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = String.format("Here are the matching tasks:%n%s", tasks.toFilteredString(filter));
        ui.showMsg(message);
    }
}

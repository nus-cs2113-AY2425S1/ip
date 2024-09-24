package jeff.command;

import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;

/**
 * Represents a command to list all tasks currently in the task list.
 * The <code>ListCommand</code> displays all tasks.
 */
public class ListCommand extends Command {
    public ListCommand(String firstWord, String line) {
        super(firstWord, line);
    }

    /**
     * Executes the ListCommand, displaying all tasks currently in the task list.
     * The tasks are shown in the order they were added, with numbering starting from 1.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui for user interaction (unused in this command).
     * @param storage The Storage object for handling task storage (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        System.out.print("orh hor never finish ur tasks:");
        for(int i = 1; i <= tasks.getCount(); i++){
            System.out.print(System.lineSeparator() + i + "." + tasks.getTask(i-1));
        }
    }
}

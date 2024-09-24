package jeff.command;

import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;
import jeff.task.Task;

import java.io.IOException;

/**
 * Represents a Command to delete a task from the task list.
 * The <code>DeleteCommand</code> removes a task specified by the user input.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand with the specified inputs.
     *
     * @param firstWord The first word of the command, which should indicate deletion (e.g., "delete").
     * @param line The full line of user input that specifies the task to be deleted.
     */
    public DeleteCommand(String firstWord, String line) {
        super(firstWord, line);
    }

    /**
     * Executes the DeleteCommand, removing the specified task from the task list.
     * Validates that the input given is an integer, and that the integer is within the task list size.
     * Provides feedback to the user via the UI and updates the storage.
     *
     * @param tasks   The TaskList from which the task will be removed.
     * @param ui      The Ui for user interaction.
     * @param storage The Storage object to handle saving the updated task list.
     * @throws IOException If an I/O error occurs when updating the task storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int taskNumber = super.getTaskNumber(line, tasks.getCount());
            Task task = tasks.getTask(taskNumber - 1);
            tasks.removeTask(taskNumber - 1);
            System.out.print("i have reeeemoved the taskkk: " +
                    System.lineSeparator() + task);
            storage.writeFileTask(tasks);

        } catch(IllegalArgumentException errorMessage) {
            System.out.print("eh how to delete a non-number task...");
        } catch(IndexOutOfBoundsException errorMessage){
            System.out.print("how to delete a non-existent task...");
        }
        System.out.print(System.lineSeparator() +
                "YAYYYY!!! Only " + tasks.getCount() + " task(s) left in ur list!");
    }
}

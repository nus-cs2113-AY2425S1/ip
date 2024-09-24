package jeff.command;

import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;
import jeff.task.Task;

import java.io.IOException;

/**
 * Represents a command to mark or unmark a task as completed in the task list.
 * The <code>MarkCommand</code> updates the status of a task based on the user's input.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a MarkCommand with the specified inputs.
     *
     * @param firstWord The first word of the user input that triggered this command ("mark" or "unmark").
     * @param line      The entire line of user input.
     */
    public MarkCommand(String firstWord, String line) {
        super(firstWord, line);
    }

    /**
     * Executes the MarkCommand, which marks the task as done/not done based on user input.
     * Validates that the task input (integer) is valid.
     * If user input is "mark", set the task to done.
     * If user input is "unmark", set the task to not done
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui for user interaction (unused in this command).
     * @param storage The Storage object for handling task storage.
     * @throws IOException If an I/O error occurs while writing to the storage file.
     */
    //Marks task as complete/uncomplete, catches any errors thrown
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int taskNumber = super.getTaskNumber(this.line, tasks.getCount());
            Task task = tasks.getTask(taskNumber - 1);
            if(firstWord.equals("mark")) {
                task.setIsDone(true);
                System.out.print("ogei marked task dOnE");
            }
            else{
                task.setIsDone(false);
                System.out.print("womp womp task not finished :(");
            }

            storage.writeFileTask(tasks);
            System.out.print(System.lineSeparator() + task);
        } catch(IllegalArgumentException errorMessage) {
            System.out.print("can u plsplspls give me a number!!!");
        } catch(IndexOutOfBoundsException errorMessage){
            System.out.print("u tryna mark a task number that isn't in the list...");
        }
    }
}

package commands;

import exceptions.XiaoMeException;
import storage.Storage;
import task.Task;

import java.util.ArrayList;

/**
 * Represents a command that deletes a task from the task list.
 * The task to be deleted is identified by its index in the list.
 */
public class DeleteCommand extends Command {

    String userInput;

    /**
     * Constructs a DeleteCommand with the user input specifying the task to be deleted.
     *
     * @param userInput the user input in the format 'delete <index>'
     */
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Executes the command to delete a task. The task to be deleted is identified by its index.
     *
     * The expected format for user input is 'delete <index>'.
     *
     * @param tasks the list of tasks from which the task will be deleted
     * @return a success message with details of the removed task
     * @throws XiaoMeException if the user input format is invalid or the index is out of bounds
     */
    @Override
    public String execute(ArrayList<Task> tasks) throws XiaoMeException {
        try {
            String[] markWords = userInput.split(" ");
            int index = Integer.parseInt(markWords[1]) - 1;
            Task temp = tasks.get(index);

            tasks.remove(index);

            Storage.saveFile(tasks);

            return "\tNoted. I've removed this task:\n"
                           + "\t\t" + temp + "\n"
                           + "\tNow you have " + tasks.size() + " tasks in the list.";

        } catch (NumberFormatException e) {
            throw new XiaoMeException("""
                    \tHEY delete should be followed by a valid integer""");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new XiaoMeException("""
                    \tInteger provided does not have a corresponding task""");
        }
    }
}

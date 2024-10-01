package commands;

import exceptions.XiaoMeException;
import storage.Storage;
import task.Task;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a command that marks a task as done or undone.
 */
public class MarkCommand extends Command {

    String userInput;

    /**
     * Constructs a MarkCommand instance with the specified user input.
     *
     * @param userInput the user input for marking a task
     */
    public MarkCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Executes the command to mark a task as done or undone based on user input.
     *
     * The expected format for user input is
     * 'mark <task_number>' to mark as done or
     * 'unmark <task_number>' to mark as not done.
     *
     * @param tasks the list of tasks where the specified task will be marked
     * @return a success message indicating the task's new status
     * @throws XiaoMeException if the user input format is invalid or if the task index is out of bounds
     */
    @Override
    public String execute(ArrayList<Task> tasks) throws XiaoMeException  {
        try {
            String string;
            // user input is mark/unmark x: mark corresponding task as done or undone
            String[] markWords = userInput.split(" ");
            int index = Integer.parseInt(markWords[1]) - 1;

            if (Objects.equals(markWords[0], "mark")) {
                tasks.get(index).setDone(true);
                Storage.saveFile(tasks);

                return "\tNice! I've marked this task as done:\n"
                        + "\t\t" + tasks.get(index);
            } else {
                tasks.get(index).setDone(false);
                Storage.saveFile(tasks);

                return "\tOK, I've marked this task as not done yet:\n"
                        + "\t\t" + tasks.get(index);
            }


        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new XiaoMeException("\tHEY mark/unmark should be followed by a valid integer");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new XiaoMeException("\tInteger provided does not have a corresponding task");
        }
    }
}

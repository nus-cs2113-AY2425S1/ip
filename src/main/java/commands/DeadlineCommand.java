package commands;

import exceptions.XiaoMeException;
import storage.Storage;
import task.Deadline;
import task.Task;

import java.util.ArrayList;

/**
 * Represents a command that adds a new deadline task to the task list.
 * The deadline task includes a description and a due date.
 */
public class DeadlineCommand extends Command {
    String userInput;

    /**
     * Constructs a DeadlineCommand with the user input specifying the deadline task details.
     *
     * @param userInput the user input in the format 'deadline <description> /by <due_date>'
     */
    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Executes the command to create a new Deadline task.
     *
     * The expected format for user input is 'deadline <description> /by <due_date>'.
     *
     * @param tasks the list of tasks where the new deadline task will be added
     * @return a success message with details of the newly added task
     * @throws XiaoMeException if the user input format is invalid
     */
    @Override
    public String execute(ArrayList<Task> tasks) throws XiaoMeException {
        try {
            // user is creating a new deadline
            String[] lineWords = userInput.split("/by");
            if (lineWords.length != 2) {
                throw new IllegalArgumentException();
            }

            tasks.add(new Deadline(lineWords[0].replace("deadline", "").trim(), lineWords[1].trim())); // add task to storage

            Storage.saveFile(tasks);

            return "\tGot it. I've added this task:\n"
                    + "\t\t" + tasks.get(tasks.size() - 1) + "\n"
                    + "\tNow you have " + tasks.size() + " tasks in the list.";

        } catch (Exception e) {
            throw new XiaoMeException("""
                    \tInvalid format to create a deadline:
                    \tUse 'deadline <description> /by <due_date>'""");
        }
    }
}

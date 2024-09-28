package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

import java.util.InputMismatchException;

/**
 * Represents a command to mark or unmark tasks in the task list.
 */
public class MarkCommand extends Command {

    private final String firstWord;

    /**
     * Constructs a MarkCommand with the type (mark/unmark) and instructions.
     *
     * @param firstWord The type of action (mark or unmark).
     * @param instructions The task number to mark/unmark.
     */
    public MarkCommand(String firstWord, String instructions) {
        super(instructions);
        this.firstWord = firstWord;
    }

    /**
     * Executes the mark/unmark command by toggling the task's status.
     *
     * @param taskList The task list containing the task to toggle.
     * @param ui The user interface for displaying status changes.
     * @param storage The storage system to save the updated task list.
     * @throws AlyException If an invalid task number or error occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        try {
            String[] splitInput = instructions.split(" ");

            if (splitInput.length != 1) {
                throw new InputMismatchException();
            }

            int indexNumToToggle = Integer.parseInt(instructions);
            markAsDone(taskList, ui, indexNumToToggle - 1);
            storage.write(taskList);

        } catch (NumberFormatException | InputMismatchException e) {
            throw new AlyException("Just type 1 number bro... So difficult meh...");
        } catch (AlyException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Marks or unmarks a task based on the action specified.
     *
     * @param taskList The task list containing the task.
     * @param ui The user interface for displaying status changes.
     * @param indexNumToToggle The index of the task to toggle.
     * @throws AlyException If the task number is out of bounds or an error occurs.
     */
    private void markAsDone(TaskList taskList, Ui ui, int indexNumToToggle) throws AlyException {
        try {
            if (firstWord.equals("mark")) {
                taskList.toggleStatus(indexNumToToggle, true);
                ui.showStatusChange(taskList, indexNumToToggle, firstWord);
            } else if (firstWord.equals("unmark")) {
                taskList.toggleStatus(indexNumToToggle, false);
                ui.showStatusChange(taskList, indexNumToToggle, firstWord);
            } else {
                throw new AlyException("Something went wrong!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AlyException("Index out of bounds lah bro");
        }
    }
}
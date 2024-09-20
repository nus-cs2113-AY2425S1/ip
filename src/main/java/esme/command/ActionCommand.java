package esme.command;

import esme.ui.Ui;

/**
 * Represents the command that handles the actions to be performed on the tasks.
 * The actions that can be performed on the tasks are "delete", "mark", and "unmark".
 * The words array should contain the command and the index of the task to be acted upon.
 */
public class ActionCommand extends Command {
    private final String[] words;

    /**
     * Creates a new ActionCommand with the given Ui and words array.
     * 
     * @param ui The Ui to interact with the user.
     * @param words The words array containing the command and index of the task to be acted upon.
     */
    public ActionCommand(Ui ui, String[] words) {
        super(ui);
        this.words = words;
    }

    /**
     * Handles the task status command given in the words array.
     * If the command is "delete", it deletes the task at the given index.
     * If the command is "mark" or "unmark", it marks or unmarks the task at the given
     * index.
     */
    @Override
    public void run() {
        if (words[0].equalsIgnoreCase("delete")) {
            super.ui.deleteTaskFromList(words);
        } else {
            super.ui.handleTaskStatus(words);
        }
    }
}

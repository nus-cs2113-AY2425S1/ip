package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Emoji;
import nus.edu.rizzler.ui.UserInterface;

public class DeleteCommand extends Command {
    private Emoji emoji = new Emoji();
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(int index) {
        super(index);
    }

    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String taskString = tasks.deleteTask(this.getTaskIndex());
        int taskSize = tasks.getSize();

        String message = String.format("Task deleted %s:%n  %s%n" +
                "Now you have %d tasks in the list.", emoji.getCoolFaceEmoji(), taskString, taskSize);
        userInterface.displayMessage(message);
    }
}


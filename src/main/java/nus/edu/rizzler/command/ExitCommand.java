package nus.edu.rizzler.command;

import nus.edu.rizzler.exception.RizzlerException;
import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.UserInterface;

import java.io.IOException;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        try {
            storage.writeToFile(tasks.toCSVString());
        } catch (IOException e) {
            throw new RizzlerException("Failed to save tasks to Rizzler.data: " + e.getMessage());
        }
    }
}



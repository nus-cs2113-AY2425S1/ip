package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.UserInterface;

public abstract class Command {
    private int taskIndex = -1;

    public Command() {}

    public Command(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, UserInterface userInterface, Storage storage);
}


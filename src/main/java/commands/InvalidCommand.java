package commands;

import task.Task;

import java.util.ArrayList;

public class InvalidCommand extends Command {

    public InvalidCommand() {
        this.isExit = false;
    }

    @Override
    public String execute(ArrayList<Task> tasks) {
        return "Invalid command :(";
    }
}

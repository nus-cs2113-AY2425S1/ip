package main.java;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KenChatException;

    public boolean isRunning() {
        return true;
    }
}

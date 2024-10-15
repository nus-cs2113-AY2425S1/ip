package main.java;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KenChatException {
        if (tasks.getSize() == 0) {
            throw new KenChatException(KenChatException.getTaskNotExistMessage());
        }
        ui.showTasksList(tasks.getTasks());
    }
}

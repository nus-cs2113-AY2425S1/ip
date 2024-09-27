package king.command;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;

import java.io.IOException;

public class FindCommand extends Command {
    private String text;

    public FindCommand(String text) {
        this.text = text;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KingException, IOException {
        tasks.findTask(text);
    }
}

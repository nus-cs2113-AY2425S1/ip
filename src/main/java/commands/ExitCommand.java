package commands;

import java.io.IOException;

import exceptions.IllegalCommandException;
import storage.TaskEncoder;
import tasks.TaskList;
import ui.Skeleton;
import ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws IllegalCommandException {
        try {
            TaskEncoder.overrideFile(taskList);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Bye. See you next time!");
            System.out.print(Skeleton.LINE_BREAK);
            System.exit(0);
        } catch (IOException e) {
            ui.fileError();
        }
    }
}

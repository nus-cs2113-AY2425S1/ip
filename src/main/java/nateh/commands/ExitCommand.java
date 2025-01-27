package nateh.commands;

import java.io.IOException;

import nateh.exceptions.IllegalCommandException;
import nateh.storage.TaskEncoder;
import nateh.tasks.TaskList;
import nateh.ui.Skeleton;
import nateh.ui.Ui;

/**
 * Represents the command to exit the program
 */
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
            ui.printFileError();
        }
    }
}

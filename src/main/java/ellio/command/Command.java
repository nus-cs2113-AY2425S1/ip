package ellio.command;

import ellio.EllioExceptions;
import ellio.storage.Storage;
import ellio.task.TaskList;
import ellio.ui.Ui;

public class Command {

    private static boolean terminateCommand = false;

    public static String inputCommand;

    public Command(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage)throws EllioExceptions {}

    public boolean isExit(){

        return terminateCommand;
    }

    public void setExit(){

        terminateCommand = true;
    }
}

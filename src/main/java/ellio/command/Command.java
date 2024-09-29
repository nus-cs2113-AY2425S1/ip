package ellio.command;

import ellio.storage.Storage;
import ellio.task.TaskList;
import ellio.ui.Ui;

public class Command {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";

    private static boolean terminateCommand = false;

    public static String inputCommand;

    public Command(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){}

    public boolean isExit(){

        return terminateCommand;
    }

    public void setExit(){

        terminateCommand = true;
    }
}

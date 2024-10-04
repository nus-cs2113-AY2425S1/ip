import commands.Command;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import data.TaskList;
import exceptions.DootException;
import parser.Parser;
import storage.Storage;
import ui.Ui;

public class Doot {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public static void main(String[] args) {
        new Doot().run();
    }

    public Doot(){
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());

    }

    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String currentInput = ui.readCommand();
            Command c = Parser.findCommand(currentInput);
            c.executeCommand(tasks, ui);
            isExit = c.isExit();
            storage.writeTaskData(tasks.getTaskListCopy());
        }
        ui.showExit();
    }

}

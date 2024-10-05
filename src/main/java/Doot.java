import commands.Command;
import tasks.Task;
import data.TaskList;
import exceptions.DootException;
import parser.Parser;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

public class Doot {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;


    /**
     * Main function for Doot. Sets up and starts the Doot app.
     *
     * @param args Arguments given from the command line. Not used by the program.
     */
    public static void main(String[] args) {
        new Doot().run();
    }

    /**
     * Default constructor for Doot.
     */
    public Doot() {
        ui = new Ui();
        storage = new Storage();
        ArrayList<Task> storedTasks = storage.load();
        if (storedTasks.isEmpty()) {
            tasks = new TaskList();
        } else {
            tasks = new TaskList(storedTasks);
        }

    }

    /**
     * Runs the Doot application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String currentInput = ui.readCommand();
            try {
                Command c = Parser.findCommand(currentInput);
                c.executeCommand(tasks, ui);
                isExit = c.isExit();
            } catch (DootException e) {
                System.out.println(e.getMessage());
            }
            storage.writeTaskData(tasks.getTaskListCopy());
        }
        ui.showExit();
    }

}

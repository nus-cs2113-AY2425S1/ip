import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents the command line bot that helps stores your tasks.
 * Functionalities include adding, deleting, finding and marking tasks as well as categorise them into todo, deadline
 * and event.
 * <p>
 * User input should follow the following: [command][arg...]
 */
public class Legin {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui = new Ui();
    private final Parser parser = new Parser();

    private Legin() {
        storage = new Storage();
        taskList = storage.getloadedTaskList();
    }

    private void runBot() {
        boolean hasSaidBye = false;
        ui.greet();
        while (!hasSaidBye) {
            String input = ui.readInput();
            hasSaidBye = parser.parseInput(taskList, input);
        }
        storage.updateTextFile(taskList);
        ui.exiting();
    }

    /**
     * Initialises the bot and start running the bot until the user terminates it
     */
    public static void main(String[] args) {
        Legin legin = new Legin();
        legin.runBot();
    }
}
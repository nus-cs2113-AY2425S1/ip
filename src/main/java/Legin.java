/**
 * Represents the command line bot that helps stores your tasks.
 * Functionalities include adding, deleting, finding and marking tasks as well as categorise them into todo, deadline
 * and event.
 * <p>
 * User input should follow the following: [command][arg...]
 */
public class Legin {
    private TaskList taskList;
    private Storage storage;
    private Ui ui = new Ui();
    private Parser parser = new Parser();

    /**
     * Constructor for Legin which instantiates a {@code Storage} member which pulls last saved data from a text file
     * and loads it for the user.
     */
    private Legin() {
        storage = new Storage();
        taskList = storage.getloadedTaskList();
    }

    /**
     * Starts Legin and continuously take in user input and calling {@code Parser} to execute the respective
     * commands until the user terminates the bot with the command bye
     *
     */
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

    public static void main(String[] args) {
        Legin legin = new Legin();
        legin.runBot();
    }
}
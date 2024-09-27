package tyrone;

import tyrone.command.Parser;
import tyrone.storage.Storage;
import tyrone.task.TaskList;

public class Tyrone {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Main loop of the chatbot.
     * Keeps taking in input from user and passing to Parser
     * until chatbot receives exit command, upon which loop
     * terminates.
     */
    private void run() {
        storage.createSaveFile();
        storage.initTaskListFromSaveFile(taskList);

        ui.printIntroMessage();
        while (true) {
            String input = ui.receiveInput();
            if (parser.isExitCommand(input)) {
                break;
            }
            parser.handleInput(input);
        }
        ui.printExitMessage();
    }

    public Tyrone() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
        parser = new Parser(taskList, storage);
    }

    /**
     * Main method of the chatbot
     *
     * @param args
     */
    public static void main(String[] args) {
        new Tyrone().run();
    }
}

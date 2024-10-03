package apsea;

import apsea.command.Command;
import apsea.exception.ApseaException;
import apsea.parser.Parser;
import apsea.task.TaskList;
import apsea.storage.Storage;
import apsea.ui.Ui;

public class Apsea {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    public Apsea() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
        storage.loadFile(taskList);
        parser = new Parser();

    }

    /**
     * Greets User and continuously takes in user commands until "bye" is received.
     * Saves existing taskList to hard disk upon termination.
     */
    public void run(){
        ui.printHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = parser.parse(fullCommand);
                c.runCommand(taskList, ui, storage);
                isExit = c.getExitStatus();
            } catch (ApseaException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
       new Apsea().run();
    }
}

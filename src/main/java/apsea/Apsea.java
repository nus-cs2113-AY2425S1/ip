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

    public static void printLine() {
        System.out.println("\t________________________________________________________");
    }

    public static void printError() {
        printLine();
        System.out.println("\tSorry, please try again with a valid command.");
        printLine();
    }

    public Apsea() {
        ui = new Ui();
        taskList = new TaskList();
        Storage.loadFile(taskList);
        parser = new Parser();

    }

    public void run(){
        ui.printHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = parser.parse(fullCommand);
                c.runCommand(taskList, ui);
                isExit = c.getExitStatus();
            } catch (ApseaException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        Storage.saveData(taskList);
    }

    public static void main(String[] args) {
       new Apsea().run();
    }
}

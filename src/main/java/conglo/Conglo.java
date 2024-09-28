package conglo;

import conglo.exception.StorageInvalidFormat;
import conglo.ui.Ui;
import conglo.exception.CongloException;
import conglo.storage.Storage;
import conglo.task.TaskList;
import conglo.command.Parser;

import java.io.FileNotFoundException;


public class Conglo {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;


    public Conglo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException | StorageInvalidFormat e) {
            ui.displayLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greetUser();
        String input;
        boolean isQuit = false;

        while (!isQuit) {
            input = ui.getUserInput();
            if (input.equals("bye")) {
                isQuit = true;
                ui.sayGoodbye();
            }
            try {
                Parser.processCommand(taskList, input);
            } catch (CongloException e) {
                System.out.println(e.getMessage());
            }
            ui.printLineSeparator();
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Conglo("./data/conglo.txt").run();
    }
}

package AlyBot;

import Commands.Command;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Aly {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final Path SAVE_FILE = Paths.get("data","taskdata.txt");

    public Aly(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (AlyException e) {
            ui.showLoadingError();
            ui.printLine();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        try {
            storage.createDirectory();
            File file = new File(SAVE_FILE.toString());
            storage.createFile(file);
        } catch (AlyException e) {
            ui.showError(e.getMessage());
        }
        ui.printLine();
        ui.startingMessage();
        ui.printLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.hasExited();
            } catch (AlyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Aly(SAVE_FILE).run();
    }
}
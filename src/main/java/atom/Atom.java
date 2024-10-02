package atom;

import atom.command.Command;
import atom.exception.AtomException;
import atom.parser.Parser;
import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;

public class Atom {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Atom(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (AtomException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printDivider(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AtomException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Atom("./data/AtomList.txt").run();
    }
}

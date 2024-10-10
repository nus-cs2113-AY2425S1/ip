import exception.LiaException;
import UI.Ui;
import storage.Storage;
import task.TaskList;
import commands.Command;
import parser.Parser;

public class Lia {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Lia(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LiaException e) {
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
                ui.printLine(); // show the divider line
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                storage.save(tasks.getTasks());
                isExit = command.isExit();
            } catch (LiaException e) {
                ui.showError(e.getMessage());
            } finally {
               ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Lia("./data/lia.txt").run();
    }
}

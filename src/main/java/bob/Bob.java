package bob;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.ui.Parser;
import bob.command.Command;

public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "data/tasks.txt";

    public Bob(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.showSeparatorLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                this.ui.showSeparatorLine();
            } catch (NullPointerException e) {
                this.ui.showSeparatorLine();
            }
        }
    }

    public static void main(String[] args) {
        new Bob(FILE_PATH).run();
    }
}
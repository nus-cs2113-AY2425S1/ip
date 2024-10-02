import freedom.tasks.TaskList;
import freedom.user.Parser;
import freedom.user.Storage;
import freedom.ui.Ui;

public class Freedom {
    private static final String DATA_FILE_PATH = "./data/freedom.txt";
    private static final String DATA_DIRECTORY_PATH = "./data";

    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    public Freedom() {
        ui = new Ui();
        storage = new Storage(DATA_FILE_PATH, DATA_DIRECTORY_PATH);

        try {
            taskList = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.printPlaceholder();
        }
    }

    public void run() {
        String input;
        ui.printStartMessage();
        taskList.printList(taskList.getTasks());
        while(!Parser.isEnd()) {
            input = ui.readInput();
            Parser.handleInput(taskList, storage, input);
        }
        ui.printEndMessage();
    }

    public static void main(String[] args) {
        new Freedom().run();
    }
}

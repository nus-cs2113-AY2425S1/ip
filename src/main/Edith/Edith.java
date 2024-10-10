import userinteraction.Storage;
import userinteraction.TaskList;
import userinteraction.Ui;

public class Edith {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Edith(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.initializeArrayList());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.giveIntroduction();
        ui.interactWithUser(tasks, storage);
        ui.sayGoodbye();
    }

    public static void main(String[] args) {
        new Edith("listOfTasks.txt").run();
    }
}

import userinteraction.Storage;
import userinteraction.TaskList;
import userinteraction.Ui;

/**
 * Main program of the project.
 */
public class Edith {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor which initializes the three class level objects.
     * @param filePath The relative path of the file which is to be used to store and load tasks to/from the memory
     */
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

    /**
     * This method uses the object of the userinteraction class to communicate with the user by calling appropriate methods.
     */
    public void run() {
        ui.giveIntroduction();
        ui.interactWithUser(tasks, storage);
        ui.sayGoodbye();
    }

    /**
     * @param args Unused
     */
    public static void main(String[] args) {
        new Edith("listOfTasks.txt").run();
    }
}

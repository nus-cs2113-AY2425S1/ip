import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        Ui ui = new Ui(); //Create instance of UI to handle user interaction
        ui.showWelcome();

        TaskList taskList;
        Storage storage = new Storage("data/duke.txt"); // Create a Storage instance, specifying the file path
        //load tasks from file at the start
        try {
            ArrayList<Task> loadedTasks = storage.loadTasks();
            taskList = new TaskList(loadedTasks);
        }
        catch (FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new TaskList(); //initialise empty list if file is not found
        }

        Parser parser = new Parser(ui, storage, taskList); // Create a parser instance, passing in the UI, Storage and task list
        parser.run(); // Start processing commands

    }
}


import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        Ui ui = new Ui();
        ui.showWelcome();
        ArrayList<Task> tasks = new ArrayList<>(); //Array of "task" object to store the tasks

        Storage storage = new Storage("data/duke.txt");
        //load tasks from file at the start
        try {
            tasks = storage.loadTasks();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        }

        Parser parser = new Parser(ui, storage, tasks);
        parser.run();

    }
}


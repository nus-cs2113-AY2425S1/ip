package medea;
import medea.command.Command;
import medea.core.Parser;
import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;
import medea.exceptions.MedeaException;

public class Medea {
    private final static String DEFAULT_FILE_PATH = "./data/medea.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Medea(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (MedeaException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Medea(DEFAULT_FILE_PATH).run();
    }

    public void run(){
        ui.showWelcome();
        handleCommands();
        ui.showFarewell();
        storage.saveTasks(tasks.toCSVString());
    }

    private void handleCommands(){
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                if (c.isExit()) return;
                ui.showLine();
                c.execute(tasks, ui, storage);
            } catch (MedeaException e) {
                ui.showError(e);
            }
            ui.showLine();
        }
    }
}

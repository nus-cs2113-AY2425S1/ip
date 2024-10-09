package doug.Main;

import doug.commands.Command;
import doug.parser.Parser;
import doug.ui.UI;
import doug.tasklist.TaskList;
import doug.storage.Storage;

import java.io.FileNotFoundException;

/**
 * Main class for the chatbot Doug.
 * Initializes Parser, UI, TaskList and Storage classes
 * Takes in user inputs from the CLI
 * Read and writes tasks to and from the save file
 */
public class Doug {
    private static final String saveFilePath = "./data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Initializes the Doug object
     * Creates new UI, Storage and TaskList objects
     * Fills TaskList object with tasks from save file, if any
     *
     * @param filePath Relative file path of the save .txt file
     */
    public Doug(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList();

        ui.printLogo();

        try {
            storage.loadTasks(tasks, ui, storage); // loads in any existing tasks from save file
        } catch (FileNotFoundException e) {
            ui.sayNewUserWelcome();
        }

    }

    /**
     * Scans for user input and executes the input command
     * Loops forever until user types in "bye" command
     */
    public void run() {
        boolean saidBye = false;

        while (!saidBye) {
            try {
                String command = ui.readCommand();
                Command user_command = Parser.extractCommandType(tasks, command, ui);
                user_command.execute(tasks, ui, storage);
                saidBye = user_command.saidBye();
            } catch (DougException e) {
                System.out.println(e.getMessage());
            }
        }

        ui.sayGoodbye();
    }

    /**
     * Starts the Chatbot
     *
     * @param args Command line arguments, which are not used
     */
    public static void main(String[] args) {
        new Doug(saveFilePath).run();
    }
}

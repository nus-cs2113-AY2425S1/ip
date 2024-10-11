import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The Duke class represents the main chatbot application.
 * It handles the initialization of key components like TaskList, Storage, Parser, and UI.
 * Duke interacts with the user to perform task management operations, such as adding, deleting, and updating tasks.
 */
public class Duke {

    // taskList variable for level-2 onwards
    private static TaskList taskListInstance;
    private static Storage storageInstance;
    private static Parser parserInstance;
    private static UI uiInstance;

    // Task count
    private static int taskCount = 0;

    // Paths for storing task data
    private static String folderPath = "./data";
    private static String filePath = folderPath + "/duke.txt";

    /**
     * The main execution method for the Duke chatbot.
     * This method initializes the required components and starts the chatbot loop to receive user input continuously.
     *
     * @throws DukeException if there is an error specific to Duke's execution.
     * @throws IOException if there is an error reading or writing files.
     */
    public void execute() throws DukeException, IOException {
        taskListInstance = new TaskList();
        storageInstance = new Storage(taskListInstance);
        uiInstance = new UI(taskListInstance);
        parserInstance = new Parser(taskListInstance, uiInstance);

        // Scanner for receiving input from the user
        Scanner inputReader = new Scanner(System.in);

        taskListInstance.setUI(uiInstance);
        taskListInstance.setParser(parserInstance);

        System.out.println("Hello I'm Lambo");
        System.out.println("What can I do for you?");

        // Load data from file storage
        storageInstance.loadFileData();

        // Super loop used for receiving inputs continuously and responding back to the user
        SuperLoop:
        while (true) {
            String input = inputReader.nextLine();
            if (parserInstance.processCommand(input) != 0) {
                break SuperLoop;
            }
            // Update the task data in the file
            storageInstance.updateFileData();
        }
    }
}

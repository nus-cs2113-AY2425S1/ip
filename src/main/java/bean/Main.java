package bean;

import bean.parser.Parser;
import bean.storage.Storage;
import bean.tasklist.TaskList;
import bean.ui.Ui;

import java.io.IOException;

/**
 * The main class for the task management application.
 * Manages the user interface (Ui), storage (Storage), and task list (TaskList) objects.
 * Handles initialization, data retrieval, and program execution.
 */
public class Main {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Enum representing the different types of tasks supported by the application.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * Initializes the application by setting up the data file, retrieving saved data,
     * and creating instances of Ui, Storage, and TaskList objects.
     *
     * @throws IOException if an I/O error occurs while initializing the data file or retrieving saved data.
     */
    public Main() throws IOException {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();

        Storage.initialiseDataFile();
        Storage.retrieveFromDataFile(taskList);
    }


    /**
     * The main entry point for the application.
     * Creates a new Main object, displays the welcome message, processes user input,
     * and displays the exit message.
     *
     * @throws IOException if an I/O error occurs during program execution.
     */
    public static void run() throws IOException {
        new Main();
        Ui.greet();
        Parser.processUserInput();
        Ui.exit();
    }

    public static void main(String[] args) throws IOException {
        run();
    }
}
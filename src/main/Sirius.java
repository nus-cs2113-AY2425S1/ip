package main;

import java.io.FileNotFoundException;
import exception.*;
import commands.*;
import ui.Ui;
import storage.Storage;
import tasklist.TaskList;
import parser.Parser;

public class Sirius {

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    // Command keywords used for parsing user input
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";
    public static final String FIND = "find";

    // Messages shown to the user for different operations
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String SLASH = "/";
    public static final String STATUS_DELIMINATOR = "\\|";
    public static final String SEPARATOR = "-----------------------------";

    // Error messages for some exceptions
    public static final String CREATE_DIRECTORY_MESSAGE = "Create a new directory named 'data'!";
    public static final String CREATE_FILE_MESSAGE = "Create a new file named 'Sirius.txt' to store the list!";
    public static final String WELCOME_MESSAGE = "Hello! I'm Sirius, your chatting robot!\nWhat can I do for you?";
    public static final String GOODBYE_MESSAGE = "Goodbye! Hope to see you soon!";
    public static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";
    public static final String DELETE_TASK_MESSAGE = "Got it. I've removed this task:";
    public static final String MARK_TASK_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNMARK_TASK_MESSAGE = "OK, I've marked this task as not done yet:";
    public static final String LIST_TASK_MESSAGE = "Here are the tasks in your list:";
    public static final String FIND_TASK_MESSAGE = "Here are the matching tasks in your list:";
    public static final String NOT_FIND_TASK_MESSAGE = "No tasks found with the keyword: ";

    public static final String INDEX_BOUND_ERROR_MESSAGE = "The task index is out of bound! Please enter a valid task index.";
    public static final String INDEX_FORMAT_ERROR_MESSAGE = "The task index must be a number! Please enter a valid index number!";
    public static final String LOADING_ERROR_MESSAGE ="Oh, Task file not found. Start with a new task list!";
    public static final String SAVING_ERROR_MESSAGE = "An error occurred while saving the tasks.";
    public static final String ILLEGAL_COMMAND_MESSAGE = "I don't understand it. Please enter an illegal command!";

    /**
     * Constructs a Sirius object with the specified file path.
     * Initializes the UI, Storage, and TaskList components.
     * If the task file is not found, it creates a new TaskList.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Sirius(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        // final variables (like tasks in this case) can only be assigned once.
        try {
            tasks = new TaskList(storage.loadTaskList());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main logic of the Sirius chatbot.
     * Continuously reads and executes user commands until getting an exit command.
     */
    public void run() {
        ui.sayHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IllegalCommandException e) {
                ui.showLine();
                ui.print(e.getMessage());
            }
            ui.printEmptyLine();
        }
    }

    /**
     * The main entry point of the Sirius application.
     * Initializes the Sirius object and starts the application.
     */
    public static void main(String[] args) {
        new Sirius("data/Sirius.txt").run();
    }
}

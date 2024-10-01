package bean;

import bean.exceptions.EmptyListException;
import bean.exceptions.InsufficientSpaceException;
import bean.exceptions.InvalidInputException;
import bean.exceptions.TaskNumOutOfBoundsException;
import bean.parser.Parser;
import bean.storage.Storage;
import bean.task.Deadline;
import bean.task.Event;
import bean.task.Task;
import bean.task.Todo;
import bean.tasklist.TaskList;
import bean.ui.Ui;
import static bean.constants.Constants.DATA_FILE_PATH;
import static bean.constants.Constants.LOGO;
import static bean.constants.Constants.SEPARATOR_LINE;
import static bean.constants.Constants.INDENT;
import static bean.constants.Constants.DELIMITER;
import static bean.constants.Constants.MAX_LIST_COUNT;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    // Set up data file and retrieve saved data
    public Main() throws IOException {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();

        Storage.initialiseDataFile();
        Storage.retrieveFromDataFile(taskList);
    }


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
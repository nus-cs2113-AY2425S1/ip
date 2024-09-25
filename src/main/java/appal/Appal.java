package appal;

import appal.exception.*;
import appal.parser.Parser;
import appal.task.Deadline;
import appal.task.Event;
import appal.task.Task;
import appal.task.ToDo;
import appal.ui.Ui;

import static appal.common.Messages.TASK_DONE_MESSAGE;
import static appal.common.Messages.UNMARK_TASK_MESSAGE;
import static appal.common.Messages.DELETE_TASK_MESSAGE;
import static appal.common.Messages.BYE_MESSAGE;
import static appal.common.Messages.LOAD_SAVED_TASKS_MESSAGE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Appal {
    // Constants for commands
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";
    public static final int COMMAND_INDEX = 0;

    // Integer constants for specific type of tasks
    public static final int TASK_INDEX = 1;
    public static final int BY_INDEX = 2;
    public static final int FROM_INDEX = 2;
    public static final int TO_INDEX = 3;

    // Constants for file reading
    public static final String FILE_PATH = "./data/saved_tasks.txt";
    public static final Path FILE_DIRECTORY = Paths.get("./data");
    public static final String COMMA_SEPARATOR = ", ";
    public static final String LINE_BREAK = "\n";
    public static final String TASK_MARKED_VALUE = "1";

    // Attributes
    private final Ui ui;
    private boolean isExited = false;
    private ArrayList<Task> taskList = new ArrayList<>();

    public Appal() {
        this.ui = new Ui();
    }

    public void handleMarkTask(String[] inputDetails, boolean isMark) throws AppalException {
        try {
            int taskId = Integer.parseInt(inputDetails[TASK_INDEX]);
            int listIndex = taskId - 1;
            Task taskToMark = markTask(listIndex, isMark);
            ui.printSeparator();
            if (isMark) {
                System.out.println(TASK_DONE_MESSAGE);
            } else {
                System.out.println(UNMARK_TASK_MESSAGE);
            }
            ui.printOneTask(taskToMark);
            ui.printSeparator();
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public Task markTask(int listIndex, boolean isMark) {
        Task taskToMark = taskList.get(listIndex);
        taskToMark.setDone(isMark);
        return taskToMark;
    }

    public void checkForTask(String[] inputDetails) throws EmptyTaskException {
        if (inputDetails[TASK_INDEX] == null) {
            throw new EmptyTaskException();
        }
    }

    public void addToDo(String[] inputDetails, boolean isFromUser) throws AppalException {
        checkForTask(inputDetails);
        ToDo newToDo = new ToDo(inputDetails[TASK_INDEX]);
        taskList.add(newToDo);
        ui.updateUserOnAddedTask(taskList, isFromUser);
    }

    public void addDeadline(String[] inputDetails, boolean isFromUser) throws AppalException {
        int totalToDos = Task.getTotalTasks();
        checkForTask(inputDetails);
        if (inputDetails[BY_INDEX] == null) {
            throw new UnspecifiedDeadlineException();
        }
        Deadline newDeadline = new Deadline(inputDetails[TASK_INDEX], inputDetails[BY_INDEX]);
        taskList.add(newDeadline);
        ui.updateUserOnAddedTask(taskList, isFromUser);
    }

    public void addEvent(String[] inputDetails, boolean isFromUser) throws AppalException{
        int totalToDos = Task.getTotalTasks();
        checkForTask(inputDetails);
        if (inputDetails[FROM_INDEX] == null || inputDetails[TO_INDEX] == null) {
            throw new UnspecifiedEventDurationException();
        }
        Event newEvent = new
                Event(inputDetails[TASK_INDEX], inputDetails[FROM_INDEX], inputDetails[TO_INDEX]);
        taskList.add(newEvent);
        ui.updateUserOnAddedTask(taskList, isFromUser);
    }

    public void deleteTask(String[] inputDetails) throws AppalException {
        try {
            int taskId = Integer.parseInt(inputDetails[TASK_INDEX]);
            int indexOfTaskToDelete = taskId - 1;
            ui.printReply(taskList, DELETE_TASK_MESSAGE, indexOfTaskToDelete);
            taskList.remove(indexOfTaskToDelete);
            Task.setTotalTasks(Task.getTotalTasks() - 1);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public void exitAppal() {
        isExited = true;
        ui.printMessage(BYE_MESSAGE);
    }

    private void loadFileContents() throws FileNotFoundException {
        File savedTasks = new File(FILE_PATH); // create a File for the given file path
        Scanner fileReader = new Scanner(savedTasks); // create a Scanner using the File as the source
        int savedTasksCount = 0;
        while (fileReader.hasNext()) {
            String line = fileReader.nextLine();
            String[] words = line.split(COMMA_SEPARATOR);
            boolean isTaskMarked = words[0].equals(TASK_MARKED_VALUE);
            String[] taskDetails = Arrays.copyOfRange(words, TASK_INDEX, words.length);
            handleInput(taskDetails, false);
            markTask(savedTasksCount, isTaskMarked);
            savedTasksCount += 1;
        }
        Task.setTotalTasks(savedTasksCount);
    }

    public void loadExistingTasksData() throws NoSavedTasksException{
        try {
            loadFileContents();
        } catch (FileNotFoundException e) {
            throw new NoSavedTasksException();
        }
        ui.printMessage(LOAD_SAVED_TASKS_MESSAGE);
    }

    public void saveTasksToFile() throws SaveTasksErrorException {
        try {
            Files.createDirectories(FILE_DIRECTORY);
            FileWriter fw = new FileWriter(FILE_PATH); // create a FileWriter in append mode
            for (int i = 0; i < Task.getTotalTasks(); i++) {
                fw.write(taskList.get(i).getStatusValue() + COMMA_SEPARATOR + taskList.get(i).getTaskInfo());
                fw.write(LINE_BREAK);
            }
            fw.close();
        } catch (IOException e) {
            throw new SaveTasksErrorException();
        }
    }

    public void handleInput(String[] inputDetails, boolean isFromUser) {
        String command = inputDetails[COMMAND_INDEX];
        try {
            switch (command) {
            case COMMAND_BYE:
                saveTasksToFile();
                exitAppal();
                break;
            case COMMAND_LIST:
                ui.printTaskList(taskList);
                break;
            case COMMAND_TODO:
                addToDo(inputDetails, isFromUser);
                break;
            case COMMAND_DEADLINE:
                addDeadline(inputDetails, isFromUser);
                break;
            case COMMAND_EVENT:
                addEvent(inputDetails, isFromUser);
                break;
            case COMMAND_MARK:
                handleMarkTask(inputDetails, true);
                break;
            case COMMAND_UNMARK:
                handleMarkTask(inputDetails, false);
                break;
            case COMMAND_DELETE:
                deleteTask(inputDetails);
                break;
            default:
                throw new AppalException();
            }
        } catch (AppalException e) {
            ui.printMessage(e.getMessage());
        }
    }

    public void runAppal() {
        ui.welcomeUser();
        try {
            loadExistingTasksData();
        } catch (NoSavedTasksException e) {
            ui.printMessage(e.getMessage());
        }
        while (!isExited) {
            String line = ui.getInput();
            String[] inputDetails = Parser.extractInputDetails(line);
            handleInput(inputDetails, true);
        }
    }
}

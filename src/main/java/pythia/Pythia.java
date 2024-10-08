package pythia;

import pythia.utility.Parser;
import pythia.utility.Ui;
import pythia.task.Task;
import pythia.task.ToDo;
import pythia.task.Deadline;
import pythia.task.Event;
import pythia.exceptions.PythiaException;
import pythia.utility.Storage;
import pythia.utility.TaskList;

/**
 * Main class of the Pythia application. This class is responsible for managing
 * the system's core components, including parsing user commands, storing and
 * retrieving tasks, and interacting with the user through the user interface.
 */
public class Pythia {
    private static String botName = "Pythia";
    private static String logo =
            "____        _   _     _       \n" +
            "|  _ \\ _   _| |_| |__ (_) __ _ \n" +
            "| |_) | | | | __| '_ \\| |/ _` |\n" +
            "|  __/| |_| | |_| | | | | (_| |\n" +
            "|_|    \\__, |\\__|_| |_|_|\\__,_|\n" +
            "       |___/                   ";

    private static boolean isByeSaid;
    private static TaskList taskList;
    private static Storage storage;
    private static Ui ui;

    /**
     * Constructs a Pythia object that initializes the system with task
     * storage and user interaction components.
     *
     * @param filePath Path to the file where tasks are saved and loaded from.
     */
    public Pythia(String filePath) {
        isByeSaid = false;
        storage = new Storage(filePath);
        taskList = storage.load();
        ui = new Ui();
    }

    /**
     * Greets the user by printing a welcome message to the console.
     */
    public static void greet() {
        String helloMsg =   "Welcome, seeker. I am " + botName + ".\n" +
                            "What brings you here?";
        ui.printResponse(helloMsg);
    }

    /**
     * Prints a farewell message to the user and sets the exit flag.
     */
    public static void sayBye() {
        String byeMsg = "Your path is set. Until we meet again.";
        ui.printResponse(byeMsg);
        isByeSaid = true;
    }

    /**
     * Displays the current list of tasks, along with the total number of
     * remaining tasks.
     */
    public static void listTasks() {
        StringBuilder comment = new StringBuilder();
        int remainingTasks = taskList.getNumberOfRemainingTasks();

        comment.append("Now you have ").append(remainingTasks);
        if (remainingTasks == 1) {
            comment.append(" task in the list.");
        } else {
            comment.append(" tasks in the list.");
        }

        ui.printTaskList(taskList, "", comment.toString());
    }

    /**
     * Adds a new generic task with the given name to the task list.
     *
     * @param taskName The name of the task to be added.
     */
    public static void addTask(String taskName) {
        taskList.add(new Task(taskName));
        ui.printAddedTask("added: " + taskName);
        storage.save(taskList);
    }

    /**
     * Adds a specific {@link Task} object to the task list.
     *
     * @param task The task to be added to the list.
     */
    public static void addTask(Task task) {
        taskList.add(task);
        ui.printAddedTask("added: " + task.getName());
        storage.save(taskList);
    }

    /**
     * Adds a new {@link ToDo} task to the task list.
     *
     * @param todoName The name of the ToDo task to be added.
     */
    public static void addToDo(String todoName) {
        addTask(new ToDo(todoName));
    }

    /**
     * Adds a new {@link Deadline} task to the task list with a specified due date.
     *
     * @param deadlineName The name of the deadline task.
     * @param dueDate      The due date of the deadline task.
     */
    public static void addDeadline(String deadlineName, String dueDate) {
        addTask(new Deadline(deadlineName, dueDate));
    }

    /**
     * Adds a new {@link Event} task to the task list with specified start and end dates.
     *
     * @param eventName The name of the event task.
     * @param startDate The start date of the event.
     * @param endDate   The end date of the event.
     */
    public static void addEvent(String eventName, String startDate, String endDate) {
        addTask(new Event(eventName, startDate, endDate));
    }

    /**
     * Marks a task as completed by its task number.
     *
     * @param taskNumber The number of the task in the list (1-based index).
     */
    public static void markTask(Integer taskNumber) {
        try {
            taskList.markAsDone(taskNumber - 1);
            String msg = "Nice! I've marked this task as done:\n\t" + taskList.get(taskNumber - 1).toString();
            ui.printResponse(msg);
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.printResponse("There is no such task :(");
        }
    }

    /**
     * Deletes a task from the list by its task number.
     *
     * @param taskNumber The number of the task to be deleted (1-based index).
     */
    public static void deleteTask(Integer taskNumber) {
        try {
            String msg = "Nice! I've deleted this task:\n\t" + taskList.get(taskNumber - 1).toString();
            taskList.remove(taskNumber - 1);
            ui.printResponse(msg);
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e){
            ui.printResponse("There is no such task :(");
        }
    }

    /**
     * Finds tasks that contain the specified keyword in their name and displays
     * them to the user.
     *
     * @param taskKeyword The keyword to search for in task names.
     */
    public static void findTasks(String taskKeyword) {
        TaskList filteredTaskList = new TaskList();
        for (Task task : taskList) {
            String taskName = task.getName();
            if (taskName.contains(taskKeyword)) {
                filteredTaskList.add(task);
            }
        }

        if (filteredTaskList.getNumberOfTasks() == 0) {
            ui.printResponse("There is no such task :(");
        } else {
            String commentBefore = "Here are the matching tasks in your list:";
            String commentAfter = "";
            ui.printTaskList(filteredTaskList, commentBefore, commentAfter);
        }
    }

    /**
     * Runs the main logic of the Pythia application. Continuously accepts user
     * input and executes parsed commands until the user says "bye".
     */
    private static void run() {
        ui.init();
        greet();
        Parser parser = new Parser();

        while (!isByeSaid) {
            try {
                String request = ui.getRequest();
                parser.parse(request);
                parser.execute();
            } catch (PythiaException e) {
                ui.printResponse(e.getUserMessage());
            }
        }
    }

    /**
     * The main method that runs the Pythia application. It initializes the UI,
     * greets the user, and enters a loop that processes user input until the user
     * says "bye".
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new Pythia("data/pythia.txt").run();
    }
}

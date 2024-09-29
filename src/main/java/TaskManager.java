import java.util.ArrayList;

public class TaskManager {

    private final static Ui ui = new Ui();
    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";
    private static final String FILE_PATH = "data/eva.txt";

    private Storage storage;
    private ArrayList<Task> tasks;
    private int count;

    public TaskManager() {
        tasks = new ArrayList<>();
        count = 0;
        storage = new Storage(FILE_PATH);
        loadTasks();
    }

    public void printTaskList() {
        ui.printTaskList(tasks);
    }

    public void markTask(String line) throws EvaException {

        int taskNumber = extractDigit(line) - 1;

        if (taskNumber < 0 || taskNumber >= count) {
            throw new EvaException("Oh no! The task number you provided is out of range.\n" +
                    "Please provide a valid task number between 1 and " + count + ".");
        }

        tasks.get(taskNumber).setMarkAsDone();

        ui.showMessage("Great! This task is marked as done: ");
        ui.showMessage(tasks.get(taskNumber).toString());
        ui.showMessage("Well done! ;)");
        ui.showMessage(HORIZONTAL_LINE);

        saveTasks();
    }

    public void unmarkTask(String line) throws EvaException {

        int taskNumber = extractDigit(line) - 1;

        if (taskNumber < 0 || taskNumber >= count) {
            throw new EvaException("Oh no! The task number you provided is out of range.\n" +
                    "Please provide a valid task number between 1 and " + count + ".");
        }

        tasks.get(taskNumber).setMarkAsNotDone();

        ui.showMessage("Ok, This task is marked as not done yet: ");
        ui.showMessage(tasks.get(taskNumber).toString());
        ui.showMessage(HORIZONTAL_LINE);

        saveTasks();
    }

    public void deleteTask(String line) throws EvaException {
        int taskNumber = extractDigit(line) - 1;

        if (taskNumber < 0 || taskNumber >= count) {
            throw new EvaException("Oh no! The task number you provided is out of range.\n" +
                    "Please provide a valid task number between 1 and " + count + ".");
        }

        String temp = tasks.get(taskNumber).toString();

        tasks.remove(taskNumber);
        count = count - 1;

        ui.showMessage("Okay. I have deleted task " + (taskNumber + 1) + ".");
        ui.showMessage(temp);
        printNumTasks(count - 1);
        ui.showMessage(HORIZONTAL_LINE);

        saveTasks();
    }

    public static int extractDigit(String input) {
        String numberString = input.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberString);
    }

    public void printTodo(String line) throws EvaException {

        if (line.isEmpty()) {
            throw new EvaException("On no! The description of a todo cannot be empty." +
                    " \nPlease try again by typing todo (name of task).");
        }

        tasks.add(new Todo(line));

        ui.showMessage("Okay, I've added this todo: ");
        ui.showMessage(tasks.get(count).toString());
        printNumTasks(count);
        ui.showMessage(HORIZONTAL_LINE);

        count++;

        saveTasks();
    }

    public void printDeadline(String description, String by) throws EvaException {

        if (description.isEmpty() || by.isEmpty()) {
            throw new EvaException("Oh no! Either the description part is empty or the by part is empty!" +
                    "\nPlease try again!");
        }

        tasks.add(new Deadline(description, by));

        ui.showMessage("Okay, I've added this deadline: ");
        ui.showMessage(tasks.get(count).toString());
        printNumTasks(count);
        ui.showMessage(HORIZONTAL_LINE);

        count++;

        saveTasks();
    }

    public void printEvent(String description, String from, String to) throws EvaException {

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new EvaException("Oh no! The description, from or to parts are empty!" +
                    "\nPlease try again!");
        }

        tasks.add(new Event(description, from, to));

        ui.showMessage("Okay, I've added this event: ");
        ui.showMessage(tasks.get(count).toString());
        printNumTasks(count);
        ui.showMessage(HORIZONTAL_LINE);

        count++;

        saveTasks();
    }

    public void printNumTasks(int count) {
        ui.showMessage("Now you have " + (count + 1) + " tasks in the list.");
    }

    private void saveTasks() {
        storage.saveTasksToFile(tasks);
    }

    private void loadTasks() {
        tasks = storage.loadTasksFromFile();
        count = tasks.size();
    }
}

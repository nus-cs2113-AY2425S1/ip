package lovespiritual;

import lovespiritual.exception.lovespiritualException;
import lovespiritual.task.Deadline;
import lovespiritual.task.Event;
import lovespiritual.task.Task;
import lovespiritual.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Main class for task manager application.
 * Handles user input, task management, and file storage.
 */
public class lovespiritual {
    public static final String SEPARATOR = "_".repeat(30);
    private static ArrayList<Task> tasks = new ArrayList<>();
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    /**
     * Constructor initialises task manager with the specified file path.
     * Loads tasks from storage and displays the welcome screen.
     *
     * @param filePath Path to the file where the list of tasks are stored.
     */
    public lovespiritual(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        taskList = new TaskList();
        storage.loadTasks(tasks);
        ui.printWelcomeMessage();
    }

    /**
     * Main loop that runs the task manager, the processing of user input.
     * Commands include adding, deleting, marking, and listing tasks.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine().trim();

            try {
                String command = Parser.parseCommand(input);

                switch (command) {
                case "bye":
                    storage.saveTasks(tasks);
                    ui.printExitMessage();
                    return;
                case "list":
                    ui.printList(tasks);
                    break;
                case "mark":
                    markTask(input, tasks);
                    storage.saveTasks(tasks);
                    break;
                case "unmark":
                    unmarkTask(input, tasks);
                    storage.saveTasks(tasks);
                    break;
                case "todo":
                    todo(input, tasks);
                    storage.saveTasks(tasks);
                    break;
                case "deadline":
                    deadline(input, tasks);
                    storage.saveTasks(tasks);
                    break;
                case "event":
                    event(input, tasks);
                    storage.saveTasks(tasks);
                    break;
                case "delete":
                    taskList.deleteTask(input, tasks);
                    storage.saveTasks(tasks);
                    break;
                case "find":
                    taskList.find(input, tasks);
                    break;
                default:
                    throw new lovespiritualException("(^_^) Let's get started with a command!");
                }
            } catch (lovespiritualException e) {
                ui.printError(e.getMessage());
            } catch (Exception e) {
                ui.printUnexpectedError();
            }
        }
    }

    /**
     * Start of the running of application.
     * Starts task manager with the file path for storing tasks.
     *
     * @param args Command-line arguments which are not used.
     */
    public static void main(String[] args) {
        new lovespiritual("data/lovespiritual.txt").run();
    }

    /**
     * Adds a new event task to the task list.
     * Ensures that the task description includes both 'from' and 'to' times.
     *
     * The expected date format is 'yyyy-MM-dd HHmm' (e.g., 2024-10-05 1800 for 5 October 2024, 6:00 PM).
     * The 'from' time must be before the 'to' time.
     *
     * @param input User input string for adding an event.
     * @param tasks List of tasks to which the new event will be added.
     * @throws lovespiritualException If the input is formatted incorrectly or missing required details.
     */
    private static void event(String input, ArrayList <Task> tasks) throws lovespiritualException {
        String fullTaskDescription = input.substring("event".length()).trim();
        if (fullTaskDescription.isEmpty()) {
            throw new lovespiritualException("Uh-oh! (~_~) Your event description seems to be missing!");
        }
        if (!fullTaskDescription.contains("from")) {
            throw new lovespiritualException("Hmmm (~_~) Your event is missing the 'from' time! Please add it.");
        }
        if (!fullTaskDescription.contains("to")) {
            throw new lovespiritualException("Oops! (0.0) The 'to' part is missing! Let's add it.");
        }
        String taskDescription;
        String from;
        String to;
        String[] taskDetails = fullTaskDescription.split("from ");
        if (taskDetails[0].trim().isEmpty()) {
            throw new lovespiritualException("Yikes! :/ You forgot to tell me what the event is about!");
        }
        taskDescription = taskDetails[0].trim();
        String[] time = taskDetails[1].split("to ");
        if (time.length < 2) {
            throw new lovespiritualException("The end date/time is missing (0.0) When does this event wrap up?");
        }
        if (time[0].trim().isEmpty()) {
            throw new lovespiritualException("Start date/time? (~_~) We can't go without it!");
        }
        if (time[1].trim().isEmpty()) {
            throw new lovespiritualException("The end date/time is missing (0.0) When does this event wrap up?");
        }
        from = time[0].trim();
        to = time[1].trim();
        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            if (!fromDateTime.isBefore(toDateTime)) {
                throw new lovespiritualException("Hmm (~_~) The start date/time must be before the end date/time.");
            }
            tasks.add(new Event(taskDescription, from, to));
            System.out.println(SEPARATOR);
            System.out.println("Woohoo! d(^_^)b Your task is safely added!");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Amazing! :D You've got " + tasks.size() + " tasks lined up!");
            System.out.println(SEPARATOR);
        } catch (DateTimeParseException e) {
            throw new lovespiritualException("Invalid date format! Please use 'yyyy-MM-dd HHmm' format.");
        }
    }

    /**
     * Adds a new deadline task to the task list.
     * Ensures that the task description includes a 'by' time for the deadline.
     *
     * The expected date format is 'yyyy-MM-dd HHmm' (e.g., 2024-10-05 1800 for 5 October 2024, 6:00 PM).
     *
     * @param input User input string for adding a deadline.
     * @param tasks List of tasks to which the new deadline will be added.
     * @throws lovespiritualException If the input is formatted incorrectly or missing required details.
     */
    private static void deadline(String input, ArrayList <Task> tasks) throws lovespiritualException {
        String fullTaskDescription = input.substring("deadline".length()).trim();
        if (fullTaskDescription.isEmpty()) {
            throw new lovespiritualException("Oops! (0.0) Your deadline needs a little description!");
        }
        if (!fullTaskDescription.contains("by")) {
            throw new lovespiritualException("The 'by' is missing! (~_~) When's it due?");
        }
        String taskDescription;
        String by;
        String[] taskDetails = fullTaskDescription.split("by", 2);
        if (taskDetails.length < 2 || taskDetails[0].trim().isEmpty()) {
            throw new lovespiritualException("Hmm... (~_~) Don’t forget to tell me what this deadline is about!");
        }
        if (taskDetails[1].trim().isEmpty()) {
            throw new lovespiritualException("Uh-oh! (0.0) I need to know the deadline date or time.");
        }
        taskDescription = taskDetails[0].trim();
        by = taskDetails[1].trim();
        try {
            tasks.add(new Deadline(taskDescription, by));
            System.out.println(SEPARATOR);
            System.out.println("Woohoo! d(^_^)b Your task is safely added!");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Amazing! :D You've got " + tasks.size() + " tasks lined up!");
            System.out.println(SEPARATOR);
        } catch (Exception e) {
            throw new lovespiritualException("Invalid date format! Please use 'yyyy-MM-dd HHmm' format.");
        }
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param input User input string for adding a todo.
     * @param tasks List of tasks to which the new todo will be added.
     * @throws lovespiritualException If the input is missing the task description.
     */
    private static void todo(String input, ArrayList <Task> tasks) throws lovespiritualException {
        String taskDescription = input.substring("todo".length()).trim();
        if (taskDescription.isEmpty()) {
            throw new lovespiritualException("Hmm... (~_~) What's the todo? Looks like the description's missing!");
        }
        tasks.add(new Todo(taskDescription));
        System.out.println(SEPARATOR);
        System.out.println("Woohoo! d(^_^)b Your task is safely added!");
        System.out.println(" [T][ ] " + taskDescription);
        System.out.println("Amazing! :D You've got " + tasks.size() + " tasks lined up!");
        System.out.println(SEPARATOR);
    }

    /**
     * Unmarks a task as not completed.
     *
     * @param input User input string for unmarking a task.
     * @param tasks List of tasks.
     * @throws lovespiritualException If the task number is invalid or out of range.
     */
    private static void unmarkTask(String input, ArrayList <Task> tasks) throws lovespiritualException {
        String taskNumber = input.substring("unmark".length()).trim();
        if (taskNumber.isEmpty()) {
            throw new lovespiritualException("Hmm... (~_~) A valid number, please?");
        }
        int indexNumber;
        try {
            indexNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new lovespiritualException("Whoa there! (0.0) That’s not a number! Can you double-check?");
        }
        if (indexNumber >= 0 && indexNumber < tasks.size()) {
            tasks.get(indexNumber).unmark();
            System.out.println(SEPARATOR);
            System.out.println("Yay! (^_^) This task isn't done yet!");
            System.out.println(tasks.get(indexNumber));
            System.out.println(SEPARATOR);
        } else {
            throw new lovespiritualException("Hmm... (~_~) That number seems a bit off. Try again?");
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param input User input string for marking a task.
     * @param tasks List of tasks.
     * @throws lovespiritualException If the task number is invalid or out of range.
     */
    private static void markTask(String input, ArrayList <Task> tasks) throws lovespiritualException {
        String taskNumber = input.substring("mark".length()).trim();
        if (taskNumber.isEmpty()) {
            throw new lovespiritualException("Hmm... (~_~) A valid number, please?");
        }
        int indexNumber;
        try {
            indexNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new lovespiritualException("Whoa there! (0.0) That’s not a number! Can you double-check?");
        }
        if (indexNumber >= 0 && indexNumber < tasks.size()) {
            tasks.get(indexNumber).mark();
            System.out.println(SEPARATOR);
            System.out.println("Yay! (^_^) This task is all done!");
            System.out.println(tasks.get(indexNumber));
            System.out.println(SEPARATOR);
        } else {
            throw new lovespiritualException("Hmm... (~_~) That number seems a bit off. Try again?");
        }
    }
}

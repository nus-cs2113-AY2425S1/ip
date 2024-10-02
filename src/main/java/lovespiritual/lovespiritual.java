package lovespiritual;

import lovespiritual.exception.lovespiritualException;
import lovespiritual.task.Deadline;
import lovespiritual.task.Event;
import lovespiritual.task.Task;
import lovespiritual.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class lovespiritual {
    public static final String SEPARATOR = "_".repeat(30);
    private static ArrayList<Task> tasks = new ArrayList<>();
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        UI ui = new UI();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/lovespiritual.txt");

    public lovespiritual(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        taskList = new TaskList();
        storage.loadTasks(tasks);
        ui.printWelcomeScreen();
    }

        // loop that keeps recurring when the program is running
        while (true) {
            String input = in.nextLine().trim();

            try {
                String command = Parser.parseCommand(input);

                switch (command) {
                case "bye":
                    storage.saveTasks(tasks);
                    ui.printExitScreen();
                    return;
                case "list":
                    ui.printList(tasks);
                    break;
                case "mark":
                    markTask(input, tasks.size(), tasks);
                    storage.saveTasks(tasks);
                    break;
                case "unmark":
                    unmarkTask(input, tasks.size(), tasks);
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
                    taskList.deleteTask(input, tasks, tasks.size());
                    storage.saveTasks(tasks);
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

    private static void event(String input, ArrayList <Task> tasks) throws lovespiritualException {
        String fullTaskDescription = input.substring("event".length()).trim();
        if (fullTaskDescription.isEmpty()) {
            throw new lovespiritualException("Uh-oh! (・_・;) Your event description seems to be missing!");
        }
        if (!fullTaskDescription.contains("from")) {
            throw new lovespiritualException("Hmmm (・_・) Your event is missing the 'from' time! Please add it.");
        }
        if (!fullTaskDescription.contains("to")) {
            throw new lovespiritualException("Oops! (•‿•) The 'to' part is missing! Let's add it.");
        }
        String taskDescription;
        String from;
        String to;
        String[] taskDetails = fullTaskDescription.split("from ");
        if (taskDetails[0].trim().isEmpty()) {
            throw new lovespiritualException("Yikes! (⊙_⊙;) You forgot to tell me what the event is about!");
        }
        taskDescription = taskDetails[0].trim();
        String[] time = taskDetails[1].split("to ");
        if (time.length < 2 || time[0].trim().isEmpty()) {
            throw new lovespiritualException("Start date/time? (。_。) We can't go without it!");
        }
        if (time[1].trim().isEmpty()) {
            throw new lovespiritualException("The end date/time is missing (･o･;) When does this event wrap up?");
        }
        from = time[0].trim();
        to = time[1].trim();
        tasks.add(new Event(taskDescription, from, to));
        System.out.println(SEPARATOR);
        System.out.println("Yay! (•‿•) I've added your task!");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Woot! (^▽^) You now have " + tasks.size() + " tasks in your list!");
        System.out.println(SEPARATOR);
    }

    private static void deadline(String input, ArrayList <Task> tasks) throws lovespiritualException {
        String fullTaskDescription = input.substring("deadline".length()).trim();
        if (fullTaskDescription.isEmpty()) {
            throw new lovespiritualException("Oops! (｡•́︿•̀｡) Your deadline needs a little description!");
        }
        if (!fullTaskDescription.contains("by")) {
            throw new lovespiritualException("The 'by' is missing! (・_・;) When's it due?");
        }
        String taskDescription;
        String by;
        String[] taskDetails = fullTaskDescription.split("by", 2);
        if (taskDetails.length < 2 || taskDetails[0].trim().isEmpty()) {
            throw new lovespiritualException("Hmm... (・_・;) Don’t forget to tell me what this deadline is about!");
        }
        if (taskDetails[1].trim().isEmpty()) {
            throw new lovespiritualException("Uh-oh! (・へ・) I need to know the deadline date or time.");
        }
        taskDescription = taskDetails[0].trim();
        by = taskDetails[1].trim();
        tasks.add(new Deadline(taskDescription, by));
        System.out.println(SEPARATOR);
        System.out.println("Yippee! (★^O^★) Task added successfully!");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Wow! (｡♥‿♥｡) You now have " + tasks.size() + " tasks! Keep going!");
        System.out.println(SEPARATOR);
    }

    private static void todo(String input, ArrayList <Task> tasks) throws lovespiritualException {
        String taskDescription = input.substring("todo".length()).trim();
        if (taskDescription.isEmpty()) {
            throw new lovespiritualException("Hmm... (¬‿¬) What's the todo? Looks like the description's missing!");
        }
        tasks.add(new Todo(taskDescription));
        System.out.println(SEPARATOR);
        System.out.println("Woohoo! (＾▽＾) Your task is safely added!");
        System.out.println(" [T][ ] " + taskDescription);
        System.out.println("Amazing! (•̀ᴗ•́) You’ve got " + tasks.size() + " tasks lined up!");
        System.out.println(SEPARATOR);
    }

    private static void unmarkTask(String input, int taskCount, ArrayList <Task> tasks) throws lovespiritualException {
        String taskNumber = input.substring("unmark".length()).trim();
        if (taskNumber.isEmpty()) {
            throw new lovespiritualException("Oopsie! (⊙_⊙) Please give me a valid number!");
        }
        int indexNumber;
        try {
            indexNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new lovespiritualException("Hmm, that's not a number! (・_・;) Try again, please!");
        }
        if (indexNumber >= 0 && indexNumber < taskCount) {
            tasks.get(indexNumber).unmark();
            System.out.println(SEPARATOR);
            System.out.println("Got it! (◠‿◠) This task isn't done yet!");
            System.out.println(tasks.get(indexNumber));
            System.out.println(SEPARATOR);
        } else {
            throw new lovespiritualException("Yikes! (≧Д≦) That number doesn't look right. Can you double-check it?");
        }
    }

    private static void markTask(String input, int taskCount, ArrayList <Task> tasks) throws lovespiritualException {
        String taskNumber = input.substring("mark".length()).trim();
        if (taskNumber.isEmpty()) {
            throw new lovespiritualException("Hmm... (ʘ‿ʘ) A valid number, please?");
        }
        int indexNumber;
        try {
            indexNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new lovespiritualException("Whoa there! (O.O) That’s not a number! Can you double-check?");
        }
        if (indexNumber >= 0 && indexNumber < taskCount) {
            tasks.get(indexNumber).mark();
            System.out.println(SEPARATOR);
            System.out.println("Yay! (^_^) This task is all done!");
            System.out.println(tasks.get(indexNumber));
            System.out.println(SEPARATOR);
        } else {
            throw new lovespiritualException("Hmm... (°ヘ°) That number seems a bit off. Try again?");
        }
    }
}

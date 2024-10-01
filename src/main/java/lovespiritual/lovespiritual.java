package lovespiritual;

import lovespiritual.exception.lovespiritualException;
import lovespiritual.task.Deadline;
import lovespiritual.task.Event;
import lovespiritual.task.Task;
import lovespiritual.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class lovespiritual {
    public static final String SEPARATOR = "_".repeat(30);
    public static final String FILE_PATH = "./data/lovespiritual.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int taskCount = 0;
        UI ui = new UI();

        loadTasks();
        ui.printWelcomeScreen();

        // loop that keeps recurring when the program is running
        while (true) {
            String input = in.nextLine().trim();

            try {
                if (input.equalsIgnoreCase("bye")) {
                    saveTasks();
                    ui.printExitScreen();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    ui.printList(tasks);
                } else if (input.startsWith("mark")) {
                    markTask(input, taskCount, tasks);
                    saveTasks();
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input, taskCount, tasks);
                    saveTasks();
                } else if (input.startsWith("todo")) {
                    taskCount = todo(input, tasks);
                    saveTasks();
                } else if (input.startsWith("deadline")) {
                    taskCount = deadline(input, tasks);
                    saveTasks();
                } else if (input.startsWith("event")) {
                    taskCount = event(input, tasks);
                    saveTasks();
                } else if (input.startsWith("delete")) {
                    deleteTask(input, tasks, taskCount);
                    saveTasks();
                }
                else {
                    throw new lovespiritualException("(^_^) Let's get started with a command!");
                }
            } catch (lovespiritualException e) {
                System.out.println(SEPARATOR);
                ui.printError(e.getMessage());
                System.out.println(SEPARATOR);
            } catch (Exception e) {
                System.out.println(SEPARATOR);
                ui.printUnexpectedError();
                System.out.println(SEPARATOR);
            }
        }
    }

    private static void deleteTask(String input, ArrayList<Task> tasks, int taskCount) throws lovespiritualException {
        String taskNumber = input.substring("delete".length()).trim();
        if (taskNumber.isEmpty()) {
            throw new lovespiritualException("Oopsie! (⊙_⊙) Please give me a valid number!");
        }
        int indexNumber;
        try {
            indexNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new lovespiritualException("Hmm, that's not a number! (・_・;) Try again, please!");
        }
        Task removedTask = tasks.get(indexNumber);
        if (indexNumber >= 0 && indexNumber < taskCount) {
            tasks.remove(indexNumber);
            System.out.println(SEPARATOR);
            System.out.println("Got it! (◠‿◠) This task is removed!");
            System.out.println(removedTask);
            System.out.println(SEPARATOR);
        } else {
            throw new lovespiritualException("Yikes! (≧Д≦) That number doesn't look right. Can you double-check it?");
        }
    }

    private static void saveTasks() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : tasks) {
                writer.write(savedFormat(task));
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(SEPARATOR);
            System.out.println("Error saving tasks (×_×;): " + e.getMessage());
            System.out.println(SEPARATOR);
        }
    }

    private static void loadTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(extractTasks(line));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(SEPARATOR);
            System.out.println("Error loading tasks (•︵•): " + e.getMessage());
            System.out.println(SEPARATOR);
        }
    }


    private static String savedFormat(Task task) {
        String taskType = "";
        String formattedTask = "";

        if (task instanceof Todo) {
            taskType = "T";
            formattedTask = taskType + " | " + (task.isMarked ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            taskType = "D";
            Deadline deadline = (Deadline) task;
            formattedTask = taskType + " | " + (task.isMarked ? "1" : "0") + " | " + task.description + " | " + deadline.by;
        } else if (task instanceof Event) {
            taskType = "E";
            Event event = (Event) task;
            formattedTask = taskType + " | " + (task.isMarked ? "1" : "0") + " | " + task.description + " | " + event.from + " | " + event.to;
        }
        return formattedTask;
    }


    private static Task extractTasks(String line) {
        try {
            String[] parts = line.split(" \\| ");

            if (parts.length < 3) {
                throw new lovespiritualException("Error reading task from file: Incomplete task data.");
            }

            String type = parts[0];
            boolean isMarked = parts[1].equals("1");
            String description = parts[2];
            Task task;

            switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new lovespiritualException("Error reading Deadline from file: Missing 'by' date.");
                }
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
                if (parts.length < 5) {
                    throw new lovespiritualException("Error reading Event from file: Missing 'from' or 'to' time.");
                }
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                throw new lovespiritualException("Error reading task from file: Unknown task type.");
            }

            if (isMarked) {
                task.mark();
            }

            return task;
        } catch (lovespiritualException e) {
            System.out.println(SEPARATOR);
            System.out.println(e.getMessage());
            System.out.println(SEPARATOR);
            return null;
        } catch (Exception e) {
            System.out.println(SEPARATOR);
            System.out.println("Error parsing task from file: " + e.getMessage());
            System.out.println(SEPARATOR);
            return null;
        }
    }

    private static int event(String input, ArrayList <Task> tasks) throws lovespiritualException {
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
        return tasks.size();
    }

    private static int deadline(String input, ArrayList <Task> tasks) throws lovespiritualException {
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
        return tasks.size();
    }

    private static int todo(String input, ArrayList <Task> tasks) throws lovespiritualException {
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
        return tasks.size();
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

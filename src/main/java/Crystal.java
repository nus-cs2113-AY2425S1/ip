import java.util.Scanner;
import java.util.ArrayList;
import exceptions.InvalidCommandException;
import exceptions.IncompleteCommandException;
import exceptions.IllegalCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Crystal {
    public static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;
    private static final int TODO_CHAR_COUNT = 4;
    private static final int DEADLINE_CHAR_COUNT = 8;
    private static final int EVENT_CHAR_COUNT = 5;

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void sayHello() {
        printHorizontalLine();
        System.out.println("Hola! I'm Crystal.\n"
            + "What can I do for you today?");
        printHorizontalLine();
    }

    public static void sayBye() {
        printHorizontalLine();
        System.out.println("Adios, hasta luego!");
        printHorizontalLine();
    }

    public static void list() {
        printHorizontalLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printHorizontalLine();
    }

    public static void markTask(int taskNumber) {
        try {
            int index = taskNumber - 1;
            if (index >= taskCount | index < 0) {
                printHorizontalLine();
                throw new IllegalCommandException("This number is not valid.");
            }
            Task t = tasks.get(index);
            t.markAsDone();
            printHorizontalLine();
            System.out.println("YAY!! This task is now marked done:\n" + t);
            printHorizontalLine();
        } catch (IllegalCommandException e) {
            printExceptionMessage(e);
        }
    }

    public static void unmarkTask(int taskNumber) {
        try {
            int index = taskNumber - 1;
            if (index >= taskCount | index < 0) {
                printHorizontalLine();
                throw new IllegalCommandException("This number is not valid.");
            }
            Task t = tasks.get(index);
            t.unmark();
            printHorizontalLine();
            System.out.println("OK, I've marked this task as not done yet:\n" + t);
            printHorizontalLine();
        } catch (IllegalCommandException e) {
            printExceptionMessage(e);
        }
    }

    public static void printAddedTaskMessage() {
        printHorizontalLine();
        System.out.println("Got it! I have added this task:");
        System.out.println(tasks.get(taskCount).toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printHorizontalLine();
    }

    public static void printExceptionMessage(Exception e) {
        String message = e.getMessage();
        if (message != null && !message.isEmpty()) {
            System.out.print(message);
        }
        System.out.println(" Can you repeat it again?");
        printHorizontalLine();
    }

    public static void addTodo(String line) {
        String description = line.substring(TODO_CHAR_COUNT + 1).trim();
        Task t =  new Todo(description);
        tasks.add(t);
        printAddedTaskMessage();
    }

    public static void addDeadline(String line) {
        try {
            String[] twoParts = line.substring(DEADLINE_CHAR_COUNT + 1).trim().split(" /by ");
            if (twoParts.length != 2) {
                printHorizontalLine();
                throw new IncompleteCommandException("You are missing some parameters! ");
            }
            String description = twoParts[0];
            String by = twoParts[1];
            Task t = new Deadline(description, by);
            tasks.add(t);
            printAddedTaskMessage();
        } catch (IncompleteCommandException e) {
            printExceptionMessage(e);
        }
    }

    public static void addEvent(String line) {
        try {
            String[] threeParts = line.substring(EVENT_CHAR_COUNT + 1).trim().split(" /from | /to ");
            if (threeParts.length != 3) {
                printHorizontalLine();
                throw new IncompleteCommandException("You are missing some parameters! ");
            }
            String description = threeParts[0];
            String from = threeParts[1];
            String to = threeParts[2];
            Task t = new Event(description, from, to);
            tasks.add(t);
            printAddedTaskMessage();
        } catch (IncompleteCommandException e) {
            printExceptionMessage(e);
        }
    }

    public static void delete(int taskNumber) {
        try {
            int index = taskNumber - 1;
            if (index >= taskCount | index < 0) {
                printHorizontalLine();
                throw new IllegalCommandException("This number is not valid.");
            }
            Task t = tasks.get(index);
            tasks.remove(index);
            taskCount--;
            printHorizontalLine();
            System.out.println("Noted. I have deleted the task below: \n" + t);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            printHorizontalLine();
        } catch (IllegalCommandException e) {
            printExceptionMessage(e);
        }
    }

    private static void saveTaskList(ArrayList<Task> list) {
        try {
            // create separate dir for saved data if !exist()
            File dir = new File("./data");
            if (!dir.exists()) {
                if (dir.mkdir()) {
                    System.out.println("Directory created successfully.");
                } else {
                    System.out.println("Directory creation failed.");
                }
            }

            // create data file if !exist()
            File file = new File(dir, "Crystal.txt");
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Data file created successfully.");
                } else {
                    System.out.println("Data file creation failed.");
                }
            }

            // rewriting data in the list to the file
            FileWriter fw = new FileWriter(file);
            for (Task task : list) {
                fw.write(task.toFormatFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException | SecurityException e) {
            printHorizontalLine();
            System.out.print("Error: " + e.getMessage());
            printExceptionMessage(e);
        }
    }

    public static void updateTask(boolean isDoneUpdated, Task t) {
        t.setIsDone(isDoneUpdated);
        tasks.add(t);
        taskCount++;
    }

    public static void addTaskToList(String[] words) {
        String command = words[0];
        boolean isDoneUpdated = Boolean.parseBoolean(words[1]);
        try {
            switch (command) {
            case "T":
                Task todo = new Todo(words[2]);
                updateTask(isDoneUpdated,todo);
                break;
            case "D":
                Task deadline = new Deadline(words[2], words[3]);
                updateTask(isDoneUpdated,deadline);
                break;
            case "E":
                Task event = new Event(words[2], words[3], words[4]);
                updateTask(isDoneUpdated,event);
                break;
            default:
                throw new IOException();
            }
        } catch (IOException e) {
            printHorizontalLine();
            System.out.print("File is Corrupted.");
            printExceptionMessage(e);
        }
    }

    public static void loadTaskList() {
        try {
            File file = new File("./data/Crystal.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String[] words = scan.nextLine().split(" \\| ");
                addTaskToList(words);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No data to load.");
        }
    }

    public static void callingCrystal() {
        boolean isBye = false;
        String line;
        String command;
        int taskNumber;
        Scanner in = new Scanner(System.in);
        while (!isBye) {
            line = in.nextLine();
            String[] words = line.split(" ");
            command = words[0];
            try {
                switch (command) {
                case "bye":
                    isBye = true;
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    taskNumber = Integer.parseInt(words[1]);
                    markTask(taskNumber);
                    saveTaskList(tasks);
                    break;
                case "unmark":
                    taskNumber = Integer.parseInt(words[1]);
                    unmarkTask(taskNumber);
                    saveTaskList(tasks);
                    break;
                case "todo":
                    addTodo(line);
                    saveTaskList(tasks);
                    break;
                case "deadline":
                    addDeadline(line);
                    saveTaskList(tasks);
                    break;
                case "event":
                    addEvent(line);
                    saveTaskList(tasks);
                    break;
                case "delete":
                    taskNumber = Integer.parseInt(words[1]);
                    delete(taskNumber);
                    break;
                default:
                    printHorizontalLine();
                    throw new InvalidCommandException("Did you misspell or miss out something? ");
                }
            } catch (InvalidCommandException e) {
                printExceptionMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        sayHello();
        loadTaskList();
        callingCrystal();
        sayBye();
    }

}

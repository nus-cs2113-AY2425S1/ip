import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Anke : a Chat Bot
 *
 * @param storage handle loading and savinf file
 * @param isExit true if the program is going to exit
 * @param ui handle UI of the program
 *
 */

public class Anke {
    private final Storage storage;
    private final Ui ui;
    public static boolean isExit = false;

    /**
     * constructor of Anke class
     * initialize ui and storage
     * load data from file path
     *
     * @param filePath the file path where previous data is stored
     * @throws AnkeException If file not found
     */
    public Anke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            Storage.load();
        } catch (AnkeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Chat Bot handling user input
     *
     * @throws AnkeException If parser cannot handle command
     *
     */
    public void run() {
        ui.showWelcome();
        while (!isExit) {
            try {
                String fullcommand = ui.readCommand();
                Parser.handle(fullcommand);
            } catch (AnkeException e) {
                ui.showError(e.getMessage());
            }
        }
        Ui.printBye();
    }

//    public static void printWelcome() {
//        System.out.println("Hello! I'm Anke.");
//        System.out.println("What can I do for you?\n");
//    }

//    private static void printBye() {
//        System.out.println("Bye. Hope to see you again soon!\n");
//    }

//    private static String getInput() {
//        String line;
//        line = in.nextLine().trim();
//        return line;
//    }
//
//    private static void printList(ArrayList<Task> tasks) {
//        for (int i = 0; i < count; i++) {
//            System.out.println((i + 1) + ". " + tasks.get(i).toString());
//        }
//        System.out.println("");
//    }
    /**
     * main function
     *
     */
    public static void main(String[] args) {
        new Anke("./Anke.txt").run();


//        ArrayList<Task> tasks = new ArrayList<>();
//        load(tasks);
//        printWelcome();
//        String line = "";
//        while (!line.equals("bye")) {
//            line = getInput();
//            Task task = null;
//            if (line.equals("bye")) {
//                break;
//            }
//            handleTasks(line, tasks);
//        }

//        printBye();
    }

//    private static void load(ArrayList<Task> tasks) {
//        try{
//            System.out.println("Loading data from file");
//            loadTasks(tasks);
//            System.out.println("Finish loading data\n");
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found, no data loaded");
//        }
//    }
//
//    private static void loadTasks(ArrayList<Task> tasks) throws FileNotFoundException {
//        File f = new File("./Anke.txt");
//        Scanner s = new Scanner(f);
//        while (s.hasNext()) {
//            String line = s.nextLine();
//            try {
//                String data = "";
//                switch (line.charAt(1)) {
//                case 'T':
//                    data = "todo " + line.substring(7);
//                    System.out.println(data);
//                    createTodo(tasks, data);
//                    break;
//                case 'D':
//                    data = "deadline " + line.substring(7);
//                    data = data.replace("(","/");
//                    data = data.replace(")","");
//                    data = data.replace(":","");
//                    System.out.println(data);
//                    createDeadline(tasks, data);
//                    break;
//                case 'E':
//                    data = "event " + line.substring(7);
//                    data = data.replace("(","/");
//                    data = data.replace(")","");
//                    data = data.replace("to","/to");
//                    data = data.replace(":","");
//                    System.out.println(data);
//                    createEvent(tasks, data);
//                    break;
//                }
//                if (line.charAt(4) == 'X') {
//                    markTask(tasks, count);
//                }
//            } catch (IndexOutOfBoundsException e) {
//                continue;
//            }
//        }
//    }
//
//    private static void handleTasks(String line, ArrayList<Task> tasks) {
//        if (line.equals("list")) {
//            printList(tasks);
//        } else if (line.length() > 5 && line.startsWith("mark ")) {
//            mark(line, tasks);
//            saveFile(tasks);
//        } else if (line.length() > 7 && line.startsWith("unmark ")) {
//            unmark(line, tasks);
//            saveFile(tasks);
//        } else if (line.startsWith("todo")) {
//            createTodo(tasks, line);
//            saveFile(tasks);
//        } else if (line.length() > 9 && line.startsWith("deadline ")) {
//            createDeadline(tasks, line);
//            saveFile(tasks);
//        } else if (line.length() > 6 && line.startsWith("event ")) {
//            createEvent(tasks, line);
//            saveFile(tasks);
//        } else if (line.length() > 7 && line.startsWith("delete ")) {
//            deleteTask(tasks, line);
//            saveFile(tasks);
//        } else {
//            handleWrongFormat();
//        }
//    }
//
//    private static void mark(String line, ArrayList<Task> tasks) {
//        try {
//            int beginIndex = 5;
//            int index = getIndex(tasks, line, beginIndex, "mark");
//            markTask(tasks, index);
//        } catch (NumberFormatException e) {
//            System.out.println("Please enter a valid number after mark\n");
//        } catch (IndexOutOfRangeException e) {
//            System.out.println("Please enter a task index from 1 to " + count + "\n");
//        } catch (TaskSameStateException e) {
//            System.out.println("Task already completed\n");
//        }
//    }
//
//    private static void unmark(String line, ArrayList<Task> tasks) {
//        try {
//            int beginIndex = 7;
//            int index = getIndex(tasks, line, beginIndex, "unmark");
//            unmarkTask(tasks, index);
//        } catch (NumberFormatException e) {
//            System.out.println("Please enter a valid number after unmark\n");
//        } catch (IndexOutOfRangeException e) {
//            System.out.println("Please enter a task index from 1 to " + count + "\n");
//        } catch (TaskSameStateException e) {
//            System.out.println("Task already not done\n");
//        }
//    }
//
//    private static void markTask(ArrayList<Task> tasks, int index) {
//        tasks.get(index - 1).setDone(true);
//        System.out.println("Nice! I've marked this task as done:");
//        System.out.println(tasks.get(index - 1).toString() + "\n");
//    }
//
//    private static void unmarkTask(ArrayList<Task> tasks, int index) {
//        tasks.get(index - 1).setDone(false);
//        System.out.println("OK, I've marked this task as not done yet:");
//        System.out.println(tasks.get(index - 1).toString() + "\n");
//    }
//
//    private static void createTask(ArrayList<Task> tasks, String line) {
//        Task task = new Task(line);
//        addTask(tasks, task);
//    }
//
//    private static void createTodo(ArrayList<Task> tasks, String line) {
//        try{
//            int beginIndex = 5;
//            String taskName = getTaskName(line, beginIndex, -2);
//            Task task = new Todo(taskName);
//            addTask(tasks, task);
//        } catch (EmptyTaskException e) {
//            System.out.println("The description of a todo cannot be empty.\n");
//        } catch (EmptyByOrFromException ignored) {
//        }
//    }
//
//    private static void createDeadline(ArrayList<Task> tasks, String line) {
//        try {
//            int beginIndex = 9;
//            int byIndex = line.indexOf("/by");
//            String taskName = getTaskName(line, beginIndex, byIndex);
//            Task task = new Deadline(taskName, line.substring(byIndex + 4));
//            addTask(tasks, task);
//        } catch (EmptyTaskException e) {
//            System.out.println("The description of a deadline cannot be empty.\n");
//        } catch (EmptyByOrFromException e) {
//            System.out.println("Please enter the deadline after \"/by\". \n");
//        }
//    }
//
//    private static void createEvent(ArrayList<Task> tasks, String line) {
//        try {
//            int beginIndex = 6;
//            int fromIndex = line.indexOf("/from");
//            int toIndex = line.indexOf("/to");
//            String taskName = getTaskName(line, beginIndex, fromIndex);
//            String from = getFrom(line, fromIndex, toIndex);
//            Task task = new Event(taskName, from, line.substring(toIndex + 4));
//            addTask(tasks, task);
//        } catch (EmptyTaskException e) {
//            System.out.println("The description of an event cannot be empty.\n");
//        } catch (EmptyByOrFromException e) {
//            System.out.println("Please enter the start time of event after \"/from\". \n");
//        } catch (EmptyToException e) {
//            System.out.println("Please enter the end time of event after \"/to\". \n");
//        }
//    }
//
//    private static void addTask(ArrayList<Task> tasks, Task task) {
////        tasks[count] = task;
//        tasks.add(task);
//        System.out.println("Got it. I've added this task:");
//        System.out.println(task.toString());
//        count++;
//        System.out.println("Now you have " + count + " tasks in the list.\n");
//    }
//
//    private static int getIndex(ArrayList<Task> tasks, String line, int beginIndex, String func) throws NumberFormatException, IndexOutOfRangeException, TaskSameStateException {
//        try {
//            int index = Integer.parseInt(line.substring(beginIndex));
//            String state;
//            if (index < 0 || index > count) {
//                throw new IndexOutOfRangeException();
//            }
//            if (func == "mark") {
//                state = "X";
//                if (tasks.get(index - 1).getStatusIcon() == state) {
//                    throw new TaskSameStateException();
//                }
//            } else if (func == "unmark"){
//                state = " ";
//                if (tasks.get(index - 1).getStatusIcon() == state) {
//                    throw new TaskSameStateException();
//                }
//            }
//            return index;
//        } catch (NumberFormatException e) {
//            throw new NumberFormatException();
//        }
//    }
//
//    private static String getTaskName(String line, int beginIndex, int endIndex) throws EmptyByOrFromException, EmptyTaskException {
//        if (endIndex == -1) {
//            throw new EmptyByOrFromException();
//        } else if (endIndex == -2 && beginIndex + 1 > line.length()) {
//              throw new EmptyTaskException();
//        } else if (endIndex == -2 && line.substring(beginIndex).trim() != "") {
//            return line.substring(beginIndex).trim();
//        } else {
//            String trimName = line.substring(beginIndex, endIndex).trim();
//            if (trimName != "") {
//                return trimName;
//            } else {
//                throw new EmptyTaskException();
//            }
//        }
//    }
//
//    private static String getFrom(String line, int fromIndex, int toIndex) throws EmptyToException, EmptyByOrFromException {
//        if (toIndex == -1 || toIndex + 4 > line.length() - 1) {
//            throw new EmptyToException();
//        } else if (fromIndex + 6 >= toIndex){
//            throw new EmptyByOrFromException();
//        } else {
//            return line.substring(fromIndex + 6, toIndex).trim();
//        }
//    }
//
//    private static void handleWrongFormat() {
//        System.out.println("Please follow the format (parameter inside {} must be non-empty!) : \n");
//        System.out.println("list : visualizing tasks");
//        System.out.println("mark {int n} : set task number {n} as done");
//        System.out.println("unmark {int n} : set task number {n} as not done");
//        System.out.println("todo {String s} : create todo with description {s}");
//        System.out.println("deadline {String s1} /by {String s2} : create deadline with description {s1} and due date {s2}");
//        System.out.println("event {String s1} /from {String s2} /to {String s3} : create event with description {s1} from {s2} to {s3}");
//        System.out.println("delete {int n} : remove task number {n} from the list\n");
//    }
//
//    private static void deleteTask(ArrayList<Task> tasks, String line) {
//        try {
//            int index = getIndex(tasks, line, 7, "delete");
//            System.out.println("Noted. I've removed this task: ");
//            System.out.println(tasks.get(index - 1));
//            tasks.remove(index - 1);
//            --count;
//            System.out.println("Now you have " + count + " tasks in the list.\n");
//        } catch (NumberFormatException e) {
//            System.out.println("Please enter a valid number after delete\n");
//        } catch (IndexOutOfRangeException e) {
//            System.out.println("Please enter a task index from 1 to " + count + "\n");
//        } catch (TaskSameStateException e) {
//            System.out.println("ERROR\n"); //won't fall into this case
//        }
//    }
//
//    private static void saveFile(ArrayList<Task> tasks) {
//        try {
//            FileWriter fw = new FileWriter("./Anke.txt");
//            for (int i = 0; i < count; ++i) {
//                fw.write(tasks.get(i)+ System.lineSeparator());
//            }
//            fw.close();
//        } catch (IOException e) {
//            System.out.println("Something went wrong during saving changes: " + e.getMessage());
//        }
//    }
}

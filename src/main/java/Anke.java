import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Anke {
    static int count = 0;
    static Scanner in = new Scanner(System.in);

    public static void printWelcome() {
        System.out.println("Hello! I'm Anke.");
        System.out.println("What can I do for you?\n");
    }

    private static void printBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    private static String getInput() {
        String line;
        line = in.nextLine().trim();
        return line;
    }

    private static void printList(Task[] tasks) {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        printWelcome();

        Task[] tasks = new Task[100];
        String line = "";
        while (!line.equals("bye")) {
            line = getInput();
            Task task = null;
            if (line.equals("bye")) {
                break;
            }
            handleTasks(line, tasks);
        }

        printBye();
    }

    private static void handleTasks(String line, Task[] tasks) {
        if (line.equals("list")) {
            printList(tasks);
        } else if (line.length() > 5 && line.startsWith("mark ")) {
            mark(line, tasks);
            saveFile(tasks);
        } else if (line.length() > 7 && line.startsWith("unmark ")) {
            unmark(line, tasks);
            saveFile(tasks);
        } else if (line.length() > 3 && line.startsWith("todo")) {
            createTodo(tasks, line);
            saveFile(tasks);
        } else if (line.length() > 9 && line.startsWith("deadline ")) {
            createDeadline(tasks, line);
            saveFile(tasks);
        } else if (line.length() > 6 && line.startsWith("event ")) {
            createEvent(tasks, line);
            saveFile(tasks);
        } else {
            handleWrongFormat();
        }
    }

    private static void mark(String line, Task[] tasks) {
        try {
            int beginIndex = 5;
            int index = getIndex(tasks, line, beginIndex);
            markTask(tasks, index);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number after mark\n");
        } catch (IndexOutOfRangeException e) {
            System.out.println("Please enter a task index from 1 to " + count + "\n");
        } catch (TaskSameStateException e) {
            System.out.println("Task already completed\n");
        }
    }

    private static void unmark(String line, Task[] tasks) {
        try {
            int beginIndex = 7;
            int index = getIndex(tasks, line, beginIndex);
            unmarkTask(tasks, index);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number after unmark\n");
        } catch (IndexOutOfRangeException e) {
            System.out.println("Please enter a task index from 1 to " + count + "\n");
        } catch (TaskSameStateException e) {
            System.out.println("Task already not done\n");
        }
    }

    private static void markTask(Task[] tasks, int index) {
        tasks[index - 1].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index - 1].toString() + "\n");
    }

    private static void unmarkTask(Task[] tasks, int index) {
        tasks[index - 1].setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[index - 1].toString() + "\n");
    }

    private static void createTask(Task[] tasks, String line) {
        Task task = new Task(line);
        addTask(tasks, task);
    }

    private static void createTodo(Task[] tasks, String line) {
        try{
            int beginIndex = 5;
            String taskName = getTaskName(line, beginIndex, -2);
            Task task = new Todo(taskName);
            addTask(tasks, task);
        } catch (EmptyTaskException e) {
            System.out.println("The description of a todo cannot be empty.\n");
        } catch (EmptyByOrFromException e) {
        }
    }

    private static void createDeadline(Task[] tasks, String line) {
        try {
            int beginIndex = 9;
            int byIndex = line.indexOf("/by");
            String taskName = getTaskName(line, beginIndex, byIndex);
            Task task = new Deadline(taskName, line.substring(byIndex + 4));
            addTask(tasks, task);
        } catch (EmptyTaskException e) {
            System.out.println("The description of a deadline cannot be empty.\n");
        } catch (EmptyByOrFromException e) {
            System.out.println("Please enter the deadline after \"/by\". \n");
        }
    }

    private static void createEvent(Task[] tasks, String line) {
        try {
            int beginIndex = 6;
            int fromIndex = line.indexOf("/from");
            int toIndex = line.indexOf("/to");
            String taskName = getTaskName(line, beginIndex, fromIndex);
            String from = getFrom(line, fromIndex, toIndex);
            Task task = new Event(taskName, from, line.substring(toIndex + 4));
            addTask(tasks, task);
        } catch (EmptyTaskException e) {
            System.out.println("The description of an event cannot be empty.\n");
        } catch (EmptyByOrFromException e) {
            System.out.println("Please enter the start time of event after \"/from\". \n");
        } catch (EmptyToException e) {
            System.out.println("Please enter the end time of event after \"/to\". \n");
        }
    }

    private static void addTask(Task[] tasks, Task task) {
        tasks[count] = task;
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        count++;
        System.out.println("Now you have " + count + " tasks in the list.\n");
    }

    private static int getIndex(Task[] tasks, String line, int beginIndex) throws NumberFormatException, IndexOutOfRangeException, TaskSameStateException {
        try {
            int index = Integer.parseInt(line.substring(beginIndex));
            String state;
            if (index < 0 || index > count) {
                throw new IndexOutOfRangeException();
            }
            if (beginIndex == 5) {
                state = "X";
            } else {
                state = " ";
            }
            if (tasks[index - 1].getStatusIcon() == state) {
                throw new TaskSameStateException();
            }
            return index;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    private static String getTaskName(String line, int beginIndex, int endIndex) throws EmptyByOrFromException, EmptyTaskException {
        if (endIndex == -1) {
            throw new EmptyByOrFromException();
        } else if (endIndex == -2 && beginIndex + 1 > line.length()) {
              throw new EmptyTaskException();
        } else if (endIndex == -2 && line.substring(beginIndex).trim() != "") {
            return line.substring(beginIndex).trim();
        } else if (line.substring(beginIndex, endIndex).trim() != "") {
            return line.substring(beginIndex, endIndex).trim();
        } else {
            throw new EmptyTaskException();
        }
    }

    private static String getFrom(String line, int fromIndex, int toIndex) throws EmptyToException, EmptyByOrFromException {
        if (toIndex == -1 || toIndex + 4 > line.length() - 1) {
            throw new EmptyToException();
        } else if (fromIndex + 6 <= toIndex - 1){
            throw new EmptyByOrFromException();
        } else {
            return line.substring(fromIndex + 6, toIndex - 1).trim();
        }
    }

    private static void handleWrongFormat() {
        System.out.println("Please follow the format (parameter inside {} must be non-empty!) : \n");
        System.out.println("list : visualizing tasks");
        System.out.println("mark {int n} : set task number {n} as done");
        System.out.println("unmark {int n} : set task number {n} as not done");
        System.out.println("todo {String s} : create todo with description {s}");
        System.out.println("deadline {String s1} /by {String s2} : create deadline with description {s1} and due date {s2}");
        System.out.println("event {String s1} /from {String s2} /to {String s3} : create event with description {s1} from {s2} to {s3}\n");
    }

    private static void saveFile(Task[] tasks) {
        try {
            FileWriter fw = new FileWriter("./Anke.txt");
            for (int i = 0; i < count; ++i) {
                fw.write(tasks[i]+ System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong during saving changes: " + e.getMessage());
        }
    }
}

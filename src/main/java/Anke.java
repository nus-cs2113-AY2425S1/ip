import java.util.Scanner;
import java.util.ArrayList;

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

    private static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        printWelcome();
        ArrayList<Task> tasks = new ArrayList<>();
//        Task[] tasks = new Task[100];
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

    private static void handleTasks(String line, ArrayList<Task> tasks) {
        if (line.equals("list")) {
            printList(tasks);
        } else if (line.length() > 5 && line.startsWith("mark ")) {
            mark(line, tasks);
        } else if (line.length() > 7 && line.startsWith("unmark ")) {
            unmark(line, tasks);
        } else if (line.startsWith("todo")) {
            createTodo(tasks, line);
        } else if (line.length() > 9 && line.startsWith("deadline ")) {
            createDeadline(tasks, line);
        } else if (line.length() > 6 && line.startsWith("event ")) {
            createEvent(tasks, line);
        } else if (line.length() > 7 && line.startsWith("delete ")) {
            deleteTask(tasks, line);
        } else {
            handleWrongFormat();
        }
    }

    private static void mark(String line, ArrayList<Task> tasks) {
        try {
            int beginIndex = 5;
            int index = getIndex(tasks, line, beginIndex, "mark");
            markTask(tasks, index);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number after mark\n");
        } catch (IndexOutOfRangeException e) {
            System.out.println("Please enter a task index from 1 to " + count + "\n");
        } catch (TaskSameStateException e) {
            System.out.println("Task already completed\n");
        }
    }

    private static void unmark(String line, ArrayList<Task> tasks) {
        try {
            int beginIndex = 7;
            int index = getIndex(tasks, line, beginIndex, "unmark");
            unmarkTask(tasks, index);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number after unmark\n");
        } catch (IndexOutOfRangeException e) {
            System.out.println("Please enter a task index from 1 to " + count + "\n");
        } catch (TaskSameStateException e) {
            System.out.println("Task already not done\n");
        }
    }

    private static void markTask(ArrayList<Task> tasks, int index) {
        tasks.get(index - 1).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1).toString() + "\n");
    }

    private static void unmarkTask(ArrayList<Task> tasks, int index) {
        tasks.get(index - 1).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1).toString() + "\n");
    }

    private static void createTask(ArrayList<Task> tasks, String line) {
        Task task = new Task(line);
        addTask(tasks, task);
    }

    private static void createTodo(ArrayList<Task> tasks, String line) {
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

    private static void createDeadline(ArrayList<Task> tasks, String line) {
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

    private static void createEvent(ArrayList<Task> tasks, String line) {
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

    private static void addTask(ArrayList<Task> tasks, Task task) {
//        tasks[count] = task;
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        count++;
        System.out.println("Now you have " + count + " tasks in the list.\n");
    }

    private static int getIndex(ArrayList<Task> tasks, String line, int beginIndex, String func) throws NumberFormatException, IndexOutOfRangeException, TaskSameStateException {
        try {
            int index = Integer.parseInt(line.substring(beginIndex));
            String state;
            if (index < 0 || index > count) {
                throw new IndexOutOfRangeException();
            }
            if (func == "mark") {
                state = "X";
                if (tasks.get(index - 1).getStatusIcon() == state) {
                    throw new TaskSameStateException();
                }
            } else if (func == "unmark"){
                state = " ";
                if (tasks.get(index - 1).getStatusIcon() == state) {
                    throw new TaskSameStateException();
                }
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
        System.out.println("delete {int n} : remove task number {n} from the list");
    }

    private static void deleteTask(ArrayList<Task> tasks, String line) {
        try {
            int index = getIndex(tasks, line, 7, "delete");
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.get(index - 1));
            tasks.remove(index - 1);
            --count;
            System.out.println("Now you have " + count + " tasks in the list.\n");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number after delete\n");
        } catch (IndexOutOfRangeException e) {
            System.out.println("Please enter a task index from 1 to " + count + "\n");
        } catch (TaskSameStateException e) {
            System.out.println("ERROR\n"); //won't fall into this case
        }
    }
}

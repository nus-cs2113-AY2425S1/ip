import java.util.Scanner;


public class Anke {
    static int count = 0;

    public static void printWelcome() {
        System.out.println("Hello! I'm Anke.");
        System.out.println("What can I do for you?\n");
    }

    private static void printBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    private static String getInput() {
        String line;
        Scanner in = new Scanner(System.in);
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
            if (!markSuccessful(line, tasks)) {
                createTask(tasks, line);
            }
        } else if (line.length() > 7 && line.startsWith("unmark ")) {
            if (!unmarkSuccessful(line, tasks)) {
                createTask(tasks, line);
            }
        } else if (line.length() > 5 && line.startsWith("todo ")) {
            createTodo(tasks, line);
        } else if (line.length() > 9 && line.startsWith("deadline ")) {
            createDeadline(tasks, line);
        } else if (line.length() > 6 && line.startsWith("event ")) {
            createEvent(tasks, line);
        } else {
            createTask(tasks, line);
        }
    }

    private static boolean markSuccessful(String line, Task[] tasks) {
        try {
            int index = Integer.parseInt(line.substring(5));
            if (index <= count && index > 0) {
                markTask(tasks, index);
                return true;
            }
        }
        catch (NumberFormatException e) {
        }
        return false;
    }

    private static boolean unmarkSuccessful(String line, Task[] tasks) {
        int index;
        try {
            index = Integer.parseInt(line.substring(7));
            if (index <= count && index > 0) {
                unmarkTask(tasks, index);
                return true;
            }
        }
        catch (NumberFormatException e) {
        }
        return false;
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
        Task task = new Todo(line.substring(5));
        addTask(tasks, task);
    }

    private static void createDeadline(Task[] tasks, String line) {
        int byIndex = line.indexOf("/by");
        Task task = new Deadline(line.substring(9, byIndex - 1), line.substring(byIndex + 4));
        addTask(tasks, task);
    }

    private static void createEvent(Task[] tasks, String line) {
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        Task task = new Event(line.substring(6, fromIndex - 1), line.substring(fromIndex + 6, toIndex - 1), line.substring(toIndex + 4));
        addTask(tasks, task);
    }

    private static void addTask(Task[] tasks, Task task) {
        tasks[count] = task;
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + count + " tasks in the list.\n");
        count++;
    }

}

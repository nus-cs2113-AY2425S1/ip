import java.util.Scanner;

public class Conglo {

    protected static final int MAX_TASKS = 100;
    protected static String command;
    protected static int taskCount = 0;

    public static void main(String[] args) {
        greetings();
        Scanner scanner = new Scanner(System.in);
        Task[] listing = new Task[MAX_TASKS];
        command = scanner.nextLine();
        while (!command.equals("bye")) {
            processCommand(command, listing);
            command = scanner.nextLine();
        }
        scanner.close();
        sayGoodbye();
    }

    public static void printLineSeparator() {
        System.out.println("-------------------------------------");
    }

    public static void greetings() {
        System.out.println("Hola! I'm Conglo the friendly bot.");
        System.out.println("What brings you here today?");
        printLineSeparator();
    }

    public static void echo(Task task) {
        String taskSuffix = taskCount > 1 ? " tasks" : " task";
        System.out.println("All done! Task added to list:");
        System.out.println(" " + task.toString());
        System.out.println("The list has " + taskCount + taskSuffix + " now.");
        printLineSeparator();
    }

    public static void listTasks(Task[] listing) {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + listing[i].toString());
        }
        printLineSeparator();
    }

    public static void markList(String[] words, Task[] listing) {
        int i = Integer.parseInt(words[1].substring(0, 1)) - 1;
        if (i >= taskCount || i < 0) {
            System.out.println("Invalid task number");
            return;
        }
        if (words[0].equals("mark")) {
            listing[i].markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            listing[i].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("[" + listing[i].getStatusIcon() + "] " + listing[i].description );
    }

    public static void setDeadline(String sentence, Task[] listing) {
        String[] words = sentence.split(" /by ");
        Deadline deadline = new Deadline(words[0], words[1]);
        listing[taskCount] = deadline;
        taskCount++;
        echo(deadline);
    }

    public static void setEvent(String sentence, Task[] listing) {
        String[] words = sentence.split(" /from | /to ");
        Event event = new Event(words[0], words[1], words[2]);
        listing[taskCount] = event;
        taskCount++;
        echo(event);
    }

    public static void processCommand(String command, Task[] listing) {
        String[] words = command.split(" ", 2);
        switch(words[0]) {
        case "list":
            listTasks(listing);
            break;
        case "unmark":
        case "mark":
            markList(words, listing);
            break;
        case "todo":
            Todo todo = new Todo(words[1]);
            listing[taskCount] = todo;
            taskCount++;
            echo(todo);
            break;
        case "deadline":
            setDeadline(words[1], listing);
            break;
        case "event":
            setEvent(words[1], listing);
            break;
        default:
            echo(new Task(command));
        }
    }

    public static void sayGoodbye() {
        System.out.println("Goodbye. See you next time!");
        printLineSeparator();
    }
}

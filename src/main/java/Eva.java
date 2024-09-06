import java.util.Scanner;

public class Eva {

    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";
    public static final int MAX_TASKS = 100;
    private final static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {

        printWelcome();

        String line;
        Scanner in = new Scanner(System.in);
        int count = 0;
        Task[] tasks = new Task[MAX_TASKS];

        while(true) {

            line = in.nextLine();

            switch (getCommand(line)) {
            case "list":
                taskManager.printTaskList();
                break;
            case "mark":
                taskManager.markTask(line);
                break;
            case "unmark":
                taskManager.unmarkTask(line);
                break;
            case "bye":
                sayBye();
                return;
            case "todo":
                taskManager.printTodo(line);
                break;
            case "deadline":
                taskManager.printDeadline(line);
                break;
            case "event":
                taskManager.printEvent(line);
                break;
            default:
                taskManager.printTasks(line);
                break;
            }
        }
    }

    public static String getCommand(String line) {

        if (line.equalsIgnoreCase("list")) {
            return "list";
        } else if (line.startsWith("mark")) {
            return "mark";
        } else if (line.startsWith("unmark")) {
            return "unmark";
        } else if (line.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (line.startsWith("todo")) {
            return "todo";
        } else if (line.startsWith("deadline")) {
            return "deadline";
        } else if (line.startsWith("event")) {
            return "event";
        } else {
            return "default";
        }
    }

    public static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Eva!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }
}

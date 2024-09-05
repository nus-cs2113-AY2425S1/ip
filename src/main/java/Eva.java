import java.util.Scanner;

public class Eva {

    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";

    public static void main(String[] args) {

        printWelcome();

        String line;
        Scanner in = new Scanner(System.in);
        int count = 0;
        Task[] tasks = new Task[100];

        while(true) {

            line = in.nextLine();

            switch (getCommand(line)) {
            case "list":
                printTaskList(tasks, count);
                break;
            case "mark":
                markTask(tasks, line);
                break;
            case "unmark":
                unmarkTask(tasks, line);
                break;
            case "bye":
                sayBye();
                return;
            case "todo":
                count = printTodo(tasks, count, line);
                break;
            case "deadline":
                count = printDeadline(tasks, count, line);
                break;
            case "event":
                count = printEvent(tasks, count, line);
                break;
            default:
                count = printTasks(tasks, count, line);
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

    public static void printNumTasks(Task[] tasks, int count) {
        System.out.println("Now you have " + (count + 1) + " tasks in the list.");
    }

    public static int printTasks(Task[] tasks, int count, String line) {
        tasks[count] = new Task(line);
        System.out.println("Okay, added: " + line);
        printNumTasks(tasks, count);
        System.out.println(HORIZONTAL_LINE);
        return count + 1;
    }

    public static int printEvent(Task[] tasks, int count, String line) {
        String[] eventParts = line.replaceFirst("event", "").split("/from|/to");
        String eventDesc = eventParts[0].trim();
        String from = eventParts[1].trim();
        String to = eventParts[2].trim();
        tasks[count] = new Event(eventDesc, from, to);
        System.out.println("Okay, I've added this event: ");
        System.out.println(tasks[count]);
        printNumTasks(tasks, count);
        System.out.println(HORIZONTAL_LINE);
        return count + 1;
    }

    public static int printDeadline(Task[] tasks, int count, String line) {
        String[] parts = line.replaceFirst("deadline", "").split("/by");
        String description = parts[0].trim();
        String by = parts[1].trim();
        tasks[count] = new Deadline(description, by);
        System.out.println("Okay, I've added this deadline: ");
        System.out.println(tasks[count]);
        printNumTasks(tasks, count);
        System.out.println(HORIZONTAL_LINE);
        return count + 1;
    }

    public static int printTodo(Task[] tasks, int count, String line) {
        String todoDesc = line.replaceFirst("todo", "").trim();
        tasks[count] = new Todo(todoDesc);
        System.out.println("Okay, I've added this todo: ");
        System.out.println(tasks[count]);
        printNumTasks(tasks, count);
        System.out.println(HORIZONTAL_LINE);
        return count + 1;
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

    public static int extractDigit(String input) {
        String numberString = input.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberString);
    }

    public static void printTaskList(Task[] tasks, int count) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTask(Task[] tasks, String line) {
        int taskNumber = extractDigit(line) - 1;
        tasks[taskNumber].setMarkAsDone();

        System.out.println("Great! This task is marked as done: ");
        System.out.println(tasks[taskNumber]);
        System.out.println("Well done! ;)");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void unmarkTask(Task[] tasks, String line) {
        int taskNumber = extractDigit(line) - 1;
        tasks[taskNumber].setMarkAsNotDone();

        System.out.println("Ok, This task is marked as not done yet: ");
        System.out.println(tasks[taskNumber]);
        System.out.println(HORIZONTAL_LINE);
    }

}

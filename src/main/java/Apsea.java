import java.util.Scanner;

public class Apsea {
    private static Task[] tasks = new Task[100];
    private static int count = 0;

    public static void printLine() {
        System.out.println("    ________________________________________________________");
    }
    public static void printHello() {
        printLine();
        System.out.println("    Hello! I'm Apsea!\n"
                + "    What can I do for you?");
        printLine();
    }
    public static void printBye() {
        printLine();
        System.out.println("    Bye. Hope to see you again soon");
        printLine();
    }

    public static void printError() {
        printLine();
        System.out.println("    Sorry, please try again.");
        printLine();
    }

    public static void listTasks(Task[] tasks, int count) {
        printLine();
        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i+1) + ". " + tasks[i]);
        }
        printLine();
    }

    public static void markTask(Task[] tasks, String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        tasks[taskIndex].markAsDone();

        printLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + tasks[taskIndex]);
        printLine();
    }

    public static void unmarkTask(Task[] tasks, String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        tasks[taskIndex].markAsUndone();

        printLine();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("    " + tasks[taskIndex]);
        printLine();
    }

    public static void addTodo(String line) {
        final int NAME_POSITION = 5;
        tasks[count] = new Todo(line.substring(NAME_POSITION));
        count++;

        printLine();
        System.out.println("    I've added this task to the list:");
        System.out.println("    " + tasks[count-1]);
        System.out.println("    Now you have " + count + " task(s) in the list:");
        printLine();
    }

    public static void addDeadline(String line) {
        final int NAME_POSITION = 9;
        final int BY_POSITION = line.indexOf("/by");

        tasks[count] = new Deadline(line.substring(NAME_POSITION, BY_POSITION),
                line.substring(BY_POSITION + 4));
        count++;

        printLine();
        System.out.println("    I've added this task to the list:");
        System.out.println("    " + tasks[count-1]);
        System.out.println("    Now you have " + count + " task(s) in the list:");
        printLine();
    }

    public static void addEvent(String line) {
        final int NAME_POSITION = 6;
        final int FROM_POSITION = line.indexOf("/from");
        final int TO_POSITION = line.indexOf("/to");

        tasks[count] = new Event(line.substring(NAME_POSITION, FROM_POSITION),
                line.substring(FROM_POSITION + 6, TO_POSITION - 1),
                line.substring(TO_POSITION + 4));
        count++;

        printLine();
        System.out.println("    I've added this task to the list:");
        System.out.println("    " + tasks[count-1]);
        System.out.println("    Now you have " + count + " task(s) in the list:");
        printLine();

    }

    public static void getInput() {
        String line;
        boolean isExit = false;
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
            case "bye":
                isExit = true;
                break;
            case "list":
                listTasks(tasks, count);
                break;
            case "todo":
                addTodo(line);
                break;
            case "deadline":
                addDeadline(line);
                break;
            case "event":
                addEvent(line);
                break;
            case "mark":
                markTask(tasks, words[1]);
                break;
            case "unmark":
                unmarkTask(tasks, words[1]);
                break;
            default:
                printError();
                break;
            }
        } while (!isExit);
    }

    public static void main(String[] args) {
        printHello();
        getInput();
        printBye();
    }
}

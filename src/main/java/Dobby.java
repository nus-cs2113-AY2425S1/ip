import java.util.Scanner;

public class Dobby {

    private static final String DASH_LINE = "____________________________________________________________";
    private static final int MAX_LIST_SIZE = 100;
    private static final Task[] taskList = new Task[MAX_LIST_SIZE];

    private static int listSize = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;

        printWelcomeMessage();

        while (true) {
            line = in.nextLine().trim();
            if (line.equalsIgnoreCase("bye")){
                break;
            }
            processCommand(line);
        }

        printGoodbyeMessage();
    }

    private static void processCommand(String line) {
        if (line.equals("list")) {
            printList();
        } else if (line.startsWith("mark ")) {
            markTaskAsDone(line);
        } else if (line.startsWith("unmark ")) {
            unmarkTaskAsDone(line);
        } else {
            addTask(line);
        }
    }

    private static void printWelcomeMessage() {
        printSeparator();
        System.out.println("    " + "Hello! Dobby is Dobby!");
        System.out.println("    " + "What can Dobby do for master?");
        printSeparator();
    }

    private static void printList() {
        printSeparator();
        System.out.println("    Here are the tasks in master's list:");
        for (int i = 1; i <= listSize; i++) {
            Task t = taskList[i-1];
            System.out.println("    " + i + "." + t);
        }
        printSeparator();
    }

    private static void addTask(String line) {
        Task task = createTask(line);
        addTaskToList(task);
        printTaskAddedMessage();
    }

    private static Task createTask(String line) {
        if (line.startsWith("todo ")) {
            return new Todo(line.substring("todo ".length()));
        } else if (line.startsWith("deadline ")) {
            return createDeadlineTask(line);
        } else if (line.startsWith("event ")){
            return createEventTask(line);
        }
        return new Task(line);
    }

    private static Deadline createDeadlineTask(String line) {
        String[] lineParts = line.split(" /by ");
        String taskDescription = lineParts[0].replaceFirst("deadline ", "");
        String byWhen = lineParts[1];
        return new Deadline(taskDescription, byWhen);
    }

    private static Event createEventTask(String line) {
        String[] lineParts = line.split(" /from | /to ");
        String taskDescription = lineParts[0].replaceFirst("event ", "");
        String fromTime = lineParts[1];
        String toTime = lineParts[2];
        return new Event(taskDescription, fromTime, toTime);
    }

    private static void addTaskToList(Task task) {
        taskList[listSize] = task;
        listSize++;
    }

    private static void printTaskAddedMessage() {
        printSeparator();
        System.out.println("    Dobby has added this task:");
        System.out.println("      " + taskList[listSize-1]);
        System.out.println("    Dobby says master has " + Task.getNumberOfTasks() + " tasks in the list!");
        printSeparator();
    }

    private static void printGoodbyeMessage() {
        printSeparator();
        System.out.println("    " + "Thank you master, Dobby is free!!!");
        printSeparator();
    }

    private static void markTaskAsDone(String line) {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (taskNumber > 0 && taskNumber <= listSize){
            taskList[taskNumber-1].markAsDone();
            printTaskStatus("done", taskNumber);
        }
    }

    private static void unmarkTaskAsDone(String line) {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (taskNumber > 0 && taskNumber <= listSize){
            taskList[taskNumber-1].unmarkAsDone();
            printTaskStatus("incomplete", taskNumber);
        }
    }

    private static void printTaskStatus(String status, int taskNumber) {
        printSeparator();
        if ("done".equals(status)) {
            System.out.println("    Dobby has magically marked this task as done:");
        } else if ("incomplete".equals(status)) {
            System.out.println("    Dobby has marked this task as incomplete:");
        }
        System.out.println("      " + taskList[taskNumber-1]);
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("  " + DASH_LINE);
    }
}

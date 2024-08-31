import java.util.Scanner;

public class Dobby {

    private static final String DASH_LINE = "____________________________________________________________";
    private static final int MAX_LIST_SIZE = 100;
    private static final Task[] taskList = new Task[MAX_LIST_SIZE];

    private static int listSize = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean saidBye = false;

        printWelcomeMessage();

        while (!saidBye){
            String line = in.nextLine().trim();
            if (line.equalsIgnoreCase("bye")){
                saidBye = true;
            } else if (line.equals("list")) {
                printList();
            } else if (line.startsWith("mark ")) {
                markTaskAsDone(line);
            } else if (line.startsWith("unmark ")) {
                unmarkTaskAsDone(line);
            } else {
                addTask(line);
            }
        }

        printGoodbyeMessage();
    }

    private static void printWelcomeMessage(){
        printSeparator();
        System.out.println("    " + "Hello! Dobby is Dobby!");
        System.out.println("    " + "What can Dobby do for master?");
        printSeparator();
    }

    private static void printList(){
        printSeparator();
        System.out.println("    Here are the tasks in master's list:");
        for (int i = 1; i <= listSize; i++) {
            Task t = taskList[i-1];
            System.out.println("    " + i + "." + t);
        }
        printSeparator();
    }

    private static void addTask(String line){
        Task task = createTask(line);
        addTaskToList(task);
        printTaskAddedMessage();
    }

    public static Task createTask(String line){
        if (line.startsWith("todo ")) {
            return new Todo(line.substring("todo ".length()));
        } else if (line.startsWith("deadline ")) {
            String[] lineParts = line.split(" /by ");
            String taskDescription = lineParts[0].replaceFirst("deadline ", "");
            String byWhen = lineParts[1];
            return new Deadline(taskDescription, byWhen);
        } else if (line.startsWith("event ")){
            String[] lineParts = line.split(" /from | /to ");
            String taskDescription = lineParts[0].replaceFirst("event ", "");
            String fromTime = lineParts[1];
            String toTime = lineParts[2];
            return new Event(taskDescription, fromTime, toTime);
        }
        return new Task(line);
    }

    private static void addTaskToList(Task task){
        taskList[listSize] = task;
        listSize++;
    }

    private static void printTaskAddedMessage(){
        printSeparator();
        System.out.println("    Dobby has added this task:");
        System.out.println("      " + taskList[listSize-1]);
        System.out.println("    Dobby says master has " + Task.getNumberOfTasks() + " tasks in the list!");
        printSeparator();
    }

    private static void printGoodbyeMessage(){
        printSeparator();
        System.out.println("    " + "Thank you master, Dobby is free!!!");
        printSeparator();
    }

    private static void markTaskAsDone(String line){
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (taskNumber > 0 && taskNumber <= listSize){
            taskList[taskNumber-1].markAsDone();
            printSeparator();
            System.out.println("    Dobby has magically marked this task as done:");
            System.out.println("      " + taskList[taskNumber-1]);
            printSeparator();
        }
    }

    private static void unmarkTaskAsDone(String line){
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (taskNumber > 0 && taskNumber <= listSize){
            taskList[taskNumber-1].unmarkAsDone();
            printSeparator();
            System.out.println("    Dobby has marked this task as incomplete:");
            System.out.println("      " + taskList[taskNumber-1]);
            printSeparator();
        }
    }

    private static void printSeparator(){
        System.out.println("  " + DASH_LINE);
    }
}

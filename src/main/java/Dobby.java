import java.util.Scanner;

public class Dobby {

    private static final String DASH_LINE = "____________________________________________________________";
    private static final int MAX_LIST_SIZE = 100;
    private static final Task[] taskList = new Task[MAX_LIST_SIZE];

    private static int listSize = 0;

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {
            printWelcomeMessage();
            String line;
            while (true) {
                line = in.nextLine().trim();
                if (line.equalsIgnoreCase("bye")){
                    break;
                }
                try {
                    processCommand(line);
                } catch (MissingDescriptionException e) {
                    printSeparator();
                    System.out.println("    Dobby thinks master should add a description here!");
                    printSeparator();
                } catch (IllegalInputException e) {
                    printSeparator();
                    System.out.println("    Dobby doesn't understand master's command!");
                    printSeparator();
                } catch (TaskAlreadyMarkedException e) {
                    printSeparator();
                    System.out.println("    Dobby says master's task is already marked!");
                    printSeparator();
                } catch (TaskAlreadyUnmarkedException e) {
                    printSeparator();
                    System.out.println("    Dobby says master's task is already unmarked!");
                    printSeparator();
                } catch (EmptyListException e) {
                    printSeparator();
                    System.out.println("    Dobby says master's list is empty!");
                    printSeparator();
                } catch (IndexOutOfBoundsException e) {
                    printSeparator();
                    System.out.println("    Dobby says master's list is full!");
                    printSeparator();
                }
            }

            printGoodbyeMessage();
        }

    }

    private static void processCommand (String line)
            throws MissingDescriptionException, IllegalInputException, TaskAlreadyMarkedException,
                TaskAlreadyUnmarkedException, EmptyListException {

        if (line.equals("list")) {
            printList();
        } else if (line.startsWith("mark ")) {
            markTaskAsDone(line);
        } else if (line.startsWith("unmark ")) {
            unmarkTaskAsDone(line);
        } else if (line.startsWith("todo") || line.startsWith("deadline") || line.startsWith("event")) {
            addTask(line);
        } else {
            throw new IllegalInputException();
        }

    }

    private static void printWelcomeMessage() {
        printSeparator();
        System.out.println("    " + "Hello! Dobby is Dobby!");
        System.out.println("    " + "What can Dobby do for master?");
        printSeparator();
    }

    private static void printList() throws EmptyListException {
        if (listSize == 0) {
            throw new EmptyListException();
        }

        printSeparator();
        System.out.println("    Here are the tasks in master's list:");
        for (int i = 1; i <= listSize; i++) {
            Task t = taskList[i-1];
            System.out.println("    " + i + "." + t);
        }
        printSeparator();
    }

    private static void addTask (String line) throws MissingDescriptionException {
        Task task = createTask(line);
        if (task != null) {
            addTaskToList(task);
            printTaskAddedMessage();
        }
    }

    private static Task createTask(String line) throws MissingDescriptionException {
        if (line.startsWith("todo")) {
            if (line.length() <= "todo".length()) {
                throw new MissingDescriptionException();
            }
            return new Todo(line.substring("todo ".length()));
        } else if (line.startsWith("deadline")) {
            return createDeadlineTask(line);
        } else if (line.startsWith("event")){
            return createEventTask(line);
        }
        return new Task(line);
    }

    private static Deadline createDeadlineTask(String line) throws MissingDescriptionException {
        String[] lineParts = line.split(" /by ");

        if (lineParts.length < 2 || lineParts[0].length() <= "deadline ".length()) {
            throw new MissingDescriptionException();
        }

        String taskDescription = lineParts[0].replaceFirst("deadline ", "");
        String byWhen = lineParts[1];
        return new Deadline(taskDescription, byWhen);
    }

    private static Event createEventTask(String line) throws MissingDescriptionException {
        String[] lineParts = line.split(" /from | /to ");

        if (lineParts.length < 3 || lineParts[0].length() <= "event ".length()) {
            throw new MissingDescriptionException();
        }

        String taskDescription = lineParts[0].replaceFirst("event ", "");
        String fromTime = lineParts[1];
        String toTime = lineParts[2];
        return new Event(taskDescription, fromTime, toTime);
    }

    private static void addTaskToList(Task task) throws IndexOutOfBoundsException{
        if (listSize >= MAX_LIST_SIZE) {
            throw new IndexOutOfBoundsException();
        }
        taskList[listSize++] = task;
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

    private static void markTaskAsDone(String line) throws TaskAlreadyMarkedException {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (isValidTaskNumber(taskNumber)){
            Task task = taskList[taskNumber-1];
            if (task.isDone()){
                throw new TaskAlreadyMarkedException();
            }
            task.markAsDone();
            printTaskStatus("done", taskNumber);
        }
    }

    private static void unmarkTaskAsDone(String line) throws TaskAlreadyUnmarkedException {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (isValidTaskNumber(taskNumber)) {
            Task task = taskList[taskNumber-1];
            if (!task.isDone()){
                throw new TaskAlreadyUnmarkedException();
            }
            task.unmarkAsDone();
            printTaskStatus("incomplete", taskNumber);
        }
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > 0 && taskNumber <= listSize;
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

import exception.LeginMissingParamsException;
import exception.LeginEmptyTaskException;
import exception.LeginIndexOutOfBoundsException;
import exception.LeginInvalidCommandException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Legin {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int currentTaskCount = 0;

    public static void horizontalLine() {
        System.out.println("______________________" +
                "______________________________________");
    }

    public static void greet() {
        horizontalLine();
        System.out.println("Hello! I'm Legin, your best online companion!");
        System.out.println("What can I do for you today my friend :D");
        horizontalLine();
    }
    public static void bye() throws IOException {
        horizontalLine();
        System.out.println("Bye " +
                Character.toString(0x1F44B) +
                ". Hope to see you again really soon! " +
                Character.toString(0x1F608));
        horizontalLine();
    }

    private static void printAddedTaskMessage() {
        horizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(currentTaskCount));
        currentTaskCount++;
        System.out.println("Now you have " + currentTaskCount + " tasks in the list.");
        horizontalLine();
    }

    private static void printExceptionMessage(Exception e) {
        System.out.println("Exception Occurred: " + e);
        horizontalLine();
    }

    public static void addTodo(String input) {
        try {
            tasks.add(new Todo(input, false));
            printAddedTaskMessage();
        } catch(LeginEmptyTaskException e) {
            printExceptionMessage(e);
        }
    }

    public static void addDeadline(String input) {
        try {
            tasks.add(new Deadline(input));
            printAddedTaskMessage();
        } catch (LeginEmptyTaskException | LeginMissingParamsException e) {
            printExceptionMessage(e);
        }
    }

    public static void addEvent(String input) {
        try {
            tasks.add(new Event(input));
            printAddedTaskMessage();
        } catch (LeginEmptyTaskException | LeginMissingParamsException e) {
            printExceptionMessage(e);
        }
    }

    public static void list() {
        horizontalLine();
        if (currentTaskCount == 0) {
            System.out.println("You have no tasks right now YIPPEE!");
        }
        for (int i = 0; i < currentTaskCount; i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(tasks.get(i));
        }
        horizontalLine();
    }

    public static void markTask(int index) {
        horizontalLine();
        tasks.get(index - 1).markTask();
        System.out.println("Good Job Buddy! I've marked this task as done:");
        System.out.println(tasks.get(index - 1));
        horizontalLine();
    }

    public static void unmarkTask(int index) {
        horizontalLine();
        tasks.get(index - 1).unmarkTask();
        System.out.println("Alright! I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1));
        horizontalLine();
    }

    public static void deleteTask(int index) {
        horizontalLine();
        Task toRemove = tasks.get(index - 1);
        tasks.remove(index - 1);
        currentTaskCount--;
        System.out.println("Noted. I've removed this task:");
        System.out.println(toRemove);
        System.out.println("Now you have " + currentTaskCount + " tasks in the list.");
        horizontalLine();
    }

    private static void checkIndexValidity(int index) throws LeginIndexOutOfBoundsException {
        if (index > currentTaskCount) {
            throw new LeginIndexOutOfBoundsException();
        }
    }

    private static boolean selectCommand(String command, String[] words, String input) {
        int indexOfTaskToMark;
        try {
            switch (command) {
            case "bye":
                return true;
            case "list":
                list();
                break;
            case "mark":
                indexOfTaskToMark = Integer.parseInt(words[1]);
                checkIndexValidity(indexOfTaskToMark);
                markTask(indexOfTaskToMark);
                break;
            case "unmark":
                indexOfTaskToMark = Integer.parseInt(words[1]);
                checkIndexValidity(indexOfTaskToMark);
                unmarkTask(indexOfTaskToMark);
                break;
            case "todo":
                addTodo(input);
                break;
            case "deadline":
                addDeadline(input);
                break;
            case "event":
                addEvent(input);
                break;
            case "delete":
                int indexOfTaskToDelete = Integer.parseInt(words[1]);
                checkIndexValidity(indexOfTaskToDelete);
                deleteTask(indexOfTaskToDelete);
                break;
            default:
                throw new LeginInvalidCommandException();
            }
        } catch (LeginInvalidCommandException | LeginIndexOutOfBoundsException e) {
            printExceptionMessage(e);
        }
        return false;
    }

    public static void runBot() {
        boolean saidBye = false;
        String command;
        String input;
        Scanner in = new Scanner(System.in);
        while (!saidBye) {
            input = in.nextLine();
            String[] words = input.split(" ");
            command = words[0];
            saidBye = selectCommand(command, words, input);
        }
    }

    public static void main(String[] args) throws IOException {
        greet();
        runBot();
        bye();
    }
}
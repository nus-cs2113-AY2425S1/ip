package apsea;

import apsea.exception.ApseaException;
import apsea.task.Deadline;
import apsea.task.Event;
import apsea.task.Task;
import apsea.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Apsea {
    private static ArrayList<Task> tasks = new ArrayList<>() ;

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
        System.out.println("    Sorry, please try again with a valid command.");
        printLine();
    }

    private static void printAddTask() {
        System.out.println("    I've added this task to the list:");
        System.out.println("    " + tasks.get(tasks.size()-1));
    }

    private static void printTotalTasks() {
        System.out.println("    Now you have " + tasks.size() + " task(s) in the list");
    }

    public static void listTasks() {
        printLine();

        int count = 1;
        for (Task task : tasks) {
            System.out.println("    " + count + ". " + task);
            count++;
        }
        printLine();
    }

    public static void markTask(String[] words) {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            (tasks.get(taskIndex)).markAsDone();

            printLine();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("    " + tasks.get(taskIndex));
            printLine();
        } catch (Exception e) {
            printLine();
            System.out.println("    Sorry, please use the format:\n"
                    + "    mark [number]");
            printLine();
        }
    }

    public static void unmarkTask(String[] words) {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            (tasks.get(taskIndex)).markAsUndone();

            printLine();
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("    " + tasks.get(taskIndex));
            printLine();
        } catch (Exception e) {
            printLine();
            System.out.println("    Sorry, please use the format:\n"
                    + "    unmark [number]");
            printLine();
        }
    }

    public static void deleteTask(String[] words) {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            Task deletedTask = tasks.get(taskIndex);
            tasks.remove(taskIndex);

            printLine();
            System.out.println("    OK, I've deleted this task:");
            System.out.println("    " + deletedTask);
            printTotalTasks();
            printLine();
        } catch (Exception e) {
            printLine();
            System.out.println("    Sorry, please use the format:\n"
                    + "    delete [number]");
            printLine();
        }
    }

    public static void addTodo(String line) throws ApseaException {
        final int NAME_POSITION = 5;
        if (line.length() <= NAME_POSITION) {
            throw new ApseaException("    Sorry, please use the format:\n"
                    + "    todo [task name]");
        }

        tasks.add(new Todo(line.substring(NAME_POSITION)));

        printLine();
        printAddTask();
        printTotalTasks();
        printLine();
    }

    public static boolean isValidDeadline(String line, int byPosition) {
        return (byPosition > 9) && (byPosition + 4 < line.length());
    }

    public static void addDeadline(String line) throws ApseaException{
        final int NAME_POSITION = 9;
        int byPosition = line.indexOf("/by");

        if (!isValidDeadline(line, byPosition)) {
            throw new ApseaException("    Sorry, please use the format:\n"
                    + "    deadline [task name] /by [time]");
        }

        tasks.add(new Deadline(line.substring(NAME_POSITION, byPosition),
                line.substring(byPosition + 4)));

        printLine();
        printAddTask();
        printTotalTasks();
        printLine();
    }

    public static boolean isValidEvent(String line, int fromPosition, int toPosition) {
        //event name is valid when /from starts after index 6
        boolean hasFrom = fromPosition > 6;
        boolean hasTo = toPosition >= 0;
        boolean isValidFrom = fromPosition + 6 < toPosition;
        boolean isValidTo = line.length() > toPosition + 4;

        return hasFrom && hasTo && isValidFrom && isValidTo;
    }

    public static void addEvent(String line) throws ApseaException {
        final int NAME_POSITION = 6;
        int fromPosition = line.indexOf("/from");
        int toPosition = line.indexOf("/to");

        if (!isValidEvent(line, fromPosition, toPosition)) {
            throw new ApseaException("    Sorry, Please use the format:\n" +
                    "    event [task name] /from [time] /to [event] ");
        }

        tasks.add(new Event(line.substring(NAME_POSITION, fromPosition),
                        line.substring(fromPosition + 6, toPosition - 1),
                        line.substring(toPosition + 4)));

        printLine();
        printAddTask();
        printTotalTasks();
        printLine();
    }

    public static void getInput() {
        String line;
        boolean isExit = false;
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] words = line.split(" ");
            try {
                switch (words[0].toLowerCase()) {
                case "bye":
                    isExit = true;
                    break;
                case "list":
                    listTasks();
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
                    markTask(words);
                    break;
                case "unmark":
                    unmarkTask(words);
                    break;
                case "delete":
                    deleteTask(words);
                    break;
                default:
                    printError();
                    break;
                }
            } catch (ApseaException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        } while (!isExit);
    }

    public static void main(String[] args) {
        printHello();
        getInput();
        printBye();
    }
}

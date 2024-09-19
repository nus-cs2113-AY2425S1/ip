package apsea;

import apsea.exception.ApseaException;
import apsea.task.Deadline;
import apsea.task.Event;
import apsea.task.Task;
import apsea.task.Todo;
import apsea.storage.Storage;

import java.util.ArrayList;
import java.util.Scanner;

public class Apsea {
    private static ArrayList<Task> tasks = new ArrayList<>() ;

    public static void printLine() {
        System.out.println("\t________________________________________________________");
    }
    public static void printHello() {
        printLine();
        System.out.println("\tHello! I'm Apsea!\n"
                + "\tWhat can I do for you?");
        printLine();
    }
    public static void printBye() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon");
        printLine();
    }

    public static void printError() {
        printLine();
        System.out.println("\tSorry, please try again with a valid command.");
        printLine();
    }

    private static void printAddTask() {
        System.out.println("\tI've added this task to the list:");
        System.out.println("\t" + tasks.get(tasks.size()-1));
    }

    private static void printTotalTasks() {
        System.out.println("\tNow you have " + tasks.size() + " task(s) in the list");
    }

    public static void listTasks() {
        printLine();

        int count = 1;
        for (Task task : tasks) {
            System.out.println("\t" + count + ". " + task);
            count++;
        }
        printLine();
    }

    public static void markTask(String[] words) {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            (tasks.get(taskIndex)).markAsDone();

            printLine();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + tasks.get(taskIndex));
            printLine();
        } catch (Exception e) {
            printLine();
            System.out.println("\tSorry, please use the format:\n"
                    + "\tmark [number]");
            printLine();
        }
    }

    public static void unmarkTask(String[] words) {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            (tasks.get(taskIndex)).markAsUndone();

            printLine();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t" + tasks.get(taskIndex));
            printLine();
        } catch (Exception e) {
            printLine();
            System.out.println("\tSorry, please use the format:\n"
                    + "\tunmark [number]");
            printLine();
        }
    }

    public static void deleteTask(String[] words) {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            Task deletedTask = tasks.get(taskIndex);
            tasks.remove(taskIndex);

            printLine();
            System.out.println("\tOK, I've deleted this task:");
            System.out.println("\t" + deletedTask);
            printTotalTasks();
            printLine();
        } catch (Exception e) {
            printLine();
            System.out.println("\tSorry, please use the format:\n"
                    + "\tdelete [number]");
            printLine();
        }
    }

    public static void addTodo(String line) throws ApseaException {
        final int NAME_POSITION = 5;
        if (line.length() <= NAME_POSITION) {
            throw new ApseaException("\tSorry, please use the format:\n"
                    + "\ttodo [task name]");
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
            throw new ApseaException("\tSorry, please use the format:\n"
                    + "\tdeadline [task name] /by [time]");
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
            throw new ApseaException("\tSorry, Please use the format:\n" +
                    "\tevent [task name] /from [time] /to [event] ");
        }

        tasks.add(new Event(line.substring(NAME_POSITION, fromPosition),
                        line.substring(fromPosition + 6, toPosition - 1),
                        line.substring(toPosition + 4)));

        printLine();
        printAddTask();
        printTotalTasks();
        printLine();
    }

    public static void loadTodo(String [] words){
        boolean isDone = words[1].equals("1");
        String description = words[2];

        tasks.add(new Todo(description, isDone));
    }

    public static void loadDeadline(String [] words){
        boolean isDone = words[1].equals("1");
        String description = words[2];
        String by = words[3];

        tasks.add(new Deadline(description, isDone, by));
    }

    public static void loadEvent(String [] words){
        boolean isDone = words[1].equals("1");
        String description = words[2];
        String from = words[3];
        String to = words[4];

        tasks.add(new Event(description, isDone, from, to));
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
        Storage.loadFile();
        getInput();
        Storage.saveData(tasks);
        printBye();
    }
}

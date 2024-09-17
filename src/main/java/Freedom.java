import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import freedom.exceptions.CannotReadFile;
import freedom.exceptions.InvalidCommand;
import freedom.tasks.Task;
import freedom.tasks.ToDo;
import freedom.tasks.Deadline;
import freedom.tasks.Event;

public class Freedom {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    static int lastIndex = 0;

    static final String LOGO = "\t________________________________________\n";

    public static void main(String[] args) {
        final String START_MESSAGE = """
                \tHello! I'm Freedom
                \tWhat can I do for you?
                """;
        final String CLOSING_MESSAGE = "\tBye. Hope to see you again soon!\n";

        System.out.println(LOGO + START_MESSAGE + LOGO);
        Scanner in = new Scanner(System.in);

        try {
            loadData();

            // Super loop for getting inputs
            while (in.hasNextLine()) {
                String line = in.nextLine();

                if (line.equals("bye")) {
                    break;
                }
                handleInput(line);
            }
        } catch (Exception e) {
            System.out.print("");
        }

        System.out.println(LOGO + CLOSING_MESSAGE + LOGO);
    }

    public static void handleInput(String input) {
        final int COMMAND_INDEX = 0;
        String[] words = input.split(" ");
        String description;

        try {
            switch (words[COMMAND_INDEX]) {
                case "list":
                    printList();
                    return;
                case "mark":
                    markTask(words, true);
                    return;
                case "unmark":
                    markTask(words, false);
                    return;
                case "todo":
                    description = input.replaceFirst("todo", "");
                    tasks.add(new ToDo(description));
                    break;
                case "deadline":
                    description = input.replaceFirst("deadline", "");
                    tasks.add(new Deadline(description));
                    break;
                case "event":
                    description = input.replaceFirst("event", "");
                    tasks.add(new Event(description));
                    break;
                default:
                    throw new InvalidCommand();

            }
            System.out.println(LOGO + "\tGot it. I've added this task: ");
            System.out.println("\t  " + tasks.get(lastIndex).printLine());
            
            lastIndex++;

            System.out.println("\tNow you have " + (lastIndex) + " tasks in the list.\n" + LOGO);
        } catch (InvalidCommand e) {
            System.out.println(LOGO + "\tSorry! I do not understand your command");
            System.out.println(LOGO);
        } catch (Exception e) {
            System.out.print("");
        }
    }

    public static void printList() {
        int counter = 1;
        System.out.print(LOGO + "\tHere are the tasks in your list:\n");
        for (int i = 0; i < lastIndex; i++) {
            System.out.println("\t" + counter + "." + tasks.get(i).printLine());
            counter++;
        }
        System.out.println(LOGO);
    }

    public static void markTask(String[] words, boolean isDone) {
        final int TASK_INDEX = 1;
        int listNumber = Integer.parseInt(words[TASK_INDEX]);
        int taskIndexInStorage = listNumber - 1;
        String message;
        Task taskToBeMarked;

        try {
            if (taskIndexInStorage >= lastIndex) {
                throw new ArrayIndexOutOfBoundsException();
            }
            taskToBeMarked = tasks.get(taskIndexInStorage);
            if (isDone) {
                taskToBeMarked.markDone();
                tasks.set(taskIndexInStorage, taskToBeMarked);
                message = "\tNice! I've marked this task as done:";
            } else {
                taskToBeMarked.markUndone();
                tasks.set(taskIndexInStorage, taskToBeMarked);
                message = "\tOk, I've marked this task as not done yet:";
            }
            System.out.println(LOGO + message);
            System.out.println("\t  " + tasks.get(taskIndexInStorage).printLine());
            System.out.println(LOGO);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print(LOGO);
            System.out.print("""
                    \tWe don't have that many tasks??
                    \tYou can use list to check
                    """);
            System.out.println(LOGO);
        } catch (Exception e) {
            System.out.print("");
        }
    }

    public static void loadData() throws Exception{
        final int COMMAND_INDEX = 0;
        final int DESCRIPTION_INDEX = 2;
        final int TIME_ONE = 3;
        final int TIME_TWO = 4;
        String input;

        try {
            File data = new File("./data/freedom.txt");
            Scanner read = new Scanner(data);
            while (read.hasNextLine()) {
                String[] words = read.nextLine().split("[|]");
                switch(words[COMMAND_INDEX].trim()) {
                    case "T":
                        input = words[DESCRIPTION_INDEX].trim();
                        tasks.add(new ToDo(input));
                        break;
                    case "D":
                        input = words[DESCRIPTION_INDEX].trim() + "/by" + words[TIME_ONE].trim();
                        tasks.add(new Deadline(input));
                        break;
                    case "E":
                        input = words[DESCRIPTION_INDEX].trim()
                                + "/from" + words[TIME_ONE].trim()
                                + "/to" + words[TIME_TWO].trim();
                        tasks.add(new Event(input));
                        break;
                    default:
                        throw new CannotReadFile();
                }
                lastIndex++;
            }
            System.out.println("\tData Loaded!");
            printList();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (CannotReadFile e) {
            System.out.print(LOGO);
            System.out.println("\tCannot Load Data! File might be corrupted :(");
            System.out.println(LOGO);
            throw new Exception();
        } catch (Exception e) {
            System.out.print("");
        }
    }
}

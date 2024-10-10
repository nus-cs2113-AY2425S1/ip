package tommi;

import tommi.Task.Task;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    /**
     * Print the intro message with ASCII art, and current save file data.
     *
     * @throws FileNotFoundException If loading save file fails
     */
    public static void printIntroMessage() throws FileNotFoundException {
        String introFirstSection = """
                 ______                  \s
                /_  __/__  __ _  __ _  (_)
                 / / / _ \\/  ' \\/  ' \\/ /\s
                /_/  \\___/_/_/_/_/_/_/_/ \s
                ____________________________________________________________
                Hello! I'm Tommi!
                Here's your last-saved task list:
                """;
        System.out.println(introFirstSection);

        Storage.loadTaskData();

        String introSecondSection = """
                ____________________________________________________________
                How can I help you?
                ____________________________________________________________
                """;
        System.out.println(introSecondSection);
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Print the entire TaskList with indexes.
     *
     * @param tasks Represents TaskList
     */
    public static void printTaskList(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". "  + tasks.get(i));
        }
        printLine();
    }

    /**
     * Print the task Added with index and current number of tasks in list
     *
     * @param tasks Represents TaskList
     * @param task Represents Task that was added
     */
    public static void printAddTask(Task task, ArrayList<Task> tasks) {
        printLine();
        System.out.println("Sure. I've added the task: " + System.lineSeparator()
                + task + System.lineSeparator()
                + "There are now " + tasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Print the task Deleted with its index and current number of tasks in list
     *
     * @param tasks Represents TaskList
     * @param index Represents index of Task that was deleted
     */
    public static void printDeleteTask(ArrayList<Task> tasks, int index) {
        printLine();
        System.out.println("I've removed the task: " + System.lineSeparator()
                + tasks.get(index) + System.lineSeparator());
        System.out.println( "There are now " + tasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Print the task Marked with its index and current number of tasks in list
     *
     * @param tasks Represents TaskList
     * @param index Represents index of Task that was marked
     */
    public static void printMarkTask(ArrayList<Task> tasks, int index) {
        Ui.printLine();
        System.out.println("Awesomesauce! I've marked this task as done:"
                + tasks.get(index));
        Ui.printLine();
    }

    /**
     * Print the task Unmarked with its index and current number of tasks in list
     *
     * @param tasks Represents TaskList
     * @param index Represents index of Task that was unmarked
     */
    public static void printUnmarkTask(ArrayList<Task> tasks, int index) {
        Ui.printLine();
        System.out.println("OK, I've marked this task as undone:"
                + tasks.get(index));
        Ui.printLine();
    }

    /**
     * Print the ArrayList of Strings in save file containing the keyword.
     * If no matches let the user know through a special message.
     *
     * @param foundResults Represents collection of strings in save file with keyword
     */
    public static void printSearchResults(ArrayList<String> foundResults) {
        if (foundResults.isEmpty()) {
            System.out.println("No results found!");
            return;
        }

        System.out.println("Here are the matching results: ");
        int index = 1;
        for (String result : foundResults) {
            System.out.println(index + ": " + result);
            index++;
        }

    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

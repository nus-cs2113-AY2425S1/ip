package main.java;

import ran.task.Deadline;
import ran.task.Event;
import ran.task.Task;
import ran.task.TaskType;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing the user interface for interacting with the user.
 */
public class Ui {
    private final String LINE = "\t____________________________________________________________";
    private final String logo = "\t     ___           ___           ___" + System.lineSeparator()
            + "\t    /\\  \\         /\\  \\         /\\__\\" + System.lineSeparator()
            + "\t   /::\\  \\       /::\\  \\       /::|  |" + System.lineSeparator()
            + "\t  /:/\\:\\  \\     /:/\\:\\  \\     /:|:|  |" + System.lineSeparator()
            + "\t /::\\~\\:\\  \\   /::\\~\\:\\  \\   /:/|:|  |__" + System.lineSeparator()
            + "\t/:/\\:\\ \\:\\__\\ /:/\\:\\ \\:\\__\\ /:/ |:| /\\__\\" + System.lineSeparator()
            + "\t\\/_|::\\/:/  / \\/__\\:\\/:/  / \\/__|:|/:/  /" + System.lineSeparator()
            + "\t   |:|::/  /       \\::/  /      |:/:/  /" + System.lineSeparator()
            + "\t   |:|\\/__/        /:/  /       |::/  /" + System.lineSeparator()
            + "\t   |:|  |         /:/  /        /:/  /" + System.lineSeparator()
            + "\t    \\|__|         \\/__/         \\/__/" + System.lineSeparator();
    private Scanner in;

    /**
     * Constructor for a <code>Ui</code> object.
     * Overloads default constructor to initialise a scanner object reading user inputs.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Display a greeting message.
     */
    public void greet() {
        System.out.println(LINE);
        System.out.println(logo + "\tHello, I'm Ran.");
        System.out.println("\tHow may I assist you?");
        System.out.println(LINE);
    }

    /**
     * Display a goodbye message.
     */
    public void bidFarewell() {
        System.out.println(LINE);
        System.out.println("\tFarewell. May we meet again!");
        System.out.println(LINE);
    }

    /**
     * Display a newly added task.
     *
     * @param addedTask Task that has been added
     * @param listCount Number of tasks currently in the list
     */
    public void printAddedTask(String addedTask, int listCount) { 
        System.out.println(LINE);
        System.out.println("\tUnderstood, I have noted down the following task:");
        System.out.println("\t " +  addedTask);
        // Conditional operator to pluralize "task" when listCount above 1
        System.out.println("\tYou currently have " + listCount + 
                (listCount <= 1 ? " task" : " tasks") + " in your list.");
        System.out.println(LINE);
    }

    /**
     * Display a task that has been marked as done.
     *
     * @param markedTask Task that has been marked as done
     */
    public void printMarkedTask(String markedTask) {
        System.out.println(LINE);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + markedTask);
        System.out.println(LINE);
    }

    /**
     * Display a task that has been set as not done.
     *
     * @param unmarkedTask Task that has been set as not done
     */
    public void printUnmarkedTask(String unmarkedTask) {
        System.out.println(LINE);
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + unmarkedTask);
        System.out.println(LINE);
    }

    /**
     * Display a task that been deleted.
     *
     * @param deletedTask Task that has been deleted
     * @param listCount Integer representing number of tasks in the list currently
     */
    public void printDeletedTask(String deletedTask, int listCount) {
        System.out.println(LINE);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + deletedTask);
        System.out.println("\tYou currently have " + listCount + 
                (listCount <= 1 ? " task" : " tasks") + " in your list.");
        System.out.println(LINE);
    }

    /**
     * Display a list of tasks.
     *
     * @param list ArrayList of tasks to be displayed
     * @param listCount Integer representing total number of tasks in <code>list</code>
     */
    public void printList(ArrayList<Task> list, int listCount) {
        System.out.println(LINE);
        for (int i = 0; i < listCount; i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Display a specified message.
     *
     * @param input String array representing the message, each element representing one line
     */
    public void printMessage(String[] input) {
        System.out.println(LINE);
        for (String line: input) {
            System.out.println("\t" + line);
        }
        System.out.println(LINE);
    }

    /**
     * Return user input as a string when user hit enter key.
     *
     * @return String representing what user typed into the program
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Display help message to user.
     */
    public void printHelp() {
        System.out.println(LINE);
        System.out.println("\tNeed help? You got it.");
        System.out.println("\tThere are 3 types of tasks I am authorised to manage:");
        System.out.println("\t\'todo\', \'deadline\', \'event\'");
        System.out.println("\tEach of these words represents the command to add a task of that type.");
        System.out.println("\tUse the \'list\' command to see all your added tasks.");
        System.out.println("\tUse the \'find\' command to search your tasks containing a keyphrase.");
        System.out.println("\tModify your tasks using the \'mark\' and \'unmark\' command.");
        System.out.println("\tAnd if you get cold feet, use the \'delete\' command to remove tasks.");
        System.out.println("\tTry typing any of these commands, I can guide you along.");
        System.out.println("\tWhen you are done, just type \'bye\' to release me back to the computer.");
        System.out.println("\tFor more comprehensive help, check out the user guide in this weblink:");
        System.out.println("\thttps://3ccly.github.io/ip/");
        System.out.println("\t...");
        System.out.println("\tYou know what? I could do with some help too.");
        System.out.println("\tHave you seen my cat?");
        System.out.println("\t...");
        System.out.println("\tHold on.");
        System.out.println("\tNevermind, she was in my right hand all along.");
        System.out.println(LINE);
    }
}

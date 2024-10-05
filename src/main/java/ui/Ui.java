package ui;

import tasks.Task;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String DIVIDER = "____________________________________________________________\n\n";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Creates a new instance of the Ui class with default values.
     */
    public Ui() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }

    /**
     * Shows a welcome message for the Doot application.
     */
    public void showWelcome() {
        out.print(DIVIDER + "Hello! I'm  Doot\nWhat can I do for you?\n" + DIVIDER);
    }

    /**
     * Shows the exit message for the Doot application.
     */
    public void showExit() {
        out.print(DIVIDER + "Bye. Hope to see you again soon!" + "\n" + DIVIDER);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return the next line of input from the user
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints the index and Task for each task in the given list
     *
     * @param idxAndTaskList ArrayList of ArrayLists holding an index and Task
     */
    public void printList(ArrayList<ArrayList<Object>> idxAndTaskList) {
        out.print(DIVIDER);
        for (ArrayList<Object> idxAndTask : idxAndTaskList) {
            out.println(idxAndTask.get(1).toString() + ". " + idxAndTask.get(0).toString());
        }
        out.print(DIVIDER);
    }
}

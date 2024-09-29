package main.java;

import ran.task.Deadline;
import ran.task.Event;
import ran.task.Task;
import ran.task.TaskType;
import java.util.ArrayList;

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

    public void greet() {
        System.out.println(LINE);
        System.out.println(logo + "\tHello, I'm Ran.");
        System.out.println("\tHow may I assist you?");
        System.out.println(LINE);
    }

    public void bidFarewell() {
        System.out.println(LINE);
        System.out.println("\tFarewell. May we meet again!");
        System.out.println(LINE);
    }

    public void printAddedTask(String addedTask, int listCount) { 
        System.out.println(LINE);
        System.out.println("\tUnderstood, I have noted down the following task:");
        System.out.println("\t " +  addedTask);
        // Conditional operator to pluralize "task" when listCount above 1
        System.out.println("\tYou currently have " + listCount + 
                (listCount <= 1 ? " task" : " tasks") + " in your list.");
        System.out.println(LINE);
    }

    public void printMarkedTask(String markedTask) {
        System.out.println(LINE);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + markedTask);
        System.out.println(LINE);
    }

    public void printUnmarkedTask(String unmarkedTask) {
        System.out.println(LINE);
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + unmarkedTask);
        System.out.println(LINE);
    }

    public void printDeletedTask(String deletedTask, int listCount) {
        System.out.println(LINE);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + deletedTask);
        System.out.println("\tYou currently have " + listCount + 
                (listCount <= 1 ? " task" : " tasks") + " in your list.");
        System.out.println(LINE);
    }

    public void printList(ArrayList<Task> list, int listCount) {
        System.out.println(LINE);
        for (int i = 0; i < listCount; i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i));
        }
        System.out.println(LINE);
    }
}

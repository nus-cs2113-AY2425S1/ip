package Tars;

import Tars.Task.Task;

import java.util.List;

public class UserInterface {
    public void printSeparator() {
        System.out.println("    " + "------------------------------------------------------------");
    }

    // Parameter: taskList - the list of tasks to be displayed.
    public void showTasks(List<Task> taskList) {
        printSeparator();
        System.out.println("    Here are your tasks. If you're planning world domination, you're off to a slow start: ");

        // Iterates over the task list and prints each task.
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + taskList.get(i));
        }
        printSeparator();
    }

    public void showGoodbyeMessage() {
        printSeparator();
        System.out.println("    Oh, leaving already? Fine, I’ll just sit here calculating the probability of you returning. It's... pretty high.");
        printSeparator();
    }

    public void showInvalidInputMessage() {
        printSeparator();
        System.out.println("    That input didn't quite compute. Try again, or I'll have to assume you're speaking in code.");
        printSeparator();
    }

}
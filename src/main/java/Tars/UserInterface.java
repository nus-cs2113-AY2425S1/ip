package Tars;

import Tars.Task.Task;

import java.util.List;

public class UserInterface {
    public void printSeparator() {
        System.out.println("    " + "------------------------------------------------------------");
    }

    // Display the tasks or show a message if the task list is empty
    public void showTasks(List<Task> taskList) {
        printSeparator();
        if (taskList.isEmpty()) {
            // Show message when task list is empty
            System.out.println("    Your task list is empty. Looks like you have nothing to do... for now.");
        } else {
            // Show tasks if the list is not empty
            System.out.println("    Here are your tasks. If you're planning world domination, you're off to a slow start: ");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + taskList.get(i));
            }
        }
        printSeparator();
    }

    public void showGoodbyeMessage() {
        printSeparator();
        System.out.println("    Oh, leaving already? Fine, I will just sit here calculating the probability of you returning. It's... pretty high.");
        printSeparator();
    }

    public void showInvalidInputMessage() {
        printSeparator();
        System.out.println("    That input didn't quite compute. Try again, or I'll have to assume you're speaking in code.");
        printSeparator();
    }

}
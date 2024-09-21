package nus.edu.rizzler.manager;

import nus.edu.rizzler.exception.InvalidInputException;
import nus.edu.rizzler.task.Task;
import nus.edu.rizzler.ui.Emoji;
import java.util.ArrayList;

public class TaskManager {
    Emoji emoji = new Emoji();

    public static ArrayList<Task> taskList = new ArrayList<>();
    public static int totalTaskCount = 0;

    public TaskManager() {}

    public void displayTaskAction(Task task, int taskNumber, String actionMessage) {
        System.out.println(actionMessage);
        System.out.println(taskNumber + ". " + task);
        System.out.println("--------------------------------------------------------");
    }

    public void displayTaskList() {
        if (totalTaskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!" + " " + emoji.getRocketEmoji() + emoji.getHundredPointsEmoji());
        } else {
            System.out.println("\nHere’s the rundown on the tasks!");
            for (int i = 0; i < totalTaskCount; i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
        System.out.println("--------------------------------------------------------");
    }

    public void addTask(String taskDescription) {
        try {
            taskList.add(Task.parseTaskString(taskDescription));
            totalTaskCount++;

            System.out.printf("\nYou have %d task(s) in your list now!\n%n", totalTaskCount);
            String actionMessage = "Let's make it happen! " + emoji.getRockstarHandEmoji() + emoji.getFireEmoji();
            displayTaskAction(taskList.get(totalTaskCount - 1), totalTaskCount, actionMessage);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public void completeTask(int taskNumber) {
        if (totalTaskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!" + " " + emoji.getRocketEmoji() + emoji.getHundredPointsEmoji());

        } else if (taskNumber < 1 || taskNumber > totalTaskCount) {
            System.out.println("ERROR: Task number out of range. Please enter a valid task number" + emoji.getExclamationMarkEmoji());

        } else {
            Task task = taskList.get(taskNumber - 1);
            task.setIsDone(true);

            String actionMessage = "\nGood Job! " + emoji.getPartyPopperEmoji();
            displayTaskAction(task, taskNumber, actionMessage);
        }
    }

    public void undoTask(int taskNumber) {
        if (totalTaskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!" + " " + emoji.getRocketEmoji() + emoji.getHundredPointsEmoji());

        } else if (taskNumber < 1 || taskNumber > totalTaskCount) {
            System.out.println("ERROR: Task number out of range. Please enter a valid task number" + emoji.getExclamationMarkEmoji());

        } else {
            Task task = taskList.get(taskNumber - 1);
            task.setIsDone(false);

            String actionMessage = "\nNo worries! Task reset. " + emoji.getReverseEmoji();
            displayTaskAction(task, taskNumber, actionMessage);
        }
    }

    public void deleteTask(int taskNumber) {
        if (totalTaskCount == 0) {
            System.out.println("Nothing in the pipeline yet! Let's get to work!" + " " + emoji.getRocketEmoji() + emoji.getHundredPointsEmoji());

        } else if (taskNumber < 1 || taskNumber > totalTaskCount) {
            System.out.println("ERROR: Task number out of range. Please enter a valid task number" + emoji.getExclamationMarkEmoji());

        } else {
            Task task = taskList.get(taskNumber - 1);
            task.setIsDone(true);

            taskList.remove(taskNumber - 1);
            totalTaskCount--;

            String actionMessage = "Task deleted: ";
            System.out.printf("\nYou have %d task(s) in your list now!\n%n", totalTaskCount);
            displayTaskAction(task, taskNumber, actionMessage);
        }
    }
}

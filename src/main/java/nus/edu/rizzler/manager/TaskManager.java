package nus.edu.rizzler.manager;

import nus.edu.rizzler.exception.InvalidInputException;
import nus.edu.rizzler.task.Task;
import nus.edu.rizzler.ui.Emoji;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    Emoji emoji = new Emoji();
    DataManager dataManager = new DataManager();

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
            System.out.println("\nHere’s the rundown on the tasks so far!");
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

            dataManager.appendToFile(String.valueOf(taskList.get(totalTaskCount - 1)));

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("RizzlerData.txt not found! Data cannot be saved!");
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

            try {
                updateFile();
            } catch (IOException e) {
                System.out.println("RizzlerData.txt not found! Data cannot be saved!");
            }
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

            try {
                updateFile();
            } catch (IOException e) {
                System.out.println("RizzlerData.txt not found! Data cannot be saved!");
            }
        }
    }

    private void updateFile() throws IOException {
        ArrayList<String> updatedTaskList = new ArrayList<>();
        for (int i = 0; i < totalTaskCount; i++) {
            updatedTaskList.add(taskList.get(i).toString());
        }

        dataManager.writeToFile(updatedTaskList);
    }

    public void load_data() {
        try {
            ArrayList<String> savedDataList = dataManager.readFromFile();

            for (String data : savedDataList) {
                Task task = Task.parseSavedString(data);
                taskList.add(task);
                totalTaskCount++;
            }

            if (savedDataList.isEmpty()) {
                System.out.println("There is no previous data. Let's get to work!");
            } else {
                displayTaskList();
            }

        } catch (InvalidInputException | IOException e) {
            System.out.println("Error loading task data!");
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

        try {
            updateFile();
        } catch (IOException e) {
            System.out.println("RizzlerData.txt not found! Data cannot be saved!");
        }
    }
}

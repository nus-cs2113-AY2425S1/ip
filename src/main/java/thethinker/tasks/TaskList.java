package thethinker.tasks;

import thethinker.parser.DateParser;
import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> listOfTasks = new ArrayList<>();
    public static int listLength = 0;

    public static void addTask(Task task) {
        listOfTasks.add(task);
        listLength++;
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.printf("Now you have %d tasks in the list.\n" , listLength);
    }

    /**
     * Adds task to task list without printing any information to the console compared to addTask()
     */
    public static void addTaskWithoutResponse(Task task) {
        listOfTasks.add(task);
        listLength++;
    }

    public static void deleteTask(int taskNumber) {

        Task taskToRemove = listOfTasks.get(taskNumber-1);
        listLength--;
        listOfTasks.remove(taskNumber-1);

        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToRemove);
        System.out.printf("Now you have %d tasks in the list.\n" , listLength);
    }

    public static void setAsDone(int listNumber) throws IndexOutOfBoundsException {
        Task currentTask = listOfTasks.get(listNumber-1);
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  [%c][X] " + currentTask.getTaskDescription() + "\n" , currentTask.getTaskType());
        currentTask.setMarkedAsDone(true);
    }

    public static void setAsNotDone(int listNumber) throws IndexOutOfBoundsException {
        Task currentTask = listOfTasks.get(listNumber-1);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("  [%c][ ] " + currentTask.getTaskDescription() + "\n" , currentTask.getTaskType());
        currentTask.setMarkedAsDone(false);
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < listLength; i++) {
            System.out.printf("%d." + listOfTasks.get(i) + "\n", i+1);
        }
    }

    public static void listTasksOfDate(String date){
        System.out.println("Here are the tasks in your list in " + date + " :");
        int counter = 0;

        for (int i = 0; i < listLength; i++) {

            Task currentTask = listOfTasks.get(i);

            if (DateParser.isMatchingDateByType(currentTask , date)) {

                System.out.printf("%d." + listOfTasks.get(i) + "\n", counter + 1);
                counter++;
            }

        }
    }

    public static void listTasksOfKeyword(String keyword) {

        System.out.println("Here are the matching tasks in your list:");
        int counter = 0;

        for (int i = 0; i < listLength; i++) {
            Task currentTask = listOfTasks.get(i);
            String taskDescription = currentTask.getTaskDescription().toLowerCase();

            if (taskDescription.contains(keyword.toLowerCase())) {

                System.out.printf("%d." + listOfTasks.get(i) + "\n", counter + 1);
                counter++;
            }
        }
    }
}

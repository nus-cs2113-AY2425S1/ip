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
     * Adds task to task list without printing any information to the console.
     */
    public static void addTaskWithoutResponse(Task task) {
        listOfTasks.add(task);
        listLength++;
    }

    /**
     * Deletes task from current task list.
     *
     * @throws IndexOutOfBoundsException If number given is more than task list length.
     */
    public static void deleteTask(int taskNumber) throws IndexOutOfBoundsException{

        Task taskToRemove = listOfTasks.get(taskNumber-1);
        listLength--;
        listOfTasks.remove(taskNumber-1);

        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToRemove);
        System.out.printf("Now you have %d tasks in the list.\n" , listLength);
    }

    /**
     * Marks task from current task list.
     *
     * @throws IndexOutOfBoundsException If number given is more than task list length.
     */
    public static void setAsDone(int taskNumber) throws IndexOutOfBoundsException {
        Task currentTask = listOfTasks.get(taskNumber-1);
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  [%c][X] " + currentTask.getTaskDescription() + "\n" , currentTask.getTaskType());
        currentTask.setMarkedAsDone(true);
    }

    /**
     * Unmarks task from current task list.
     *
     * @throws IndexOutOfBoundsException If number given is more than task list length.
     */
    public static void setAsNotDone(int taskNumber) throws IndexOutOfBoundsException {
        Task currentTask = listOfTasks.get(taskNumber-1);
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

    /**
     * Lists all the task with deadline same as date given.
     *
     * @param date should be dd/MM/yyyy format.
     */
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

    /**
     * Lists all the task in Task List which contains the keyword.
     *
     * @param keyword one single word only.
     */
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
